package ap2;

import java.util.Scanner;

public class Q02 {
	
	private Scanner input = new Scanner(System.in);
	private int a;
	private int b;

	public long potencia(int base, int expoente){
		long result = 1;

		for(int i=0;i<expoente;i++ ){
			result*= base;
		}

		return result;
	}
	
	public void setA(int a){
		this.a = a;
	}
	
	public void setB(int b){
		this.b = b;
	}
	
	public int getA(){
		return a;
	}
	
	public int getB(){
		return b;
	}

	public int leia(){
		
		int result = 0;
		result = input.nextInt();
		return result;
		
	}

	public static void main(String[] args) {


		Q02 q2 = new Q02();

		System.out.println("Insira os numeros");
		q2.a = q2.leia();
		q2.b = q2.leia();

		System.out.println(q2.potencia(q2.a,q2.b));

	}

}
