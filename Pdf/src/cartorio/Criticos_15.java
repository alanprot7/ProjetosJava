package cartorio;

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

public class Criticos_15 {

	public static void main(String[] args) throws IOException {

		TransformaPDFtoTXT transPDF = new TransformaPDFtoTXT();
		
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

		
		ArrayList<File[]> dires = new ArrayList<>();
			
		label.setText("Carregando Intimações");
		
		for(char c='A';c<='Z';c++){
			Path temp = Paths.get(c+":/Projetos/Notificacoes-STL");
			if(Files.exists(temp)){
				File lista2015 = new  File(c+":/Projetos/Notificacoes-STL/Anos/2015");
				File lista2016 = new  File(c+":/Projetos/Notificacoes-STL/2016");
				File lista2017 = new  File(c+":/Projetos/Notificacoes-STL/2017");
				File dire2015[] = lista2015.listFiles(); unidade = c;
				File dire2016[] = lista2016.listFiles(); unidade = c;
				File dire2017[] = lista2017.listFiles(); unidade = c;
				dires.add(dire2015);
				dires.add(dire2016);
				dires.add(dire2017);
				
				for(File[] diretorio : dires)
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


		File fonte  = new File(unidade+":/Projetos/XPROGs/CRITICOS");
		File fonteDir[] = fonte.listFiles();
		String source = "";

		for(int i=0;i<fonteDir.length;i++){
			if(fonteDir[i].toString().toLowerCase().endsWith(".pdf"))
				source = fonteDir[i].toString();
		}
		
			
		String destination = unidade+":/Projetos/XPROGs/CRITICOS/Files/SemRetorno.pdf";

		File arqSource = new File(source);
		File arqDestin = new File(destination);
		copyFile(arqSource, arqDestin);


		List<String> semRetorno = transPDF.tranforma(destination);
		
		List<String> bancos = Files.readAllLines(pathBancos, charset);

		List<String> particulares = new ArrayList<>();

		Files.createDirectories(pathSaida.getParent());
		BufferedWriter w = Files.newBufferedWriter(pathSaida, charset );

		List<String> compSemRet = new ArrayList<>();

		for(String comp : semRetorno){
			if(comp.length()<32){
				continue;
			} else {
				if(comp.substring(0,1).equals("*"))
					compSemRet.add(comp.substring(1,8));

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
