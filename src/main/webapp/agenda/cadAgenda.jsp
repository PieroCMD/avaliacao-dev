<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Gerenciamento de Agendas</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/webjars/bootstrap/5.1.3/css/bootstrap.min.css">

    <style>

        body {
            min-height: 100vh;
            background: #f4f6f9;
        }

        .container {
            padding-top: 50px;
            padding-bottom: 50px;
        }

        .titulo {
            font-weight: 600;
            color: #212529;
        }

        .subtitulo {
            color: #6c757d;
            margin-bottom: 30px;
        }

        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        }

        .card-header {
            background: #ffffff;
            border-bottom: 1px solid #eeeeee;
            padding: 20px;
            font-weight: 600;
            font-size: 18px;
        }

        .card-body {
            padding: 25px;
        }

        .form-label {
            font-weight: 500;
            margin-bottom: 7px;
        }

        .form-control,
        .form-select {
            min-height: 42px;
            border-radius: 6px;
        }

        .btn-cadastrar {
            min-height: 42px;
            padding-left: 25px;
            padding-right: 25px;
        }

        .tabela-card {
            margin-top: 30px;
        }

        .table {
            margin-bottom: 0;
            vertical-align: middle;
        }

        .table thead {
            background: #f8f9fa;
        }

        .table th {
            font-weight: 600;
            color: #495057;
        }

        .table td {
            color: #495057;
        }

        .btn-editar {
            border-radius: 6px;
            padding: 6px 16px;
        }

        .btn-excluir {
            border-radius: 6px;
            padding: 6px 16px;
        }

        .id-teste {
            color: #6c757d;
            font-size: 13px;
            margin-bottom: 15px;
        }

        .periodo {
            font-weight: 500;
        }

    </style>

</head>

<body>

<div class="container">

    <!-- título -->
    <div class="text-center">

        <h1 class="titulo">
            Gerenciamento de Agendas
        </h1>

        <p class="subtitulo">
            Cadastre, consulte e edite as agendas do sistema.
        </p>

    </div>


    <!-- formulário -->
    <div class="card">

        <div class="card-header">

            <s:if test="agendaVo.rowid == null || agendaVo.rowid.trim().isEmpty()">

                Nova Agenda

            </s:if>

            <s:else>

                Editar Agenda

            </s:else>

        </div>


        <div class="card-body">

            <s:form action="novoAgendas.action">

                <!-- ID da agenda -->
                <s:hidden name="agendaVo.rowid"/>


                <!-- ID temporário para nosso teste -->
                <s:if test="agendaVo.rowid != null && !agendaVo.rowid.trim().isEmpty()">

                    <div class="id-teste">

                        Editando agenda de ID:

                        <strong>

                            <s:property value="agendaVo.rowid"/>

                        </strong>

                    </div>

                </s:if>


                <div class="row g-3">


                    <!-- nome -->
                    <div class="col-md-7">

                        <label class="form-label">
                            Nome da agenda
                        </label>

                        <s:textfield
                            cssClass="form-control"
                            name="agendaVo.nome"
                            placeholder="Digite o nome da agenda"/>

                    </div>


                    <!-- período -->
                    <div class="col-md-3">

                        <label class="form-label">
                            Período disponível
                        </label>

                        <s:select
                            cssClass="form-select"
                            name="agendaVo.periodo"
                            list="#{
                                'MANHA':'Manhã',
                                'TARDE':'Tarde',
                                'AMBOS':'Ambos'
                            }"
                            headerKey=""
                            headerValue="Selecione"/>

                    </div>


                    <!-- botão -->
                    <div class="col-md-2 d-flex align-items-end">

                        <button
                            type="submit"
                            class="btn btn-success btn-cadastrar w-100">

                            <s:if test="agendaVo.rowid == null || agendaVo.rowid.trim().isEmpty()">

                                Cadastrar

                            </s:if>

                            <s:else>

                                Atualizar

                            </s:else>

                        </button>

                    </div>

                </div>

            </s:form>

        </div>

    </div>


    <!-- tabela -->
    <div class="card tabela-card">

        <div class="card-header">

            Agendas cadastradas

        </div>

        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-hover">

                    <thead>

                        <tr>

                            <th class="ps-4">
                                ID
                            </th>

                            <th>
                                Nome
                            </th>

                            <th>
                                Período
                            </th>

                            <th class="text-center">
                                Ações
                            </th>

                        </tr>

                    </thead>


                    <tbody>

                        <s:iterator value="agendas">

                            <tr>

                                <!-- ID -->
                                <td class="ps-4">

                                    <strong>
                                        <s:property value="rowid"/>
                                    </strong>

                                </td>


                                <!-- nome -->
                                <td>

                                    <s:property value="nome"/>

                                </td>


                                <!-- período -->
                                <td>

                                    <span class="periodo">

                                        <s:if test="periodo == 'MANHA'">

                                            Manhã

                                        </s:if>

                                        <s:elseif test="periodo == 'TARDE'">

                                            Tarde

                                        </s:elseif>

                                        <s:elseif test="periodo == 'AMBOS'">

                                            Ambos

                                        </s:elseif>

                                        <s:else>

                                            <s:property value="periodo"/>

                                        </s:else>

                                    </span>

                                </td>


                                <!-- ações -->
                                <td class="text-center">

                                    <!-- botão para editar a agenda -->
                                    <a
                                        href="editarAgendas.action?agendaVo.rowid=<s:property value='rowid'/>"
                                        class="btn btn-primary btn-sm btn-editar">

                                        Editar

                                    </a>


                                    <!-- botão para excluir a agenda -->
                                    <a
                                        href="excluirAgendas.action?agendaVo.rowid=<s:property value='rowid'/>"
                                        class="btn btn-danger btn-sm btn-excluir"
                                        onclick="return confirm('Deseja realmente excluir esta agenda?');">

                                        Excluir

                                    </a>

                                </td>

                            </tr>

                        </s:iterator>


                        <!-- quando não existem agendas -->
                        <s:if test="agendas.isEmpty()">

                            <tr>

                                <td colspan="4"
                                    class="text-center text-muted py-4">

                                    Nenhuma agenda cadastrada.

                                </td>

                            </tr>

                        </s:if>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>

</body>

</html>