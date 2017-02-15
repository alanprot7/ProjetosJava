package fametro.dac.pizzadelivery;

public class TesteFacade {

	public static void main(String[] args) {

		Cliente cliente = new Cliente("Roberto Arruda","Av Santos Dumont 2500 Ap 207");

		Pizzaria pizzaria = new Pizzaria("Da Vovó");

		Entregas entregas = new Entregas(cliente);

		DeliveryFacade delivery = new DeliveryFacade(pizzaria, entregas);

		delivery.pedePizza(FormaPagamento.CARTAO.toString(),
				MenuSabores.CALABRESA.toString(),
				MenuSabores.CALABRESA.getValor());

	}

}
