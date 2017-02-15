package fametro.redes.lab22;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	
	public static void main(String[] args) throws IOException {
		
		String clientSentene;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		while(true){
			
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToCliente = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentene = inFromClient.readLine();
			System.out.println("Recebido: "+clientSentene);
			capitalizedSentence  = clientSentene.toUpperCase()+'\n';
			outToCliente.writeBytes(capitalizedSentence);
			
			
		}
		
	}

}
