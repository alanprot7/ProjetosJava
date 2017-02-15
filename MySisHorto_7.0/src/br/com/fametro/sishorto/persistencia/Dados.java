package br.com.fametro.sishorto.persistencia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.fametro.sishorto.entidade.Canteiro;
import br.com.fametro.sishorto.entidade.Usuario;

public class Dados {

	public static void recuperaUsuario(List<String> fileUsuario,List<String> fileMatricula,List<String> fileFuncao, Usuario usuario){

		for(String usuarios : fileUsuario){
			usuario.getNome().add(usuarios);
		}

		for(String matriculas : fileMatricula){
			usuario.getMatricula().add(matriculas);
		}

		for(String funcoes : fileFuncao){
			usuario.getFuncao().add(funcoes);
		}
	}

	public static void recuperaCanteiro(List<String> fileCanteiro,List<String> filePlanta, Canteiro canteiro){

		for(String canteiros : fileCanteiro){
			canteiro.getIdentificacao().add(canteiros);
		}

		for(String plantas : filePlanta){
			canteiro.getPlanta().getEspecie().add(plantas);
		}

	}

	public static void recuperaIrrigacao(List<String> fileIrrigacao,List<String> fileIrrigacaoVaor, Map<String, String> mapaIrrigacao){

		for(int i=0; i< fileIrrigacao.size() ; i++){
			mapaIrrigacao.put(fileIrrigacao.get(i), fileIrrigacaoVaor.get(i));
		}

	}


	public static void gravaUsuario(BufferedWriter usuarioW, BufferedWriter matriculaW, BufferedWriter funcaoW,  Usuario usuario) throws IOException{

		for(String usuarios : usuario.getNome()){
			usuarioW.write(usuarios);
			usuarioW.newLine();
		}
		usuarioW.flush();

		for(String matriculas : usuario.getMatricula()){
			matriculaW.write(matriculas);
			matriculaW.newLine();

		}
		matriculaW.flush();
		for(String funcoes : usuario.getFuncao()){
			funcaoW.write(funcoes);
			funcaoW.newLine();

		}
		funcaoW.flush();
	}

	public static void gravaCanteiro(BufferedWriter canteiroW, BufferedWriter plantaW, Canteiro canteiro) throws IOException{

		for(String canteiros : canteiro.getIdentificacao()){
			canteiroW.write(canteiros);
			canteiroW.newLine();
			
		}
		canteiroW.flush();
		for(String plantas : canteiro.getPlanta().getEspecie()){
			plantaW.write(plantas);
			plantaW.newLine();
			
		}
		plantaW.flush();

	}

	public static void gravaMapaIrrigacao(BufferedWriter irrigacaoW,BufferedWriter irrigacaoValorW, Map<String, String> mapaIrrigacao) throws IOException{

		Set<String> chaves = mapaIrrigacao.keySet();

		for(String chave : chaves){
			irrigacaoW.write(chave);
			irrigacaoW.newLine();
			irrigacaoValorW.write(mapaIrrigacao.get(chave));
			irrigacaoValorW.newLine();

		}
		
		irrigacaoW.flush();
		irrigacaoValorW.flush();

	}


}
