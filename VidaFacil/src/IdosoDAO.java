import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Para converter a data do Java para SQL

public class IdosoDAO {
	BD banco = new BD();
	
	   public IdosoDAO() {
	        this.banco = new BD(); // usa sua classe BD para conectar
	    }
	   
    public void cadastrarIdoso(Idoso idoso) throws Exception {
        String sql = "INSERT INTO Idoso (Nome, CPF, Email, Senha, Data_Nascimento, Codigo_Conexao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = banco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 4. Pega os dados do OBJETO e joga no SQL
            stmt.setString(1, idoso.getNome());
            stmt.setString(2, idoso.getCpf());
            stmt.setString(3, idoso.getEmail());
            stmt.setString(4, idoso.getSenha());
            // Conversão de LocalDate (Java) para Date (SQL)
            stmt.setDate(5, Date.valueOf(idoso.getDataNascimento()));
            stmt.setString(6, idoso.getCodigoConexao());

            // 5. Executa o comando no banco
            stmt.execute();
            String sqlBuscaId = "SELECT LAST_INSERT_ID()";
            int idGerado = 0;
            
            try (PreparedStatement stmtId = conn.prepareStatement(sqlBuscaId); ResultSet rs = stmtId.executeQuery()) {
                 if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
            
            if(idGerado != 0) {
            	HidratacaoDAO dao = new HidratacaoDAO();
            	dao.criarMetaHidratacaoPadrao(conn, idGerado);
            }
            System.out.println("Idoso cadastrado com sucesso!");
            
        } catch (SQLException e) {
        	if (e.getErrorCode() == 0) {
                throw new Exception("Não foi possível conectar ao sistema.");
            }
            if (e.getErrorCode() == 1062) {
            	if(e.getMessage().toLowerCase().contains("cpf")) {
            		throw new Exception("Este CPF já está cadastrado no Sistema");
            	} else if (e.getMessage().toLowerCase().contains("email")) {
            		throw new Exception("Este email já está em uso no Sistema");
            	} else {
            		throw new Exception("Registro duplicado");
            	}
            }
        }
    }
    
    public boolean verificarLogin(String email, String senhaDigitada) throws Exception {

        String sql = "SELECT * FROM idoso WHERE email = ?"; 

        try (Connection conn = banco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaBD = rs.getString("senha");
                    Idoso idosoLogado = new Idoso();
                    idosoLogado.setId(rs.getInt("Id_Idoso"));
                    idosoLogado.setNome(rs.getString("Nome"));
                    idosoLogado.setCodigoConexao(rs.getString("Codigo_Conexao"));
                    Session.idosoLogado = idosoLogado;
                    return senhaBD.equals(senhaDigitada);
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
        	if (e.getErrorCode() == 0) { 
                throw new Exception("Não foi possível conectar ao sistema.");
            }
            e.printStackTrace();
            throw new Exception("Erro ao tentar fazer login: " + e.getMessage());
        }
    }
}
	

