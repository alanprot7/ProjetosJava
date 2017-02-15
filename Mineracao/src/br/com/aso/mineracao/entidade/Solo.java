package br.com.aso.mineracao.entidade;

public class Solo {

	public final int PROFUNDIDADE = 1600;
	private int nivelInicial;
	private int nivelFinal;
	private Metal metal;


	public void setNivelInicial(int nivelInicial) {
		this.nivelInicial = nivelInicial;
	}

	public int getNivelInicial() {
		return nivelInicial;
	}

	public void setNivelFinal(int nivelFinal) {
		this.nivelFinal = nivelFinal;
	}

	public int getNivelFinal() {
		return nivelFinal;
	}

	public void setMetal(Metal metal) {
		this.metal = metal;
	}

	public Metal getMetal() {
		return metal;
	}
}

