package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RelatorioTjBuxo {

	public static void main(String[] args) throws IOException {

		Path pathProtNull = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/TJBUXO/PROTDADIF.TXT");
		Path pathRelatGeral = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/TJBUXO/5anos.txt");
		
		Path pathSaida = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/RelatorioAlexandre.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);

		List<String> protNull = Files.readAllLines(pathProtNull, charset);
		List<String> relatGeral = Files.readAllLines(pathRelatGeral, charset);

		System.out.println("PROCESSANDO...");
		
		for(String prot : relatGeral){
			if(prot.length()>130)
				if(protNull.contains(prot.substring(1,7))){
				saida.write(prot.substring(0, 131));
				saida.newLine();
			}else
				if(protNull.contains(prot.substring(1,8))){
					saida.write(prot.substring(0, 131));
					saida.newLine();
				}
			
		}		
		saida.flush();
		saida.close();
		System.out.println("<<<CONCLUIDO>>>");

	}
}
