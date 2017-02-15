package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RelatoriosAdalberto {
	
	public static void main(String[] args) throws IOException {
		
		Path pathEntregues = Paths.get("C:/Users/AlanProt7/Downloads/entregues.txt");
		Path pathDevolvidos = Paths.get("C:/Users/AlanProt7/Downloads/devolvidos.txt");
		Path pathCerinfo = Paths.get("F:/Projetos/XPROGs/CRITICOS/Files/Critico.txt");
		Path pathAberto = Paths.get("C:/Users/AlanProt7/Downloads/aberto.txt");
		Path pathStatus = Paths.get("CritStatus.txt");
		Charset charset = Charset.forName("ISO-8859-1");
		
		ArrayList<String> protocolosDevolvidos = new ArrayList<>();
		ArrayList<String> protocolosEntregues = new ArrayList<>();
		ArrayList<String> protocolosAberto = new ArrayList<>();
		
		List<String> listaEntregues = Files.readAllLines(pathEntregues, charset);
		List<String> listaDevolvidos = Files.readAllLines(pathDevolvidos, charset);
		List<String> listaAberto = Files.readAllLines(pathAberto, charset);
		List<String> listaCerinfo = Files.readAllLines(pathCerinfo, charset);
		
		for (String string : listaDevolvidos) {
			if(string.contains(" 1.3")){
				int inicial = string.indexOf(" 1.3");
				
				protocolosDevolvidos.add(string.substring(inicial+1, inicial+10).replace(".","").replace(",", ""));
			}
		}
		
		for (String string : listaEntregues) {
			if(string.contains(" 1.3")){
				int inicial = string.indexOf(" 1.3");
				
				protocolosEntregues.add(string.substring(inicial+1, inicial+10).replace(".","").replace(",", ""));
			}
		}
		
		for (String string : listaAberto) {
			if(string.contains(" 1.3")){
				int inicial = string.indexOf(" 1.3");
				
				protocolosAberto.add(string.substring(inicial+1, inicial+10).replace(".","").replace(",", ""));
			}
		}

		
		BufferedWriter saida = Files.newBufferedWriter(pathStatus);
		
		for (String string : listaCerinfo) {
			
			if(string.substring(0,1).equals("1")){
				if(protocolosDevolvidos.contains(string.substring(0,7))){
					saida.append(string+" DEVOLVIDA");
					saida.newLine();
				}else
					if(protocolosEntregues.contains(string.substring(0,7))){
						saida.append(string+" ENTREGUE");
						saida.newLine();
					}else
						if(protocolosAberto.contains(string.substring(0,7))){
							saida.append(string+" ABERTO");
							saida.newLine();
							}else{
								saida.append(string+" --------------");
								saida.newLine();
							}
			}else{
				saida.append(string);
				saida.newLine();
			}
		}
		saida.flush();
		saida.close();
		

	}

}
