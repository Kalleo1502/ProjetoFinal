package services;

import dao.PacienteDAO;
import exceptions.SistemaException;
import models.Nutricionista;
import models.Paciente;

import java.util.ArrayList;

public class PacienteService {
    private final PacienteDAO dao = new PacienteDAO();

    public Boolean cadastrar(Paciente p) throws SistemaException {
        validar(p);
        return dao.inserir(p);
    }

    public String listar(Integer idNutricionista) {
        ArrayList<Paciente> lista = dao.selecionar(idNutricionista);
        String texto = "";
        if (lista == null || lista.isEmpty()) return "Nenhum paciente encontrado.";
        for (Paciente p : lista) texto += p + "\n";
        return texto;
    }

    public Paciente buscarPorId(Integer id, Integer idNutricionista) {
        return dao.selecionarPorId(id, idNutricionista);
    }

    public Boolean alterar(Paciente p) throws SistemaException {
        validar(p);
        return dao.atualizar(p);
    }

    public Boolean excluir(Integer id) {
        return dao.deletar(id);
    }

    public Paciente login(String email, String senha) {
        Paciente paciente = dao.login(email);
        return paciente != null && paciente.getSenha().equals(senha) ? paciente : null;
    }

    private void validar(Paciente p) throws SistemaException {
        String erros = "";
        if (p.getNome() == null || p.getNome().trim().isEmpty()) erros += "\n- Nome obrigatório.";
        if (p.getEmail() == null || !p.getEmail().contains("@")) erros += "\n- E-mail inválido.";
        if (p.getSenha() == null || p.getSenha().trim().isEmpty()) erros += "\n- Senha obrigatória.";
        if (!erros.isEmpty()) throw new SistemaException(erros);
    }
}
