// Raquel de Parde Motta 854017

import java.sql.*;
import java.math.*;
import java.security.*;

public class DAO 
{
	protected Connection conexao;
	
	// Construtor
	public DAO() 
	{
		conexao = null;
	}
	
	// Fazer conexao
	public boolean conectar() 
	{	
		// Parametros da conexao
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "postgres";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "raquelpsql";
		String password = "senha";
		
		boolean status = false; 	// status de retorno

		try 
		{
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} 
		catch (ClassNotFoundException e) 
		{ 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} 
		catch (SQLException e) 
		{
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}
		return status;
	}

	public boolean close() 
	{
		boolean status = false;
		
		try 
		{
			conexao.close();
			status = true;
		} 
		catch (SQLException e) 
		{
			System.err.println(e.getMessage());
		}
		return status;
	}
}
