package simples;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ticador {

	public static void main(String[] args) throws IOException {

		Path pathMaior = Paths
				.get("F:/Projetos/XPROGs/CO-DEVEDORES/CoDev/Relacao.txt");
		Path pathMenor = Paths
				.get("F:/Projetos/XPROGs/CO-DEVEDORES/SERASACodev/Canceladosteste.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		List<String> arquivoMaior = Files.readAllLines(pathMaior, charset);
		List<String> arquivoMenor = Files.readAllLines(pathMenor, charset);

		ArrayList<String> comparador = new ArrayList<>();

		for (String str : arquivoMaior) {
			if (str.length() >= 7)
				comparador.add(str.substring(0, 7));
		}

		for (String str : arquivoMenor) {
			if (str.length() >= 7) {
				String protocolo = str.substring(0, 7);
				if (!comparador.contains(protocolo))
					System.out.println(protocolo);
			}
		}

	}

}
