package fametro.dac;

public abstract class Bebida {
	
	protected String descricao;
	
	public String getDescricao(){
		return descricao;
	}
	
	public abstract double custo();

}
