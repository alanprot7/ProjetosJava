package cjm;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
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

public class EnvelopeSw {

	String caminho;
	ArrayList<Path> caminhos = new ArrayList<>();
	ArrayList<String> arquivo = new ArrayList<>();
	String listaCaminho = "";

	private JFrame frmEv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnvelopeSw window = new EnvelopeSw();
					window.frmEv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnvelopeSw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JTextArea textArea_1 = new JTextArea();

	private void initialize() {
		frmEv = new JFrame();
		frmEv.setTitle("Ev31");
		frmEv.setBounds(100, 100, 151, 560);
		frmEv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEv.getContentPane().setLayout(null);
		JLabel lblNenhum = new JLabel("Arquivos");
		JButton btnNewButton = new JButton("Abrir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser file = new JFileChooser(System
						.getProperty("user.dir"));
				file.setFileSelectionMode(JFileChooser.FILES_ONLY);
				file.setFileFilter(new FileNameExtensionFilter(
						"Arquivos de Texto", "txt"));
				file.setAcceptAllFileFilterUsed(false);
				file.showOpenDialog(null);
				caminho = file.getSelectedFile().getPath();
				Path path = Paths.get(caminho);
				caminhos.add(path);
				listaCaminho += path.getFileName().toString() + "\n";
				textArea_1.setText(listaCaminho);
			}
		});
		btnNewButton.setBounds(22, 133, 89, 23);
		frmEv.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Protocolos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 167, 89, 14);
		frmEv.getContentPane().add(lblNewLabel);

		JTextArea textArea = new JTextArea();

		JButton btnNewButton_1 = new JButton("Gerar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Path path;

					String[] listaProtocolos = textArea.getText().toString()
							.split("\n");

					int cont = 0;
					Charset charset = Charset.forName("ISO-8859-1");

					for (Path selectArq : caminhos) {
						List<String> arq = Files.readAllLines(selectArq,
								charset);

						for (String linha : arq)
							arquivo.add(linha);
					}

					path = Paths.get(caminho);
					Path pathSaida = Paths.get(path.getRoot().toString()
							+ path.subpath(0, path.getNameCount() - 2)
							+ "/Envelopes.txt");

					Path pathSaidaLista = Paths.get(path.getRoot().toString()
							+ path.subpath(0, path.getNameCount() - 2)
							+ "/EnvelopesLista.txt");

					BufferedWriter saida = Files.newBufferedWriter(pathSaida,
							charset);

					BufferedWriter saidaLista = Files.newBufferedWriter(
							pathSaidaLista, charset);

					ArrayList<String> listaList = new ArrayList<>();
					int pagina = 0;

					
					ArrayList<Etiqueta> etiqueta = new ArrayList<>();
					
					
					for (String linha : arquivo) {
						if (linha.length() > 819)
							linha = linha.substring(1, linha.length());

						for (String pesquisa : listaProtocolos) {
							pesquisa = "7000" + pesquisa;

							

							if (linha.indexOf(pesquisa) != -1) {
								
								listaList.add(linha);
								
								Etiqueta etiqu = new Etiqueta();
								
								etiqu.setProtocolo(linha.substring(316, 323));
								etiqu.setDocumento(linha.substring(52, 70));
								etiqu.setIndice(arquivo.indexOf(linha));
								etiqueta.add(etiqu);
								
							}
						}
					}
					
					
					Map<String, ArrayList<Etiqueta>> docuMap = new TreeMap<>();
					
					for (Etiqueta eti : etiqueta){
						
						String chave = eti.getDocumento();
						
						if(docuMap.containsKey(chave)){
							docuMap.get(chave).add(eti);
						}else{
							ArrayList<Etiqueta> valor = new ArrayList<>();
							valor.add(eti);
							docuMap.put(chave, valor);
						}
							
					}
					
					Set<String> keySet = docuMap.keySet();
					
					ArrayList<Etiqueta> etiSaida = new ArrayList<>();
		
					for(String key : keySet){
						Etiqueta etiSai = new Etiqueta();
						etiSai.setIndice(docuMap.get(key).get(0).getIndice());
						String protocolo = docuMap.get(key).get(0).getProtocolo();
						for(Etiqueta valor : docuMap.get(key)){
							if(valor.getIndice() != etiSai.getIndice())
								protocolo += "-" + valor.getProtocolo();
						}
						etiSai.setProtocolo(protocolo);
						etiSaida.add(etiSai);
					}
					
					for (Etiqueta eti : etiSaida) {

						int indice = eti.getIndice();

						for(int j = 0; j < 2; j++) {
							saida.append("\t\t\tDestinatário: " + String.format("%1$30s)", "("+eti.getProtocolo()));
							saida.newLine();
							saida.append("\t\t\t"
									+ arquivo.get(indice).substring(2, 47));
							saida.newLine();
							saida.append("\t\t\t"
									+ arquivo.get(indice).substring(324, 369));
							saida.newLine();
							saida.append("\t\t\t"
									+ String.format("%s - %s (%s)",
											arquivo.get(indice).substring(384, 399),
											arquivo.get(indice).substring(433, 453),
											arquivo.get(indice).substring(473, 475)));
							saida.newLine();
							saida.append("\t\t\tCEP: "
									+ arquivo.get(indice).substring(424, 433));
							if (pagina < 7) {
								pagina++;
								for (int i = 0; i < 7; i++)
									saida.newLine();
							} else {
								pagina = 0;
								saida.newLine();
							}

						}
						cont++;
					}
					

					listaList
							.add("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
					listaList
							.add("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ");

					int folhas = 0;
					
					if((cont % 4) == 0){
						folhas = (cont / 4);
					}else
						folhas = (cont / 4) + 1;
					
					int papelRecibo = 0;

					for (int i = 0; i < listaProtocolos.length; i+=3) {
						papelRecibo++;
						saidaLista.append("+----------------------------");
						saidaLista.append("+----------------------------");
						saidaLista.append("+----------------------------+");
						saidaLista.newLine();
						saidaLista.append(String.format("|Data: %1$-22s",listaList.get(i).substring(198,208)));
						saidaLista.append(String.format("|Data: %1$-22s",listaList.get(i + 1).substring(198,208)));
						saidaLista.append(String.format("|Data: %1$-22s",listaList.get(i + 2).substring(198,208))+"|");
						saidaLista.newLine();
						saidaLista.append(String.format("|Prot: %1$-22s",listaList.get(i).substring(316,323)));
						saidaLista.append(String.format("|Prot: %1$-22s",listaList.get(i + 1).substring(316,323)));
						saidaLista.append(String.format("|Prot: %1$-22s",listaList.get(i + 2).substring(316,323))+"|");
						saidaLista.newLine();
						saidaLista.append("|Nome: " + listaList.get(i).substring(2,23)+" ");
						saidaLista.append("|Nome: " + listaList.get(i + 1).substring(2,23)+" ");
						saidaLista.append("|Nome: " + listaList.get(i + 2).substring(2,23)+" "+"|");
						saidaLista.newLine();
						saidaLista.append("|Apre: " + listaList.get(i).substring(70,91)+" ");
						saidaLista.append("|Apre: " + listaList.get(i + 1).substring(70,91)+" ");
						saidaLista.append("|Apre: " + listaList.get(i + 2).substring(70,91)+" "+"|");
						saidaLista.newLine();
						if(papelRecibo == 16){
							saidaLista.append("+----------------------------");
							saidaLista.append("+----------------------------");
							saidaLista.append("+----------------------------+");
							saidaLista.newLine();
							saidaLista.newLine();
							papelRecibo = 0;
						}

					}

					if (cont > 0) {
						saidaLista.append("+----------------------------");
						saidaLista.append("+----------------------------");
						saidaLista.append("+----------------------------+");
						saidaLista.newLine();
					}

					saida.flush();
					saida.close();
					saidaLista.flush();
					saidaLista.close();
					arquivo.clear();
					listaList.clear();
					JOptionPane.showMessageDialog(null, "Envelopes " + cont+" || Folhas: "+folhas);
				} catch (IOException err) {

				}
			}
		});
		btnNewButton_1.setBounds(22, 488, 89, 23);
		frmEv.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 192, 115, 285);
		frmEv.getContentPane().add(scrollPane);

		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);

		lblNenhum.setHorizontalAlignment(SwingConstants.CENTER);
		lblNenhum.setBounds(22, 11, 89, 14);
		frmEv.getContentPane().add(lblNenhum);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 36, 115, 85);
		frmEv.getContentPane().add(scrollPane_1);
		textArea_1.setEditable(false);
		scrollPane_1.setViewportView(textArea_1);
	}
}
