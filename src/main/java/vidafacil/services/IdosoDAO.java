package vidafacil.services;

import vidafacil.model.Idoso;
import java.sql.*;

public class IdosoDAO {
    public void cadastrarIdoso(Idoso idoso) {
        try (Connection conn = vidafacil.database.ConexaoBanco.conectar();
             Statement stmt = conn.createStatement()) {
            String criarTabelaSQL =
                    "CREATE TABLE IF NOT EXISTS Idoso (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL," +
                    "senha TEXT NOT NULL, " +
                    "email TEXT NOT NULL, " +
                    "cpf TEXT NOT NULL, " +
                    "data_nascimento TEXT NOT NULL," +
                    "codigo_conexao TEXT NOT NULL);";
            stmt.execute(criarTabelaSQL);

            String inserirSQL = "INSERT INTO Idoso (Nome, Senha, Email, CPF, Data_Nascimento, Codigo_Conexao) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(inserirSQL)) {
                pstmt.setString(1, idoso.getNome());
                pstmt.setString(2, idoso.getSenha());
                pstmt.setString(3, idoso.getEmail());
                pstmt.setString(4, idoso.getCpf());
                java.sql.Date dataSQL = java.sql.Date.valueOf(idoso.getDataNascimento());
                pstmt.setDate(5,dataSQL);
                pstmt.setString(6, idoso.getCodigoConexao());
                pstmt.executeUpdate();
                System.out.println("Sucesso! '" + idoso.getNome() + "' foi salvo no SQLite.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Idoso buscarLogin(String email) {
        String sql = "SELECT * FROM Idoso WHERE email = ?";
        try (Connection conn = vidafacil.database.ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String senhaHash = rs.getString("senha"); // Essa é a senha criptografada
                String emailBanco = rs.getString("email");
                String cpf = rs.getString("cpf");
                String dataString = rs.getString("data_nascimento");
                java.time.LocalDate dataNasc;
                if (dataString.matches("\\d+")) {
                    long tempo = Long.parseLong(dataString);
                    dataNasc = new java.sql.Date(tempo).toLocalDate();
                } else {
                    dataNasc = java.time.LocalDate.parse(dataString);
                }
                return new Idoso(nome, senhaHash, emailBanco, cpf, dataNasc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }
}