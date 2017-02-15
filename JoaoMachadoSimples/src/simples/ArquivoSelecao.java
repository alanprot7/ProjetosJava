package simples;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArquivoSelecao {

	public static void main(String[] args) throws IOException {

		int naoEncontrado = -1;
		// STL =================================
		String arq = "BL070802.txt";
		String pesquisa = "21/02/2017" + "CS0061401002601";
		String pes2 = "03/03/2017" + "CS0061401002601";
		
		Charset charset = Charset.forName("ISO-8859-1");
		boolean protocolo = false;
		boolean remessaCCB = true;

		// CCB BRASIL ================================
		if (remessaCCB) {
			arq = "E0602109.txt";
			pes2 = "210217" +"00";
			pesquisa = "030317" + "00";
		}

		int cont = 0;

		Path path = Paths.get("C:/Users/AlanProt7/Documents/" + arq);
		List<String> arquivo = Files.readAllLines(path, charset);

		for (String linha : arquivo) {
			if (linha.indexOf(pesquisa) != naoEncontrado
			 || linha.indexOf(pes2) != naoEncontrado
			) {
				if (protocolo) {
					System.out.println(linha.substring(316, 323));
					cont++;
				} else {
					System.out.println(linha);
					cont++;
				}
			}

		}

		System.out.println("\n" + cont);

	}
}
