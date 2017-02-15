package br.com.aso.mineracao.entidade;

public class Tanque {

	private Broca broca;
	private Metal[] deposito = new Metal[160];

	public void setBroca(Broca broca) {
		this.broca = broca;
	}

	public Broca getBroca() {
		return broca;
	}

	public void setDeposito(Metal deposito, int index) {
		this.deposito[index] = deposito;
	}

	public Metal getDeposito(int index) {
		return deposito[index];
	}

}

