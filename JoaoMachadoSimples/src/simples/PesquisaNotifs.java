package simples;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PesquisaNotifs {

	public static void main(String[] args) throws IOException {

		
		String arq = "Notificacoes.txt";

		int cont = 0;
		Charset charset = Charset.forName("ISO-8859-1");
		Path path = Paths.get("C:/Users/AlanProt7/Desktop/"+arq);
		List<String> arquivo = Files.readAllLines(path, charset);
		
		for(String linha : arquivo){
			
			if(linha.indexOf("167000229")!=-1
					&& linha.indexOf("/2016CS0061401")!=-1 
					&& linha.indexOf("")!=-1){
			
				System.out.println(linha);
				cont++;
			}
		}
		
		System.out.println("\n"+cont);
		
	}
}
