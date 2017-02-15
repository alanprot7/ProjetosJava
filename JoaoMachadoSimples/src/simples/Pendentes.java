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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Pendentes {

	public static void main(String[] args) throws IOException {

		JFrame form = new JFrame();
		form.setTitle("Aguarde");
		form.setBounds(SwingConstants.LEFT+50,SwingConstants.TOP+50, 200,100);
		JLabel label = new JLabel();
		label.setText("Lendo Diretorio");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		form.add(label);
		form.setVisible(true);

		Charset charset = Charset.forName("ISO-8859-1");
		File diretorio[];
		List<String> lancados = new ArrayList<>();

		Path pathSaida = Paths.get("Pendentes.txt");
		Path pathLista = Paths.get("Lancados.txt");
		BufferedWriter lista = Files.newBufferedWriter(pathSaida, charset);
		List<String> mesmoDia = Files.readAllLines(pathLista,charset);


		for(char c='A';c<='Z';c++){
			File arquivos = new File(c+":/Projetos/DEVOLUCAO CRA/JA LANCADOS");
			if(arquivos.exists()){
				diretorio = arquivos.listFiles();
				for(int i=0;i<diretorio.length;i++){
					Path path = Paths.get(diretorio[i].toString());
					List<String> temp = Files.readAllLines(path,charset);
					for(String s : temp)
						lancados.add(s);
				}
			}

		}

		label.setText("Gerando Pendentes");

		lista.write("Protocolos Pendentes");
		lista.newLine();
		lista.newLine();

		String titulo = lancados.get(0).substring(49, 57);

		for(int i=1;i<lancados.size();i++){
			if(lancados.get(i).substring(11, 19).equals(titulo) && !mesmoDia.contains(lancados.get(i).substring(1,11))){

				lista.write(lancados.get(i).substring(1,11)+" "+lancados.get(i).substring(30,62)+" "+titulo);
				lista.newLine();
			}else
				if(i<lancados.size()-1 && lancados.get(i).substring(49, 57).equals(titulo)){
					titulo = lancados.get(++i).substring(49, 57);
				}
		}

		lista.flush();
		lista.close();

		label.setText("Terminado");

		Runtime.getRuntime().exec("notepad.exe Pendentes.txt");

		System.exit(0);
	}
}
