package simples;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class VerificaLancadoAnteriores {

	public static void main(String[] args) throws IOException {

		Charset charset = Charset.forName("ISO-8859-1");
		File diretorio[];
		int totais = 0;
		List<String> lancados = new ArrayList<>();

		JFrame form = new JFrame();
		form.setBounds(SwingConstants.LEFT+50,SwingConstants.TOP+50, 200,100);
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("Lendo Diretório");
		form.setTitle("Aguarde");
		form.add(label);
		form.setVisible(true);

		for(char c='A';c<='Z';c++){
			File arquivos = new File(c+":/Projetos/DEVOLUCAO CRA/ANOS ANTERI");
			if(arquivos.exists()){
				diretorio = arquivos.listFiles();
				totais = diretorio.length;
				for(int i=0;i<diretorio.length;i++){
					Path path = Paths.get(diretorio[i].toString());
					List<String> temp = Files.readAllLines(path,charset);
					for(String s : temp)
						lancados.add(s);
				}
			}

		}

		label.setText(totais+" Arquivos Carregados");
		
		do{
		String protocolo = JOptionPane.showInputDialog("Digite o Protocolo");
		boolean achou = false;

		String titulo = lancados.get(0).substring(49, 57);
		
		try{
			for(int i=1;i<lancados.size();i++){
				if(protocolo.length()>9)
					if(lancados.get(i).startsWith("1"+protocolo) || lancados.get(i).startsWith("2"+protocolo)){

						JOptionPane.showMessageDialog(null, lancados.get(i).substring(1,11)+" "+lancados.get(i).substring(30,62)+" "+titulo);
						achou = true;
					}else
						if(i<lancados.size()-1 && lancados.get(i).substring(49, 57).equals(titulo)){
							titulo = lancados.get(++i).substring(49, 57);
						}
			}

			if(protocolo.length()<10){
				JOptionPane.showMessageDialog(null, "Protocolo Fora do Padrão");
			}else
			if(!achou)
				JOptionPane.showMessageDialog(null, "Não foi encontrado o protocolo " + protocolo);

		}catch(NullPointerException e){

		}
		}while(JOptionPane.showConfirmDialog(null, "Sair?") != 0);
		
		System.exit(0);

	}
}
