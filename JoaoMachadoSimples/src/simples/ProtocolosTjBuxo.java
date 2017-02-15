package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProtocolosTjBuxo {

	public static void main(String[] args) throws IOException {

		Path pathProtNull = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/ALEXTODOSPROTNULL.CSV");
		Path pathCancGeral = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/ALEXTODOSCANC.CSV");
		
		Path pathSaida = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/RelacaoProtNaoRecolhido.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);

		List<String> protNull = Files.readAllLines(pathProtNull, charset);
		List<String> cancGeral = Files.readAllLines(pathCancGeral, charset);

		System.out.println("PROCESSANDO...");
		
		for(String prot : protNull){
			if(!cancGeral.contains(prot)){
				saida.write(prot);
				saida.newLine();
			}
			
		}		
		saida.flush();
		saida.close();
		System.out.println("<<<CONCLUIDO>>>");

	}
}
