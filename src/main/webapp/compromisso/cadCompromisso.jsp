<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Gerenciamento de Compromissos</title>

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

    </style>

</head>

<body>

    <div class="container">

        <!-- título da página -->
        <div class="text-center">

            <h1 class="titulo">
                Gerenciamento de Compromissos
            </h1>

            <p class="subtitulo">
                Cadastre e consulte os compromissos do sistema.
            </p>

        </div>


        <!-- mostra as mensagens de erro -->
        <s:if test="hasActionErrors()">

            <div class="alert alert-danger" role="alert">

                <s:actionerror/>

            </div>

        </s:if>


        <!-- card do formulário -->
        <div class="card">

            <div class="card-header">

                <!-- mostra um título diferente quando estiver editando -->
                <s:if test="compromissoVo.rowid != null">

                    Editar Compromisso

                </s:if>

                <!-- mostra o título de cadastro normalmente -->
                <s:else>

                    Novo Compromisso

                </s:else>

            </div>


            <div class="card-body">

                <!-- formulário usado para cadastrar ou editar compromissos -->
                <s:form action="novoCompromissos.action">

                    <!-- mantém o ID durante a edição -->
                    <s:hidden name="compromissoVo.rowid"/>


                    <div class="row g-3">

                        <!-- campo de funcionário -->
                        <div class="col-md-3">

                            <label class="form-label">
                                Funcionário
                            </label>

                            <select name="compromissoVo.funcionarioId"
                                    class="form-select">

                                <option value="">
                                    Selecione
                                </option>

                                <!-- percorre todos os funcionários disponíveis -->
                                <s:iterator value="funcionarios">

                                    <!-- seleciona automaticamente o funcionário durante a edição -->
                                    <option value="<s:property value='rowid'/>"
                                        <s:if test="rowid == compromissoVo.funcionarioId">
                                            selected
                                        </s:if>>

                                        <s:property value="nome"/>

                                    </option>

                                </s:iterator>

                            </select>

                        </div>


                        <!-- campo de agenda -->
                        <div class="col-md-3">

                            <label class="form-label">
                                Agenda
                            </label>

                            <select name="compromissoVo.agendaId"
                                    class="form-select">

                                <option value="">
                                    Selecione
                                </option>

                                <!-- percorre todas as agendas disponíveis -->
                                <s:iterator value="agendas">

                                    <!-- seleciona automaticamente a agenda durante a edição -->
                                    <option value="<s:property value='rowid'/>"
                                        <s:if test="rowid == compromissoVo.agendaId">
                                            selected
                                        </s:if>>

                                        <s:property value="nome"/>

                                    </option>

                                </s:iterator>

                            </select>

                        </div>


                        <!-- campo de data -->
                        <div class="col-md-3">

                            <label class="form-label">
                                Data
                            </label>

                            <!-- preenche a data automaticamente durante a edição -->
                            <input type="date"
                                   name="compromissoVo.data"
                                   value="<s:property value='compromissoVo.data'/>"
                                   class="form-control">

                        </div>


                        <!-- campo de horário -->
                        <div class="col-md-2">

                            <label class="form-label">
                                Horário
                            </label>

                            <!-- preenche o horário automaticamente durante a edição -->
                            <input type="time"
                                   name="compromissoVo.hora"
                                   value="<s:property value='compromissoVo.hora'/>"
                                   class="form-control">

                        </div>


                        <!-- botão para salvar -->
                        <div class="col-md-1 d-flex align-items-end">

                            <button type="submit"
                                    class="btn btn-success btn-cadastrar w-100">

                                Salvar

                            </button>

                        </div>

                    </div>

                </s:form>

            </div>

        </div>


        <!-- card da tabela -->
        <div class="card tabela-card">

            <div class="card-header">

                Compromissos cadastrados

            </div>


            <div class="card-body p-0">

                <div class="table-responsive">

                    <!-- tabela com os compromissos cadastrados -->
                    <table class="table table-hover">

                        <thead>

                            <tr>

                                <th>ID</th>
                                <th>ID Funcionário</th>
                                <th>ID Agenda</th>
                                <th>Data</th>
                                <th>Horário</th>
                                <th class="text-center">Ações</th>

                            </tr>

                        </thead>


                        <tbody>

                            <!-- percorre todos os compromissos cadastrados -->
                            <s:iterator value="compromissos">

                                <tr>

                                    <!-- ID do compromisso -->
                                    <td>

                                        <strong>
                                            <s:property value="rowid"/>
                                        </strong>

                                    </td>


                                    <!-- ID do funcionário -->
                                    <td>

                                        <s:property value="funcionarioId"/>

                                    </td>


                                    <!-- ID da agenda -->
                                    <td>

                                        <s:property value="agendaId"/>

                                    </td>


                                    <!-- data do compromisso -->
                                    <td>

                                        <s:property value="data"/>

                                    </td>


                                    <!-- horário do compromisso -->
                                    <td>

                                        <s:property value="hora"/>

                                    </td>


                                    <!-- botões de ação -->
                                    <td class="text-center">

                                        <!-- botão para editar o compromisso -->
                                        <a href="editarCompromissos.action?compromissoVo.rowid=<s:property value='rowid'/>"
                                           class="btn btn-primary btn-sm">

                                            Editar

                                        </a>


                                        <!-- botão para excluir o compromisso -->
                                        <a href="excluirCompromissos.action?compromissoVo.rowid=<s:property value='rowid'/>"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Deseja realmente excluir este compromisso?');">

                                            Excluir

                                        </a>

                                    </td>

                                </tr>

                            </s:iterator>


                            <!-- mostra uma mensagem quando não existem compromissos -->
                            <s:if test="compromissos.isEmpty()">

                                <tr>

                                    <td colspan="6"
                                        class="text-center text-muted py-4">

                                        Nenhum compromisso cadastrado.

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