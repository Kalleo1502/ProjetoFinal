package models;

import java.sql.Date;
import java.sql.Time;

public class Consulta {
    private Integer idConsulta;
    private Integer idNutricionista;
    private Integer idPaciente;
    private Date dtConsulta;
    private Time hrConsulta;
    private String dsConsulta;
    private String stConsulta;
    private Double valorConsulta;
    private String stPagamento;

    public Consulta() {
    }

    public Consulta(Integer idNutricionista, Integer idPaciente, Date data, Time hora, String descricao, Double valor) {
        this.idNutricionista = idNutricionista;
        this.idPaciente = idPaciente;
        this.dtConsulta = data;
        this.hrConsulta = hora;
        this.dsConsulta = descricao;
        this.valorConsulta = valor;
        this.stConsulta = "Agendada";
        this.stPagamento = "Pendente";
    }

    public Consulta(Integer id, Integer idNutricionista, Integer idPaciente, Date data, Time hora,
                    String descricao, String status, Double valor, String pagamento) {
        this.idConsulta = id;
        this.idNutricionista = idNutricionista;
        this.idPaciente = idPaciente;
        this.dtConsulta = data;
        this.hrConsulta = hora;
        this.dsConsulta = descricao;
        this.stConsulta = status;
        this.valorConsulta = valor;
        this.stPagamento = pagamento;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer id) {
        idConsulta = id;
    }

    public Integer getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(Integer v) {
        idNutricionista = v;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer v) {
        idPaciente = v;
    }

    public Date getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(Date v) {
        dtConsulta = v;
    }

    public Time getHrConsulta() {
        return hrConsulta;
    }

    public void setHrConsulta(Time v) {
        hrConsulta = v;
    }

    public String getDsConsulta() {
        return dsConsulta;
    }

    public void setDsConsulta(String v) {
        dsConsulta = v;
    }

    public String getStConsulta() {
        return stConsulta;
    }

    public void setStConsulta(String v) {
        stConsulta = v;
    }

    public Double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(Double v) {
        valorConsulta = v;
    }

    public String getStPagamento() {
        return stPagamento;
    }

    public void setStPagamento(String v) {
        stPagamento = v;
    }

    @Override
    public String toString() {
        return "Id: " + idConsulta + " | Paciente: " + idPaciente + " | " + dtConsulta + " " + hrConsulta +
                " | " + stConsulta + " | R$ " + valorConsulta + " | Pagamento: " + stPagamento + " | " + dsConsulta;
    }
}
