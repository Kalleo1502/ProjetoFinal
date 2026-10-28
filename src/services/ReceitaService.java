package services;

import dao.ReceitaDAO;
import dao.PacienteDAO;
import models.Paciente;
import exceptions.SistemaException;
import models.Receita;

import java.util.ArrayList;

public class ReceitaService {
    private final ReceitaDAO dao = new ReceitaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public Boolean cadastrar(Receita r) throws SistemaException {
        validar(r);
        validarRelacionamento(r);
        return dao.inserir(r);
    }

    public String listar(Integer idNutricionista) {
        ArrayList<Receita> lista = dao.selecionar(idNutricionista);
        String texto = "";
        if (lista == null || lista.isEmpty()) return "Nenhuma receita encontrada.";
        for (Receita r : lista) texto += r + "\n";
        return texto;
    }

    public String listarPorPacienteDia(Integer idPaciente, Integer idNutricionista, String dia) {
        ArrayList<Receita> lista = dao.selecionarPorPacienteDia(idPaciente, idNutricionista, dia);
        String texto = "";
        if (lista == null || lista.isEmpty()) return "Nenhuma receita encontrada para " + dia + ".";
        for (Receita r : lista) texto += r + "\n";
        return texto;
    }

    public Receita buscarPorId(Integer id, Integer idNutricionista) {
        return dao.selecionarPorId(id, idNutricionista);
    }

    public Boolean alterar(Receita r) throws SistemaException {
        validar(r);
        validarRelacionamento(r);
        return dao.atualizar(r);
    }

    public Boolean excluir(Integer id, Integer idNutricionista) {
        return dao.deletar(id, idNutricionista);
    }

    private void validarRelacionamento(Receita r) throws SistemaException {
        if (r.getIdNutricionista() == null) {
            throw new SistemaException("Nutricionista não identificado. Entre novamente no sistema.");
        }
        Paciente paciente = pacienteDAO.selecionarPorId(r.getIdPaciente(), r.getIdNutricionista());
        if (paciente == null) {
            throw new SistemaException("O ID informado não pertence a um paciente ativo deste nutricionista.");
        }
    }

    private void validar(Receita r) throws SistemaException {
        String erros = "";
        if (r.getNmTitulo() == null || r.getNmTitulo().trim().isEmpty()) erros += "\n- Título obrigatório.";
        if (r.getNmDescricao() == null || r.getNmDescricao().trim().isEmpty()) erros += "\n- Descrição obrigatória.";
        String[] dias = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
        boolean valido = false;
        if (r.getDiaSemana() != null) for (String d : dias) if (d.equalsIgnoreCase(r.getDiaSemana())) valido = true;
        if (!valido) erros += "\n- Selecione um dia da semana válido.";
        if (r.getIdPaciente() == null) erros += "\n- ID do paciente obrigatório.";
        if (!erros.isEmpty()) throw new SistemaException(erros);
    }
}
