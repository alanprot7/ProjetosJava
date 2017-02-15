package fametro.dac;

public class Leite extends IngredienteDecorator {
	
	public Leite(Bebida bebida){
		this.bebida = bebida;
	}
	
	public String getDescricao(){
		return bebida.getDescricao() + ", com Leite";
	}
	
	public double custo(){
		return bebida.custo() + 2.00;
	}

}
