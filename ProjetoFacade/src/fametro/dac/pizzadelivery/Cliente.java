package fametro.dac.pizzadelivery;

public class Cliente {
	
	private String nome;
	private String end;
	
	
	public Cliente(String nome, String end){
		
		this.nome = nome;
		this.end = end;

		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}


}
