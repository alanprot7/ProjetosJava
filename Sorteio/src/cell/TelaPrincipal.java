package cell;
import java.util.*;

public class TelaPrincipal {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		ArrayList<Integer> homemArr = new ArrayList<> ();
		ArrayList<Integer> sorteadoArr = new ArrayList<> ();

		Geral geral = new Geral();
		Homem homem = new Homem();
		Sorteado sorteado = new Sorteado();

		for(int sorte : sorteado.getSorteado())
			sorteadoArr.add(sorte);

		for(int man : homem.getHomem())
			homemArr.add(man);

		System.out.println("Homens? (s/n)");
		String man = scan.nextLine() ;

		int sorte; 


		for(int i=1;i<=1; ) {
			sorte = (int)(Math.random() *geral.getTodos().length);
			if(man.equals("s")) {
				if(!sorteadoArr.contains(sorte)&&homemArr.contains(sorte)) {
					System.out.println(sorte+" "+geral.getTodos()[sorte]);
					i++;
				}
			}else
				if(!sorteadoArr.contains(sorte)&&!homemArr.contains(sorte)) {
					System.out.println(sorte+" "+geral.getTodos()[sorte]);
					i++;
				}
			scan.close();

		}
	}
}