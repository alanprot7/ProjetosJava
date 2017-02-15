package cartorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Converte {
	
	public static void main(String[] args) throws IOException {
		
		
		Path pathC = Paths.get("c.pdf");
		Path pathV = Paths.get("v.pdf");
		Path pathCerinfo = Paths.get("Cerinfo.pdf");

		File fonte  = new File("F:/Projetos/XPROGs/COMPARA_PAGOS");
		File fonteDir[] = fonte.listFiles();

		
		for(File caminho : fonteDir ){
			if(caminho.toString().contains("CANCELADOS"))
				pathC = caminho.toPath();
			if(caminho.toString().contains("DEVOLVIDOS"))
				pathV = caminho.toPath();
			if(caminho.toString().contains("TITULOS PAGOS"))
				pathCerinfo = caminho.toPath();
		}
			
			
		Charset charset = Charset.forName("ISO-8859-1");
		TransformaPDFtoTXT pdf = new TransformaPDFtoTXT();
		
		List<String> cList = pdf.tranforma(pathC.toAbsolutePath().toString());
		List<String> vList = pdf.tranforma(pathV.toAbsolutePath().toString());
		List<String> cerinfoList = pdf.tranforma(pathCerinfo.toAbsolutePath().toString());
		
		pathC = Paths.get("c.TXT");
		pathV = Paths.get("v.TXT");
		pathCerinfo = Paths.get("Cerinfo.TXT");
		
		BufferedWriter cSaida = Files.newBufferedWriter(pathC, charset );
		BufferedWriter vSaida = Files.newBufferedWriter(pathV, charset );
		BufferedWriter cerinfoSaida = Files.newBufferedWriter(pathCerinfo, charset );
		
		
		for(String linha : cList){
			cSaida.append(linha);
			cSaida.newLine();
		}
		for(String linha : vList){
			vSaida.append(linha);
			vSaida.newLine();
		}
		for(String linha : cerinfoList){
			cerinfoSaida.append(linha);
			cerinfoSaida.newLine();
		}
		
		cSaida.flush();
		cSaida.close();
		vSaida.flush();
		vSaida.close();
		cerinfoSaida.flush();
		cerinfoSaida.close();
		
	}
	

}
