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

import javax.swing.JOptionPane;

public class RemessaCCB {

	public static void main(String[] args) throws IOException {

		Charset charset = Charset.forName("ISO-8859-1");
		Path pathSaida = Paths.get("Remessa.txt");
		int opcao = 0;

		if(Files.exists(pathSaida))
			opcao = JOptionPane.showConfirmDialog(null, "Remessa já existe\nDeseja substituir?");

		if(opcao==0){
			String[] nomeArq = veriRemessa("E0602109");
			try{
				Path pathRemessa = Paths.get(nomeArq[0]);
				Path pathRetorno = Paths.get(nomeArq[1]);
				List<String> remessa = Files.readAllLines(pathRemessa,charset);
				List<String> retorno = Files.readAllLines(pathRetorno, charset);
				BufferedWriter saida = Files.newBufferedWriter(pathSaida, charset);
				List<String> novaRemessa = new ArrayList<>();


				for(String rem : retorno){
					if(rem.substring(134,136).equals("13") 
							|| rem.substring(134,136).equals("47") 
							|| rem.substring(134,136).equals("35") ){
						for(String res : remessa){
							if(res.indexOf(rem.substring(37,44))!=-1)
								novaRemessa.add(res);
						}
					}
				}

				for(String s : novaRemessa){
					saida.write(s);
					saida.newLine();
				}
				saida.flush();
				saida.close();
				arrumaCep(pathSaida,charset);
				JOptionPane.showMessageDialog(null, "Arquivo Gerado!");
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(null, "Não Existe arquivo com Retorno");
			}
		}


		if(JOptionPane.showConfirmDialog(null, "Deseja Montar a Nova Remessa?")==0){

			List<String> retorno = listaDir("E0602109");
			List<String> remessa;
			String lista = "";
			for(int i=0;i<retorno.size();i++)
				lista+= i+" "+retorno.get(i)+"\n";

			try{
				String op = JOptionPane.showInputDialog("Escolha a Opção:\n"+lista);
				if(Integer.parseInt(op)<=(retorno.size()-1)){
					Path path = Paths.get(retorno.get(Integer.parseInt(op)));
					remessa = Files.readAllLines(path, charset);

					List<String> novaRemessa = Files.readAllLines(pathSaida, charset);
					retorno.clear();
					for(int i=0;i<remessa.size()-1;i++)
						retorno.add(remessa.get(i));

					int linhaFinal = remessa.size()-1;

					for(String s : novaRemessa){
						retorno.add(s);
					}

					retorno.add(remessa.get(linhaFinal));
					BufferedWriter saida = Files.newBufferedWriter(path, charset);
					List<String> remessaFinal = arrumaSeq(retorno);
					for(String s : remessaFinal){
						saida.write(s);
						saida.newLine();
					}

					saida.flush();
					saida.close();
					JOptionPane.showMessageDialog(null, "Remessa Gerada!");
				}
			}catch(NumberFormatException e){}


		}
	}



	public static String[] veriRemessa(String nome){
		String[] res = new String[2];
		for(int i=11;i<=311;i+=10){
			String ext = "";
			if(i<101)
				ext = "0"+i;
			else
				ext = String.valueOf(i);
			File temp = new File(nome+"."+ext);
			File retor = new File("R"+temp.toString().substring(1,11)+"0");
			if(temp.exists() && retor.exists()){
				res[0] = temp.toString();
				res[1] = retor.toString();
				return res;
			}
		}
		return res;
	}

	public static List<String> listaDir(String nome){

		List<String> res = new ArrayList<>();

		for(int i=11;i<=311;i+=10){
			String ext = "";
			if(i<101)
				ext = "0"+i;
			else
				ext = String.valueOf(i);
			File temp = new File(nome+"."+ext);
			if(temp.exists()){
				res.add(temp.toString());
			}
		}
		return res;
	}

	public static List<String> arrumaSeq(List<String> lista){

		List<String> res = new ArrayList<>();
		int cont = 1;
		String fim = "0000";
		for(String s : lista){
			String conts = String.valueOf(cont);
			res.add(s.substring(0,s.length()-4)+fim.substring(0,(fim.length()-conts.length()))+conts);
			cont++;
		}

		return res;
	}


	public static void arrumaCep(Path path, Charset cs) {

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

		}catch(Exception e){

		}
	}




}
