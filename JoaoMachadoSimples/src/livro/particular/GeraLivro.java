package livro.particular;

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

public class GeraLivro {

	public static void main(String[] args) throws IOException {

		System.out.println("PROCESSANDO...");
		Path pathTodosLivros = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/TJBUXO/TODOSLIVROFOLHA.CSV");
		Path pathTodosParticulare = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/TJBUXO/TODOSPARTICULARES.CSV");
		Path pathSaida = Paths.get("D:/BackupXP/Prot7/BxDoc/VEJA/P2P/TJBUXO/LivrosLista.txt");
		Charset charset = Charset.forName("ISO-8859-1");

		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);

		List<String> todosLivros = Files.readAllLines(pathTodosLivros);
		List<String> particulares = Files.readAllLines(pathTodosParticulare);
		Map<String, Integer> mapaParticular = new TreeMap<>();
		Map<String, ArrayList<String>> mapaLivro = new TreeMap<>();

		mapaParticular = geraMapaPart(particulares);
		Set<String> chave = mapaParticular.keySet();
		mapaLivro = geraMapaLiv(todosLivros);
		int lis = 0;
		for(String key : chave){
			for(int i=0 , j = 0;i<mapaParticular.get(key);j++)
				for(char c='A';c<='Z' && i<mapaParticular.get(key);c++){
					saida.write(particulares.get(lis).substring(24,particulares.get(lis).length())+","
							+mapaLivro.get(key).get(j).substring(0,4).trim()+","
							+mapaLivro.get(key).get(j).substring(11,mapaLivro.get(key).get(j).length())+","+c);
					saida.newLine();
					i++;
					lis++;
				}
		}

		saida.flush();
		saida.close();
		System.out.println("<<CONCLUIDO>> ");
	}


	public static Map<String, ArrayList<String>> geraMapaLiv(List<String> lista){

		Map<String, ArrayList<String>> mapa = new TreeMap<>();

		for(String lis : lista){
			if(Integer.parseInt(lis.substring(24,28).trim())>800)
				if(mapa.containsKey(lis.substring(0,10))){
					mapa.get(lis.substring(0,10)).add(lis.substring(24,lis.length()));
				}else{
					mapa.put(lis.substring(0,10), new ArrayList<>());
					mapa.get(lis.substring(0,10)).add(lis.substring(24,lis.length()));
				}
		}

		return mapa;
	}

	public static Map<String, Integer> geraMapaPart(List<String> lista){

		Map<String, Integer> mapa = new TreeMap<>();

		for(String lis : lista){
			if(mapa.containsKey(lis.substring(0,10))){
				mapa.put(lis.substring(0,10), mapa.get(lis.substring(0,10))+1);
			}else
				mapa.put(lis.substring(0, 10), 1);
		}


		return mapa;
	}


}
