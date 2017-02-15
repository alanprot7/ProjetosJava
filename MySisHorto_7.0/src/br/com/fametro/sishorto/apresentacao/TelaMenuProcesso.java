package br.com.fametro.sishorto.apresentacao;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import br.com.fametro.sishorto.entidade.Canteiro;
import br.com.fametro.sishorto.entidade.Usuario;
import br.com.fametro.sishorto.negocio.Servico;


public class TelaMenuProcesso {
	
	Scanner scan = new Scanner(System.in);
	Servico servico = new Servico();
	Usuario usuario = new Usuario();
	Canteiro canteiro = new Canteiro();
	Map<String, String> mapaIrrigacao = new TreeMap<>();
	
	
	public void menuPrincipal() {

		System.out.println("\n********************************************");
		System.out.println("Sistema de Controle e Monitoramento de Horto");
		System.out.println("\t\tPRINCIPAL");
		System.out.println("********************************************");
		System.out.println("1 - Canteiros");
		System.out.println("2 - Plantas");
		System.out.println("3 - Irrigacao");
		System.out.println("4 - Usuario");
		System.out.println("5 - Sair");
		
		
	}
	
	public void menuEntidade(String menuNome) {

		System.out.println("\n********************************************");
		System.out.println("Sistema de Controle e Monitoramento de Horto");
		System.out.println("\t\t"+menuNome);
		System.out.println("********************************************");
		System.out.println("1 - Adicionar");
		System.out.println("2 - Excluir");
		System.out.println("3 - Editar");
		System.out.println("4 - Obter por ID");
		System.out.println("5 - Obter Todos");
		System.out.println("6 - Voltar Menu Principal");
		
		
	}

	
	public void menuIrrigacao() {

		System.out.println("\n********************************************");
		System.out.println("Sistema de Controle e Monitoramento de Horto");
		System.out.println("\t\tIRRIGACAO");
		System.out.println("********************************************");
		System.out.println("1 - Vincular Canteiro/Planta");
		System.out.println("2 - Desvincular Canteiro/Planta");
		System.out.println("3 - Exibir Vinculos Canteiro/Planta");
		System.out.println("4 - Agendar Irrigacao");
		System.out.println("5 - Editar Agendamentos");
		System.out.println("6 - Consultar Agendamento");
		System.out.println("7 - Listar Irrigados");
		System.out.println("8 - Listar Impossibilidades");
		System.out.println("9 - Voltar Menu Principal");
		
		
	}
	
	
	public void menuOpPrincipal(int opcao) {
		
		switch (opcao) {
			
		case 1: menuEntidade("CANTEIRO"); menuOpCanteiro(scan.nextInt()); break;
		case 2: menuEntidade("PLANTA"); menuOpPlanta(scan.nextInt()); break;
		case 3: menuIrrigacao(); menuOpIrrigacao(scan.nextInt()); break;
		case 4: menuEntidade("USUARIO"); menuOpUsuario(scan.nextInt()); break;
		case 5: System.out.println("Até Logo!"); break;
		default: rodaMenu();break; 
		
		}
					
	}
	
	public void menuOpIrrigacao(int opcao) {
		
		switch (opcao) {
			
		case 1: servico.vincularCanteiroPlanta(canteiro, mapaIrrigacao); menuIrrigacao(); menuOpIrrigacao(scan.nextInt()); break;
		case 2: servico.desvincularCanteiroPlanta(canteiro, mapaIrrigacao);menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		case 3: servico.visualizarTodos(mapaIrrigacao); menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		case 4: menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		case 5: menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		case 6: menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		case 7: menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		case 8: menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		case 9: rodaMenu();break;
		default: menuIrrigacao(); menuOpIrrigacao(scan.nextInt());break;
		
		}
	}
	
	
	public void menuOpUsuario(int opcao) {
		
		switch (opcao) {
		
		case 1: servico.cadastraUsuario(usuario); menuEntidade("USUARIO"); menuOpUsuario(scan.nextInt());break;
		case 2: servico.excluirUsuario(usuario); menuEntidade("USUARIO"); menuOpUsuario(scan.nextInt());break;
		case 3: servico.editarUsuario(usuario); menuEntidade("USUARIO"); menuOpUsuario(scan.nextInt());break;
		case 4: servico.obterUsuarioPorId(usuario); menuEntidade("USUARIO"); menuOpUsuario(scan.nextInt());break;
		case 5: servico.oberTodosUsuario(usuario); menuEntidade("USUARIO"); menuOpUsuario(scan.nextInt());break;
		case 6: rodaMenu();break;
		default: menuEntidade("USUARIO"); menuOpUsuario(scan.nextInt());break;

		}
	}
	
	public void menuOpCanteiro(int opcao) {
		
		switch (opcao) {
		
		case 1: servico.cadastraCanteiro(canteiro); menuEntidade("CANTEIRO"); menuOpCanteiro(scan.nextInt());break;
		case 2: servico.excluirCanteiro(canteiro,mapaIrrigacao); menuEntidade("CANTEIRO"); menuOpCanteiro(scan.nextInt());break;
		case 3: servico.editarCanteiro(canteiro,mapaIrrigacao); menuEntidade("CANTEIRO"); menuOpCanteiro(scan.nextInt());break;
		case 4: servico.obterCanteiroPorId(canteiro); menuEntidade("CANTEIRO"); menuOpCanteiro(scan.nextInt());break;
		case 5: servico.oberTodosCanteiro(canteiro); menuEntidade("CANTEIRO"); menuOpCanteiro(scan.nextInt());break;
		case 6:rodaMenu();break;
		default: menuEntidade("CANTEIRO"); menuOpCanteiro(scan.nextInt());break;

		}
	}

	public void menuOpPlanta(int opcao) {
		
		switch (opcao) {
		
		case 1: servico.cadastraPlanta(canteiro); menuEntidade("PLANTA"); menuOpPlanta(scan.nextInt());break;
		case 2: servico.excluirPlanta(canteiro,mapaIrrigacao); menuEntidade("PLANTA"); menuOpPlanta(scan.nextInt());break;
		case 3: servico.editarPlanta(canteiro, mapaIrrigacao); menuEntidade("PLANTA"); menuOpPlanta(scan.nextInt());break;
		case 4: servico.obterPlantaPorId(canteiro); menuEntidade("PLANTA"); menuOpPlanta(scan.nextInt());break;
		case 5: servico.oberTodosPlanta(canteiro); menuEntidade("PLANTA"); menuOpPlanta(scan.nextInt());break;
		case 6: rodaMenu();break;
		default: menuEntidade("PLANTA"); menuOpPlanta(scan.nextInt());break;

		}
	}

	public void rodaMenu() {
		
		try {
		
		menuPrincipal();
		menuOpPrincipal(scan.nextInt());
		
		} catch (InputMismatchException e) {
			System.err.println("Utilize somente numeros inteiros para os\nMenus principais e funcoes que solicitem IDs");
			scan.nextLine();
			rodaMenu();
		} 

			
	}
		
	
}
	