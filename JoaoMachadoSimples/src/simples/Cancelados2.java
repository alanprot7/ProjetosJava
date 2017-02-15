package simples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Cancelados2 {

	public static void main(String[] args) throws IOException {

		Path pathCancelados = Paths.get("Files2/Cancelados.txt");
		Path pathBancos = Paths.get("Files2/Bancos.txt");
		Path pathSaida = Paths.get("Files2/Fermoju.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		List<String> cancelados = Files.readAllLines(pathCancelados, charset);
		List<String> bancos = Files.readAllLines(pathBancos, charset);

		Files.createDirectories(pathSaida.getParent());
		BufferedWriter w = Files.newBufferedWriter(pathSaida, charset);

		List<String> particulares = new ArrayList<>();

		for (int linhaAtual = 0; linhaAtual < cancelados.size(); linhaAtual++) {
			int linhaPosterior = linhaAtual + 1;
			boolean ehParticular = true;
			if (cancelados.get(linhaAtual).length() > 60
					&& cancelados.get(linhaAtual).contains("  -  ")) {
				for (String ban : bancos) {
					int linhaTemp = linhaPosterior;
					if(cancelados.get(linhaPosterior).length() < 6)
						linhaTemp++;
					if (cancelados.get(linhaTemp).contains(ban))
						ehParticular = false;
				}
				if (ehParticular) {
					int inicio = 0, fim = 0, contPosicao = 0;
					String linha = cancelados.get(linhaAtual);
					for (int tam = linha.length() - 1; tam > 0; tam--) {
						if (linha.substring(tam, tam + 1).equals(" ")
								&& contPosicao == 1) {
							inicio = tam;
							break;
						}
						if (linha.substring(tam, tam + 1).equals(" ")
								&& contPosicao == 0) {
							fim = tam;
							contPosicao++;
						}

					}
					particulares.add(cancelados.get(linhaAtual)
							.substring(inicio, fim).replace(".", "")
							.replace(",", "").trim());
					
					int linhaTemp = linhaPosterior;
					if(cancelados.get(linhaPosterior).length() < 6)
						linhaTemp++;
					
					w.write(cancelados.get(linhaTemp));
					w.newLine();

				}

			}
		}

		int faixa3001 = 0, faixa3002 = 0, faixa3003 = 0, faixa3004 = 0, faixa3005 = 0, faixa3006 = 0;

		for (String part : particulares)
			if (Integer.parseInt(part) >= 85149)
				faixa3006++;
			else if (Integer.parseInt(part) >= 42631)
				faixa3005++;
			else if (Integer.parseInt(part) >= 21277)
				faixa3004++;
			else if (Integer.parseInt(part) >= 8529)
				faixa3003++;
			else if (Integer.parseInt(part) >= 1421)
				faixa3002++;
			else
				faixa3001++;

		w.newLine();
		w.write("Faixas de Cancelados");
		w.newLine();
		w.newLine();
		w.write("" + faixa3001);
		w.newLine();
		w.write("" + faixa3002);
		w.newLine();
		w.write("" + faixa3003);
		w.newLine();
		w.write("" + faixa3004);
		w.newLine();
		w.write("" + faixa3005);
		w.newLine();
		w.write("" + faixa3006);
		w.newLine();
		w.newLine();
		w.write("Total = "
				+ (faixa3001 + faixa3002 + faixa3003 + faixa3004 + faixa3005 + faixa3006));
		w.newLine();

		w.flush();
		w.close();

		Runtime.getRuntime().exec(" notepad Files2/Fermoju.txt");

	}
}
