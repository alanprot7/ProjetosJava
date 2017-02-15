package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArrumaSeloInstru2Via {

	public static void main(String[] args) throws IOException {



		Charset charset = Charset.forName("ISO-8859-1");
		Path pathEntrada = Paths.get("F:/Projetos/DEVOLUCAO CRA/290920162VIA.mht");
		Path pathSaida = Paths.get("F:/Projetos/DEVOLUCAO CRA/SelosArrumados2via.mht");
		Path pathSelos = Paths.get("F:/Projetos/DEVOLUCAO CRA/numeros.txt");
		Path pathSelosNovos = Paths.get("F:/Projetos/DEVOLUCAO CRA/numeros2.txt");
		List<String> arquivo = Files.readAllLines(pathEntrada,charset);
		List<String> selos = Files.readAllLines(pathSelos,charset);
		List<String> novoSelo = Files.readAllLines(pathSelosNovos,charset);
		List<String> novaLista = new ArrayList<>();
		String seloAntigo = "AG-802";

		for(String arq : arquivo){
			if(arq.indexOf(seloAntigo)>-1){
				for(int i=0;i<selos.size();i++){
					if(arq.indexOf(selos.get(i))>-1){
						novaLista.add(arq.replace(selos.get(i), novoSelo.get(i)));
					}

				}
			}else
				novaLista.add(arq);

		}

		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);

		for(String arq : novaLista){
			saida.write(arq);
			saida.newLine();
		}

		saida.flush();
		saida.close();

	}
}
