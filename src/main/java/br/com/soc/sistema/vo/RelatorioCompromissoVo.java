package br.com.soc.sistema.vo;

public class RelatorioCompromissoVo {

    private String funcionarioId;
    private String funcionarioNome;
    private String agendaId;
    private String agendaNome;
    private String data;
    private String hora;

    public RelatorioCompromissoVo() {
    }

    public String getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(String funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getAgendaNome() {
        return agendaNome;
    }

    public void setAgendaNome(String agendaNome) {
        this.agendaNome = agendaNome;
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
}