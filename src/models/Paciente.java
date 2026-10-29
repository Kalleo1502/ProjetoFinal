package models;

import java.util.Objects;

public class Paciente {
    private Integer id;
    private Nutricionista nutricionista;
    private String nome;
    private String email;
    private String senha;
    private Boolean status;

    public Paciente() {
    }

    public Paciente(Nutricionista nutricionista, String nome, String email, String senha) {
        this.nutricionista = nutricionista;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.status = true;
    }

    public Paciente(Integer id, Nutricionista nutricionista, String nome, String email, String senha, Boolean status) {
        this.id = id;
        this.nutricionista = nutricionista;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) && Objects.equals(nutricionista, paciente.nutricionista) && Objects.equals(nome, paciente.nome) && Objects.equals(email, paciente.email) && Objects.equals(senha, paciente.senha) && Objects.equals(status, paciente.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nutricionista, nome, email, senha, status);
    }

    @Override
    public String toString() {
        return "Id: " + id + " | Nome: " + nome + " | E-mail: " + email + " | Ativo: " + status;
    }
}
