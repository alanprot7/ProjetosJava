package Cartorio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Cancelados {

	public static void main(String[] args) throws IOException {

		Path pathCancelados = Paths.get("Files2/Cancelados.txt");
		Path pathBancos = Paths.get("Files2/Bancos.txt");
		Path pathSaida = Paths.get("Files2/Fermoju.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		List<String> cancelados = Files.readAllLines(pathCancelados, charset);
		List<String> bancos = Files.readAllLines(pathBancos, charset);

		Files.createDirectories(pathSaida.getParent());
		BufferedWriter w = Files.newBufferedWriter(pathSaida, charset );

		List<String> particulares = new ArrayList<>();

		for(int i = 0;i< cancelados.size();i++){
			int ii = i+1;
			if(cancelados.get(i).length() > 60){
				if(cancelados.get(i).substring(4,5).equals(".") && cancelados.get(i).substring(8,9).equals(".") && cancelados.get(i).substring(12,13).equals("-") || 
						cancelados.get(i).substring(3,4).equals(".") && cancelados.get(i).substring(7,8).equals(".") && cancelados.get(i).substring(11,12).equals("/")){
					if(!bancos.contains(cancelados.get(ii).substring(47,84))){
						particulares.add(cancelados.get(i).substring(106,124).replace(".","").replace(",", "").trim());
						w.write(cancelados.get(i));
						w.newLine();
						w.write(cancelados.get(ii));
						w.newLine();
						w.newLine();
					}
				}
			} 
		}

		int faixa3001 = 0,
				faixa3002 = 0,
				faixa3003 = 0,
				faixa3004 = 0,
				faixa3005 = 0,
				faixa3006 = 0;



		for (String part : particulares)
			if (Integer.parseInt(part) >= 85149)
				faixa3006++;
			else
				if (Integer.parseInt(part) >= 42631)
					faixa3005++;
				else
					if (Integer.parseInt(part) >= 21277)
						faixa3004++;
					else
						if (Integer.parseInt(part) >= 8529)
							faixa3003++;
						else
							if (Integer.parseInt(part) >= 1421)
								faixa3002++;
							else
								faixa3001++;




		w.write("Faixas de Cancelados");
		w.newLine();
		w.newLine();
		w.write("3011 = "+faixa3001);
		w.newLine();
		w.write("3012 = "+faixa3002);
		w.newLine();
		w.write("3013 = "+faixa3003);
		w.newLine();
		w.write("3014 = "+faixa3004);
		w.newLine();
		w.write("3015 = "+faixa3005);
		w.newLine();
		w.write("3016 = "+faixa3006);
		w.newLine();
		w.newLine();
		w.write("Total = "+(faixa3001+faixa3002+faixa3003+faixa3004+faixa3005+faixa3006));
		w.newLine();


		w.flush();
		w.close();

		Runtime.getRuntime().exec(" notepad Files2/Fermoju.txt");

	}

}
