package views;

import javax.swing.*;
import models.*;

public class SistemaView {
    public int menu(String titulo,String[] opcoes) {
        return JOptionPane.showOptionDialog(null,"Selecione uma opção:",titulo,
                JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opcoes,opcoes[0]);
    }
    public String ler(String titulo,String pergunta,Object valor) {
        return (String)JOptionPane.showInputDialog(null,pergunta,titulo,JOptionPane.QUESTION_MESSAGE,null,null,valor);
    }
    public void exibir(String titulo,String texto) {
        JOptionPane.showMessageDialog(null,texto,titulo,JOptionPane.PLAIN_MESSAGE);
    }
    public void sucesso(String titulo,String texto) {
        JOptionPane.showMessageDialog(null,texto,titulo,JOptionPane.INFORMATION_MESSAGE);
    }
    public void erro(String titulo,String texto) {
        JOptionPane.showMessageDialog(null,texto,titulo,JOptionPane.ERROR_MESSAGE);
    }
    public int confirmar(String titulo,String texto) {
        return JOptionPane.showConfirmDialog(null,texto,titulo,JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
    }
    public Nutricionista dadosNutricionista(Nutricionista n) {
        String nome=ler("Nutricionista","Nome:",n.getNmNome()); if(nome==null)return null;
        String email=ler("Nutricionista","E-mail:",n.getNmEmail()); if(email==null)return null;
        String senha=ler("Nutricionista","Senha:",n.getNmSenha()); if(senha==null)return null;
        n.setNmNome(nome);n.setNmEmail(email);n.setNmSenha(senha);return n;
    }
    public Paciente dadosPaciente(Paciente p) {
        String nome=ler("Paciente","Nome:",p.getNmNome());if(nome==null)return null;
        String email=ler("Paciente","E-mail:",p.getNmEmail());if(email==null)return null;
        String senha=ler("Paciente","Senha:",p.getNmSenha());if(senha==null)return null;
        p.setNmNome(nome);p.setNmEmail(email);p.setNmSenha(senha);return p;
    }
    public Receita dadosReceita(Receita r) {
        String id=ler("Receita","ID do paciente:",r.getIdPaciente());if(id==null)return null;
        String titulo=ler("Receita","Título:",r.getNmTitulo());if(titulo==null)return null;
        String descricao=ler("Receita","Descrição:",r.getNmDescricao());if(descricao==null)return null;
        String[] dias={"Domingo","Segunda","Terça","Quarta","Quinta","Sexta","Sábado"};
        Object dia=JOptionPane.showInputDialog(null,"Dia da semana:","Receita",JOptionPane.QUESTION_MESSAGE,null,dias,r.getDiaSemana()==null?dias[0]:r.getDiaSemana());
        if(dia==null)return null;
        try {r.setIdPaciente(Integer.parseInt(id));}catch(Exception e){erro("Receita","ID de paciente inválido.");return null;}
        r.setNmTitulo(titulo);r.setNmDescricao(descricao);r.setDiaSemana(dia.toString());return r;
    }
    public Consulta dadosConsulta(Consulta c) {
        String id=ler("Consulta","ID do paciente:",c.getIdPaciente());if(id==null)return null;
        String data=ler("Consulta","Data (AAAA-MM-DD):",c.getDtConsulta());if(data==null)return null;
        String hora=ler("Consulta","Horário (HH:MM):",c.getHrConsulta());if(hora==null)return null;
        String descricao=ler("Consulta","Descrição:",c.getDsConsulta());if(descricao==null)return null;
        String valor=ler("Consulta","Valor:",c.getValorConsulta());if(valor==null)return null;
        try {
            c.setIdPaciente(Integer.parseInt(id));
            c.setDtConsulta(java.sql.Date.valueOf(data));
            c.setHrConsulta(java.sql.Time.valueOf(hora.length()==5?hora+":00":hora));
            c.setValorConsulta(Double.parseDouble(valor.replace(",",".")));
        } catch(Exception e){erro("Consulta","ID, data, horário ou valor inválido.");return null;}
        c.setDsConsulta(descricao);return c;
    }
}
