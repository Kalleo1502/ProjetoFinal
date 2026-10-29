package dao;

import connections.ConexaoMySQL;
import models.Consulta;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {
    public ArrayList<Consulta> selecionar(Integer idNutricionista) {
        try {
            PreparedStatement declaracao = ConexaoMySQL.get().prepareStatement("SELECT * FROM consulta WHERE idNutricionista=? ORDER BY dtConsulta,hrConsulta");
            declaracao.setInt(1, idNutricionista);
            ResultSet resultado = declaracao.executeQuery();

            ArrayList<Consulta> lista = new ArrayList<>();
            while (resultado.next()) lista.add(mapear(resultado));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Consulta selecionarPorId(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement("SELECT * FROM consulta WHERE idConsulta=? AND idNutricionista=?");
            preparacao.setInt(1, id);
            preparacao.setInt(2, idNutricionista);
            ResultSet resultado = preparacao.executeQuery();
            return resultado.next() ? mapear(resultado) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Consulta mapear(ResultSet resultado) throws SQLException {
        return new Consulta(resultado.getInt("idConsulta"), resultado.getInt("idNutricionista"), resultado.getInt("idPaciente"),
                resultado.getDate("dtConsulta"), resultado.getTime("hrConsulta"), resultado.getString("dsConsulta"),
                resultado.getString("stConsulta"), resultado.getDouble("valorConsulta"), resultado.getString("stPagamento"));
    }

    public Boolean inserir(Consulta consulta) {
        try {
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement("INSERT INTO consulta(idNutricionista,idPaciente,dtConsulta,hrConsulta,dsConsulta,valorConsulta,stConsulta,stPagamento) VALUES(?,?,?,?,?,?,?,?)");
            preparacao.setInt(1, consulta.getIdNutricionista());
            preparacao.setInt(2, consulta.getIdPaciente());
            preparacao.setDate(3, consulta.getDtConsulta());
            preparacao.setTime(4, consulta.getHrConsulta());
            preparacao.setString(5, consulta.getDsConsulta());
            preparacao.setDouble(6, consulta.getValorConsulta());
            preparacao.setString(7, consulta.getStConsulta());
            preparacao.setString(8, consulta.getStPagamento());
            return preparacao.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean atualizar(Consulta consulta) {
        try {
            PreparedStatement p = ConexaoMySQL.get().prepareStatement("UPDATE consulta SET idPaciente=?,dtConsulta=?,hrConsulta=?,dsConsulta=?,stConsulta=?,valorConsulta=?,stPagamento=? WHERE idConsulta=? AND idNutricionista=?");
            p.setInt(1, consulta.getIdPaciente());
            p.setDate(2, consulta.getDtConsulta());
            p.setTime(3, consulta.getHrConsulta());
            p.setString(4, consulta.getDsConsulta());
            p.setString(5, consulta.getStConsulta());
            p.setDouble(6, consulta.getValorConsulta());
            p.setString(7, consulta.getStPagamento());
            p.setInt(8, consulta.getIdConsulta());
            p.setInt(9, consulta.getIdNutricionista());
            return p.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean cancelar(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement("UPDATE consulta SET stConsulta='Cancelada' WHERE idConsulta=? AND idNutricionista=?");
            preparacao.setInt(1, id);
            preparacao.setInt(2, idNutricionista);
            return preparacao.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean pagar(Integer id, Integer idNutricionista) {
        try {
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement("UPDATE consulta SET stPagamento='Pago' WHERE idConsulta=? AND idNutricionista=?");
            preparacao.setInt(1, id);
            preparacao.setInt(2, idNutricionista);
            return preparacao.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
