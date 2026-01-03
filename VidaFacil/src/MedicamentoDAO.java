import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {
	BD banco = new BD();
	
	   public MedicamentoDAO() {
	        this.banco = new BD(); 
	    }
	   
	   public void cadastrarMedicamento(Medicamento medicamento) throws Exception {
	        String sql = "INSERT INTO Medicamento (ID_Idoso, Nome, Dosagem, Unidade, Intervalo_Aviso, Data_Inicio, Data_Fim, Ultima_Dose) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        try (Connection conn = banco.conectar();
	        	PreparedStatement stmt = conn.prepareStatement(sql)) {
	        	stmt.setInt(1, medicamento.getId_idoso());
	        	stmt.setString(2, medicamento.getNome());
	        	stmt.setInt(3, medicamento.getDosagem());
	        	stmt.setString(4, medicamento.getUnidade().name());
	        	stmt.setInt(5, medicamento.getIntervalo_Aviso());
	            stmt.setDate(6, Date.valueOf(medicamento.getDataInicio()));
	            if (medicamento.getDataFim() != null) {
	                // Se a data existe, converte e manda o valor
	                stmt.setDate(7, Date.valueOf(medicamento.getDataFim()));
	            } else {
	                // Se a data é nula manda o tipo SQL NULL
	                stmt.setNull(7, java.sql.Types.DATE);
	            }	            
	            Timestamp ultimaDose =  Timestamp.valueOf(medicamento.getUltima_Dose());
	            stmt.setTimestamp(8, ultimaDose);
	            stmt.execute();
	        } catch (SQLException e) {
	        	if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
	        }
	   }
	   
	   public void atualizarMedicamento(Medicamento medicamento) throws Exception {
		   String sql = "UPDATE Medicamento SET Nome = ?, Dosagem = ?, Intervalo_Aviso = ?, Data_Fim = ? WHERE ID_Medicamento = ?";	   
		   try (Connection conn = banco.conectar();
		        	PreparedStatement stmt = conn.prepareStatement(sql)) {
			   		if (medicamento.getId() == 0) {
			   			throw new Exception("teste malando pra ver se o id n tá passando");
			   		}
			   		stmt.setInt(5, medicamento.getId());
		        	stmt.setString(1, medicamento.getNome());
		        	stmt.setInt(2, medicamento.getDosagem());
		        	stmt.setInt(3, medicamento.getIntervalo_Aviso());
		            if (medicamento.getDataFim() != null) {
		                stmt.setDate(4, Date.valueOf(medicamento.getDataFim()));
		            } else {
		                stmt.setNull(4, java.sql.Types.DATE);
		            }	           
		            stmt.execute();
		        	
		   } catch (SQLException e) {
			   if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
	            throw new Exception("Erro: " + e.getMessage());
	        }
	   }
	   
	   public void deletarMedicamento(int idMedicamento) throws Exception {
		    String sql = "DELETE FROM Medicamento WHERE ID_Medicamento = ?";
		    Connection conn = null;
		    PreparedStatement stmt = null;

		    try {
		        conn = banco.conectar();
		        conn.setAutoCommit(false);

		        deletarAlertas(conn, idMedicamento);

		        stmt = conn.prepareStatement(sql);
		        stmt.setInt(1, idMedicamento);
		        stmt.executeUpdate();

		        conn.commit();
		   } catch(SQLException e) {
			   if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
			   throw new Exception("Erro: " + e.getMessage());
		   }
	   }
	   
	   
	   private void deletarAlertas(Connection conn, int idMedicamento) throws SQLException {
		// TODO Auto-generated method stub
		   String sql = "DELETE FROM Alerta_Medicamento WHERE ID_Medicamento = ?";
		    
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, idMedicamento);
		        stmt.executeUpdate();
		    }
	}

	   public List<Medicamento> listarPorIdoso(int idIdoso) throws Exception {
	        List<Medicamento> lista = new ArrayList<>();
	        String sql = "SELECT * FROM Medicamento WHERE ID_Idoso = ?"; 
	        
	        try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idIdoso);             
	            try (ResultSet rs = stmt.executeQuery()) {
	                
	                while (rs.next()) {
	                    Medicamento m = new Medicamento(rs.getInt("ID_Medicamento"), rs.getInt("ID_Idoso"));
	                    m.setNome(rs.getString("Nome"));
	                    m.setDosagem(rs.getInt("Dosagem"));
	                    m.setIntervalo_Aviso(rs.getInt("Intervalo_Aviso"));
	                    
	                    
	                    if (rs.getDate("Data_Inicio") != null) {
	                        m.setDataInicio(rs.getDate("Data_Inicio").toLocalDate());
	                    }
	                    
	                    if (rs.getDate("Data_Fim") != null) {
	                        m.setDataFim(rs.getDate("Data_Fim").toLocalDate());
	                    } else {
	                        m.setDataFim(null); 
	                    }
	                    
	                    m.setUltima_Dose(rs.getTimestamp("Ultima_Dose").toLocalDateTime());
	                    
	                    String unidadeString = rs.getString("Unidade");
	                    m.setUnidade(Medicamento.tipoUnidade.valueOf(unidadeString));

	                    lista.add(m);
	                }
	            }

	        } catch (SQLException e) {
	        	if (e.getErrorCode() == 0) { 
	                throw new Exception("Não foi possível conectar ao sistema.");
	            }
	            throw new Exception("Erro ao listar medicamentos para o Idoso ID " + idIdoso + ": " + e.getMessage());
	        }
	        return lista;
	    }
	   
	   public Medicamento buscarPorId(int id) throws Exception {
	        String sql = "SELECT * FROM Medicamento WHERE ID_Medicamento = ?"; 
	        Medicamento m = null;
	        try (Connection conn = banco.conectar();
	        		PreparedStatement stmt = conn.prepareStatement(sql)) {
	        	stmt.setInt(1, id);
	        	try (ResultSet rs = stmt.executeQuery()) {
	        		if(rs.next()) {
	        		m = new Medicamento(id, rs.getInt("ID_Idoso"));
                    m.setNome(rs.getString("Nome"));
                    m.setDosagem(rs.getInt("Dosagem"));
                    m.setIntervalo_Aviso(rs.getInt("Intervalo_Aviso"));
                    
                    
                    if (rs.getDate("Data_Inicio") != null) {
                        m.setDataInicio(rs.getDate("Data_Inicio").toLocalDate());
                    }
                    
                    if (rs.getDate("Data_Fim") != null) {
                        m.setDataFim(rs.getDate("Data_Fim").toLocalDate());
                    } else {
                        m.setDataFim(null); 
                    }
                    
                    m.setUltima_Dose(rs.getTimestamp("Ultima_Dose").toLocalDateTime());
                    
                    String unidadeString = rs.getString("Unidade");
                    m.setUnidade(Medicamento.tipoUnidade.valueOf(unidadeString));
	        		
	        		}
	        	} catch (SQLException e) {
	        		if (e.getErrorCode() == 0) { 
	                    throw new Exception("Não foi possível conectar ao sistema.");
	                }
	        	}
	        }

	        return m;
	   }


	}

