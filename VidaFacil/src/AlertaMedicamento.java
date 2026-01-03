import java.time.LocalDateTime;
public class AlertaMedicamento {
	private int id;
    private int idMedicamento;
    private LocalDateTime dataHora;
	public enum tipoStatus { 
		Pendente,
		Confirmado,
		Recusado,
		Perdido
	}
	private String nomeMedicamento;
	private tipoStatus status;
    
    public AlertaMedicamento(int id, int idMedicamento) {
    	this.id = id;
    	this.idMedicamento = idMedicamento;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public tipoStatus getStatus() {
		return status;
	}
	public void setStatus(tipoStatus status) {
		this.status = status;
	}
	public String getNomeMedicamento() {
		return nomeMedicamento;
	}
	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}


    
}
