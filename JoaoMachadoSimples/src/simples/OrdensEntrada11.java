package simples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdensEntrada11 {

	public static void main(String[] args) throws IOException {

		int arqs = 0;

		Path pathOjud = Paths.get("OJud.txt");
		Path pathSaida = Paths.get("RESUMO.TXT");
		File listaArquivos[];
		String dir = "";

		for(char c='A';c<='Z';c++){
			Path teste = Paths.get(c+":/Remessas/Temp");
			if(Files.exists(teste)){
				dir = c+":/Remessas/Temp";
				break;
			}
		}


		File diretorio = new File(dir);
		listaArquivos = diretorio.listFiles();
		List<String> apontados = new ArrayList<>();
		List<String> porArquivo = new ArrayList<>();


		Charset charset = Charset.forName("ISO-8859-1");
		BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);

		Date data = new Date(System.currentTimeMillis());


		for(int i=0;i<listaArquivos.length;i++){
			Path pathApontados = Paths.get(listaArquivos[i].toString());

			if(listaArquivos[i].toString().substring(listaArquivos[i].toString().length()-2,listaArquivos[i].toString().length()-1).equals("7")
					&&Integer.parseInt(listaArquivos[i].toString().substring(listaArquivos[i].toString().length()-3,listaArquivos[i].toString().length()))>=571){
				porArquivo = Files.readAllLines(pathApontados, charset);
				for(String por : porArquivo)
					apontados.add(por);
			}
		}

		List<String> ordens = Files.readAllLines(pathOjud, charset);
		List<String> docsNaoRepetidos = new ArrayList<>();

		for(String apon : apontados){
			if(apon.length()>360){
				if(ordens.contains(apon.substring(345,359))){
					if(!docsNaoRepetidos.contains(apon.substring(345,359))){
						docsNaoRepetidos.add(apon.substring(345,359));
					}
				}
			}

		}

		for(String apon : apontados){
			if(apon.length()>596 && apon.substring(596,600).equals("0001")){
				arqs++;
			}
		}

		String mes=(""+data).substring(4,7);

		switch (mes){
		case "Jan": mes = "01";break;
		case "Feb": mes = "02";break;
		case "Mar": mes = "03";break;
		case "Apr": mes = "04";break;
		case "May": mes = "05";break;
		case "Jun": mes = "06";break;
		case "Jul": mes = "07";break;
		case "Aug": mes = "08";break;
		case "Sep": mes = "09";break;
		case "Oct": mes = "10";break;
		case "Nov": mes = "11";break;
		case "Dec": mes = "12";break;
		default:;
		}

		saida.write("==============================================================");
		saida.newLine();
		saida.write("\t\tRESUMO MOVIMENTO DIA "+(""+data).substring(8,10)+"/"+mes);
		saida.newLine();
		saida.write("--------------------------------------------------------------");
		saida.newLine();
		saida.write("\t\t\tORDENS");
		saida.newLine();
		saida.write("--------------------------------------------------------------");
		saida.newLine();

		for(String doc : docsNaoRepetidos){
			for(String apon : apontados){
				if(apon.substring(345,359).contains(doc)){
					saida.write(apon.substring(297,359));
					break;
				}
			}
			saida.newLine();
		}
		saida.write("--------------------------------------------------------------");
		saida.newLine();
		saida.write("\t\tTOTAL ORDENS = "+docsNaoRepetidos.size());
		saida.newLine();
		saida.write("--------------------------------------------------------------");
		saida.newLine();
		saida.write("\t\tTOTAL ENTRAD = "+((apontados.size())-(arqs*2)));
		saida.newLine();
		saida.write("==============================================================");
		saida.flush();

		Runtime.getRuntime().exec("notepad.exe RESUMO.TXT");

	}
}
