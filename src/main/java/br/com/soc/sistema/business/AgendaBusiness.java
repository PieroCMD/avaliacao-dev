package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {

    // acesso aos dados das agendas
    private AgendaDao dao;

    // acesso aos dados dos compromissos
    private CompromissoDao compromissoDao;

    public AgendaBusiness() {

        // cria o objeto responsável pelas agendas
        this.dao = new AgendaDao();

        // cria o objeto responsável pelos compromissos
        this.compromissoDao = new CompromissoDao();
    }

    public void salvarAgenda(AgendaVo agendaVo) {

        // salva uma nova agenda
        dao.insertAgenda(agendaVo);
    }

    public List<AgendaVo> trazerTodasAsAgendas() {

        // busca todas as agendas cadastradas
        return dao.findAllAgendas();
    }

    public AgendaVo buscarAgendaPor(Integer codigo) {

        // busca uma agenda pelo seu código
        return dao.findAgendaById(codigo);
    }

    public void atualizarAgenda(AgendaVo agendaVo) {

        // atualiza uma agenda existente
        dao.updateAgenda(agendaVo);
    }

    public boolean possuiCompromissos(Integer codigo) {

        // verifica se existem compromissos vinculados à agenda
        return compromissoDao.existeCompromissoNaAgenda(codigo);
    }

    public void excluirAgenda(Integer codigo) {

        // verifica se a agenda possui compromissos
        if (possuiCompromissos(codigo)) {
            return;
        }

        // exclui a agenda quando não possui compromissos
        dao.deleteAgenda(codigo);
    }
}