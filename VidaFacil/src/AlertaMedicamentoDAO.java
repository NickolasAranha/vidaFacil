import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AlertaMedicamentoDAO {
	BD banco = new BD();
	 
	 public void criarAlertaPendente(int idMedicamento) throws Exception {
		 String sql = "INSERT INTO Alerta_Medicamento (ID_Medicamento, Data_Hora, Status) VALUES (?, NOW(), 'Pendente')";
		 try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idMedicamento);
	            stmt.execute();
		 } catch (SQLException e ) {
	        	if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
		 }
	 }
		 
	 
	 public void atualizarStatusConfirmado(int idMedicamento) throws Exception {
	        String sql = "UPDATE Alerta_Medicamento SET Status = 'Confirmado' WHERE ID_Medicamento = ? AND Status = 'Pendente'";
	        try (Connection conn = banco.conectar(); 
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idMedicamento);
	            stmt.executeUpdate();
	        } catch (SQLException e ) {
	            if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
	        }
	        atualizarUltimaDose(idMedicamento);
	 }
	 public void atualizarStatusFinalizado(int idMedicamento, String novoStatus) throws Exception {
	        String sql = "UPDATE Alerta_Medicamento SET Status = ? WHERE ID_Medicamento = ? AND Status = 'Pendente'";
	        try (Connection conn = banco.conectar(); 
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, novoStatus); 
	            stmt.setInt(2, idMedicamento);
	            stmt.executeUpdate();
	        } catch (SQLException e ) {
	            if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
	        }
	        atualizarUltimaDose(idMedicamento);
	    }
	 
	 public void atualizarUltimaDose(int idMedicamento) throws Exception {
	        // Atualiza a Ultima_Dose na tabela Medicamento
	        String sql = "UPDATE Medicamento SET Ultima_Dose = NOW() WHERE ID_Medicamento = ?";
	        try (Connection conn = banco.conectar(); 
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idMedicamento);
	            stmt.executeUpdate();
	        } catch (SQLException e ) {
	            if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
	        } 
	    }
	 
	   public List<AlertaMedicamento> buscarHistoricoAlertas(int idIdoso) throws Exception {
	        List<AlertaMedicamento> historico = new ArrayList<>();
	        String sql ="SELECT A.ID_Alerta, A.ID_Medicamento, A.Data_Hora, A.Status, M.Nome AS NomeRemedio " + "FROM Alerta_Medicamento A " + "JOIN Medicamento M ON A.ID_Medicamento = M.ID_Medicamento " + "WHERE M.ID_Idoso = ? " + "ORDER BY A.Data_Hora DESC";
	        try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idIdoso); 
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                	AlertaMedicamento alerta = new AlertaMedicamento(rs.getInt("ID_Alerta"),rs.getInt("ID_Medicamento"));
	                	alerta.setDataHora(rs.getObject("Data_Hora", LocalDateTime.class));
	                    String statusString = rs.getString("Status");
	                    alerta.setStatus(AlertaMedicamento.tipoStatus.valueOf(statusString));
	                    alerta.setNomeMedicamento(rs.getString("NomeRemedio"));
	                    historico.add(alerta);
	                }
	                
	            }} catch (SQLException e) {
		        	if (e.getErrorCode() == 0) { 
		                throw new Exception("Não foi possível conectar ao sistema.");
		            }
		            throw new Exception("Erro ao buscar histórico para o Idoso ID " + idIdoso + ": " + e.getMessage());
		       }
		   return historico;
	   }
}
