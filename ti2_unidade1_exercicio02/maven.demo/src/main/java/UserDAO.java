import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO 
{	
	// Construtor
	public UserDAO () 
	{
		super();
		conectar();
	}

	// Terminar conexao
	public void finalize () 
	{
		close();
	}
	
	// Adicionar usuario
	public boolean insertUser (User user)
	{
		boolean status = false;
		try
		{
			Statement st = conexao.createStatement();
			String sql = ("INSERT INTO tableraq (ID, Idade, Nome, Comida)" 
							+ " VALUES ("+ user.getID() + ", " 
									     + user.getAge() + ", '"
									     + user.getName() + "', '"
									     + user.getFood() + "');");
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		}
		catch(SQLException u)
		{
			throw new RuntimeException(u);
		}
		return (status);
	}
	
	// Retornar usuario [n]
	public User get (int ID) 
	{
		User User = null;
		
		try 
		{
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM tableraq WHERE id=" + ID;
			
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
	        if(rs.next())
	        {            
	        	User = new User(rs.getInt("ID"), rs.getInt("Idade"), rs.getString("Nome"), rs.getString("Comida"));
	        }
	        st.close();
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
		return User;
	}
	
	// Listas
	public List<User> get() 
	{
		return get("");
	}
	
	public List<User> getOrderByID() 
	{
		return get("ID");
	}
	
	public List<User> getOrderByLogin() 
	{
		return get("Idade");
	}
	
	public List<User> getOrderByAge() 
	{
		return get("Nome");		
	}
	
	private List<User> get(String orderBy) 
	{	
		List<User> Users = new ArrayList<User>();
		
		try 
		{
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM tableraq" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
	        while(rs.next()) 
	        {	            	
	        	User u = new User(rs.getInt("ID"), rs.getInt("Idade"), rs.getString("Nome"), rs.getString("Comida"));
	            Users.add(u);
	        }
	        st.close();
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
		return Users;
	}
	
	// Atualizar usuario
	public boolean update (User User) 
	{
		boolean status = false;
		try 
		{  
			Statement st = conexao.createStatement();
			String sql = "UPDATE tableraq SET Nome = '" 
							+ User.getName() + "', Idade  = '"  
							+ User.getAge() + "', Comida = '" 
							+ User.getFood() + "'" + " WHERE ID = " 
							+ User.getID();
			
			System.out.println(sql);
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} 
		catch (SQLException u) 
		{  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	// Deletar usuario
	public boolean delete (int ID) 
	{
		boolean status = false;
		try 
		{  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM tableraq WHERE ID = " + ID;
			
			System.out.println(sql);
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} 
		catch (SQLException u) 
		{  
			throw new RuntimeException(u);
		}
		return status;
	}
}
