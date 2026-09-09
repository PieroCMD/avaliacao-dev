package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoDao extends Dao {

    // adiciona um novo compromisso no banco
    public void insertCompromisso(CompromissoVo compromissoVo) {

        StringBuilder query = new StringBuilder(
            "INSERT INTO compromisso "
            + "(funcionario_id, agenda_id, dt_compromisso, hr_compromisso) "
            + "VALUES (?, ?, ?, ?)"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            int i = 1;

            // define o funcionário do compromisso
            ps.setInt(
                i++,
                Integer.parseInt(compromissoVo.getFuncionarioId())
            );

            // define a agenda do compromisso
            ps.setInt(
                i++,
                Integer.parseInt(compromissoVo.getAgendaId())
            );

            // define a data do compromisso
            ps.setString(
                i++,
                compromissoVo.getData()
            );

            // define o horário do compromisso
            ps.setString(
                i++,
                compromissoVo.getHora()
            );

            // executa o INSERT
            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // busca todos os compromissos cadastrados
    public List<CompromissoVo> findAllCompromissos() {

        StringBuilder query = new StringBuilder(
            "SELECT rowid, funcionario_id, agenda_id, "
            + "dt_compromisso, hr_compromisso "
            + "FROM compromisso"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery()
        ) {

            List<CompromissoVo> compromissos = new ArrayList<>();

            while (rs.next()) {

                CompromissoVo compromisso = new CompromissoVo();

                // recupera o ID do compromisso
                compromisso.setRowid(
                    rs.getString("rowid")
                );

                // recupera o ID do funcionário
                compromisso.setFuncionarioId(
                    rs.getString("funcionario_id")
                );

                // recupera o ID da agenda
                compromisso.setAgendaId(
                    rs.getString("agenda_id")
                );

                // recupera a data
                compromisso.setData(
                    rs.getString("dt_compromisso")
                );

                // recupera o horário
                compromisso.setHora(
                    rs.getString("hr_compromisso")
                );

                compromissos.add(compromisso);
            }

            return compromissos;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Collections.emptyList();
    }


    // busca um compromisso específico pelo seu ID ou código
    public CompromissoVo findCompromissoById(Integer codigo) {

        StringBuilder query = new StringBuilder(
            "SELECT rowid, funcionario_id, agenda_id, "
            + "dt_compromisso, hr_compromisso "
            + "FROM compromisso WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            // informa o ID do compromisso que vai ser buscado
            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    CompromissoVo compromisso = new CompromissoVo();

                    // recupera o ID do compromisso
                    compromisso.setRowid(
                        rs.getString("rowid")
                    );

                    // recupera o ID do funcionário
                    compromisso.setFuncionarioId(
                        rs.getString("funcionario_id")
                    );

                    // recupera o ID da agenda
                    compromisso.setAgendaId(
                        rs.getString("agenda_id")
                    );

                    // recupera a data
                    compromisso.setData(
                        rs.getString("dt_compromisso")
                    );

                    // recupera o horário
                    compromisso.setHora(
                        rs.getString("hr_compromisso")
                    );

                    return compromisso;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // atualiza os dados de um compromisso já existente
    public void updateCompromisso(CompromissoVo compromissoVo) {

        StringBuilder query = new StringBuilder(
            "UPDATE compromisso "
            + "SET funcionario_id = ?, agenda_id = ?, "
            + "dt_compromisso = ?, hr_compromisso = ? "
            + "WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            int i = 1;

            // atualiza o funcionário
            ps.setInt(
                i++,
                Integer.parseInt(compromissoVo.getFuncionarioId())
            );

            // atualiza a agenda
            ps.setInt(
                i++,
                Integer.parseInt(compromissoVo.getAgendaId())
            );

            // atualiza a data
            ps.setString(
                i++,
                compromissoVo.getData()
            );

            // atualiza o horário
            ps.setString(
                i++,
                compromissoVo.getHora()
            );

            // informa qual compromisso vai ser atualizado
            ps.setInt(
                i++,
                Integer.parseInt(compromissoVo.getRowid())
            );

            // executa o UPDATE
            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // exclui um compromisso pelo seu ID/codigo
    public void deleteCompromisso(Integer codigo) {

        StringBuilder query = new StringBuilder(
            "DELETE FROM compromisso WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            // informa qual compromisso vai ser excluído
            ps.setInt(1, codigo);

            // executa o DELETE
            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // verifica se uma agenda tem compromissos
    public boolean existeCompromissoNaAgenda(Integer agendaId) {

        StringBuilder query = new StringBuilder(
            "SELECT COUNT(*) FROM compromisso WHERE agenda_id = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            // informa qual agenda vai ser consultada
            ps.setInt(1, agendaId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

 // exclui todos os compromissos de um funcionário
    public void deleteCompromissosPorFuncionario(Integer funcionarioId) {

        StringBuilder query = new StringBuilder(
            "DELETE FROM compromisso WHERE funcionario_id = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            // informa qual funcionário terá os compromissos excluídos
            ps.setInt(1, funcionarioId);

            // executa a exclusão
            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}