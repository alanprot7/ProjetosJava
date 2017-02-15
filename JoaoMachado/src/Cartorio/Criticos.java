package Cartorio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Criticos {

	public static void main(String[] args) throws IOException {

		Path pathSemRetorno = Paths.get("Files/SemRetorno.txt");
		Path pathBancos = Paths.get("Files/Bancos.txt");
		Path pathApontados = Paths.get("Files/Apontados.txt");
		Path pathSaida = Paths.get("Files/Critico.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		List<String> semRetorno = Files.readAllLines(pathSemRetorno, charset);
		List<String> bancos = Files.readAllLines(pathBancos, charset);
		List<String> apontados = Files.readAllLines(pathApontados, charset);

		Files.createDirectories(pathSaida.getParent());
		BufferedWriter w = Files.newBufferedWriter(pathSaida, charset );

		List<String> compSemRet = new ArrayList<>();

		for(String comp : semRetorno){
			if(comp.length()<32){
				continue;
			} else {
				if(comp.substring(31,32).equals("/"))
					compSemRet.add(comp.substring(5,12));

			}

		}

		w.write("PROTS.   ENTRADA   APRESENTANTE");
		w.newLine();
		w.write("========================================================");
		w.newLine();
		int total = 0;
		for(int i=0; i<apontados.size();i++){
			int linha = i+1;

			if(apontados.get(i).length()>39){
				if(compSemRet.contains(apontados.get(i).substring(2,9)) && 
						bancos.contains(apontados.get(linha).substring(2,39))){
					total++;
					w.write(apontados.get(i).substring(2,9) + "  "+
							apontados.get(i).substring(15,23) +"  "+
							apontados.get(linha).substring(2,39));
					w.newLine();
				}

			}
		}

		w.write("========================================================");
		w.newLine();
		w.write("TOTAL = "+total);
		w.flush();
		w.close();

	}

}
