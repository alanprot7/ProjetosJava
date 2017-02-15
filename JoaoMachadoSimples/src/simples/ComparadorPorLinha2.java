package simples;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ComparadorPorLinha2 {

	public static void main(String[] args) throws IOException {

		try {
			Charset cs = Charset.forName("ISO-8859-1");
			Path pathArq1 = Paths.get(abreArquivo());
			Path pathArq2 = Paths.get(abreArquivo());

			List<String> arq1 = Files.readAllLines(pathArq1, cs);
			List<String> arq2 = Files.readAllLines(pathArq2, cs);

			String check = "<HTML><FONT SIZE=\"10\" COLOR=\"GREEN\">IGUAL</FONT></HTML>";

			if (arq1.size() != arq2.size()) {
				check = "<HTML><FONT SIZE=\"10\" COLOR=\"RED\">DIFERENTE TAMANHO AQUIVO</FONT></HTML>";
			} else
				for (int i = 0; i < arq1.size(); i++) {
					char[] letras1 = arq1.get(i).toCharArray();
					char[] letras2 = arq2.get(i).toCharArray();

					if (letras1.length != letras2.length ){
						check = "<HTML><FONT SIZE=\"10\" COLOR=\"RED\">DIFERENTE TAMANHO DA LINHA "+(i+1)+"</FONT></HTML>";
						break;
					}else
						for(int j = 0;j< letras1.length;j++){
							if(letras1[j] != letras2[j]){
								check = "<HTML><FONT SIZE=\"10\" COLOR=\"RED\">DIFERENTE CARACTERE NA LINHA "+(i+1)+" COLUNA "+(j+1)+"</FONT></HTML>";
								i = arq1.size();
								break; 
								
							}
						}
				}

			JOptionPane.showMessageDialog(null, check);
		} catch (NullPointerException e) {

		} catch (MalformedInputException e) {
			JOptionPane
					.showMessageDialog(null,
							"<HTML><FONT SIZE=\"10\" COLOR=\"RED\">DIFERENTE POR EXCEPTION</FONT></HTML>");
		}
	}

	public static String abreArquivo() {

		JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		file.showOpenDialog(null);
		String caminho = file.getSelectedFile().getPath();

		return caminho;

	}

}
