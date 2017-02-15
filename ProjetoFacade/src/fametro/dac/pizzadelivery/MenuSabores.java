package fametro.dac.pizzadelivery;

public enum MenuSabores {
	
	MUSSARELA(15.00), CALABRESA(15.00), FRANGO(20.00), PORTUGUESA(18.00);

	private final double valor;
	
	MenuSabores(double valorOpcao){
		
		valor = valorOpcao;
	}
	
	public double getValor(){
		return valor;
	}
	
}
