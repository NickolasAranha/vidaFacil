package vidafacil.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Medicamento {
    private Integer id;
    private Integer id_user;

    private String nome;
    private Integer dosagem;
    private Unidade unidade;
    private Integer intervalo_aviso;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime ultima_Dose;

    public Medicamento(Integer id_user, String nome, Integer dosagem, Unidade unidade, Integer intervalo_aviso, LocalDate dataInicio, LocalDate dataFim, LocalDateTime ultima_Dose) {
        this.id_user = id_user;
        this.nome = nome;
        this.dosagem = dosagem;
        this.unidade = unidade;
        this.intervalo_aviso = intervalo_aviso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ultima_Dose = ultima_Dose;
    }

    public Medicamento(Integer id, Integer id_user, String nome, Integer dosagem, Unidade unidade, Integer intervalo_aviso, LocalDate dataInicio, LocalDate dataFim, LocalDateTime ultima_Dose) {
        this.id = id;
        this.id_user = id_user;
        this.nome = nome;
        this.dosagem = dosagem;
        this.unidade = unidade;
        this.intervalo_aviso = intervalo_aviso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ultima_Dose = ultima_Dose;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDosagem() {
        return dosagem;
    }

    public void setDosagem(Integer dosagem) {
        this.dosagem = dosagem;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Integer getIntervalo_aviso() {
        return intervalo_aviso;
    }

    public void setIntervalo_aviso(Integer intervalo_aviso) {
        this.intervalo_aviso = intervalo_aviso;
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
}
