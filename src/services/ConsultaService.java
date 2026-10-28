package services;

import dao.ConsultaDAO;
import dao.PacienteDAO;
import models.Paciente;
import exceptions.SistemaException;
import models.Consulta;

import java.util.ArrayList;

public class ConsultaService {
    private final ConsultaDAO dao = new ConsultaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public Boolean cadastrar(Consulta c) throws SistemaException {
        validar(c);
        validarRelacionamento(c);
        return dao.inserir(c);
    }

    public String listar(Integer idNutricionista) {
        ArrayList<Consulta> lista = dao.selecionar(idNutricionista);
        String texto = "";
        if (lista == null || lista.isEmpty()) return "Nenhuma consulta encontrada.";
        for (Consulta c : lista) texto += c + "\n";
        return texto;
    }

    public Consulta buscarPorId(Integer id, Integer idNutricionista) {
        return dao.selecionarPorId(id, idNutricionista);
    }

    public Boolean alterar(Consulta c) throws SistemaException {
        validar(c);
        validarRelacionamento(c);
        return dao.atualizar(c);
    }

    public Boolean cancelar(Integer id, Integer idNutricionista) {
        return dao.cancelar(id, idNutricionista);
    }

    public Boolean pagar(Integer id, Integer idNutricionista) {
        return dao.pagar(id, idNutricionista);
    }

    private void validarRelacionamento(Consulta c) throws SistemaException {
        if (c.getIdNutricionista() == null) {
            throw new SistemaException("Nutricionista não identificado. Entre novamente no sistema.");
        }
        Paciente paciente = pacienteDAO.selecionarPorId(c.getIdPaciente(), c.getIdNutricionista());
        if (paciente == null) {
            throw new SistemaException("O ID informado não pertence a um paciente ativo deste nutricionista.");
        }
    }

    private void validar(Consulta c) throws SistemaException {
        String erros = "";
        if (c.getIdNutricionista() == null) erros += "\n- Nutricionista não identificado.";
        if (c.getIdPaciente() == null) erros += "\n- ID do paciente obrigatório.";
        if (c.getDtConsulta() == null) erros += "\n- Data inválida. Use AAAA-MM-DD.";
        if (c.getHrConsulta() == null) erros += "\n- Horário inválido. Use HH:MM.";
        if (c.getValorConsulta() == null || c.getValorConsulta() < 0) erros += "\n- Valor inválido.";
        if (!erros.isEmpty()) throw new SistemaException(erros);
    }
}
