package br.com.soc.sistema.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.soc.sistema.business.RelatorioCompromissoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioCompromissoExcelAction extends Action {

    private String dataInicial;
    private String dataFinal;

    private InputStream arquivo;

    private RelatorioCompromissoBusiness business =
        new RelatorioCompromissoBusiness();

    public String gerar() {

        List<RelatorioCompromissoVo> relatorios =
            business.buscarPorPeriodo(dataInicial, dataFinal);

        try (
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream()
        ) {

            Sheet sheet = workbook.createSheet("Compromissos");

            Row cabecalho = sheet.createRow(0);

            cabecalho.createCell(0).setCellValue("Código Funcionário");
            cabecalho.createCell(1).setCellValue("Funcionário");
            cabecalho.createCell(2).setCellValue("Código Agenda");
            cabecalho.createCell(3).setCellValue("Agenda");
            cabecalho.createCell(4).setCellValue("Data");
            cabecalho.createCell(5).setCellValue("Horário");

            int linha = 1;

            for (RelatorioCompromissoVo relatorio : relatorios) {

                Row row = sheet.createRow(linha++);

                row.createCell(0).setCellValue(
                    relatorio.getFuncionarioId()
                );

                row.createCell(1).setCellValue(
                    relatorio.getFuncionarioNome()
                );

                row.createCell(2).setCellValue(
                    relatorio.getAgendaId()
                );

                row.createCell(3).setCellValue(
                    relatorio.getAgendaNome()
                );

                row.createCell(4).setCellValue(
                    relatorio.getData()
                );

                row.createCell(5).setCellValue(
                    relatorio.getHora()
                );
            }

            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);

            arquivo = new ByteArrayInputStream(
                outputStream.toByteArray()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return ERROR;
        }

        return SUCCESS;
    }

    public InputStream getArquivo() {
        return arquivo;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}