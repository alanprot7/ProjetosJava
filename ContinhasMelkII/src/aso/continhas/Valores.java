package aso.continhas;

public enum Valores {
	
	UMA_CASA(9), DUAS_CASA(99);
	
	private int valor;
	
	private Valores(int opcao){
		
		valor = opcao;
	}
	
	
	public int getValor(){
		
		return valor;
	}

}
