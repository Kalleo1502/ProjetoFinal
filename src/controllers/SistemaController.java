package controllers;

import exceptions.SistemaException;
import models.*;
import services.*;
import views.SistemaView;

import java.sql.Date;
import java.sql.Time;

public class SistemaController {
    private final SistemaView view = new SistemaView();
    private final NutricionistaService nutricionistaService = new NutricionistaService();
    private final PacienteService pacienteService = new PacienteService();
    private final ReceitaService receitaService = new ReceitaService();
    private final ConsultaService consultaService = new ConsultaService();

    public void iniciar() {
        int opcao;
        do {
            opcao = view.menu("SISTEMA DE NUTRIÇÃO", new String[]{"Entrar como nutricionista", "Entrar como paciente", "Cadastrar nutricionista", "Sair"});
            try {
                switch (opcao) {
                    case 0:
                        loginNutricionista();
                        break;
                    case 1:
                        loginPaciente();
                        break;
                    case 2:
                        cadastrarNutricionista();
                        break;
                }
            } catch (Exception e) {
                view.erro("Sistema", e.getMessage());
            }
        } while (opcao >= 0 && opcao != 3);
    }

    private void cadastrarNutricionista() throws SistemaException {
        Nutricionista n = view.dadosNutricionista(new Nutricionista());
        if (n != null && nutricionistaService.cadastrar(n)) view.sucesso("Cadastro", "Nutricionista cadastrado!");
        else if (n != null) view.erro("Cadastro", "Não foi possível cadastrar.");
    }

    private void loginNutricionista() {
        String email = view.ler("Login", "E-mail:", null);
        if (email == null) return;
        String senha = view.ler("Login", "Senha:", null);
        if (senha == null) return;
        Nutricionista n = nutricionistaService.login(email, senha);
        if (n == null) {
            view.erro("Login", "E-mail ou senha inválidos.");
            return;
        }
        menuNutricionista(n);
    }

    private void loginPaciente() {
        String email = view.ler("Login do paciente", "E-mail:", null);
        if (email == null) return;
        String senha = view.ler("Login do paciente", "Senha:", null);
        if (senha == null) return;
        Paciente p = pacienteService.login(email, senha);
        if (p == null) {
            view.erro("Login", "E-mail ou senha inválidos.");
            return;
        }
        menuPaciente(p);
    }

    private void menuNutricionista(Nutricionista n) {
        int op;
        do {
            op = view.menu("NUTRICIONISTA - " + n.getNmNome(), new String[]{"Pacientes", "Receitas", "Consultas e cobranças", "Meu cadastro", "Sair"});
            try {
                switch (op) {
                    case 0:
                        menuPacientes(n);
                        break;
                    case 1:
                        menuReceitas(n);
                        break;
                    case 2:
                        menuConsultas(n);
                        break;
                    case 3:
                        editarNutricionista(n);
                        break;
                }
            } catch (Exception e) {
                view.erro("Nutricionista", e.getMessage());
            }
        } while (op >= 0 && op != 4);
    }

    private void editarNutricionista(Nutricionista n) throws SistemaException {
        Nutricionista alterado = view.dadosNutricionista(n);
        if (alterado == null) return;
        if (nutricionistaService.alterar(alterado)) view.sucesso("Cadastro", "Dados alterados.");
        else view.erro("Cadastro", "Não foi possível alterar.");
    }

    private void menuPacientes(Nutricionista n) throws SistemaException {
        int op;
        do {
            op = view.menu("PACIENTES", new String[]{"Listar", "Cadastrar", "Alterar", "Inativar", "Voltar"});
            switch (op) {
                case 0:
                    view.exibir("Pacientes", pacienteService.listar(n.getIdNutricionista()));
                    break;
                case 1:
                    cadastrarPaciente(n);
                    break;
                case 2:
                    alterarPaciente(n);
                    break;
                case 3:
                    inativarPaciente(n);
                    break;
            }
        } while (op >= 0 && op != 4);
    }

    private void cadastrarPaciente(Nutricionista n) throws SistemaException {
        Paciente p = view.dadosPaciente(new Paciente(n.getIdNutricionista(), "", "", ""));
        if (p != null && pacienteService.cadastrar(p))
            view.sucesso("Paciente", "Paciente cadastrado. Informe ao paciente o e-mail e a senha.");
        else if (p != null) view.erro("Paciente", "Não foi possível cadastrar.");
    }

    private Integer pedirId(String titulo, String lista) {
        String s = view.ler(titulo, lista + "\nInforme o ID:", null);
        if (s == null) return null;
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            view.erro(titulo, "ID inválido.");
            return null;
        }
    }

    private void alterarPaciente(Nutricionista n) throws SistemaException {
        Integer id = pedirId("Alterar paciente", pacienteService.listar(n.getIdNutricionista()));
        if (id == null) return;
        Paciente p = pacienteService.buscarPorId(id, n.getIdNutricionista());
        if (p == null) {
            view.erro("Paciente", "Paciente não encontrado.");
            return;
        }
        Paciente alterado = view.dadosPaciente(p);
        if (alterado != null && pacienteService.alterar(alterado)) view.sucesso("Paciente", "Paciente alterado.");
    }

    private void inativarPaciente(Nutricionista n) {
        Integer id = pedirId("Inativar paciente", pacienteService.listar(n.getIdNutricionista()));
        if (id == null) return;
        if (view.confirmar("Inativar", "Deseja inativar o paciente " + id + "?") == 0) {
            if (pacienteService.excluir(id, n.getIdNutricionista())) view.sucesso("Paciente", "Paciente inativado.");
            else view.erro("Paciente", "Não foi possível inativar.");
        }
    }

    private void menuReceitas(Nutricionista n) throws SistemaException {
        int op;
        do {
            op = view.menu("RECEITAS", new String[]{"Listar", "Cadastrar", "Alterar", "Excluir", "Voltar"});
            switch (op) {
                case 0:
                    view.exibir("Receitas", receitaService.listar(n.getIdNutricionista()));
                    break;
                case 1:
                    salvarReceita(n, null);
                    break;
                case 2: {
                    Integer id = pedirId("Alterar receita", receitaService.listar(n.getIdNutricionista()));
                    if (id != null) {
                        Receita r = receitaService.buscarPorId(id, n.getIdNutricionista());
                        if (r == null) view.erro("Receita", "Receita não encontrada.");
                        else salvarReceita(n, r);
                    }
                    break;
                }
                case 3: {
                    Integer id = pedirId("Excluir receita", receitaService.listar(n.getIdNutricionista()));
                    if (id != null && view.confirmar("Excluir", "Deseja excluir a receita " + id + "?") == 0) {
                        if (receitaService.excluir(id, n.getIdNutricionista()))
                            view.sucesso("Receita", "Receita excluída.");
                        else view.erro("Receita", "Não foi possível excluir.");
                    }
                    break;
                }
            }
        } while (op >= 0 && op != 4);
    }

    private void salvarReceita(Nutricionista n, Receita r) throws SistemaException {
        boolean nova = r == null;
        if (nova) r = new Receita();
        r.setIdNutricionista(n.getIdNutricionista());
        Receita dados = view.dadosReceita(r);
        if (dados == null) return;
        boolean ok = nova ? receitaService.cadastrar(dados) : receitaService.alterar(dados);
        if (ok) view.sucesso("Receita", "Receita salva.");
        else view.erro("Receita", "Não foi possível salvar. Verifique o ID do paciente.");
    }

    private void menuConsultas(Nutricionista n) throws SistemaException {
        int op;
        do {
            op = view.menu("CONSULTAS E COBRANÇAS", new String[]{"Listar consultas", "Agendar consulta", "Cancelar consulta", "Registrar pagamento", "Voltar"});
            switch (op) {
                case 0:
                    view.exibir("Consultas", consultaService.listar(n.getIdNutricionista()));
                    break;
                case 1: {
                    Consulta c = view.dadosConsulta(new Consulta(n.getIdNutricionista(), null, null, null, "", 0.0));
                    if (c != null) {
                        c.setIdNutricionista(n.getIdNutricionista());
                        if (consultaService.cadastrar(c)) view.sucesso("Consulta", "Consulta agendada.");
                        else view.erro("Consulta", "Não foi possível agendar.");
                    }
                    break;
                }
                case 2: {
                    Integer id = pedirId("Cancelar consulta", consultaService.listar(n.getIdNutricionista()));
                    if (id != null && view.confirmar("Cancelar", "Deseja cancelar a consulta " + id + "?") == 0) {
                        if (consultaService.cancelar(id, n.getIdNutricionista()))
                            view.sucesso("Consulta", "Consulta cancelada.");
                        else view.erro("Consulta", "Não foi possível cancelar.");
                    }
                    break;
                }
                case 3: {
                    Integer id = pedirId("Registrar pagamento", consultaService.listar(n.getIdNutricionista()));
                    if (id != null && consultaService.pagar(id, n.getIdNutricionista()))
                        view.sucesso("Pagamento", "Pagamento registrado.");
                    else if (id != null) view.erro("Pagamento", "Não foi possível registrar o pagamento.");
                    break;
                }
            }
        } while (op >= 0 && op != 4);
    }

    private void menuPaciente(Paciente p) {
        String[] dias = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
        Object dia;
        do {
            dia = javax.swing.JOptionPane.showInputDialog(null, "Escolha o dia para consultar suas receitas:",
                    "RECEITAS DO PACIENTE", javax.swing.JOptionPane.QUESTION_MESSAGE, null, dias, dias[0]);
            if (dia != null)
                view.exibir("Receitas - " + dia, receitaService.listarPorPacienteDia(p.getIdPaciente(), p.getIdNutricionista(), dia.toString()));
        } while (dia != null);
    }
}
