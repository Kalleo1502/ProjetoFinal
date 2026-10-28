package dao;

import connections.ConexaoMySQL;
import models.Paciente;

import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO {
    public ArrayList<Paciente> selecionar(Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("SELECT * FROM paciente WHERE idNutricionista=? ORDER BY idPaciente");
            p.setInt(1, idNutricionista);
            ResultSet r = p.executeQuery();
            ArrayList<Paciente> lista = new ArrayList<>();
            while (r.next())
                lista.add(new Paciente(r.getInt("idPaciente"), r.getInt("idNutricionista"), r.getString("nmNome"),
                        r.getString("nmEmail"), r.getString("nmSenha"), r.getBoolean("stAtivo")));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Paciente selecionarPorId(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("SELECT * FROM paciente WHERE idPaciente=? AND idNutricionista=? AND stAtivo=1");
            p.setInt(1, id);
            p.setInt(2, idNutricionista);
            ResultSet r = p.executeQuery();
            if (r.next())
                return new Paciente(r.getInt("idPaciente"), r.getInt("idNutricionista"), r.getString("nmNome"),
                        r.getString("nmEmail"), r.getString("nmSenha"), r.getBoolean("stAtivo"));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Paciente login(String email) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("SELECT * FROM paciente WHERE nmEmail=? AND stAtivo=1");
            p.setString(1, email);
            ResultSet r = p.executeQuery();
            if (r.next())
                return new Paciente(r.getInt("idPaciente"), r.getInt("idNutricionista"), r.getString("nmNome"),
                        r.getString("nmEmail"), r.getString("nmSenha"), r.getBoolean("stAtivo"));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean inserir(Paciente x) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("INSERT INTO paciente(idNutricionista,nmNome,nmEmail,nmSenha) VALUES(?,?,?,?)");
            p.setInt(1, x.getIdNutricionista());
            p.setString(2, x.getNmNome());
            p.setString(3, x.getNmEmail());
            p.setString(4, x.getNmSenha());
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean atualizar(Paciente x) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("UPDATE paciente SET nmNome=?,nmEmail=?,nmSenha=? WHERE idPaciente=? AND idNutricionista=?");
            p.setString(1, x.getNmNome());
            p.setString(2, x.getNmEmail());
            p.setString(3, x.getNmSenha());
            p.setInt(4, x.getIdPaciente());
            p.setInt(5, x.getIdNutricionista());
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deletar(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("UPDATE paciente SET stAtivo=0 WHERE idPaciente=? AND idNutricionista=?");
            p.setInt(1, id);
            p.setInt(2, idNutricionista);
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
