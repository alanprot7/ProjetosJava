package br.com.fametro.sishorto.apresentacao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import br.com.fametro.sishorto.persistencia.Dados;

/**
 *  
 * @author ALAN SANTOS DE OLIVEIRA - ALINE CHAVES DA SILVA - WALCLIDES JULIO PINHEIRO PRAXEDES
 *
 */

public class TelaPrincipal {

	public static void main(String[] args) {

		TelaMenuProcesso processo = new TelaMenuProcesso();

		Path pathUsuario = Paths.get("Files/Usuario.txt");
		Path pathMatricula = Paths.get("Files/Matricula.txt");
		Path pathFuncao = Paths.get("Files/Funcao.txt");
		Path pathCanteiro = Paths.get("Files/Canteiro.txt");
		Path pathPlanta = Paths.get("Files/Planta.txt");
		Path pathIrrigacao = Paths.get("Files/Irrigacao.txt");
		Path pathIrrigacaoValor = Paths.get("Files/IrrigacaoValor.txt");

		Charset charset = Charset.forName("ISO-8859-1");

		try {

			if(!Files.exists(pathUsuario.getParent())) {
				Files.createDirectories(pathUsuario.getParent());
			}

			if(!Files.exists(pathUsuario)){
				Files.createFile(pathUsuario);	
			}


			if(!Files.exists(pathCanteiro)){
				Files.createFile(pathCanteiro);	
			}

			if(!Files.exists(pathIrrigacao)){
				Files.createFile(pathIrrigacao);	
			}

			if(!Files.exists(pathMatricula)){
				Files.createFile(pathMatricula);	
			}
			if(!Files.exists(pathFuncao)){
				Files.createFile(pathFuncao);	
			}
			if(!Files.exists(pathIrrigacaoValor)){
				Files.createFile(pathIrrigacaoValor);	
			}
			if(!Files.exists(pathPlanta)){
				Files.createFile(pathPlanta);	
			}

			List<String> fileUsuario = Files.readAllLines(pathUsuario, charset);
			List<String> fileMatricula = Files.readAllLines(pathMatricula, charset);
			List<String> fileFuncao = Files.readAllLines(pathFuncao, charset);
			List<String> fileCanteiro = Files.readAllLines(pathCanteiro, charset);
			List<String> filePlanta = Files.readAllLines(pathPlanta, charset);
			List<String> fileIrrigacao = Files.readAllLines(pathIrrigacao, charset);
			List<String> fileIrrigacaoValor = Files.readAllLines(pathIrrigacaoValor, charset);



			BufferedWriter usuarioW = Files.newBufferedWriter(pathUsuario, charset );
			BufferedWriter matriculaW = Files.newBufferedWriter(pathMatricula, charset );
			BufferedWriter funcaoW = Files.newBufferedWriter(pathFuncao, charset );
			BufferedWriter canteiroW = Files.newBufferedWriter(pathCanteiro, charset );
			BufferedWriter plantaW = Files.newBufferedWriter(pathPlanta, charset );
			BufferedWriter irrigacaoW = Files.newBufferedWriter(pathIrrigacao, charset );
			BufferedWriter irrigacaoValorW = Files.newBufferedWriter(pathIrrigacaoValor, charset );


			Dados.recuperaUsuario(fileUsuario,fileMatricula ,fileFuncao,  processo.usuario);
			Dados.recuperaCanteiro(fileCanteiro,filePlanta, processo.canteiro);
			Dados.recuperaIrrigacao(fileIrrigacao,fileIrrigacaoValor, processo.mapaIrrigacao);

			processo.rodaMenu();

			Dados.gravaUsuario(usuarioW,matriculaW,funcaoW, processo.usuario);
			Dados.gravaCanteiro(canteiroW,plantaW, processo.canteiro);
			Dados.gravaMapaIrrigacao(irrigacaoW, irrigacaoValorW, processo.mapaIrrigacao);


		} catch (IOException e) {

		}





	}

}
