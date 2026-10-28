package services;

import dao.NutricionistaDAO;
import exceptions.SistemaException;
import models.Nutricionista;

public class NutricionistaService {
    private final NutricionistaDAO dao = new NutricionistaDAO();

    public Boolean cadastrar(Nutricionista n) throws SistemaException {
        validar(n.getNmNome(), n.getNmEmail(), n.getNmSenha());
        if (dao.selecionarPorEmail(n.getNmEmail()) != null) throw new SistemaException("E-mail já cadastrado.");
        return dao.inserir(n);
    }

    public Nutricionista login(String email, String senha) {
        Nutricionista n = dao.selecionarPorEmail(email);
        return n != null && n.getNmSenha().equals(senha) ? n : null;
    }

    public String listar() {
        String texto = "";
        java.util.ArrayList<Nutricionista> lista = dao.selecionar();
        if (lista == null || lista.isEmpty()) return "Nenhum nutricionista encontrado.";
        for (Nutricionista n : lista) texto += n + "\n";
        return texto;
    }

    public Nutricionista buscarPorId(Integer id) {
        return dao.selecionarPorId(id);
    }

    public Boolean alterar(Nutricionista n) throws SistemaException {
        validar(n.getNmNome(), n.getNmEmail(), n.getNmSenha());
        return dao.atualizar(n);
    }

    public Boolean excluir(Integer id) {
        return dao.deletar(id);
    }

    private void validar(String nome, String email, String senha) throws SistemaException {
        String erros = "";
        if (nome == null || nome.trim().isEmpty()) erros += "\n- Nome obrigatório.";
        if (email == null || !email.contains("@")) erros += "\n- E-mail inválido.";
        if (senha == null || senha.trim().isEmpty()) erros += "\n- Senha obrigatória.";
        if (!erros.isEmpty()) throw new SistemaException(erros);
    }
}
