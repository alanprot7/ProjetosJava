package dac.adapter;

/**
 * 
 * @author Alan Santos de Oliveira
 *
 */

public class TestePagamento {

	public static void main(String[] args) {
		
		PagamentoDolar moedaDolar = new PagamentoDolar();
		PagamentoReal moeadaReal = new PagamentoReal();
		Real moedaRealAdaptadaDolar = new PagamentoDolarAdapter(moedaDolar);
		
		testaReal(moeadaReal);
		testaReal(moedaRealAdaptadaDolar);
		

	}
	
	public static void testaReal(Real real){
		real.pagamento();
	}
	
}
