package fametro.dac.pizzadelivery;

public class Pizzaria {
	
	private String nomeEmpresa;
	private String sabor;
	private double valor;
	private String formaPagamento;
	

	public Pizzaria(String nome){
		nomeEmpresa = nome;
		
	}
	
	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void registraPedido(){
				
		
		System.out.println("Pizzaria "+nomeEmpresa+":");
		System.out.println("Pizza sabor "+sabor+" no valor de R$ "+valor+" em "+formaPagamento);
		
	}
	
}
