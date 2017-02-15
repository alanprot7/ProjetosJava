package br.com.aso.mineracao.entidade;

public class Metal {

	private String tipo;
	private int valor;
	private int probabilidade;


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setProbabilidade(int probabilidade) {
		this.probabilidade = probabilidade;
	}

	public int getProbabilidade() {
		return probabilidade;
	}

}