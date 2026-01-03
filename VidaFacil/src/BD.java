import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	
	public class BD {
	    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&useSSL=false";
	    private static final String USUARIO = "root";
	    private static final String SENHA = "kumo1964"; 

	    public Connection conectar() {
	        Connection conexao = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
	            System.out.println("Conexão realizada com sucesso!");
	        } catch (ClassNotFoundException e) {
	            System.out.println("Driver não encontrado: " + e.getMessage());
	        } catch (SQLException e) {
	            System.out.println("Erro ao conectar: " + e.getMessage());
	        }
	        return conexao;
	    }
	}


