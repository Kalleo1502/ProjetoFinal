package models;

public class Nutricionista {
    private Integer idNutricionista;
    private String nmNome;
    private String nmEmail;
    private String nmSenha;
    private Boolean stAtivo;

    public Nutricionista() {
    }

    public Nutricionista(String nmNome, String nmEmail, String nmSenha) {
        this.nmNome = nmNome;
        this.nmEmail = nmEmail;
        this.nmSenha = nmSenha;
        this.stAtivo = true;
    }

    public Nutricionista(Integer id, String nome, String email, String senha, Boolean ativo) {
        idNutricionista = id;
        nmNome = nome;
        nmEmail = email;
        nmSenha = senha;
        stAtivo = ativo;
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
        return "Id: " + idNutricionista + " | Nome: " + nmNome + " | E-mail: " + nmEmail + " | Ativo: " + stAtivo;
    }
}
