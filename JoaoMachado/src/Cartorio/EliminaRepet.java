package Cartorio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EliminaRepet {

	public static void main(String[] args) throws IOException {
		
		Path pathBancos = Paths.get("Files2/Bancos.txt");
		Path pathBancos2 = Paths.get("Files2/Bancos2.txt");
		
		Charset charset = Charset.forName("ISO-8859-1");

		List<String> bancos = Files.readAllLines(pathBancos, charset);
		List<String> bancos2 = Files.readAllLines(pathBancos2, charset);
		
		List<String> unicos = new ArrayList<>();
		
		for(String bank : bancos){
				unicos.add(bank);
		}

		for(String bank : bancos2){
			if(!unicos.contains(bank)){
				System.out.println(bank);
			}
		}

	
	}
	
	
	
}
