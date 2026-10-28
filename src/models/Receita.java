package models;

public class Receita {
    private Integer idReceita;
    private Integer idNutricionista;
    private Integer idPaciente;
    private String nmTitulo;
    private String nmDescricao;
    private String diaSemana;

    public Receita() {
    }

    public Receita(Integer idNutricionista, Integer idPaciente, String titulo, String descricao, String diaSemana) {
        this.idNutricionista = idNutricionista;
        this.idPaciente = idPaciente;
        nmTitulo = titulo;
        nmDescricao = descricao;
        this.diaSemana = diaSemana;
    }

    public Receita(Integer id, Integer idNutricionista, Integer idPaciente, String titulo, String descricao, String dia) {
        idReceita = id;
        this.idNutricionista = idNutricionista;
        this.idPaciente = idPaciente;
        nmTitulo = titulo;
        nmDescricao = descricao;
        diaSemana = dia;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer v) {
        idReceita = v;
    }

    public Integer getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(Integer v) {
        idNutricionista = v;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer v) {
        idPaciente = v;
    }

    public String getNmTitulo() {
        return nmTitulo;
    }

    public void setNmTitulo(String v) {
        nmTitulo = v;
    }

    public String getNmDescricao() {
        return nmDescricao;
    }

    public void setNmDescricao(String v) {
        nmDescricao = v;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String v) {
        diaSemana = v;
    }

    @Override
    public String toString() {
        return "Id: " + idReceita + " | Paciente: " + idPaciente + " | " + diaSemana + " | " + nmTitulo + " | " + nmDescricao;
    }
}
