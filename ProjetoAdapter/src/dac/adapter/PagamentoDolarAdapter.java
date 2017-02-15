package dac.adapter;

public class PagamentoDolarAdapter implements Real{

	PagamentoDolar dolar;
	
	public PagamentoDolarAdapter(PagamentoDolar dolar) {
		this.dolar = dolar;
	}
	

	public void pagamento() {
		System.out.print("Sou uma moeda Real e "); 
		dolar.pagamento();
		
		
	}

}
