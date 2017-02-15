package ap2;

public class Q03 {

	public static void main(String[] args) {

		Q02 q2 = new Q02();
		int a,x,n;
		long result;

		System.out.println("Dado a formula R = a + (x^1 * a^1) + (x^2 * a^2) + ... (x^n * a^n)");
		
		System.out.println("Entre com o valor de a");
		a = q2.leia();
		System.out.println("Entre com o valor de x");
		x = q2.leia();
		System.out.println("Entre com o valor de n");
		n = q2.leia();
		
		result = a;
		
		for(int i=1; i<=n ;i++){
			result+= (q2.potencia(x, i) * q2.potencia(a, i));
		}
		
		System.out.println("R = "+result);

	}

}
