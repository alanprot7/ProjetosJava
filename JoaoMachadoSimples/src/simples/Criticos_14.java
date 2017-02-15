package simples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Criticos_14 {

	public static void main(String[] args) throws IOException {

		Path pathSemRetorno = Paths.get("Files/SemRetorno.txt");
		Path pathBancos = Paths.get("Files/Bancos.txt");
		Path pathSaida = Paths.get("Files/Critico.txt");
		Charset charset = Charset.forName("ISO-8859-1");
		char unidade = ' ';

		JFrame form = new JFrame();
		form.setTitle("Aguarde");
		form.setBounds(SwingConstants.LEFT+50, SwingConstants.TOP+50, 200 , 100);
		JLabel label = new JLabel();
		label.setText("Inicio");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		form.add(label);
		form.setVisible(true);

		List<String> apontados = new ArrayList<>();

		File diretorio[];
		File diretorio2[];
			
		label.setText("Carregando Intimações");
		
		for(char c='A';c<='Z';c++){
			Path temp = Paths.get(c+":/Projetos/Notificacoes-STL");
			if(Files.exists(temp)){
				File listaDir = new  File(c+":/Projetos/Notificacoes-STL/Anos/2015");
				File listaDir2 = new  File(c+":/Projetos/Notificacoes-STL");{
					diretorio = listaDir.listFiles(); unidade = c;
					diretorio2 = listaDir2.listFiles(); unidade = c;
				}
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
				
				for(int i=0;i<diretorio2.length;i++){
					int tam = diretorio2[i].toString().length();
					if(diretorio2[i].toString().substring(tam-4,tam-2).equals("20")){
						File arqlist = new File(diretorio2[i].toString());
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


		File fonte  = new File(unidade+":/Projetos/XPROGs/CRITICOS");
		File fonteDir[] = fonte.listFiles();
		String source = "";

		for(int i=0;i<fonteDir.length;i++){
			if(fonteDir[i].toString().toLowerCase().endsWith(".txt"))
				source = fonteDir[i].toString();
		}
		String destination = unidade+":/Projetos/XPROGs/CRITICOS/Files/SemRetorno.txt";

		File arqSource = new File(source);
		File arqDestin = new File(destination);
		copyFile(arqSource, arqDestin);


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

		label.setText("Montando Críticos");
		
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
		
		System.exit(0);
		
	}

	public static void copyFile(File source, File destination) throws IOException {  

		if (source.exists())  
			destination.delete();  

		FileChannel sourceChannel = null;  
		FileChannel destinationChannel = null;  

		try {  
			if(source.exists()){
				sourceChannel = new FileInputStream(source).getChannel();  
				destinationChannel = new FileOutputStream(destination).getChannel();  
				sourceChannel.transferTo(0, sourceChannel.size(),destinationChannel);
				source.deleteOnExit();
			}
		} finally {  
			if (sourceChannel != null && sourceChannel.isOpen())  
				sourceChannel.close();  
			if (destinationChannel != null && destinationChannel.isOpen())  
				destinationChannel.close();  
		}  
	}  


}
