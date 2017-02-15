package src.entidade;

import java.util.Scanner;

public class Entrada {
	
	private String nome;
	private Scanner scan;

	public String lerLinha(String prompt){
		
		scan = new Scanner(System.in);
		System.out.println(prompt);
		nome = scan.nextLine();
		return nome;
	}


}
