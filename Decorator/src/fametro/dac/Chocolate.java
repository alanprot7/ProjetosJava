package fametro.dac;

public class Chocolate extends IngredienteDecorator {

	public Chocolate(Bebida bebida){
		this.bebida = bebida;
	}
	
	public String getDescricao(){
		return bebida.getDescricao() + ", com Chocolate";
	}
	
	public double custo(){
		return bebida.custo() + 1.00;
	}
}
