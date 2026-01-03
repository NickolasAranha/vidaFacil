import java.time.LocalTime;

public class MetaDiaria {
	private int idConfig;
	private int id_idoso;
	private int metaDiaria;
	private int intervaloAviso;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	
	
	
	public int getIdConfig() {
		return idConfig;
	}
	public void setIdConfig(int idConfig) {
		this.idConfig = idConfig;
	}
	public int getId_idoso() {
		return id_idoso;
	}
	public void setId_idoso(int id_idoso) {
		this.id_idoso = id_idoso;
	}
	public int getMetaDiaria() {
		return metaDiaria;
	}
	public void setMetaDiaria(int metaDiaria) {
		this.metaDiaria = metaDiaria;
	}
	public int getIntervaloAviso() {
		return intervaloAviso;
	}
	public void setIntervaloAviso(int intervaloAviso) {
		this.intervaloAviso = intervaloAviso;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalTime getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}
	
	
	
}

