package simples;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JOptionPane;

public class ArrumaCEP {

	public static void main(String[] args) {

		Path path = Paths.get("Remessa.txt");
		Charset cs = Charset.forName("ISO-8859-1");
		try {
			List<String> remessa = Files.readAllLines(path, cs);
			int fimLinha = remessa.get(0).length();
			BufferedWriter saida = Files.newBufferedWriter(path, cs);
			String cep = "60025130";
			for(String s : remessa){
				saida.write(s.substring(0,327)+cep+s.substring(335,fimLinha));
				saida.newLine();
			}
			
			saida.flush();
			saida.close();
			JOptionPane.showMessageDialog(null, "CEPs Alterados!");
			
		}catch(Exception e){

		}
	}

}
