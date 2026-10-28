package models;

public class Paciente {
    private Integer idPaciente;
    private Integer idNutricionista;
    private String nmNome;
    private String nmEmail;
    private String nmSenha;
    private Boolean stAtivo;

    public Paciente() {
    }

    public Paciente(Integer idNutricionista, String nome, String email, String senha) {
        this.idNutricionista = idNutricionista;
        nmNome = nome;
        nmEmail = email;
        nmSenha = senha;
        stAtivo = true;
    }

    public Paciente(Integer id, Integer idNutricionista, String nome, String email, String senha, Boolean ativo) {
        idPaciente = id;
        this.idNutricionista = idNutricionista;
        nmNome = nome;
        nmEmail = email;
        nmSenha = senha;
        stAtivo = ativo;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer v) {
        idPaciente = v;
    }

    public Integer getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(Integer v) {
        idNutricionista = v;
    }

    public String getNmNome() {
        return nmNome;
    }

    public void setNmNome(String v) {
        nmNome = v;
    }

    public String getNmEmail() {
        return nmEmail;
    }

    public void setNmEmail(String v) {
        nmEmail = v;
    }

    public String getNmSenha() {
        return nmSenha;
    }

    public void setNmSenha(String v) {
        nmSenha = v;
    }

    public Boolean getStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(Boolean v) {
        stAtivo = v;
    }

    @Override
    public String toString() {
        return "Id: " + idPaciente + " | Nome: " + nmNome + " | E-mail: " + nmEmail + " | Ativo: " + stAtivo;
    }
}
