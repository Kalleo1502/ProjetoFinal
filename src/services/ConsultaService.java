package services;

import dao.ConsultaDAO;
import dao.PacienteDAO;
import models.Paciente;
import exceptions.SistemaException;
import models.Consulta;

import java.util.ArrayList;

public class ConsultaService {
    private final ConsultaDAO consultaDao = new ConsultaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public Boolean cadastrar(Consulta consulta) throws SistemaException {
        validar(consulta);
        validarRelacionamento(consulta);
        return consultaDao.inserir(consulta);
    }

    public String listar(Integer idNutricionista) {
        ArrayList<Consulta> lista = consultaDao.selecionar(idNutricionista);
        String texto = "";
        if (lista == null || lista.isEmpty()) return "Nenhuma consulta encontrada.";
        for (Consulta consulta : lista) texto += consulta + "\n";
        return texto;
    }

    public Consulta buscarPorId(Integer id, Integer idNutricionista) {
        return consultaDao.selecionarPorId(id, idNutricionista);
    }

    public Boolean alterar(Consulta consulta) throws SistemaException {
        validar(consulta);
        validarRelacionamento(consulta);
        return consultaDao.atualizar(consulta);
    }

    public Boolean cancelar(Integer id, Integer idNutricionista) {
        return consultaDao.cancelar(id, idNutricionista);
    }

    public Boolean pagar(Integer id, Integer idNutricionista) {
        return consultaDao.pagar(id, idNutricionista);
    }

    private void validarRelacionamento(Consulta consulta) throws SistemaException {
        if (consulta.getIdNutricionista() == null) {
            throw new SistemaException("Nutricionista não identificado. Entre novamente no sistema.");
        }
        Paciente paciente = pacienteDAO.selecionarPorId(consulta.getIdPaciente(), consulta.getIdNutricionista());
        if (paciente == null) {
            throw new SistemaException("O ID informado não pertence a um paciente ativo deste nutricionista.");
        }
    }

    private void validar(Consulta consulta) throws SistemaException {
        String erros = "";
        if (consulta.getIdNutricionista() == null) erros += "\n- Nutricionista não identificado.";
        if (consulta.getIdPaciente() == null) erros += "\n- ID do paciente obrigatório.";
        if (consulta.getDtConsulta() == null) erros += "\n- Data inválida. Use AAAA-MM-DD.";
        if (consulta.getHrConsulta() == null) erros += "\n- Horário inválido. Use HH:MM.";
        if (consulta.getValorConsulta() == null || consulta.getValorConsulta() < 0) erros += "\n- Valor inválido.";
        if (!erros.isEmpty()) throw new SistemaException(erros);
    }
}
