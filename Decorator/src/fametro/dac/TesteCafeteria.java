package fametro.dac;

public class TesteCafeteria {
	
	public static void main(String[] args) {
		
		Descafeinado descafeinado = new Descafeinado();
		
		Creme creme = new Creme(descafeinado);
		Creme cream = new Creme(creme);
		
			
		System.out.println(cream	.getDescricao());
		
		System.out.println("R$" + cream.custo());
		
		
	}

}
