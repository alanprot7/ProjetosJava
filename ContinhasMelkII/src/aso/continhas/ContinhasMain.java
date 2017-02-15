package aso.continhas;

import java.util.ArrayList;
import java.util.Random;

public class ContinhasMain {

	public static void main(String[] args) {

		ArrayList<Sinal> contas = new ArrayList<>();

		contas.add(new Adicao());
		contas.add(new Subtracao());
		contas.add(new Divisao());
		contas.add(new Multiplicacao());

		Random rd = new Random();

		for(int i=0; i<5;i++){
			for(int j=0;j<4;j++){
				int valor1, valor2;
				do{


					valor1 = rd.nextInt(Valores.DUAS_CASA.getValor())+1;
					valor2 = rd.nextInt(j<2? Valores.DUAS_CASA.getValor() : Valores.UMA_CASA.getValor())+1;

				}while(valor1 < valor2);

				contas.get(j).setValor(valor1);
				contas.get(j).setValor2(valor2);
				contas.get(j).monta();
				System.out.print("\t\t");

			}
			for(int j=0;j<5;j++)
				System.out.println("\n");
		}

	}

}
