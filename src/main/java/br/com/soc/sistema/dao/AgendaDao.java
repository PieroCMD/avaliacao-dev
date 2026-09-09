package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.AgendaVo;

public class AgendaDao extends Dao {

    // cadastra uma nova agenda
    public void insertAgenda(AgendaVo agendaVo) {

        StringBuilder query = new StringBuilder(
            "INSERT INTO agenda (nm_agenda, periodo) VALUES (?, ?)"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            int i = 1;

            // define o nome da agenda
            ps.setString(i++, agendaVo.getNome());

            // define o período da agenda
            ps.setString(i++, agendaVo.getPeriodo());

            // executa o INSERT
            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // busca todas as agendas cadastradas
    public List<AgendaVo> findAllAgendas() {

        StringBuilder query = new StringBuilder(
            "SELECT rowid id, nm_agenda nome, periodo FROM agenda"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery()
        ) {

            AgendaVo vo;
            List<AgendaVo> agendas = new ArrayList<>();

            while (rs.next()) {

                vo = new AgendaVo();

                // recupera o ID da agenda
                vo.setRowid(rs.getString("id"));

                // recupera o nome da agenda
                vo.setNome(rs.getString("nome"));

                // recupera o período da agenda
                vo.setPeriodo(rs.getString("periodo"));

                agendas.add(vo);
            }

            return agendas;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Collections.emptyList();
    }


    // busca uma agenda pelo seu código
    public AgendaVo findAgendaById(Integer codigo) {

        StringBuilder query = new StringBuilder(
            "SELECT rowid id, nm_agenda nome, periodo "
            + "FROM agenda WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            // informa qual agenda será buscada
            ps.setInt(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    AgendaVo agenda = new AgendaVo();

                    // recupera o ID da agenda
                    agenda.setRowid(rs.getString("id"));

                    // recupera o nome da agenda
                    agenda.setNome(rs.getString("nome"));

                    // recupera o período da agenda
                    agenda.setPeriodo(rs.getString("periodo"));

                    return agenda;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // atualiza uma agenda existente
    public void updateAgenda(AgendaVo agendaVo) {

        StringBuilder query = new StringBuilder(
            "UPDATE agenda "
            + "SET nm_agenda = ?, periodo = ? "
            + "WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            int i = 1;

            // atualiza o nome da agenda
            ps.setString(i++, agendaVo.getNome());

            // atualiza o período da agenda
            ps.setString(i++, agendaVo.getPeriodo());

            // informa qual agenda será atualizada
            ps.setInt(
                i++,
                Integer.parseInt(agendaVo.getRowid())
            );

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    // exclui uma agenda pelo seu ID/código
    public void deleteAgenda(Integer codigo) {

        StringBuilder query = new StringBuilder(
            "DELETE FROM agenda WHERE rowid = ?"
        );

        try (
            Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(query.toString())
        ) {

            // informa qual agenda será excluída
            ps.setInt(1, codigo);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

}