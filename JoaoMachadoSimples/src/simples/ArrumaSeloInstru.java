package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArrumaSeloInstru {

	public static void main(String[] args) throws IOException {



		Charset charset = Charset.forName("ISO-8859-1");
		Path pathEntrada = Paths.get("F:/Projetos/DEVOLUCAO CRA/290920161VIA.mht");
		Path parhSaida = Paths.get("F:/Projetos/DEVOLUCAO CRA/SelosArrumados.mht");
		Path pathSaida2 = Paths.get("F:/Projetos/DEVOLUCAO CRA/numeros.txt");
		Path pathSaida3 = Paths.get("F:/Projetos/DEVOLUCAO CRA/numeros2.txt");
		List<String> arquivo = Files.readAllLines(pathEntrada,charset);
		List<String> selos = new ArrayList<>();
		List<String> novaLista = new ArrayList<>();
		List<String> selosNovos = new ArrayList<>();
		String seloAntigo = "AG-802";

		for(String arq : arquivo){
			if(arq.indexOf(seloAntigo)>-1){
				int inicio = arq.indexOf(seloAntigo);
				selos.add(arq.substring(inicio,(inicio+9)));
			}
		}

		int novoSelo = 802851;

		for(String arq : arquivo){
			if(arq.indexOf(seloAntigo)>-1){
				for(String selo : selos){
					if(arq.indexOf(selo)>-1){
						novaLista.add(arq.replace(selo, "AG-"+novoSelo));
						selosNovos.add("AG-"+novoSelo);
						novoSelo++;
					}

				}
			}else
				novaLista.add(arq);

		}

		BufferedWriter saida = Files.newBufferedWriter(parhSaida, charset);

		for(String arq : novaLista){
			saida.write(arq);
			saida.newLine();
		}

		saida.flush();
		saida.close();

		saida = Files.newBufferedWriter(pathSaida2, charset);

		for(String arq : selos){
			saida.write(arq);
			saida.newLine();			
		}

		saida.flush();
		saida.close();

		saida = Files.newBufferedWriter(pathSaida3, charset);

		for(String arq : selosNovos){
			saida.write(arq);
			saida.newLine();			
		}

		saida.flush();
		saida.close();

	}
}
