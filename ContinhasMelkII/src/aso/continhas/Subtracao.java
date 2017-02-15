package aso.continhas;

public class Subtracao implements Sinal{
	
	private int valor;
	private int valor2;
	
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getValor2() {
		return valor2;
	}
	public void setValor2(int valor2) {
		this.valor2 = valor2;
	}
	@Override
	public void monta() {
		System.out.print(valor+" - "+valor2);
		
	}
	
	

}
