package fametro.dac.pizzadelivery;

public class Entregas {
	
	private Cliente cliente;
	private String nomeCliente;
	private String endereco;
	
	
	public Entregas(Cliente cliente){
		
		this.nomeCliente = cliente.getNome();
		this.endereco = cliente.getEnd();
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public void registraEntrega(){
		
		System.out.println("Cliente: "+nomeCliente);
		System.out.println("Endereço de Entrega: "+endereco);
	}

}
