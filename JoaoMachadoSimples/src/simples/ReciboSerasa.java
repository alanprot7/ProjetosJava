package simples;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ReciboSerasa {

	public static void main(String[] args) {

		try{
		JFileChooser file = new JFileChooser("F:/projetos/serasa"); 
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        file.showOpenDialog(null);
        String caminho = file.getSelectedFile().getPath();

		List<String> arquivos = new ArrayList<>();
		Charset cs = Charset.forName("ISO-8859-1");

		

			File diretorio[];
			File lista = new File(caminho);
			diretorio = lista.listFiles();
			for(File s : diretorio){
				if(s.toString().endsWith("FLA")){
					Path path = Paths.get(s.toString());
					List<String> arq = Files.readAllLines(path, cs);
					for(String a : arq){
						arquivos.add(a);
					}
				}
			}

			arquivos = retfArquivos(arquivos);

			Map<String , Map<String,Integer>> protestado = new TreeMap<>();
			Map<String , Map<String,Integer>> cancelado = new TreeMap<>();

			for(String s : arquivos){
				if(s.substring(0,3).equals("1PI")){
					if(protestado.containsKey(s.substring(260,268))){
						if(protestado.get(s.substring(260,268)).containsKey(s.substring(345,360))){
							int qtd = protestado.get(s.substring(260,268)).get(s.substring(345,360));
							protestado.get(s.substring(260,268)).put(s.substring(345,360), qtd+1);
						}else
							protestado.get(s.substring(260,268)).put(s.substring(345,360), 1);
					}else{
						Map<String, Integer> mapTemp = new TreeMap<>();
						mapTemp.put(s.substring(345,360), 1);
						protestado.put(s.substring(260,268), mapTemp);
					}
				}else
					if(s.substring(0,3).equals("1CE")){
						if(cancelado.containsKey(s.substring(477,485))){
							if(cancelado.get(s.substring(477,485)).containsKey(s.substring(345,360))){
								int qtd = cancelado.get(s.substring(477,485)).get(s.substring(345,360));
								cancelado.get(s.substring(477,485)).put(s.substring(345,360), qtd+1);
							}else
								cancelado.get(s.substring(477,485)).put(s.substring(345,360), 1);

						}else{							
							Map<String, Integer> mapTemp = new TreeMap<>();
							mapTemp.put(s.substring(345,360), 1);
							cancelado.put(s.substring(477,485), mapTemp);
						}
					}

			}

			String quinzena = JOptionPane.showInputDialog("Insira:\n1 para 1ª Quinzena\n2 para 2ª Quinzena");

			Set<String> protDiaLista = protestado.keySet();
			Set<String> cancDiaLista = cancelado.keySet();

			int protDocsPrimarios = 0, protDocsSecundarios = 0, cancDocsPrimarios = 0, cancDocsSecundarios = 0;

			if(quinzena.equals("1")){

				for(String s : protDiaLista){
					if(Integer.parseInt(s.substring(0,2))>=1 && Integer.parseInt(s.substring(0,2))<=15){
						Set<String> listaDocs = protestado.get(s).keySet();
						for(String l : listaDocs){
							protDocsPrimarios++;
							protDocsSecundarios+= protestado.get(s).get(l);
						}
					}
				}

				for(String s : cancDiaLista){
					if(Integer.parseInt(s.substring(0,2))>=1 && Integer.parseInt(s.substring(0,2))<=15){
						Set<String> listaDocs = cancelado.get(s).keySet();
						for(String l : listaDocs){
							cancDocsPrimarios++;
							cancDocsSecundarios+= cancelado.get(s).get(l);
						}
					}
				}


			}else
				if(quinzena.equals("2")){

					for(String s : protDiaLista){
						if(Integer.parseInt(s.substring(0,2))>=16 && Integer.parseInt(s.substring(0,2))<=31){
							Set<String> listaDocs = protestado.get(s).keySet();
							for(String l : listaDocs){
								protDocsPrimarios++;
								protDocsSecundarios+= protestado.get(s).get(l);
							}
						}
					}

					for(String s : cancDiaLista){
						if(Integer.parseInt(s.substring(0,2))>=16 && Integer.parseInt(s.substring(0,2))<=31){
							Set<String> listaDocs = cancelado.get(s).keySet();
							for(String l : listaDocs){
								cancDocsPrimarios++;
								cancDocsSecundarios+= cancelado.get(s).get(l);
							}
						}
					}

				}


			 
      String memoria = "     CONTAGEM REFERENTE À "+quinzena+"ª QUINZENA\n";
			memoria += "STATUS DO TITULO      SEC      PRIME    TOTAL \n";
			memoria += "----------------------------------------------\n";
			memoria += "CANCELADOS           "+String.format("%4d",(cancDocsSecundarios-cancDocsPrimarios))+"       "+String.format("%4d",cancDocsPrimarios)+"     "+String.format("%4d",cancDocsSecundarios)+"\n";
			memoria += "----------------------------------------------\n";
			memoria += "PROTESTADOS          "+String.format("%4d",(protDocsSecundarios-protDocsPrimarios))+"       "+String.format("%4d",protDocsPrimarios)+"     "+String.format("%4d",protDocsSecundarios)+"\n";
			memoria += "----------------------------------------------\n";
		

			copyClip(memoria);

			JOptionPane.showMessageDialog(null, "Resultado Copiado Na Memória!");


		}catch(Exception e){

		}
	}

	public static void copyClip(String memoria){

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		String text = memoria;
		StringSelection select = new StringSelection(text);
		clip.setContents(select, null);

	}

	public static List<String> retfArquivos(List<String> arquivos){

		List<String> arq = new ArrayList<>();

		for(String s : arquivos){
			String dia = "0";
			if(s.indexOf("1CECEFLA")!=-1){
				dia = s.substring(477,479);
			}
			if(s.indexOf("1PICEFLA")!=-1){
				dia = s.substring(260,262);
			}

			if(Integer.parseInt(dia)>=1 && Integer.parseInt(dia)<=15){
				if(s.indexOf("1CECEFLA")!=-1){
					dia = "10";
					arq.add(s.substring(0,477)+dia+s.substring(479,s.length()));
				}
			}
			if(Integer.parseInt(dia)>=16 && Integer.parseInt(dia)<=31){
				if(s.indexOf("1CECEFLA")!=-1){
					dia = "20";
					arq.add(s.substring(0,477)+dia+s.substring(479,s.length()));
				}
			}

			if(Integer.parseInt(dia)>=1 && Integer.parseInt(dia)<=15){
				if(s.indexOf("1PICEFLA")!=-1){
					dia = "10";
					arq.add(s.substring(0,260)+dia+s.substring(262,s.length()));
				}
			}
			if(Integer.parseInt(dia)>=16 && Integer.parseInt(dia)<=31){
				if(s.indexOf("1PICEFLA")!=-1){
					dia = "20";
					arq.add(s.substring(0,260)+dia+s.substring(262,s.length()));
				}
			}


		}

		return arq;
	}

}
