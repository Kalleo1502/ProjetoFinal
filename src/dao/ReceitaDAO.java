package dao;

import connections.ConexaoMySQL;
import models.Receita;

import java.sql.*;
import java.util.ArrayList;

public class ReceitaDAO {
    public ArrayList<Receita> selecionar(Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("SELECT * FROM receita WHERE idNutricionista=? ORDER BY idReceita");
            p.setInt(1, idNutricionista);
            ResultSet r = p.executeQuery();
            ArrayList<Receita> lista = new ArrayList<>();
            while (r.next())
                lista.add(new Receita(r.getInt("idReceita"), r.getInt("idNutricionista"), r.getInt("idPaciente"),
                        r.getString("nmTitulo"), r.getString("nmDescricao"), r.getString("diaSemana")));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Receita> selecionarPorPacienteDia(Integer idPaciente, Integer idNutricionista, String dia) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement(
                    "SELECT * FROM receita WHERE idPaciente=? AND idNutricionista=? AND diaSemana=? ORDER BY idReceita");
            p.setInt(1, idPaciente);
            p.setInt(2, idNutricionista);
            p.setString(3, dia);
            ResultSet r = p.executeQuery();
            ArrayList<Receita> lista = new ArrayList<>();
            while (r.next())
                lista.add(new Receita(r.getInt("idReceita"), r.getInt("idNutricionista"), r.getInt("idPaciente"),
                        r.getString("nmTitulo"), r.getString("nmDescricao"), r.getString("diaSemana")));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Receita selecionarPorId(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("SELECT * FROM receita WHERE idReceita=? AND idNutricionista=?");
            p.setInt(1, id);
            p.setInt(2, idNutricionista);
            ResultSet r = p.executeQuery();
            if (r.next()) return new Receita(r.getInt("idReceita"), r.getInt("idNutricionista"), r.getInt("idPaciente"),
                    r.getString("nmTitulo"), r.getString("nmDescricao"), r.getString("diaSemana"));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean inserir(Receita x) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("INSERT INTO receita(idNutricionista,idPaciente,nmTitulo,nmDescricao,diaSemana) VALUES(?,?,?,?,?)");
            p.setInt(1, x.getIdNutricionista());
            p.setInt(2, x.getIdPaciente());
            p.setString(3, x.getNmTitulo());
            p.setString(4, x.getNmDescricao());
            p.setString(5, x.getDiaSemana());
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean atualizar(Receita x) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("UPDATE receita SET idPaciente=?,nmTitulo=?,nmDescricao=?,diaSemana=? WHERE idReceita=? AND idNutricionista=?");
            p.setInt(1, x.getIdPaciente());
            p.setString(2, x.getNmTitulo());
            p.setString(3, x.getNmDescricao());
            p.setString(4, x.getDiaSemana());
            p.setInt(5, x.getIdReceita());
            p.setInt(6, x.getIdNutricionista());
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deletar(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("DELETE FROM receita WHERE idReceita=? AND idNutricionista=?");
            p.setInt(1, id);
            p.setInt(2, idNutricionista);
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
