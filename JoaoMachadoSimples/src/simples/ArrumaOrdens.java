package simples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrumaOrdens {

	public static void main(String[] args) throws IOException {

		FileReader ori = new FileReader("T_ORJUD.txt");
		BufferedReader lerOri = new BufferedReader(ori);
		FileWriter saida = new FileWriter("T_ORJUD.txt_OK.txt");
		BufferedWriter escreve = new BufferedWriter(saida);

		List<String> ordens = new ArrayList<>();


		String linhaO = lerOri.readLine();

		while (linhaO!= null){
			
			String zero ="";
			if(linhaO != null && linhaO.length()<14 ){
				for(int i=0;i<14-linhaO.length();i++)
					zero+= "0";
			}

			ordens.add(zero+linhaO);
			linhaO = lerOri.readLine();
		}


		for(String ord : ordens){
			escreve.write(ord);
			escreve.newLine();
		}

		lerOri.close();
		escreve.close();

	}
}
