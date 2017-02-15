package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Envelopes {

	public static void main(String[] args) throws IOException {

		JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		file.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto",
				"txt"));
		file.setAcceptAllFileFilterUsed(false);
		file.showOpenDialog(null);
		String caminho = file.getSelectedFile().getPath();

		String pesquisa = JOptionPane
				.showInputDialog("Insira a data do Correio Ex: 01/07/2016");

		pesquisa += "CS0061401";

		int cont = 0;
		Charset charset = Charset.forName("UTF-8");
		Path path = Paths.get(caminho);
		List<String> arquivo = Files.readAllLines(path, charset);
		Path pathSaida = Paths.get(path.getRoot().toString()
				+ path.subpath(0, path.getNameCount() - 2) + "/Envelopes.txt");
		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);
		int pagina = 0;
		for (String linha : arquivo) {
			if(cont == 0)
				linha = linha.substring(1, linha.length());
			
			if (linha.indexOf(pesquisa) != -1) {
				for (int j = 0; j < 2; j++) {
					saida.append("\t\t\tDestinatário:\t\t\t    ("
							+ linha.substring(316, 323) + ")");
					saida.newLine(); saida.append("\t\t\t"+linha.substring(2, 47));
					saida.newLine(); saida.append("\t\t\t"+linha.substring(324, 369));
					saida.newLine(); saida.append("\t\t\t"+String.format("%s - %s (%s)", linha.substring(384, 399),linha.substring(433, 453),linha.substring(473, 475)));
					saida.newLine(); saida.append("\t\t\tCEP: "+linha.substring(424, 433));
					if (pagina < 7) {
						pagina++;
						for (int i = 0; i < 7; i++)
							saida.newLine();
					} else{
						pagina = 0;
						saida.newLine();
					}

				}
				cont++;
			}
		}
		saida.flush();
		saida.close();

		JOptionPane.showMessageDialog(null, "Envelopes " + cont);

	}

}
