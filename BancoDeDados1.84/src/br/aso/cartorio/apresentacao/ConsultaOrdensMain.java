package br.aso.cartorio.apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import br.aso.cartorio.persistencia.Conexao;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.swing.JCheckBox;

public class ConsultaOrdensMain extends JFrame {

	private String obsTemp;
	private String nomeTemp;
	private String nomePesq = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Conexao conexao = new Conexao();


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaOrdensMain frame = new ConsultaOrdensMain(conexao);
					frame.setVisible(true);
					frame.textFieldCpfCnpj.grabFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaOrdensMain(Conexao conexao) {


		setResizable(false);
		setTitle("Ordens Judiciais 1.84");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 122, 743, 396);
		contentPane.add(scrollPane);

		JButton btnIncluir = new JButton("Incluir");
		JButton btnAlterar = new JButton("Alterar");
		JButton btnExcluir = new JButton("Excluir");
		JButton btnConfirma = new JButton("Confirma");
		JButton btnNovo = new JButton("Novo");
		
		JButton btnCpfcnpj = new JButton("Lista OJud");
		
		JLabel lblSrv = new JLabel("Servidor");
		JButton btnImprimir = new JButton("Imprimir");

		JTextArea dtrpnObs = new JTextArea();

		dtrpnObs.setMargin(new Insets(3, 3, 3, 3));
		dtrpnObs.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		dtrpnObs.setBackground(new Color(245, 245, 220));
		scrollPane.setViewportView(dtrpnObs);
		dtrpnObs.setWrapStyleWord(true);
		dtrpnObs.setLineWrap(true);
		ValidaCPFCNPJ valida = new ValidaCPFCNPJ();


		JLabel lblCpfcnpj = new JLabel("CPF/CNPJ");
		lblCpfcnpj.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCpfcnpj.setBounds(10, 56, 77, 14);
		contentPane.add(lblCpfcnpj);

		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setBounds(106, 55, 161, 20);
		contentPane.add(textFieldCpfCnpj);
		textFieldCpfCnpj.setColumns(10);   


		dtrpnObs.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if(e.getClickCount() == 2){
					if(textFieldCpfCnpj.getText().equals("") || textFieldCpfCnpj.getText().equals(null)){
						e.consume();
						textFieldCpfCnpj.setText(dtrpnObs.getSelectedText());
						btnConfirma.doClick();
					}
				}


			}
		});



		JLabel lblNome = new JLabel("NOME");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 81, 77, 14);
		contentPane.add(lblNome);

		JButton btnPesquisa = new JButton("Pesquisa");
		JCheckBox chckbxPesqObs = new JCheckBox("Obs.");
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(106, 80, 365, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);


		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try{
					if(valida.isCPF(textFieldCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", "").replace(" ", "")) || valida.isCNPJ(textFieldCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", "").replace(" ", ""))){
						conexao.insert(textFieldCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", "").replace(" ", ""), textFieldNome.getText(), dtrpnObs.getText());
						btnIncluir.setEnabled(false);
						textFieldCpfCnpj.setEditable(false);
					} else {
						JOptionPane.showMessageDialog(null, "CPF/CNPJ INVALIDO");
					}
				}catch (SQLException e){
					if(e.getMessage().equals("ERRO: valor é muito longo para tipo character(60)")){
						JOptionPane.showMessageDialog(null, "IMPOSSIVEL INCLUIR: NOME MAIOR QUE 60 CARACTERES");
					}else{
						JOptionPane.showMessageDialog(null, "ERRO SQL: "+e.getMessage());
					}

				}

			}
		});
		btnIncluir.setBounds(208, 11, 89, 23);
		contentPane.add(btnIncluir);


		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					conexao.update(textFieldNome.getText(), dtrpnObs.getText());
					JOptionPane.showMessageDialog(null, "REGISTRO ALTERADO");
					btnConfirma.setEnabled(true);
					dtrpnObs.setText(null);
					textFieldNome.setText(null);
					textFieldCpfCnpj.setText(null);
					textFieldCpfCnpj.setEditable(true);
					conexao.setNull();
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
					btnIncluir.setEnabled(true);
					btnPesquisa.setEnabled(true);
					btnCpfcnpj.setEnabled(true);
					lblSrv.setEnabled(true);
					chckbxPesqObs.setEnabled(true);
					textFieldCpfCnpj.grabFocus();
					btnImprimir.setEnabled(false);
					obsTemp = null;
					nomeTemp = null;

				}catch (SQLException er){
					if(er.getMessage().equals("ERRO: valor é muito longo para tipo character(60)")){
						JOptionPane.showMessageDialog(null, "IMPOSSIVEL ALTERAR: NOME MAIOR QUE 60 CARACTERES");
					}else{
						JOptionPane.showMessageDialog(null, "ERRO SQL: "+er.getMessage());
					}

				}

			}
		});
		btnAlterar.setBounds(307, 11, 89, 23);
		contentPane.add(btnAlterar);
		btnAlterar.setEnabled(false);





		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ok = -1;
				ok = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR O REGISTRO?");

				if(ok==0){
					conexao.delete();
					btnConfirma.setEnabled(true);
					dtrpnObs.setText(null);
					textFieldNome.setText(null);
					textFieldCpfCnpj.setText(null);
					textFieldCpfCnpj.setEditable(true);
					conexao.setNull();
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
					btnIncluir.setEnabled(true);
					btnPesquisa.setEnabled(true);
					btnCpfcnpj.setEnabled(true);
					lblSrv.setEnabled(true);
					chckbxPesqObs.setEnabled(true);
					textFieldCpfCnpj.grabFocus();
					btnImprimir.setEnabled(false);
					obsTemp = null;
					nomeTemp = null;
				} else {
					if(ok==1){
						btnConfirma.setEnabled(true);
						dtrpnObs.setText(null);
						textFieldNome.setText(null);
						textFieldCpfCnpj.setText(null);
						textFieldCpfCnpj.setEditable(true);
						conexao.setNull();
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnIncluir.setEnabled(true);
						btnPesquisa.setEnabled(true);
						btnCpfcnpj.setEnabled(true);
						lblSrv.setEnabled(true);
						chckbxPesqObs.setEnabled(true);
						textFieldCpfCnpj.grabFocus();
						btnImprimir.setEnabled(false);
						obsTemp = null;
						nomeTemp = null;

					}
				}

			}
		});
		btnExcluir.setBounds(406, 11, 89, 23);
		contentPane.add(btnExcluir);
		btnExcluir.setEnabled(false);




		btnConfirma.setEnabled(true);
		btnConfirma.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try{

					if(!valida.isCPF(textFieldCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", "").replace(" ", "")) && !valida.isCNPJ(textFieldCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", "").replace(" ", ""))){
						JOptionPane.showMessageDialog(null, "ATENÇÃO: CPF/CNPJ INVÁLIDO!!");
					}

					conexao.setTfDocu(textFieldCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", "").replace(" ", ""));
					conexao.query();

					conexao.getDocu().isEmpty(); //PROVOCA O NullPointerException
					obsTemp = conexao.getObs();
					nomeTemp = conexao.getNome();
					dtrpnObs.setText(conexao.getObs());
					textFieldNome.setText(conexao.getNome());
					btnConfirma.setEnabled(false);
					textFieldCpfCnpj.setEditable(false);
					btnIncluir.setEnabled(false);
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnPesquisa.setEnabled(false);
					btnCpfcnpj.setEnabled(false);
					chckbxPesqObs.setEnabled(false);
					lblSrv.setEnabled(false);
					btnImprimir.setEnabled(true);

				}catch(NullPointerException e){
					JOptionPane.showMessageDialog(null, "REGISTRO NAO ENCONTRADO");
					textFieldCpfCnpj.grabFocus();
				}

			}
		});
		btnConfirma.setBounds(10, 11, 89, 23);
		contentPane.add(btnConfirma);



		textFieldCpfCnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					btnConfirma.doClick();
				}
			}
		});
		
		
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.isControlDown() && (arg0.getKeyCode() == KeyEvent.VK_F)){
					nomePesq = textFieldNome.getText();
					btnPesquisa.doClick();
				}
			}
		});



		dtrpnObs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.isControlDown() && (arg0.getKeyCode() == KeyEvent.VK_S)){
					btnAlterar.doClick();
				}
			}
		});
	
		dtrpnObs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.isControlDown() && (arg0.getKeyCode() == KeyEvent.VK_N)){
					btnNovo.doClick();
				}
			}
		});

		
		

		btnNovo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try{
					if(!obsTemp.equals(dtrpnObs.getText()) || !nomeTemp.equals(textFieldNome.getText())){

						if(JOptionPane.showConfirmDialog(null, "A consulta foi aterada deseja salvar?") == 0){
							btnAlterar.doClick();
						}
					}

					obsTemp = null;
					nomeTemp = null;

				}catch(NullPointerException err){

				}

				btnConfirma.setEnabled(true);
				dtrpnObs.setText(null);
				textFieldNome.setText(null);
				textFieldCpfCnpj.setText(null);
				textFieldCpfCnpj.setEditable(true);
				conexao.setNull();
				btnExcluir.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnIncluir.setEnabled(true);
				btnPesquisa.setEnabled(true);
				btnCpfcnpj.setEnabled(true);
				chckbxPesqObs.setEnabled(true);
				lblSrv.setEnabled(true);
				textFieldCpfCnpj.grabFocus();
				btnImprimir.setEnabled(false);
			}

		});
		btnNovo.setBounds(109, 11, 89, 23);
		contentPane.add(btnNovo);





		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!chckbxPesqObs.isSelected()){
					if(nomePesq == null)
					nomePesq = JOptionPane.showInputDialog("INSIRA O NOME A PESQUISAR");
					dtrpnObs.setText(conexao.queryNome(nomePesq));
					conexao.setNull();
					nomePesq = null;

				}else{
					if(nomePesq == null)
					nomePesq = JOptionPane.showInputDialog("INSIRA UMA PARTE DA INFORMACAO A PESQUISAR DENTRO DAS OBS");
					dtrpnObs.setText(conexao.queryObs(nomePesq));
					conexao.setNull();
					nomePesq = null;
				}

			}
		});
		btnPesquisa.setBounds(589, 54, 89, 23);
		contentPane.add(btnPesquisa);





		btnCpfcnpj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dtrpnObs.setText(conexao.queryDoc());
				conexao.setNull();	

			}
		});
		btnCpfcnpj.setBounds(589, 11, 104, 23);
		contentPane.add(btnCpfcnpj);


		lblSrv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{ 
					if(lblSrv.isEnabled()){
						String srv = conexao.getSrv();
						srv = JOptionPane.showInputDialog(null, "SERVIDOR = "+conexao.getSrv());
						if(!srv.equals(conexao.getSrv()))
							conexao.setSrv(srv);
					}

				} catch(NullPointerException e){

				}
			}
		});
		lblSrv.setBounds(703, 15, 50, 14);
		contentPane.add(lblSrv);


		chckbxPesqObs.setBounds(684, 54, 69, 23);
		contentPane.add(chckbxPesqObs);


		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{

					Path path = Paths.get("temp.txt");
					Charset cs = Charset.forName("ISO-8859-1");
					BufferedWriter saida = Files.newBufferedWriter(path,cs);
					saida.write(dtrpnObs.getSelectedText());
					saida.flush();
					saida.close();
					Runtime.getRuntime().exec("notepad.exe /p temp.txt");


				} catch (IOException e) {

				} catch(NullPointerException e){
					JOptionPane.showMessageDialog(null,"Para Imprimir selecione o Texto");
				}



			}
		});
		btnImprimir.setBounds(589, 88, 89, 23);
		contentPane.add(btnImprimir);
		btnImprimir.setEnabled(false);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try{
					if(!obsTemp.equals(dtrpnObs.getText()) || !nomeTemp.equals(textFieldNome.getText())){

						if(JOptionPane.showConfirmDialog(null, "A consulta foi aterada deseja salvar?") == 0){
							btnAlterar.doClick();
						}
					}

				}catch(NullPointerException err){

				}

			}
		});

	}
}
