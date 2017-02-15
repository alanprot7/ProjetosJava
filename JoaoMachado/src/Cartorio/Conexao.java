package Cartorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexao {
	
	public static void main(String[] args) {
		
		
		try{
			
		
		Class.forName("org.postgresql.Driver");
		Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost/Cartorio","postgres","root");
		
		Statement instrucao = conexao.createStatement();
		ResultSet resul = instrucao.executeQuery("select docu from ordens where nome like '%TECNOMECANICA%'");
		
		while(resul.next()){
			System.out.println(resul.getString("docu"));
		}
		
		
		} catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "ERRO CLASSE: "+e.getMessage());
		} catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO SQL: "+e.getMessage());
		}finally{
			
		}
		
		
		
		
	}

}
