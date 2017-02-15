package simples;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
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

public class JanelaFalencia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textDocumento;
	private JTextField textSerie;
	private JTextField textNumero;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblSelo;
	private JLabel lblNumero;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaFalencia frame = new JanelaFalencia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaFalencia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textNome = new JTextField();
		textNome.setBounds(54, 48, 172, 27);
		contentPane.add(textNome);
		textNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("NOME");
		lblNewLabel.setBounds(54, 23, 46, 14);
		contentPane.add(lblNewLabel);

		textDocumento = new JTextField();
		textDocumento.setBounds(54, 109, 172, 27);
		contentPane.add(textDocumento);
		textDocumento.setColumns(10);

		textSerie = new JTextField();
		textSerie.setBounds(54, 191, 46, 27);
		contentPane.add(textSerie);
		textSerie.setColumns(10);

		textNumero = new JTextField();
		textNumero.setBounds(121, 191, 105, 27);
		contentPane.add(textNumero);
		textNumero.setColumns(10);

		lblNewLabel_1 = new JLabel("DOCUMENTO");
		lblNewLabel_1.setBounds(54, 86, 105, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("SERIE");
		lblNewLabel_2.setBounds(54, 166, 35, 14);
		contentPane.add(lblNewLabel_2);

		lblSelo = new JLabel("SELO");
		lblSelo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelo.setBounds(121, 147, 46, 14);
		contentPane.add(lblSelo);

		lblNumero = new JLabel("NUMERO");
		lblNumero.setBounds(149, 166, 77, 14);
		contentPane.add(lblNumero);

		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbreArquivos open = new AbreArquivos();
				Charset cs = Charset.forName("ISO-8859-1");
				Path pathentrada = Paths.get(open.getCaminho());
				List<String> instrumentoG =  new ArrayList<>();
				try {
					List<String> instrumento = Files.readAllLines(pathentrada, cs);
					for(String lis : instrumento){
						String textoOriginal = "responsável(is) através de : pela forma Regular| Para Efeito de Falência ";
						String textoSelo = "class=tblNormal1><STRONG></STRONG></SPAN></TD></TR></TBODY></TABLE></TD>";
							
						if(lis.indexOf(textoOriginal)!=-1)
							instrumentoG.add(lis+"| Recebido: "+textNome.getText().toUpperCase()+" "+textDocumento.getText().toUpperCase());
						else
							if(lis.indexOf(textoSelo)!=-1)
								instrumentoG.add(lis.substring(0,37)+textSerie.getText().toUpperCase()+"-"+textNumero.getText()+lis.substring(37,lis.length()));
							else
								instrumentoG.add(lis);
					}
					
					BufferedWriter arquivosaida = Files.newBufferedWriter(pathentrada, cs);
					
					for(String lis : instrumentoG){
						arquivosaida.write(lis);
						arquivosaida.newLine();
					}
					arquivosaida.flush();
					arquivosaida.close();
					JOptionPane.showMessageDialog(null, "Instrumento Alterado com Sucesso!");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGerar.setBounds(95, 237, 85, 23);
		contentPane.add(btnGerar);
	}
}
