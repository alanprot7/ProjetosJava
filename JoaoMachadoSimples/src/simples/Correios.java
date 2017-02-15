package simples;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Correios {

	public static void main(String[] args) throws IOException {

		
		JFileChooser file = new JFileChooser(System.getProperty("user.dir")); 
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto", "txt"));
        file.setAcceptAllFileFilterUsed(false);
        file.showOpenDialog(null);
        String caminho = file.getSelectedFile().getPath();
		
        String pesquisa = JOptionPane.showInputDialog("Insira a data do Correio Ex: 01/07/2016");
        
		pesquisa += "CS0061401";
		StringBuilder result = new StringBuilder();
		
		int cont = 0;
		Charset charset = Charset.forName("ISO-8859-1");
		Path path = Paths.get(caminho);
		List<String> arquivo = Files.readAllLines(path, charset);
		
		result.append("Adalberto ou Axel,\n\nFavor separar as seguintes intimações para Correios:\n\n");
		
		for(String linha : arquivo){
			if(linha.indexOf(pesquisa)!=-1){
				result.append(linha.substring(316,323)+" "+linha.substring(433,460)+"\n");
				cont++;
			}
		}
		
		result.append("\nTotal: "+cont);
		copiar(result.toString());
		JOptionPane.showMessageDialog(null, "Copiado "+cont+" Ocorrencias");
		
	}
	
	public static void copiar(String text){
		
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection select = new StringSelection(text);
		clip.setContents(select, null);

		
	}
	
}
