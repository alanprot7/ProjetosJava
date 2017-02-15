package simples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Criticos_12 {

	public static void main(String[] args) throws IOException {

		Path pathSemRetorno = Paths.get("Files/SemRetorno.txt");
		Path pathBancos = Paths.get("Files/Bancos.txt");
		Path pathSaida = Paths.get("Files/Critico.txt");
		Charset charset = Charset.forName("ISO-8859-1");


		List<String> apontados = new ArrayList<>();

		File diretorio[];

		for(char c='A';c<='Z';c++){
			Path temp = Paths.get(c+":/Projetos/Notificacoes-STL");
			if(Files.exists(temp)){
				File listaDir = new  File(c+":/Projetos/Notificacoes-STL");
				diretorio = listaDir.listFiles();
				for(int i=0;i<diretorio.length;i++){
					int tam = diretorio[i].toString().length();
					if(diretorio[i].toString().substring(tam-4,tam-2).equals("20")){
						File arqlist = new File(diretorio[i].toString());
						File arquivo[] = arqlist.listFiles();
						for(int j=0;j<arquivo.length;j++)
							if(arquivo[j].toString().toLowerCase().endsWith(".txt")){
								Path pathApontados = Paths.get(arquivo[j].toString());
								List<String> arq = Files.readAllLines(pathApontados,charset);
								for(String a : arq)
									apontados.add(a);
							}
					}
				}
			}
		}


		List<String> semRetorno = Files.readAllLines(pathSemRetorno, charset);
		List<String> bancos = Files.readAllLines(pathBancos, charset);

		List<String> particulares = new ArrayList<>();

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

		w.write("PROTS.   ENTRADA     LIMITE      APRESENTANTE");
		w.newLine();
		w.write("======================================================================");
		w.newLine();
		int totalBancos = 0;
		for(int i=0; i<apontados.size();i++){

			if(apontados.get(i).length()>426){
				if(compSemRet.contains(apontados.get(i).substring(316,323)) && 
						bancos.contains(apontados.get(i).substring(70,107))){
					totalBancos++;
					w.write(apontados.get(i).substring(316,323) +"  "+
							apontados.get(i).substring(198,208) +"  "+
							apontados.get(i).substring(508,518) +"  "+
							apontados.get(i).substring(70,107));
					w.newLine();
				}else
					if(compSemRet.contains(apontados.get(i).substring(316,323)) && 
							!bancos.contains(apontados.get(i).substring(70,107))&&
							apontados.get(i).substring(433,438).toUpperCase().equals("FORTA")&&
							apontados.get(i).substring(424,426).equals("60")){
						particulares.add(apontados.get(i).substring(316,323) +"  "+
								apontados.get(i).substring(198,208) +"  "+
								apontados.get(i).substring(508,518) +"  "+
								apontados.get(i).substring(70,107));

					}

			}
		}

		w.write("----------------------------------------------------------------------");
		w.newLine();
		w.write("                          PARTICULARES");
		w.newLine();
		w.write("----------------------------------------------------------------------");
		w.newLine();

		for(String part : particulares){
			w.write(part);
			w.newLine();
		}

		w.write("======================================================================");
		w.newLine();
		w.write("Total Bancos = "+totalBancos);
		w.newLine();
		w.write("Total Partic = "+particulares.size());
		w.flush();
		w.close();
		Runtime.getRuntime().exec("notepad.exe Files/Critico.txt");
	}



}
