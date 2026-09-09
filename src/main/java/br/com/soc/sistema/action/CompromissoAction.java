package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class CompromissoAction extends Action {

    // lista os compromissos cadastrados
    private List<CompromissoVo> compromissos = new ArrayList<>();

    // lista os funcionários para o formulário
    private List<FuncionarioVo> funcionarios = new ArrayList<>();

    // lista as agendas para o formulário
    private List<AgendaVo> agendas = new ArrayList<>();

    // responsável pelas regras dos compromissos
    private CompromissoBusiness business = new CompromissoBusiness();

    // responsável pelas operações dos funcionários
    private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

    // responsável pelas operações das agendas
    private AgendaBusiness agendaBusiness = new AgendaBusiness();

    // representa o compromisso sendo cadastrado ou editado
    private CompromissoVo compromissoVo = new CompromissoVo();

    public String todos() {

        // carrega os compromissos existentes
        compromissos.addAll(
            business.trazerTodosOsCompromissos()
        );

        // carrega os funcionários para o formulário
        funcionarios.addAll(
            funcionarioBusiness.trazerTodosOsFuncionarios()
        );

        // carrega as agendas para o formulário
        agendas.addAll(
            agendaBusiness.trazerTodasAsAgendas()
        );

        return SUCCESS;
    }

    public String novo() {

        // verifica se os campos obrigatórios foram preenchidos
        if (compromissoVo.getFuncionarioId() == null
                || compromissoVo.getAgendaId() == null
                || compromissoVo.getData() == null
                || compromissoVo.getHora() == null) {

            return INPUT;
        }

        try {

            // se não possui ID, cadastra um novo compromisso
            if (compromissoVo.getRowid() == null
                    || compromissoVo.getRowid().trim().isEmpty()) {

                business.salvarCompromisso(compromissoVo);

            } else {

                // se possui ID, atualiza o compromisso existente
                business.atualizarCompromisso(compromissoVo);
            }

        } catch (IllegalArgumentException e) {

            // adiciona a mensagem de erro para ser exibida na tela
            addActionError(e.getMessage());

            // carrega novamente os funcionários
            funcionarios.addAll(
                funcionarioBusiness.trazerTodosOsFuncionarios()
            );

            // carrega novamente as agendas
            agendas.addAll(
                agendaBusiness.trazerTodasAsAgendas()
            );

            // volta para a tela do formulário
            return INPUT;
        }

        return REDIRECT;
    }

    public String editar() {

        // verifica se foi informado um ID válido
        if (compromissoVo.getRowid() == null
                || compromissoVo.getRowid().trim().isEmpty()) {

            return REDIRECT;
        }

        // busca o compromisso que será editado
        compromissoVo = business.buscarCompromissoPor(
            Integer.parseInt(compromissoVo.getRowid())
        );

        // carrega os compromissos para manter a tabela preenchida
        compromissos.addAll(
            business.trazerTodosOsCompromissos()
        );

        // carrega os funcionários para o formulário
        funcionarios.addAll(
            funcionarioBusiness.trazerTodosOsFuncionarios()
        );

        // carrega as agendas para o formulário
        agendas.addAll(
            agendaBusiness.trazerTodasAsAgendas()
        );

        return INPUT;
    }

    public String excluir() {

        // verifica se foi informado um ID válido
        if (compromissoVo.getRowid() == null
                || compromissoVo.getRowid().trim().isEmpty()) {

            return REDIRECT;
        }

        // exclui o compromisso através da camada Business
        business.excluirCompromisso(
            Integer.parseInt(compromissoVo.getRowid())
        );

        // volta para a lista de compromissos
        return REDIRECT;
    }

    public List<CompromissoVo> getCompromissos() {
        return compromissos;
    }

    public void setCompromissos(List<CompromissoVo> compromissos) {
        this.compromissos = compromissos;
    }

    public List<FuncionarioVo> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioVo> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<AgendaVo> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<AgendaVo> agendas) {
        this.agendas = agendas;
    }

    public CompromissoVo getCompromissoVo() {
        return compromissoVo;
    }

    public void setCompromissoVo(CompromissoVo compromissoVo) {
        this.compromissoVo = compromissoVo;
    }
}