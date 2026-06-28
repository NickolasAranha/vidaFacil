package vidafacil.services;

import vidafacil.database.ConexaoBanco;
import vidafacil.model.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {
    public MedicamentoDAO() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS Medicamentos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_user INTEGER NOT NULL, " +
                "nome TEXT NOT NULL, " +
                "dosagem INTEGER NOT NULL, " +
                "unidade TEXT NOT NULL, " +
                "intervalo_aviso INTEGER NOT NULL, " +
                "data_inicio TEXT NOT NULL, " +
                "data_fim TEXT, " + // Pode ser nulo devido ao uso contínuo
                "ultima_dose TEXT NOT NULL" +
                ");";

        try (Connection conn = ConexaoBanco.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela Medicamentos: " + e.getMessage());
        }
    }

    public void salvar(Medicamento medicamento) {
        String sql = "INSERT INTO Medicamentos (id_user, nome, dosagem, unidade, intervalo_aviso, data_inicio, data_fim, ultima_dose) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, medicamento.getId_user());
            pstmt.setString(2, medicamento.getNome());
            pstmt.setInt(3, medicamento.getDosagem());

            pstmt.setString(4, medicamento.getUnidade().name());

            pstmt.setInt(5, medicamento.getIntervalo_aviso());

            pstmt.setString(6, medicamento.getDataInicio().toString());

            if (medicamento.getDataFim() != null) {
                pstmt.setString(7, medicamento.getDataFim().toString());
            } else {
                pstmt.setNull(7, java.sql.Types.VARCHAR);
            }
            pstmt.setString(8, medicamento.getUltima_Dose().toString());
            pstmt.executeUpdate();
            System.out.println("Sucesso! O medicamento '" + medicamento.getNome() + "' foi salvo no banco.");

        } catch (Exception e) {
            System.out.println("Erro ao salvar o medicamento: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar no banco de dados.");
        }
    }

    public List<Medicamento> buscarPorUsuario(int idUser) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamentos WHERE id_user = ?";

        try (Connection conn = vidafacil.database.ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int dosagem = rs.getInt("dosagem");
                vidafacil.model.Unidade unidade = vidafacil.model.Unidade.valueOf(rs.getString("unidade"));
                int intervalo = rs.getInt("intervalo_aviso");

                LocalDate inicio = LocalDate.parse(rs.getString("data_inicio"));

                String dataFimTexto = rs.getString("data_fim");
                LocalDate fim = (dataFimTexto != null) ? LocalDate.parse(dataFimTexto) : null;

                LocalDateTime ultimaDose = LocalDateTime.parse(rs.getString("ultima_dose"));

                Medicamento med = new Medicamento(idUser, nome, dosagem, unidade, intervalo, inicio, fim, ultimaDose);
                med.setId(id); // Guarda o ID verdadeiro do banco!

                lista.add(med);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar medicamentos: " + e.getMessage());
        }
        return lista;
    }

    public List<Medicamento> buscarMedicamentosAtivos(int idUser) {
        List<Medicamento> ativos = new ArrayList<>();
        String sql = "SELECT * FROM Medicamentos WHERE id_user = ?";
        LocalDate hoje = LocalDate.now();

        try (Connection conn = vidafacil.database.ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocalDate inicio = LocalDate.parse(rs.getString("data_inicio"));
                String fimTexto = rs.getString("data_fim");
                LocalDate fim = (fimTexto != null) ? LocalDate.parse(fimTexto) : null;

                boolean jaIniciou = !hoje.isBefore(inicio);
                boolean naoTerminou = (fim == null) || !hoje.isAfter(fim);

                if (jaIniciou && naoTerminou) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    int dosagem = rs.getInt("dosagem");
                    vidafacil.model.Unidade unidade = vidafacil.model.Unidade.valueOf(rs.getString("unidade"));
                    int intervalo = rs.getInt("intervalo_aviso");
                    LocalDateTime ultimaDose = LocalDateTime.parse(rs.getString("ultima_dose"));
                    Medicamento med = new Medicamento(idUser, nome, dosagem, unidade, intervalo, inicio, fim, ultimaDose);
                    med.setId(id);
                    ativos.add(med);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar medicamentos ativos: " + e.getMessage());
        }
        return ativos;
    }

    public void atualizarUltimaDose(Medicamento medicamento) {
        String sql = "UPDATE Medicamentos SET ultima_dose = ? WHERE id = ?";

        try (Connection conn = vidafacil.database.ConexaoBanco.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, medicamento.getUltima_Dose().toString());
            pstmt.setInt(2, medicamento.getId());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o horário da última dose: " + e.getMessage());
        }
    }
}