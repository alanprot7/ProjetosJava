package cartorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ConverteCancel {
	
	public static void main(String[] args) throws IOException {
		
		
		Path pathC = Paths.get("c.pdf");


		File fonte  = new File("F:/Projetos/XPROGs/CANCELADOS_FERMOJU");
		File fonteDir[] = fonte.listFiles();

		
		for(File caminho : fonteDir ){
			if(caminho.toString().contains("TITULOS CANCELADOS"))
				pathC = caminho.toPath();

		}
			
			
		Charset charset = Charset.forName("ISO-8859-1");
		TransformaPDFtoTXT pdf = new TransformaPDFtoTXT();
		
		List<String> cList = pdf.tranforma(pathC.toAbsolutePath().toString());

		
		pathC = Paths.get("c.TXT");

		
		BufferedWriter cSaida = Files.newBufferedWriter(pathC, charset );

		
		
		for(String linha : cList){
			cSaida.append(linha);
			cSaida.newLine();
		}

		cSaida.flush();
		cSaida.close();

	}
	

}
