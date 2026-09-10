package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.RelatorioCompromissoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoAction extends Action {

    // lista os dados que serão exibidos no relatório
    private List<RelatorioCompromissoVo> relatorios =
        new ArrayList<>();

    // datas utilizadas para filtrar o relatório
    private String dataInicial;
    private String dataFinal;

    // responsável pelas regras do relatório
    private RelatorioCompromissoBusiness business =
        new RelatorioCompromissoBusiness();

    public String todos() {

        // verifica se as datas foram informadas
        if (dataInicial == null
                || dataInicial.trim().isEmpty()
                || dataFinal == null
                || dataFinal.trim().isEmpty()) {

            return SUCCESS;
        }

        try {

            // busca os compromissos dentro do período informado
            relatorios = business.buscarPorPeriodo(
                dataInicial,
                dataFinal
            );

        } catch (IllegalArgumentException e) {

            // adiciona a mensagem de erro para a tela
            addActionError(e.getMessage());

        }

        return SUCCESS;
    }

    public List<RelatorioCompromissoVo> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(
            List<RelatorioCompromissoVo> relatorios) {

        this.relatorios = relatorios;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}