package simples;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ComparaCancelamentoCerinfo {

	public static void main(String[] args) throws IOException {


		String arq = "LC072505.TXT";
		String arq2 = "LC072505b.TXT";
		String caminho = "C:/Users/AlanProt7/Documents/";
		Charset cs = Charset.forName("ISO-8859-1");
		Path pathCerinfoAtual = Paths.get(caminho+arq);
		Path pathCefinfoAntig = Paths.get(caminho+arq2);
		List<String> listaAtual =  Files.readAllLines(pathCerinfoAtual, cs);
		List<String> listaAntig =  Files.readAllLines(pathCefinfoAntig, cs);

		boolean naoAchou = true ;
		int cont = 0;
		for(String lis : listaAtual){
			naoAchou = true;
			if(lis.length()>59)
				if(lis.substring(59,60).equals("/") && lis.substring(81,82).equals("/") ){
					for(String ant : listaAntig){
						if(ant.length()>60)
							if(ant.substring(59,60).equals("/") && ant.substring(81,82).equals("/"))
								if(ant.indexOf(lis.substring(22, 29))!=-1){
									naoAchou = false;
								}
					}
					if(naoAchou){
						System.out.println(lis.substring(22, 29));
						cont++;
					}
				}
		}
		System.out.println("\nTotal = "+cont);
	}

}
