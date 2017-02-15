package br.com.aso.mineracao.negocio;

import br.com.aso.mineracao.entidade.*;

public class Service {

	public void limpaTanque(Tanque tanque){
		Metal metal = new Metal();
		metal.setTipo(null);
		metal.setProbabilidade(0);
		metal.setValor(0);
		for(int i=0;i<160;i++)
			tanque.setDeposito(metal, i);
	}


	public void constroiMetal(Metal metal, String tipo, int valor, int probabilidade) {
		metal.setTipo(tipo);
		metal.setValor(valor);
		metal.setProbabilidade(probabilidade);
	}

	public void constroiSolo(Solo solo, int nivelInicial, int nivelFinal, Metal metal) {
		solo.setNivelInicial(nivelInicial);
		solo.setNivelFinal(nivelFinal);
		solo.setMetal(metal);
	}

	public void constroiBroca(Broca broca, String tipo, int limiteProfundidade, int velocidade, double tempo) {
		broca.setTipo(tipo);
		broca.setLimiteProfundidade(limiteProfundidade);
		broca.setVelocidade(velocidade);
		broca.setTempo(tempo);

	}

	public double calculaTempo(int velocidade, int metros) {

		double result = metros/((double)velocidade);
		return result;

	}

	public int calculaValorTotal(Tanque tanque) {
		int valortotal=0;
		for(int i=0;i<160;i++)
			if(tanque.getDeposito(i).getTipo()!=null)
			valortotal+= tanque.getDeposito(i).getValor();

		return valortotal;

	}
	
	public int coletaMetal(Tanque tanque, Solo solo, int cont, int rd) {
		if(rd % solo.getMetal().getProbabilidade()==0){
		tanque.setDeposito(solo.getMetal(), cont);
		System.out.println(tanque.getDeposito(cont).getTipo());
		cont++;
		}
		return cont;
	}
	
	public void publicaTotal(Tanque tanque, int valortotal, int quantidade, double tempo){
		System.out.println("*****************************");
		System.out.println("Coleta da Broca de "+tanque.getBroca().getTipo());
		System.out.println("Qtd de Metais = "+quantidade);
		System.out.println("Valor Total R$ "+valortotal);
		System.out.println("Tempo Total    "+tempo);
		System.out.println("*****************************");
		
	}
	
	public int calculaQtd(Tanque tanque){
		int result = 0;
		for(int i=0;i<160;i++)
			if(tanque.getDeposito(i).getTipo()!=null)
				result++;
		
		return result;
	}

}


