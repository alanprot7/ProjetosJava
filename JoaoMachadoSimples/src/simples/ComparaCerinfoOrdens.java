package simples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComparaCerinfoOrdens {

	public static void main(String[] args) throws IOException {

		FileReader revo = new FileReader("Revogados.txt");
		BufferedReader lerRevo = new BufferedReader(revo);
		FileReader revoC = new FileReader("CerinfoRevogados.txt");
		BufferedReader lerRevoC = new BufferedReader(revoC);
		FileWriter escrita = new FileWriter("Lista.txt");
		BufferedWriter saida = new BufferedWriter(escrita);

		List<String> revogados = new ArrayList<>();
		List<String> revogadosC = new ArrayList<>();

		String linhaR, linhaC;

		do{

			linhaR = lerRevo.readLine();
			String zero ="";
			if(linhaR != null && linhaR.length()<14 ){
				for(int i=0;i<14-linhaR.length();i++)
					zero+= "0";
			}

			revogados.add(zero+linhaR);

		}while (linhaR!= null);

		do{

			linhaC = lerRevoC.readLine();
			String zero ="";
			if(linhaC != null && linhaC.length()<14 ){
				for(int i=0;i<14-linhaC.length();i++)
					zero+= "0";
			}

			revogadosC.add(zero+linhaC);

		}while (linhaC!= null);

		System.out.println("Tem na Cerinfo, mas não tem na lista");
		for(String arg : revogadosC){
			if(!revogados.contains(arg)){
				saida.write(arg);
				saida.newLine();
			}
		}

		lerRevo.close();
		lerRevoC.close();
		saida.flush();
		saida.close();
		Runtime.getRuntime().exec("notepad.exe Lista.txt");

	}
}
