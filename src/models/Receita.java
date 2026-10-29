package models;

public class Receita {
    private Integer id;
    private Nutricionista nutricionista;
    private Paciente paciente;
    private String titulo;
    private String descricao;
    private String diaSemana;

    public Receita() {
    }

    public Receita(Nutricionista nutricionista, Paciente paciente, String titulo, String descricao, String diaSemana) {
        this.nutricionista = nutricionista;
        this.paciente = paciente;
        this.titulo = titulo;
        this.descricao = descricao;
        this.diaSemana = diaSemana;
    }

    public Receita(Integer id, Nutricionista nutricionista, Paciente paciente, String titulo, String descricao, String dia) {
        this.id = id;
        this.nutricionista = nutricionista;
        this.paciente = paciente;
        this.titulo = titulo;
        this.descricao = descricao;
        this.diaSemana = dia;
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
