import java.time.LocalDate;
import java.time.LocalDateTime;

public class Medicamento {
	private int id;
	private int id_idoso;
	private String nome;
	private int dosagem;
	public enum tipoUnidade { 
		mg,
		ml,
		gotas,
		comprimido
	}
	private tipoUnidade unidade;
	
	private int intervalo_Aviso;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private LocalDateTime ultima_Dose;
	
	public Medicamento(int id, int id_idoso) {
		this.id = id;
		this.id_idoso = id_idoso;
	}
	public Medicamento(int id_idoso, String nome, int dosagem, int intervalo_Aviso, LocalDate dataInicio, LocalDate dataFim, LocalDateTime ultima_Dose) {
		this.id_idoso = id_idoso;
		this.nome = nome;
		this.dosagem = dosagem;
		this.intervalo_Aviso = intervalo_Aviso;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.ultima_Dose = ultima_Dose;
	}
	
	public Medicamento(int id, int id_idoso, String nome, int dosagem, int intervalo_Aviso, LocalDate dataInicio, LocalDate dataFim, LocalDateTime ultima_Dose) {
		this.id = id;
		this.id_idoso = id_idoso;
		this.nome = nome;
		this.dosagem = dosagem;
		this.intervalo_Aviso = intervalo_Aviso;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.ultima_Dose = ultima_Dose;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDosagem() {
		return dosagem;
	}

	public void setDosagem(int dosagem) {
		this.dosagem = dosagem;
	}

	public int getIntervalo_Aviso() {
		return intervalo_Aviso;
	}

	public void setIntervalo_Aviso(int intervalo_Aviso) {
		this.intervalo_Aviso = intervalo_Aviso;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getUltima_Dose() {
		return ultima_Dose;
	}

	public void setUltima_Dose(LocalDateTime ultima_Dose) {
		this.ultima_Dose = ultima_Dose;
	}

	public int getId() {
		return id;
	}

	public int getId_idoso() {
		return id_idoso;
	}

	public tipoUnidade getUnidade() {
		return unidade;
	}

	public void setUnidade(tipoUnidade unidade) {
		this.unidade = unidade;
	}
	

	
	
}
