package br.com.soc.sistema.vo;

public class CompromissoVo {

    private String rowid;
    private String funcionarioId;
    private String agendaId;
    private String data;
    private String hora;

    public CompromissoVo() {
    }

    public CompromissoVo(String rowid, String funcionarioId, String agendaId,
            String data, String hora) {

        this.rowid = rowid;
        this.funcionarioId = funcionarioId;
        this.agendaId = agendaId;
        this.data = data;
        this.hora = hora;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(String funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "CompromissoVo [rowid=" + rowid
                + ", funcionarioId=" + funcionarioId
                + ", agendaId=" + agendaId
                + ", data=" + data
                + ", hora=" + hora + "]";
    }
}