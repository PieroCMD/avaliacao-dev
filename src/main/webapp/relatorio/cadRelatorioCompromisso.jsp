<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Relatório de Compromissos</title>

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

        .form-control {
            min-height: 42px;
            border-radius: 6px;
        }

        .btn-pesquisar {
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

    <!-- título -->
    <div class="text-center">

        <h1 class="titulo">
            Relatório de Compromissos
        </h1>

        <p class="subtitulo">
            Consulte os compromissos dentro de um determinado período.
        </p>

    </div>


    <!-- mostra as mensagens de erro -->
    <s:if test="hasActionErrors()">

        <div class="alert alert-danger" role="alert">

            <s:actionerror/>

        </div>

    </s:if>


    <!-- card do filtro -->
    <div class="card">

        <div class="card-header">

            Filtro do relatório

        </div>


        <div class="card-body">

            <s:form action="relatorioCompromissos.action">

                <div class="row g-3">

                    <!-- data inicial -->
                    <div class="col-md-4">

                        <label class="form-label">
                            Data inicial
                        </label>

                        <input
                            type="date"
                            name="dataInicial"
                            value="<s:property value='dataInicial'/>"
                            class="form-control">

                    </div>


                    <!-- data final -->
                    <div class="col-md-4">

                        <label class="form-label">
                            Data final
                        </label>

                        <input
                            type="date"
                            name="dataFinal"
                            value="<s:property value='dataFinal'/>"
                            class="form-control">

                    </div>


                    <!-- botão pesquisar -->
                    <div class="col-md-4 d-flex align-items-end">

                        <button
                            type="submit"
                            class="btn btn-primary btn-pesquisar w-100">

                            Pesquisar

                        </button>

                    </div>

                </div>

            </s:form>

        </div>

    </div>


    <!-- tabela do relatório -->
    <div class="card tabela-card">

        <div class="card-header">

            Compromissos encontrados

        </div>


        <div class="card-body p-0">

            <div class="table-responsive">

                <table class="table table-hover">

                    <thead>

                        <tr>

                            <th class="ps-4">
                                Código Funcionário
                            </th>

                            <th>
                                Funcionário
                            </th>

                            <th>
                                Código Agenda
                            </th>

                            <th>
                                Agenda
                            </th>

                            <th>
                                Data
                            </th>

                            <th>
                                Horário
                            </th>

                        </tr>

                    </thead>


                    <tbody>

                        <!-- percorre os resultados do relatório -->
                        <s:iterator value="relatorios">

                            <tr>

                                <!-- código do funcionário -->
                                <td class="ps-4">

                                    <strong>
                                        <s:property value="funcionarioId"/>
                                    </strong>

                                </td>


                                <!-- nome do funcionário -->
                                <td>

                                    <s:property value="funcionarioNome"/>

                                </td>


                                <!-- código da agenda -->
                                <td>

                                    <strong>
                                        <s:property value="agendaId"/>
                                    </strong>

                                </td>


                                <!-- nome da agenda -->
                                <td>

                                    <s:property value="agendaNome"/>

                                </td>


                                <!-- data -->
                                <td>

                                    <s:property value="data"/>

                                </td>


                                <!-- horário -->
                                <td>

                                    <s:property value="hora"/>

                                </td>

                            </tr>

                        </s:iterator>


                        <!-- mostra mensagem quando não existem resultados -->
                        <s:if test="relatorios.isEmpty()">

                            <tr>

                                <td
                                    colspan="6"
                                    class="text-center text-muted py-4">

                                    Nenhum compromisso encontrado no período informado.

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