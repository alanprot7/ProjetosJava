package br.aso.cartorio.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexao {

	private	String docu, 
	nome, 
	obs; 

	String servidor = "localhost";

	public void query() {

		try{

			Class.forName("org.postgresql.Driver");
			Connection conexao = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/Cartorio","postgres","root");
			Statement instrucao = conexao.createStatement();

			ResultSet querySet = instrucao.executeQuery("SELECT * FROM ordens WHERE docu = '"+docu+"'");
			docu = null;
			while (querySet.next()){
				docu = querySet.getString("docu");
				nome = querySet.getString("nome");
				obs = querySet.getString("obs");

			}

			conexao.close();
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO SQL: "+e.getMessage());

		}

	}




	public void update(String nome, String obs) throws SQLException {

		Connection conexao = null;
		try{

			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/Cartorio","postgres","123");
			Statement instrucao = conexao.createStatement();

			instrucao.execute("UPDATE ordens SET nome = '"+nome.toUpperCase()+"' WHERE docu = '"+docu+"'");
			instrucao.execute("UPDATE ordens SET obs = '"+obs.toUpperCase()+"' WHERE docu = '"+docu+"'");

			
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		}finally{
			conexao.close();
		}

	}


	public void insert(String docu, String nome, String obs) throws SQLException {

		Connection conexao = null;
		
		try{

			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/Cartorio","postgres","123");
			Statement instrucao = conexao.createStatement();

			instrucao.execute("INSERT INTO ordens VALUES('"+docu+"','"+nome.toUpperCase()+"','"+obs.toUpperCase()+"')");
			JOptionPane.showMessageDialog(null, "REGISTRO INSERIDO");	
			
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		}finally{
			conexao.close();
		}

	}


	public void delete() {

		try{

			Class.forName("org.postgresql.Driver");
			Connection conexao = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/Cartorio","postgres","123");
			Statement instrucao = conexao.createStatement();

			instrucao.execute("DELETE FROM ordens WHERE docu = '"+docu+"'");
			JOptionPane.showMessageDialog(null, "REGISTRO EXCLUIDO");



			conexao.close();
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO SQL: "+e.getMessage());

		}

	}

	public String queryNome(String nome) {
		Connection conexao = null;
		StringBuilder pesquisa = new StringBuilder();
		int cont = 0;
		try{

			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/Cartorio","postgres","123");
			Statement instrucao = conexao.createStatement();

			ResultSet querySet = instrucao.executeQuery("SELECT * FROM ordens WHERE nome LIKE '%"+nome.toUpperCase()+"%'");

			while (querySet.next()){
				docu= querySet.getString("docu");
				this.nome= querySet.getString("nome");

				pesquisa.append(docu+" \t"+this.nome+"\n");
				cont++;
			}

			pesquisa.append("\nRegistros = "+cont);
			conexao.close();
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO SQL: "+e.getMessage());

		}catch(NullPointerException e){

		}finally{
			try{
			conexao.close();
			}catch(SQLException err){
				throw new RuntimeException(err);
			}
		}
		return pesquisa.toString(); 

	}

	public String queryObs(String nome) {
		Connection conexao = null;
		StringBuilder pesquisa = new StringBuilder();
		int cont = 0;
		try{

			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/Cartorio","postgres","123");
			Statement instrucao = conexao.createStatement();

			ResultSet querySet = instrucao.executeQuery("SELECT * FROM ordens WHERE obs LIKE '%"+nome.toUpperCase()+"%'");

			while (querySet.next()){
				docu= querySet.getString("docu");
				this.nome= querySet.getString("nome");

				pesquisa.append(docu+" \t"+this.nome+"\n");
				cont++;
			}

			pesquisa.append("\nRegistros = "+cont);
			conexao.close();
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO SQL: "+e.getMessage());

		}catch(NullPointerException e){

		}finally{
			try{
				conexao.close();
			}catch(SQLException err){
				throw new RuntimeException(err);
			}
		}
		return pesquisa.toString(); 

	}


	public String queryDoc() {
		StringBuilder pesquisa = new StringBuilder();
		try{

			Class.forName("org.postgresql.Driver");
			Connection conexao = DriverManager.getConnection("jdbc:postgresql://"+servidor+":5432/Cartorio","postgres","123");
			Statement instrucao = conexao.createStatement();
			
			int cont = 0;
			
			ResultSet querySet = instrucao.executeQuery("SELECT docu FROM ordens ");
			
			while (querySet.next()){
				docu= querySet.getString("docu").trim();

				pesquisa.append(docu+"\n");
				cont++;
			}
			
			JOptionPane.showMessageDialog(null, "Total = "+cont);
			conexao.close();
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO SQL: "+e.getMessage());

		}catch(NullPointerException e){

		}
		return pesquisa.toString(); 

	}




	public String getNome(){
		return this.nome;
	}

	public String getDocu(){
		return this.docu;
	}

	public String getObs(){
		return this.obs;
	}

	public void setTfDocu(String docu){
		this.docu = docu;
	}


	public String getSrv(){
		return servidor;
	}

	public void setSrv(String servidor){
		this.servidor = servidor;
	}

	public void setNull(){
		nome = null;
		docu = null;
		obs = null;

	}

}
