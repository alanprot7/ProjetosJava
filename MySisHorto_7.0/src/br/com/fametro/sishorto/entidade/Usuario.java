package br.com.fametro.sishorto.entidade;

import java.util.ArrayList;

public class Usuario {

	private ArrayList<String> nome = new ArrayList<>();
	private ArrayList<String> matricula = new ArrayList<>();;
	private ArrayList<String> funcao = new ArrayList<>();;


	public ArrayList<String> getNome() {
		return nome;
	}

	public ArrayList<String> getMatricula() {
		return matricula;
	}

	public ArrayList<String> getFuncao() {
		return funcao;
	}

}
