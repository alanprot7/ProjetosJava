package fametro.dac.pizzadelivery;

public class DeliveryFacade {
	
	Pizzaria pizzaria;
	Entregas entregas;
	
	public DeliveryFacade(Pizzaria pizzaria, Entregas entregas){
		
		this.pizzaria = pizzaria;
		this.entregas = entregas;
		
	}

	public void pedePizza(String formaPagamento, String sabor, double valor){
		
		pizzaria.setSabor(sabor);
		pizzaria.setValor(valor);				
		pizzaria.setFormaPagamento(formaPagamento);
				
		pizzaria.registraPedido();
		entregas.registraEntrega();
	}
	
}
