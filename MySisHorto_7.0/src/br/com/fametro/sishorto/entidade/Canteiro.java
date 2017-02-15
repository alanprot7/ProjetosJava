package br.com.fametro.sishorto.entidade;

import java.util.ArrayList;

public class Canteiro {
	
	private ArrayList<String> identificacao = new ArrayList<>();
	private Planta planta = new Planta();
	private Irrigacao irrigacao = new Irrigacao();
	private Solo solo = new Solo();
	private ArrayList<Double> temperatura = new ArrayList<>();
	
	public ArrayList<Double> getTemperatura() {
		return temperatura;
	}
	
	public Solo getSolo() {
		return solo;
	}
	
	public Irrigacao getIrrigacao() {
		return irrigacao;
	}

	public Planta getPlanta() {
		return planta;
	}
	
	public ArrayList<String> getIdentificacao() {
		return identificacao;
	}

}

