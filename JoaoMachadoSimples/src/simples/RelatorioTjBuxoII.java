package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RelatorioTjBuxoII {

	public static void main(String[] args) throws IOException {

		Path pathProtNull = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/TJBUXO/RelacaoProtNaoRecolhidoFINAL.txt");
		Path pathRelatGeral = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/RelatorioAlexandre.txt");
		
		Path pathSaida = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/Diferenca.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);

		List<String> protNull = Files.readAllLines(pathProtNull, charset);
		List<String> relatGeral = Files.readAllLines(pathRelatGeral, charset);
		List<String> protRelat = new ArrayList<>();

		System.out.println("PROCESSANDO...");
		
		for(String rela : relatGeral){
			protRelat.add(rela.substring(1, 8).trim());
		}
		
		for(String prot : protNull){
			if(!protRelat.contains(prot)){
				saida.write(prot);
				saida.newLine();
			}
		}
		
		saida.flush();
		saida.close();
		System.out.println("<<<CONCLUIDO>>>");

	}
}
