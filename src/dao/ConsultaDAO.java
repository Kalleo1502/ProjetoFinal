package dao;

import connections.ConexaoMySQL;
import models.Consulta;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {
    public ArrayList<Consulta> selecionar(Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("SELECT * FROM consulta WHERE idNutricionista=? ORDER BY dtConsulta,hrConsulta");
            p.setInt(1, idNutricionista);
            ResultSet r = p.executeQuery();
            ArrayList<Consulta> lista = new ArrayList<>();
            while (r.next()) lista.add(mapear(r));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Consulta selecionarPorId(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("SELECT * FROM consulta WHERE idConsulta=? AND idNutricionista=?");
            p.setInt(1, id);
            p.setInt(2, idNutricionista);
            ResultSet r = p.executeQuery();
            return r.next() ? mapear(r) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Consulta mapear(ResultSet r) throws SQLException {
        return new Consulta(r.getInt("idConsulta"), r.getInt("idNutricionista"), r.getInt("idPaciente"),
                r.getDate("dtConsulta"), r.getTime("hrConsulta"), r.getString("dsConsulta"),
                r.getString("stConsulta"), r.getDouble("valorConsulta"), r.getString("stPagamento"));
    }

    public Boolean inserir(Consulta x) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("INSERT INTO consulta(idNutricionista,idPaciente,dtConsulta,hrConsulta,dsConsulta,valorConsulta,stConsulta,stPagamento) VALUES(?,?,?,?,?,?,?,?)");
            p.setInt(1, x.getIdNutricionista());
            p.setInt(2, x.getIdPaciente());
            p.setDate(3, x.getDtConsulta());
            p.setTime(4, x.getHrConsulta());
            p.setString(5, x.getDsConsulta());
            p.setDouble(6, x.getValorConsulta());
            p.setString(7, x.getStConsulta());
            p.setString(8, x.getStPagamento());
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean atualizar(Consulta x) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("UPDATE consulta SET idPaciente=?,dtConsulta=?,hrConsulta=?,dsConsulta=?,stConsulta=?,valorConsulta=?,stPagamento=? WHERE idConsulta=? AND idNutricionista=?");
            p.setInt(1, x.getIdPaciente());
            p.setDate(2, x.getDtConsulta());
            p.setTime(3, x.getHrConsulta());
            p.setString(4, x.getDsConsulta());
            p.setString(5, x.getStConsulta());
            p.setDouble(6, x.getValorConsulta());
            p.setString(7, x.getStPagamento());
            p.setInt(8, x.getIdConsulta());
            p.setInt(9, x.getIdNutricionista());
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean cancelar(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("UPDATE consulta SET stConsulta='Cancelada' WHERE idConsulta=? AND idNutricionista=?");
            p.setInt(1, id);
            p.setInt(2, idNutricionista);
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean pagar(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("UPDATE consulta SET stPagamento='Pago' WHERE idConsulta=? AND idNutricionista=?");
            p.setInt(1, id);
            p.setInt(2, idNutricionista);
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
