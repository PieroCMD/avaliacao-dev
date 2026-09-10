package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.RelatorioCompromissoDao;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoBusiness {

    // acesso aos dados do relatório
    private RelatorioCompromissoDao dao;

    public RelatorioCompromissoBusiness() {

        // cria o objeto responsável pelo acesso ao banco
        this.dao = new RelatorioCompromissoDao();
    }

    // busca os compromissos dentro do período informado
    public List<RelatorioCompromissoVo> buscarPorPeriodo(
            String dataInicial, String dataFinal) {

        // verifica se a data inicial foi informada
        if (dataInicial == null || dataInicial.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "A data inicial deve ser informada."
            );
        }

        // verifica se a data final foi informada
        if (dataFinal == null || dataFinal.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "A data final deve ser informada."
            );
        }

        // verifica se a data inicial é maior que a data final
        if (dataInicial.compareTo(dataFinal) > 0) {

            throw new IllegalArgumentException(
                "A data inicial não pode ser maior que a data final."
            );
        }

        // busca os dados no banco
        return dao.findCompromissosPorPeriodo(
            dataInicial,
            dataFinal
        );
    }
}