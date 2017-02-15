package Cartorio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CoDevedores {

	public static void main(String[] args) throws IOException {

		Path pathApontados = Paths.get("CoDev/APT.txt");
		Path pathSaida = Paths.get("CoDev/Relacao.txt");
		
		Charset charset = Charset.forName("ISO-8859-1");

		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);
		
		List<String> apontados = Files.readAllLines(pathApontados, charset);

		List<String> codevedores = new ArrayList<>();
		Map<String, Integer> mapa = new TreeMap<>();

		for(String comp : apontados){
			if(comp.length()>18){
				if(comp.substring(17,18).equals("/")){
					codevedores.add(comp.substring(2,9));
				}
			}
		}

		for(String cod : codevedores){
			if(mapa.containsKey(cod)){
				mapa.put(cod, mapa.get(cod)+1);
			}else{
				mapa.put(cod, 1);
			}
		}

		Set<String> chaves = new TreeSet<>();
		chaves = mapa.keySet();

		int cont = 0;

		
		for(String chave : chaves){
			if(mapa.get(chave)>1){
				saida.write(chave);
				saida.newLine();
				cont++;
			}
		}

		saida.newLine();
		saida.write("\n"+cont);
		saida.flush();
		
		Runtime.getRuntime().exec("notepad.exe CoDev/Relacao.txt");
	}
}
