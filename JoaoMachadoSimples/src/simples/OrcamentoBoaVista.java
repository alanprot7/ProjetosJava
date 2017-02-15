package simples;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrcamentoBoaVista {

	public static void main(String[] args) throws IOException {

		Path arqcancelado = Paths.get("F:/Projetos/XPROGs/RECIBO-SERASA/OrçaBoaVista/jmc.txt");
		Path arqprotestado = Paths.get("F:/Projetos/XPROGs/RECIBO-SERASA/OrçaBoaVista/jmp.txt");
		Charset cs = Charset.forName("ISO-8859-1");
		List<String> cancelados = Files.readAllLines(arqcancelado, cs);
		List<String> protestados = Files.readAllLines(arqprotestado, cs);
		Map<String, Map<Integer, ArrayList<String>>> mapaProtesto = new TreeMap<>();
		Map<String, Map<Integer, ArrayList<String>>> mapaCancelamento = new TreeMap<>();
		int originalProtesto = 0;
		int repetidoProtesto = 0;
		int originalCancelado = 0;
		int repetidoCancelado = 0;

		for (String string : protestados) {
			
			String mes = string.substring(7, 9);
			int fim = string.length() - 1;
			String documento = string.substring(28, fim);
			int dia = Integer.parseInt(string.substring(10, 12));
			int quinzena = 0;
			
			if (dia >= 1 && dia <= 15)
				quinzena = 1;
			else
				quinzena = 2;
			
			if (mapaProtesto.containsKey(mes)) {

				if (mapaProtesto.get(mes).containsKey(quinzena)) {
					if (mapaProtesto.get(mes).get(quinzena).contains(documento)) {
						repetidoProtesto++;
					}else{
						mapaProtesto.get(mes).get(quinzena).add(documento);
						originalProtesto++;
					}
				}else{
					ArrayList<String> arr = new ArrayList<>();
					arr.add(documento);
					mapaProtesto.get(mes).put(quinzena, arr);
					originalProtesto++;
				}
					
			}else{
				ArrayList<String> arr = new ArrayList<>();
				arr.add(documento);
				Map<Integer, ArrayList<String>> mapin = new TreeMap<>();
				mapin.put(quinzena, arr);
				mapaProtesto.put(mes, mapin);
				originalProtesto++;
			}
				
		}
		
		for (String string : cancelados) {
			
			String mes = string.substring(31, 33);
			int fim = string.length() - 1;
			String documento = string.substring(52, fim);
			int dia = Integer.parseInt(string.substring(34, 36));
			int quinzena = 0;
			
			if (dia >= 1 && dia <= 15)
				quinzena = 1;
			else
				quinzena = 2;
			
			if (mapaCancelamento.containsKey(mes)) {

				if (mapaCancelamento.get(mes).containsKey(quinzena)) {
					if (mapaCancelamento.get(mes).get(quinzena).contains(documento)) {
						repetidoCancelado++;
					}else{
						mapaCancelamento.get(mes).get(quinzena).add(documento);
						originalCancelado++;
					}
				}else{
					ArrayList<String> arr = new ArrayList<>();
					arr.add(documento);
					mapaCancelamento.get(mes).put(quinzena, arr);
					originalCancelado++;
				}
					
			}else{
				ArrayList<String> arr = new ArrayList<>();
				arr.add(documento);
				Map<Integer, ArrayList<String>> mapin = new TreeMap<>();
				mapin.put(quinzena, arr);
				mapaCancelamento.put(mes, mapin);
				originalCancelado++;
			}
				
		}
		
		System.out.println(String.format("Protestados Originais: %1$5d\nProtestados Repetidos: %2$5d\nCancelados Originais: %3$6d\nCancelados Repetidos: %4$6d",
				originalProtesto,
				repetidoProtesto,
				originalCancelado,
				repetidoCancelado));

	}
}
