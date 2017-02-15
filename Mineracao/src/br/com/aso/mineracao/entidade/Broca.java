package br.com.aso.mineracao.entidade;

public class Broca {

	private String tipo;
	private int limiteProfundidade;
	private int velocidade;
	private double tempo;


	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setLimiteProfundidade(int limiteProfundidade) {
		this.limiteProfundidade = limiteProfundidade;
	}

	public int getLimiteProfundidade() {
		return limiteProfundidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade =  velocidade;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public double getTempo() {
		return tempo;
	}

}