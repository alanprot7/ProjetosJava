package src.apresentacao;

import src.entidade.*;

import java.util.*;



public class Exibir {
	public static void main(String[]args)
	{
		final int maxPessoas = 5;
		final String prompt = "digite o nome das pessoas: ";
		
		Entrada entrada = new Entrada();
		String[] cadastro = new String[maxPessoas];
		String nome;
		
		int numPessoas=0;
		for(int i=0; i<maxPessoas;i++)
		{
			nome=entrada.lerLinha(prompt);
			cadastro[numPessoas++]=nome;
		}
		
		System.out.println();
		for(int i = 0;i<numPessoas;i++)
		{
			System.out.println(cadastro[i]);
		}
		
		String[]cadOrdenacao = new String[numPessoas];
		for (int i = 0; i< numPessoas;  i++)
		{
			cadOrdenacao[i] = cadastro[i];
		}
		
		Arrays.sort(cadOrdenacao);
		
		System.out.println();
		
		for (int i = 0; i < numPessoas; i++)
		{
			System.out.println(cadOrdenacao[i]);
			
		}
		
		
	} 
}
