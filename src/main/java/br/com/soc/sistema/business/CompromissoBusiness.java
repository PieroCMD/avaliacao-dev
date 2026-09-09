package br.com.soc.sistema.business;

import java.time.LocalTime;
import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoBusiness {

    private CompromissoDao dao;
    private AgendaDao agendaDao;

    public CompromissoBusiness() {

        // cria os objetos responsáveis pelo acesso ao banco
        this.dao = new CompromissoDao();
        this.agendaDao = new AgendaDao();
    }

    // verifica se o horário está dentro do período da agenda
    private void validarPeriodo(CompromissoVo compromissoVo) {

        // busca a agenda escolhida pelo usuário
        AgendaVo agenda = agendaDao.findAgendaById(
            Integer.parseInt(compromissoVo.getAgendaId())
        );

        // converte o horário informado para facilitar a validação
        LocalTime hora = LocalTime.parse(
            compromissoVo.getHora()
        );

        String periodo = agenda.getPeriodo();

        // verifica se o horário está fora do período da manhã
        if (periodo.equals("MANHA") && hora.getHour() >= 12) {

            throw new IllegalArgumentException(
                "O horário do compromisso está fora da disponibilidade da agenda."
            );
        }

        // verifica se o horário está fora do período da tarde
        if (periodo.equals("TARDE") && hora.getHour() < 12) {

            throw new IllegalArgumentException(
                "O horário do compromisso está fora da disponibilidade da agenda."
            );
        }
    }

    public void salvarCompromisso(CompromissoVo compromissoVo) {

        // valida o período antes de salvar
        validarPeriodo(compromissoVo);

        // salva o compromisso depois da validação
        dao.insertCompromisso(compromissoVo);
    }

    public List<CompromissoVo> trazerTodosOsCompromissos() {

        // busca todos os compromissos cadastrados
        return dao.findAllCompromissos();
    }

    public CompromissoVo buscarCompromissoPor(Integer codigo) {

        // busca um compromisso específico para edição
        return dao.findCompromissoById(codigo);
    }

    public void atualizarCompromisso(CompromissoVo compromissoVo) {

        // valida o período antes de atualizar
        validarPeriodo(compromissoVo);

        // atualiza o compromisso depois da validação
        dao.updateCompromisso(compromissoVo);
    }

    public void excluirCompromisso(Integer codigo) {

        // exclui o compromisso pelo seu código
        dao.deleteCompromisso(codigo);
    }
}