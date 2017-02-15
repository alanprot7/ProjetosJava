package simples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class CoDevedores3 {

	public static void main(String[] args) throws IOException {

		JFrame form = new JFrame();
		form.setTitle("Aguarde");
		form.setBounds(SwingConstants.LEFT + 50, SwingConstants.TOP + 50, 200,
				100);
		JLabel label = new JLabel();
		label.setText("Inicio");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		form.add(label);
		form.setVisible(true);

		Path pathSaida = Paths.get("CoDev/Relacao.txt");
		Path pathExcecao = Paths.get("CoDev/Excecao.txt");
		Path pathModpro = Paths.get("CoDev/Modpro.txt");
		Path pathCancelados = Paths.get("SERASACodev/Cancelados.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);
		BufferedWriter saidaCancelados = Files.newBufferedWriter(pathCancelados, charset);

		List<String> notificacoes = new ArrayList<>();
		List<String> excecao = Files.readAllLines(pathExcecao, charset);
		List<String> modPro = Files.readAllLines(pathModpro, charset);

		ArrayList<File[]> dires = new ArrayList<>();

		label.setText("Carregando");
		double total = 0;
		double atual = 0;

		for (char c = 'A'; c <= 'Z'; c++) {
			Path temp = Paths.get(c + ":/Projetos/Notificacoes-STL");
			if (Files.exists(temp)) {
				File lista2015 = new File(c
						+ ":/Projetos/Notificacoes-STL/Anos/2015");
				File lista2016 = new File(c
						+ ":/Projetos/Notificacoes-STL/2016");
				File lista2017 = new File(c
						+ ":/Projetos/Notificacoes-STL/2017");
				File dire2015[] = lista2015.listFiles();
				File dire2016[] = lista2016.listFiles();
				File dire2017[] = lista2017.listFiles();
				total += dire2015.length;
				total += dire2016.length;
				total += dire2017.length;
				dires.add(dire2015);
				dires.add(dire2016);
				dires.add(dire2017);

				for (File[] diretorio : dires) {
					for (int i = 0; i < diretorio.length; i++) {
						int tam = diretorio[i].toString().length();
						if (diretorio[i].toString().substring(tam - 4, tam - 2)
								.equals("20")) {
							File arqlist = new File(diretorio[i].toString());
							File arquivo[] = arqlist.listFiles();
							for (int j = 0; j < arquivo.length; j++)
								if (arquivo[j].toString().toLowerCase()
										.endsWith(".txt")) {
									Path pathApontados1 = Paths.get(arquivo[j]
											.toString());
									List<String> arq = Files.readAllLines(
											pathApontados1, charset);
									for (String a : arq)
										notificacoes.add(a);

								}
						}

						atual++;
						double porcentagem = (atual / total) * 100;
						label.setText(String.format("Carregando %1$.0f%%",
								porcentagem));
					}

				}
			}
		}

		List<String> codevedores = new ArrayList<>();

		Map<String, Doc> mapa = new TreeMap<>();

		for (String comp : notificacoes) {
			if (comp.length() > 600) {
				codevedores.add(comp.substring(316, 323) + " "
						+ comp.substring(52, 70));
			}
		}

		for (String cod : codevedores) {
			String protocolo = cod.substring(0, 7);
			if (mapa.containsKey(protocolo)) {
				String docu = cod.substring(8, 26);
				if (!docu.equals(mapa.get(protocolo).getDoc())) {
					int conttemp = mapa.get(protocolo).getCont();
					mapa.get(protocolo).setCont(++conttemp);
				}

			} else {
				Doc doc = new Doc();
				doc.setDoc(cod.substring(8, 26));
				doc.setCont(1);
				mapa.put(protocolo, doc);
			}
		}

		Set<String> chaves = new TreeSet<>();
		chaves = mapa.keySet();

		int cont = 0;
		
		for(String mod : modPro){
			saidaCancelados.write(mod);
			saidaCancelados.newLine();
		}
		

		for (String chave : chaves) {
			if (mapa.get(chave).getCont() > 1) {
				if (!excecao.contains(chave)) {
					cont++;
					saida.write(String.format("%s,CERINFO%4d", chave, cont));
					saidaCancelados.write(String.format("%s,CERINFO%4d", chave, cont));
					saidaCancelados.newLine();
					saida.newLine();
				}

			}
		}

		saida.newLine();
		saida.write("\n" + cont);
		saida.flush();
		saidaCancelados.flush();
		saidaCancelados.close();
		JOptionPane.showMessageDialog(null, "Concluído");
		
		System.exit(0);
	}
}
