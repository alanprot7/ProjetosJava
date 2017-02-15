package simples;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AbreArquivos {
	
	public String getCaminho(){
	JFileChooser file = new JFileChooser(System.getProperty("user.dir")); 
    file.setFileSelectionMode(JFileChooser.FILES_ONLY);
    file.setFileFilter(new FileNameExtensionFilter("Arquivos", "htm"));
    file.setAcceptAllFileFilterUsed(false);
    file.showOpenDialog(null);
    String caminho = file.getSelectedFile().getPath();
    return caminho;
	}

}
