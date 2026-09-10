package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoDao extends Dao {

    // busca os compromissos dentro do período informado
    public List<RelatorioCompromissoVo> findCompromissosPorPeriodo(
            String dataInicial, String dataFinal) {

        StringBuilder query = new StringBuilder(
            "SELECT "
            + "f.rowid funcionario_id, "
            + "f.nm_funcionario funcionario_nome, "
            + "a.rowid agenda_id, "
            + "a.nm_agenda agenda_nome, "
            + "c.dt_compromisso data, "
            + "c.hr_compromisso hora "
            + "FROM compromisso c "
            + "JOIN funcionario f "
            + "ON c.funcionario_id = f.rowid "
            + "JOIN agenda a "
            + "ON c.agenda_id = a.rowid "
            + "WHERE c.dt_compromisso BETWEEN ? AND ? "
            + "ORDER BY c.dt_compromisso, c.hr_compromisso"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            // converte a data inicial para o formato do banco
            ps.setDate(
                1,
                Date.valueOf(dataInicial)
            );

            // converte a data final para o formato do banco
            ps.setDate(
                2,
                Date.valueOf(dataFinal)
            );

            try (ResultSet rs = ps.executeQuery()) {

                List<RelatorioCompromissoVo> relatorios =
                    new ArrayList<>();

                while (rs.next()) {

                    RelatorioCompromissoVo vo =
                        new RelatorioCompromissoVo();

                    // recupera o código do funcionário
                    vo.setFuncionarioId(
                        rs.getString("funcionario_id")
                    );

                    // recupera o nome do funcionário
                    vo.setFuncionarioNome(
                        rs.getString("funcionario_nome")
                    );

                    // recupera o código da agenda
                    vo.setAgendaId(
                        rs.getString("agenda_id")
                    );

                    // recupera o nome da agenda
                    vo.setAgendaNome(
                        rs.getString("agenda_nome")
                    );

                    // recupera a data do compromisso
                    vo.setData(
                        rs.getString("data")
                    );

                    // recupera o horário do compromisso
                    vo.setHora(
                        rs.getString("hora")
                    );

                    relatorios.add(vo);
                }

                return relatorios;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}