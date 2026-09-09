package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaAction extends Action {

    // lista as agendas cadastradas
    private List<AgendaVo> agendas = new ArrayList<>();

    // responsável pelas regras das agendas
    private AgendaBusiness business = new AgendaBusiness();

    // representa a agenda sendo cadastrada ou editada
    private AgendaVo agendaVo = new AgendaVo();

    public String todos() {

        // busca todas as agendas cadastradas
        agendas.addAll(business.trazerTodasAsAgendas());

        return SUCCESS;
    }

    public String novo() {

        // verifica se o nome foi informado
        if (agendaVo.getNome() == null)
            return INPUT;

        // verifica se é uma nova agenda ou uma edição
        if (agendaVo.getRowid() == null
                || agendaVo.getRowid().trim().isEmpty()) {

            // cadastra uma nova agenda
            business.salvarAgenda(agendaVo);

        } else {

            // atualiza uma agenda existente
            business.atualizarAgenda(agendaVo);
        }

        return REDIRECT;
    }

    public String editar() {

        // verifica se foi informado um ID válido
        if (agendaVo.getRowid() == null
                || agendaVo.getRowid().trim().isEmpty())
            return REDIRECT;

        // busca a agenda que será editada
        agendaVo = business.buscarAgendaPor(
            Integer.parseInt(agendaVo.getRowid())
        );

        // carrega todas as agendas para a tabela
        agendas.addAll(
            business.trazerTodasAsAgendas()
        );

        return INPUT;
    }

    public String excluir() {

        // verifica se foi informado um ID válido
        if (agendaVo.getRowid() == null
                || agendaVo.getRowid().trim().isEmpty())
            return REDIRECT;

        Integer codigo = Integer.parseInt(
            agendaVo.getRowid()
        );

        // verifica se a agenda possui compromissos
        if (business.possuiCompromissos(codigo)) {

            // impede a exclusão da agenda
            return REDIRECT;
        }

        // exclui a agenda quando não existem compromissos
        business.excluirAgenda(codigo);

        return REDIRECT;
    }

    public List<AgendaVo> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<AgendaVo> agendas) {
        this.agendas = agendas;
    }

    public AgendaVo getAgendaVo() {
        return agendaVo;
    }

    public void setAgendaVo(AgendaVo agendaVo) {
        this.agendaVo = agendaVo;
    }
}