package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RelatoriosAdalberto2 {

	public static void main(String[] args) throws IOException {

		Path pathRelatorio = Paths
				.get("C:/Users/AlanProt7/Downloads/RelatorioEntregas.txt");
		Path pathCerinfo = Paths
				.get("F:/Projetos/XPROGs/CRITICOS/Files/Critico.txt");
		Path pathStatus = Paths.get("CritStatus.txt");
		Charset charset = Charset.forName("ISO-8859-1");

		ArrayList<String> protocolos = new ArrayList<>();

		List<String> listaRelatorio = Files
				.readAllLines(pathRelatorio, charset);

		List<String> listaCerinfo = Files.readAllLines(pathCerinfo, charset);

		Map<String, String> map = new TreeMap<>();

		for (String string : listaRelatorio) {
			if (string.contains(" 1.3")) {
				int inicial = string.indexOf(" 1.3");

				protocolos.add(string.substring(inicial + 1, inicial + 10)
						.replace(".", "").replace(",", ""));
				map.put(string.substring(inicial + 1, inicial + 10)
						.replace(".", "").replace(",", ""),
						string.substring(string.length() - 1, string.length()));
			}
		}

		BufferedWriter saida = Files.newBufferedWriter(pathStatus);

		for (String string : listaCerinfo) {

			if (string.substring(0, 1).equals("1")) {
				if (protocolos.contains(string.substring(0, 7))) {

					if (map.get(string.substring(0, 7)).equals("D")) {
						saida.append(string + " DEVOLVIDA");
						saida.newLine();
					} else if (map.get(string.substring(0, 7)).equals("R")) {
						saida.append(string + " ENTREGUE");
						saida.newLine();
					} else {
						saida.append(string + " ABERTO");
						saida.newLine();
					}

				} else {
					saida.append(string + " --------------");
					saida.newLine();
				}
			} else {
				saida.append(string);
				saida.newLine();
			}
		}
		saida.flush();
		saida.close();

	}

}
