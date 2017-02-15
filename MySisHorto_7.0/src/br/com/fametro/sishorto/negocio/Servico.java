package br.com.fametro.sishorto.negocio;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import br.com.fametro.sishorto.entidade.Canteiro;
import br.com.fametro.sishorto.entidade.Usuario;

public class Servico {

	Scanner scan;

	//Metodos Usuario=========================================================================

	public void cadastraUsuario(Usuario usuario) {

		boolean existe;

		scan = new Scanner(System.in);

		do {
			System.out.println("Insira o Nome do Usuario");
			String nome = scan.nextLine();
			existe = false;
			if(usuario.getNome().contains(nome)){
				existe = true;
				System.out.println("Nome ja Cadastrado");
			}else {
				usuario.getNome().add(nome);
				System.out.println("Nome Cadastrado");
			}

		} while (existe);


		do {
			System.out.println("Insira a Matricula do Usuario");
			String nome = scan.nextLine();
			existe = false;
			if(usuario.getMatricula().contains(nome)){
				existe = true;
				System.out.println("Matricula ja Cadastrada");
			}else {
				usuario.getMatricula().add(nome);
				System.out.println("Matricula Cadastrada");
			}

		} while (existe);

		System.out.println("Insira a Funcao do Usuario");
		usuario.getFuncao().add(scan.nextLine());
		System.out.println("\n<<<Concluido>>>");

	}

	public void oberTodosUsuario(Usuario usuario) {
		int cont=0;
		System.out.println("Lista de Usuarios (Todos)\n");

		for(String get : usuario.getNome()){
			int pos = usuario.getNome().indexOf(get);
			System.out.println("ID: "+(++cont)+"\nNOME:\t"+get+"\nMATRI:\t"+usuario.getMatricula().get(pos)+"\nFUNCAO:\t"+usuario.getFuncao().get(pos)+"\n\n");
		}
		System.out.println("<<<Concluido>>>");
	}

	public void editarUsuario(Usuario usuario) {

		boolean existe;
		scan = new Scanner(System.in);
		int pos=0; 
		System.out.println("Insira o ID a Editar");
		pos = scan.nextInt();
		if(pos>0 && pos<=usuario.getNome().size()) {

			System.out.println("\nNOME:\t"+usuario.getNome().get(pos-1)+"\nMATRI:\t"+usuario.getMatricula().get(pos-1)+"\nFUNCAO:\t"+usuario.getFuncao().get(pos-1)+"\n\n");
			scan.nextLine();



			do{
				existe = false;

				System.out.println("Insira o Novo Nome");
				String nome = scan.nextLine();

				if(usuario.getNome().contains(nome) && !nome.equals(usuario.getNome().get(pos-1))){
					System.out.println("Nome ja existe");
					existe = true;
				}else {
					usuario.getNome().set(pos-1,nome);
					System.out.println("Nome editado");
				}

			}while(existe);


			do{
				existe = false;

				System.out.println("Insira o Nova Matricula");
				String nome = scan.nextLine();
				if(usuario.getMatricula().contains(nome) && !nome.equals(usuario.getMatricula().get(pos-1))){
					System.out.println("Matricula ja existe");
					existe = true;
				}else {
					usuario.getMatricula().set(pos-1,nome);
					System.out.println("Matricula editada");
				}

			}while(existe);


			System.out.println("Insira o Nova Funcao");
			usuario.getFuncao().set(pos-1,scan.nextLine());

			System.out.println("\n<<<Concluido>>>");
		}
		else
			System.out.println("ID não encontrado");
	}

	public void excluirUsuario(Usuario usuario) {
		scan = new Scanner(System.in);
		int pos=0; 
		System.out.println("Insira o ID a Excluir");
		pos= scan.nextInt();
		if(pos>0 && pos<=usuario.getNome().size()) {
			usuario.getNome().remove(pos-1);
			usuario.getMatricula().remove(pos-1);
			usuario.getFuncao().remove(pos-1);
			System.out.println("\n<<<Concluido>>>");
		}else
			System.out.println("ID não encontrado");
	}

	public void obterUsuarioPorId(Usuario usuario) {
		scan = new Scanner(System.in);
		int pos=0;
		System.out.println("Insira o ID");
		pos = scan.nextInt();
		if(pos>0 && pos<=usuario.getNome().size()) {
			System.out.println("\nNOME:\t"+usuario.getNome().get(pos-1)+"\nMATRI:\t"+usuario.getMatricula().get(pos-1)+"\nFUNCAO:\t"+usuario.getFuncao().get(pos-1)+"\n\n");
			System.out.println("<<<Concluido>>>");
		}else
			System.out.println("Digite um ID Válido");
	}

	//FIM Usuario******************************************************************************


	//Metodos Canteiro========================================================================

	public void cadastraCanteiro(Canteiro canteiro) {
		scan = new Scanner(System.in);
		System.out.println("Insira a Identificação do Canteiro");
		String nome = scan.nextLine();
		if(canteiro.getIdentificacao().contains(nome)){
			System.out.println("Identificacao ja Cadastrada");	
		}else {
			canteiro.getIdentificacao().add(nome);
			System.out.println("\n<<<Concluido>>>");
		}
	}

	public void oberTodosCanteiro(Canteiro canteiro) {
		int cont=0;
		System.out.println("Lista de Canteiros (Todos)");
		for(String get : canteiro.getIdentificacao())
			System.out.println(++cont+"-"+get);
		System.out.println("\n<<<Concluido>>>");
	}

	public void editarCanteiro(Canteiro canteiro, Map<String, String> mapa) {
		boolean existe;
		scan = new Scanner(System.in);
		int pos=0;
		System.out.println("Insira a ID do Canteiro a Editar");
		pos = scan.nextInt();
		scan.nextLine();
		
		if(pos>0 && pos<=canteiro.getIdentificacao().size()) {
			String nomeCanteiro = canteiro.getIdentificacao().get(pos-1);
			if(mapa.containsKey(nomeCanteiro)){
				System.out.println("Impossivel editar, primeiro desvincule Canteiro/Planta");
			} else{
				do{
					existe = false;
					System.out.println("Insira a nova Identificação do Canteiro");
					String nome = scan.nextLine();
					if(canteiro.getIdentificacao().contains(nome) && !nome.equals(canteiro.getIdentificacao().get(pos-1))){
						System.out.println("Ja existe Identificacao Cadastrada");
						existe = true;
					}else{
						canteiro.getIdentificacao().set(pos-1,nome);
						System.out.println("\n<<<Concluido>>>");	
					}

				}while(existe);
			}
		}
		else
			System.out.println("ID não encontrada");
	}

	public void excluirCanteiro(Canteiro canteiro, Map<String, String> mapa) {
		scan = new Scanner(System.in);
		int pos=0;
		System.out.println("Insira a ID do Canteiro a Excluir");
		pos = scan.nextInt();
		if(pos>0 && pos<=canteiro.getIdentificacao().size()) {
			if(mapa.containsKey(canteiro.getIdentificacao().get(pos-1))){
				System.out.println("Impossivel Excluir, existe vinculo estabelecido\nprimeiro desvincule a Planta do Canteiro");
			}else{
				canteiro.getIdentificacao().remove(pos-1);
				System.out.println("\n<<<Concluido>>>");
			}
		}else
			System.out.println("ID não encontrada");
	}

	public void obterCanteiroPorId(Canteiro canteiro) {
		scan = new Scanner(System.in);
		int pos=0;
		System.out.println("Insira o ID");
		pos = scan.nextInt();
		if(pos>0 && pos<=canteiro.getIdentificacao().size()) {
			System.out.println(canteiro.getIdentificacao().get(pos-1));
			System.out.println("\n<<<Concluido>>>");
		}else
			System.out.println("ID não encontrada");
	}

	//FIM Canteiro******************************************************************************


	//Metodos Planta==========================================================================

	public void cadastraPlanta(Canteiro canteiro) {
		scan = new Scanner(System.in);
		System.out.println("Insira a Espécie da Planta");
		String nome = scan.nextLine();
		if(canteiro.getPlanta().getEspecie().contains(nome)){
			System.out.println("Esta Especie ja Cadastrada");
		}else {
			canteiro.getPlanta().getEspecie().add(nome);
			System.out.println("\n<<<Concluido>>>");
		}
	}


	public void oberTodosPlanta(Canteiro canteiro) {
		int cont=0;
		System.out.println("Lista de Plantas (Todos)");
		for(String get : canteiro.getPlanta().getEspecie())
			System.out.println(++cont+"-"+get);
		System.out.println("\n<<<Concluido>>>");
	}


	public void editarPlanta(Canteiro canteiro, Map<String, String> mapa) {
		boolean existe;
		scan = new Scanner(System.in);
		int pos=0;
		System.out.println("Insira o ID da Planta a Editar");
		pos = scan.nextInt();
		scan.nextLine();
		
		if(pos>0 && pos<= canteiro.getPlanta().getEspecie().size()) {
			String nomePlanta = canteiro.getPlanta().getEspecie().get(pos-1);
			if(mapa.containsValue(nomePlanta)){
				System.out.println("Impossivel editar, primeiro desvincule Canteiro/Planta");
			} else{
				do{
					existe = false;
					System.out.println("Insira a nova Espécie de Planta");
					String nome = scan.nextLine();
					if(canteiro.getPlanta().getEspecie().contains(nome) && !nome.equals(canteiro.getPlanta().getEspecie().get(pos-1))){
						System.out.println("Ja existe Especie Cadastrada");
						existe = true;
					} else {
						canteiro.getPlanta().getEspecie().set(pos-1,nome);
						System.out.println("\n<<<Concluido>>>");
					}
				}while(existe);
			}
			
			
		}else
			System.out.println("ID não encontrado");
	}


	public void excluirPlanta(Canteiro canteiro, Map <String, String> mapa) {
		scan = new Scanner(System.in);
		int pos=0;
		System.out.println("Insira o ID da Planta a Excluir");
		pos = scan.nextInt();
		if(pos>0 && pos<= canteiro.getPlanta().getEspecie().size()) {
			if(mapa.containsValue(canteiro.getPlanta().getEspecie().get(pos-1))){
				System.out.println("Impossivel Excluir, existe vinculo estabelecido\nprimeiro desvincule a Planta do Canteiro");
			}
			else{
				canteiro.getPlanta().getEspecie().remove(pos-1);
				System.out.println("\n<<<Concluido>>>");
			}
		}else
			System.out.println("ID não encontrado");
	}


	public void obterPlantaPorId(Canteiro canteiro) {
		scan = new Scanner(System.in);
		int pos=0;
		System.out.println("Insira o ID");
		pos = scan.nextInt();
		if(pos>0 && pos<=canteiro.getPlanta().getEspecie().size()) {
			System.out.println(canteiro.getPlanta().getEspecie().get(pos-1));
			System.out.println("\n<<<Concluido>>>");
		}else
			System.out.println("Digite um ID Válido");
	}

	//FIM Planta******************************************************************************


	//Metodos Irrigacao==========================================================================


	public void vincularCanteiroPlanta(Canteiro canteiro, Map<String , String> mapa){

		int cont = 0 , posCanteiro = 0 , posPlanta = 0 ;

		scan = new Scanner(System.in);
		System.out.println("Lista de Canteiros\n");

		for(String get : canteiro.getIdentificacao()){
			++cont;
			System.out.println(mapa.containsKey(get)?"X-"+get:cont+"-"+get);
		}
		System.out.println();

		System.out.println("Lista de Plantas\n");

		cont = 0;

		for(String get : canteiro.getPlanta().getEspecie()){
			++cont;
			System.out.println(mapa.containsValue(get)?"X-"+get:cont+"-"+get);
		}

		System.out.println("\nEscolha o Canteiro e a Planta a serem vinculados\nsendo os marcados com \"X\" os que ja estao vinculados\n");

		System.out.print("ID Canteiro: ");

		posCanteiro = scan.nextInt();

		System.out.print("ID Planta: ");

		posPlanta = scan.nextInt();
		System.out.println();

		if(posCanteiro>0 && posCanteiro<= canteiro.getIdentificacao().size() &&
				posPlanta>0 && posPlanta<= canteiro.getPlanta().getEspecie().size()) {

			String nomeCanteiro = canteiro.getIdentificacao().get(posCanteiro-1),
					nomePlanta = canteiro.getPlanta().getEspecie().get(posPlanta-1);

			if(mapa.containsKey(nomeCanteiro)) {
				System.out.println("O Canteiro \""+nomeCanteiro+"\" ja esta vinculado a Planta \""+mapa.get(nomeCanteiro)+"\"");
			} else
				mapa.put(nomeCanteiro, nomePlanta);
			System.out.println("\n<<<Concluido>>>");
		} else {
			System.out.println("ID nao Encontrada");
		}

	} 

	public void desvincularCanteiroPlanta(Canteiro canteiro, Map<String , String> mapa){

		int cont = 0 , posCanteiro = 0;

		scan = new Scanner(System.in);
		System.out.println("Lista de Canteiros\n");

		for(String get : canteiro.getIdentificacao()){
			++cont;
			System.out.println(mapa.containsKey(get)?"X-"+get:cont+"-"+get);
		}
		System.out.println();

		System.out.println("Lista de Plantas\n");

		cont = 0;

		for(String get : canteiro.getPlanta().getEspecie()){
			++cont;
			System.out.println(mapa.containsValue(get)?"X-"+get:cont+"-"+get);
		}

		System.out.println("\nEscolha o Canteiro e a Planta a serem desvinculados\nsendo os marcados com \"X\" os que ja estao vinculados\n");

		System.out.print("ID Canteiro: ");

		posCanteiro = scan.nextInt();

		if(posCanteiro>0 && posCanteiro<= canteiro.getIdentificacao().size()){

			String nomeCanteiro = canteiro.getIdentificacao().get(posCanteiro-1);
			mapa.remove(nomeCanteiro);
		} else{
			System.out.println("ID não Encontrado");
		}

	}
	
	public void visualizarTodos(Map<String, String> mapa){
		
		Set<String> chaves = mapa.keySet();
		
		System.out.println("Lista de Vinculos");
		System.out.println("Canteiro = Planta");
		
		for(String chave : chaves){
			System.out.println(chave+" = "+mapa.get(chave));
			
		}
		
	}

	//FIM Irrigacao******************************************************************************

} 
