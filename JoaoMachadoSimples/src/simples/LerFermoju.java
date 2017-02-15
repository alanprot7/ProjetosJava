package simples;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LerFermoju {

	public static void main(String[] args) throws IOException {

		Charset cs = Charset.forName("ISO-8859-1");

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt");
		JFileChooser file = new JFileChooser("D:/BackupXP/Prot7/BxDoc/VEJA/TJBUXO/RELATORIO FINAL MAR2016");
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		file.setMultiSelectionEnabled(true);
		file.setFileFilter(filter);
		file.showOpenDialog(null);
		File lista[] = file.getSelectedFiles();
		int totalGeral = 0;

		for(File lis : lista){

			String caminho = lis.getPath();

			Path pathArquivo = Paths.get(caminho);

			List<String> fermo = Files.readAllLines(pathArquivo, cs);

			int total = 0;
			String primeiro = "";
			String ultimo = "" ;

			for(String fr : fermo){

				if(fr.indexOf(" 0030")!=-1){
					String conta[] = fr.split(" ");

					if(Integer.parseInt(conta[2])>=3011 && Integer.parseInt(conta[2])<=3016){
						total+= Integer.parseInt(conta[5]);
						if(primeiro.equals("")){
							primeiro = conta[0];
						}
						ultimo = conta[0];
					}
				}

			}

			System.out.println(String.format("%s à %s total = %6d",primeiro,ultimo,total));
			totalGeral += total;
		}
		
		System.out.println(String.format("------------------------TOTAL = %6d",totalGeral));
							
	}
}
