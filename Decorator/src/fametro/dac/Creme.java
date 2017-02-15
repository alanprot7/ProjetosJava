package fametro.dac;

public class Creme extends IngredienteDecorator{

	public Creme(Bebida bebida){
		this.bebida = bebida;
	}
	
	public String getDescricao(){
		return bebida.getDescricao() + ", com Creme";
	}
	
	public double custo(){
		return bebida.custo() + 1.50;
	}
	
}
