package src.entidade;


import java.util.ArrayList;

public class Aluno implements Comparable<Object> {
	private String nome;
	private int idade;

	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();

	public String getNome() {
		return this.nome;
	}

	public void setNome(String n) {
		this.nome = n;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int i) {
		this.idade = i;
	}

	public void adicionaAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}

	public void contaitsAlunos(Aluno aluno) {
		this.alunos.contains(aluno);

	}

	public String listaAluno() {
		String lista = "";

		for (int i = 0; i < this.alunos.size(); i++) {
			Aluno alun = this.alunos.get(i);
			lista += "nome = " + alun.getNome() + "idade + " + alun.getIdade();
		}
		return lista;
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

}
