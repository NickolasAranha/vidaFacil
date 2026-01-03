import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class HidratacaoDAO {
	BD banco = new BD();
	
	   public HidratacaoDAO() {
	        this.banco = new BD(); 
	    }
	   
	   public void criarMetaHidratacaoPadrao(Connection conn, int id_idoso) throws SQLException {
		   String sql = "INSERT INTO Meta_Diaria (ID_Idoso, Meta_Diaria, Intervalo_Aviso, Hora_Inicio, Hora_Fim) " + "VALUES (?, 2000, 2, '08:00:00', '20:00:00')";
		   try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			   stmt.setInt(1, id_idoso);
			   stmt.execute();
		   }
	   }
	   
	   public MetaDiaria buscarMeta(int idIdoso) throws Exception {
		   String sql = "SELECT * FROM Meta_Diaria WHERE ID_Idoso = ?";
		   MetaDiaria meta = null;
		   try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			    stmt.setInt(1, idIdoso);
		    	try (ResultSet rs = stmt.executeQuery()) {
		    		if (rs.next()) {
		    			meta = new MetaDiaria();
		    			meta.setIdConfig(rs.getInt("ID_Config"));
	                    meta.setId_idoso(rs.getInt("ID_Idoso"));
	                    meta.setMetaDiaria(rs.getInt("Meta_Diaria"));
	                    meta.setIntervaloAviso(rs.getInt("Intervalo_Aviso"));
	                    Time inicioSql = rs.getTime("Hora_Inicio");
	                    if (inicioSql != null) {
	                        meta.setHoraInicio(inicioSql.toLocalTime());
	                    }

	                    Time fimSql = rs.getTime("Hora_Fim");
	                    if (fimSql != null) {
	                        meta.setHoraFim(fimSql.toLocalTime());
	                    }
		    		}
		    	} catch (Exception e) {
		            e.printStackTrace();
		            throw new Exception("Erro ao buscar meta de hidratação.");
		        }
		    	return meta;
		   }
	   }
	   public void atualizarrMeta(MetaDiaria meta) throws SQLException {
		   String sql = "UPDATE Meta_Diaria SET Meta_Diaria=?, Intervalo_Aviso=?, Hora_Inicio=?, Hora_Fim=? WHERE ID_Idoso=?";
		   try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			   stmt.setInt(1, meta.getMetaDiaria());
			   stmt.setInt(2, meta.getIntervaloAviso());
			   stmt.setTime(3, Time.valueOf(meta.getHoraInicio()));
	           stmt.setTime(4, Time.valueOf(meta.getHoraFim()));
			   stmt.setInt(5, meta.getId_idoso());
	           stmt.execute();
		   }
	   }
	   
	   public void registrarConsumo(int idIdoso, int quantidadeMl) throws Exception {
	        String sql = "INSERT INTO Registro_Consumo (ID_Idoso, Data_Hora, Quantidade) VALUES (?, NOW(), ?)";
	        
	        try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idIdoso);
	            stmt.setInt(2, quantidadeMl);
	            stmt.execute();
	        } catch (SQLException e) {
	            throw new Exception("Erro ao registrar consumo de água.");
	        }
	   }
	   public int calcularTotalConsumidoHoje(int idIdoso) throws Exception {
		   String sql = "SELECT SUM(Quantidade) as Total FROM Registro_Consumo " + "WHERE ID_Idoso = ? AND DATE(Data_Hora) = CURRENT_DATE";
		   int total = 0;
		   try (Connection conn = banco.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {			        
			        stmt.setInt(1, idIdoso);
			        
			        try (ResultSet rs = stmt.executeQuery()) {
			            if (rs.next()) {
			                total = rs.getInt("Total");
			            }
			        }
			    } catch (Exception e) {
			        throw new Exception("Erro ao calcular hidratação: " + e.getMessage());
			    }
			    
			    return total;
			}
	   
	   public boolean verificarMetaAtingida(int idIdoso) throws Exception {
		    MetaDiaria config = buscarMeta(idIdoso); 
		    int totalBebeu = calcularTotalConsumidoHoje(idIdoso);
		    if(totalBebeu >= config.getMetaDiaria()) {
		    	return true;
		    } else {
		    	return false;
		    }
	   }
}


