package com.example.ProductionManagementSystem.Service;

import com.example.ProductionManagementSystem.Exception.ErrorResponse;
import com.example.ProductionManagementSystem.Model.Process;
import com.example.ProductionManagementSystem.Model.ProcessLog;
import com.example.ProductionManagementSystem.Model.Processes.ProcessInput;
import com.example.ProductionManagementSystem.Model.Processes.ProcessOutput;
import com.example.ProductionManagementSystem.Repo.ProcessLogRepository;
import com.example.ProductionManagementSystem.Repo.ProcessRepository;
import com.example.ProductionManagementSystem.Model.Order;
import com.example.ProductionManagementSystem.Repo.OrderRepository;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.Repo.Processes.ProcessInputRepository;
import com.example.ProductionManagementSystem.Repo.Processes.ProcessOutputRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ProcessRepository processRepository;
    private final ProcessInputRepository processInputRepository;
    private final ProcessOutputRepository processOutputRepository;
    private final ProcessLogRepository processLogRepository;
    private final OrderRepository orderRepository;

    public byte[] generateOrderReport(Integer orderId) throws ServiceException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_ORDER));

        List<Process> processes = processRepository.findByOrderId(orderId);
        List<ProcessLog> processLogs = processLogRepository.findByOrderId(orderId);
        List<ProcessInput> processInputs = processInputRepository.findByProcessIdIn(
                processes.stream().map(Process::getId).toList());
        List<ProcessOutput> processOutputs = processOutputRepository.findByProcessIdIn(
                processes.stream().map(Process::getId).toList());

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Захиалгын тайлан", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph orderDetailsTitle = new Paragraph("Захиалгын дэлгэрэнгүй", sectionFont);
            document.add(orderDetailsTitle);

            PdfPTable orderTable = new PdfPTable(2);
            orderTable.setWidthPercentage(100);
            orderTable.setSpacingBefore(10f);
            orderTable.setSpacingAfter(10f);

            addTableHeader(orderTable, "Захиалгын дугаар", order.getId().toString());
            addTableHeader(orderTable, "Захиалгын огноо", order.getOrderDate().toString());
            addTableHeader(orderTable, "Харилцагчийн нэр", order.getCustomerName());
            addTableHeader(orderTable, "Түүхий эдийн өнгө", order.getFiberColor());
            addTableHeader(orderTable, "Түүхий эдийн төрөл", order.getFiberType());
            addTableHeader(orderTable, "Төлөв", order.getStatus().toString());

            document.add(orderTable);

            Paragraph processesTitle = new Paragraph("Процессууд", sectionFont);
            document.add(processesTitle);

            PdfPTable processTable = new PdfPTable(5);
            processTable.setWidthPercentage(100);
            processTable.setSpacingBefore(10f);
            processTable.setSpacingAfter(10f);
            processTable.setWidths(new float[]{2, 3, 2, 3, 3});

            addTableHeader(processTable, "Процессын дугаар", "Процесс нэр", "Төлөв", "Хэрэглэгчийн нэр", "Огноо цаг");

            for (Process process : processes) {
                addTableCell(processTable, process.getId().toString());
                addTableCell(processTable, process.getProcessName());
                addTableCell(processTable, process.getStatus().toString());
                addTableCell(processTable, process.getUsername() != null ? process.getUsername() : "N/A");
                addTableCell(processTable, process.getDateTime() != null ? process.getDateTime().toString() : "N/A");
            }

            document.add(processTable);

            Paragraph inputsTitle = new Paragraph("Процесс оролтууд", sectionFont);
            document.add(inputsTitle);

            PdfPTable inputTable = new PdfPTable(6);
            inputTable.setWidthPercentage(100);
            inputTable.setSpacingBefore(10f);
            inputTable.setSpacingAfter(10f);
            inputTable.setWidths(new float[]{2, 3, 3, 2, 2, 2});

            addTableHeader(inputTable, "Оролтын дугаар", "Процессын дугаар", "Материал", "Жин", "Өнгө", "Огноо цаг");

            for (ProcessInput input : processInputs) {
                addTableCell(inputTable, input.getId().toString());
                addTableCell(inputTable, input.getProcessId().toString());
                addTableCell(inputTable, input.getMaterial());
                addTableCell(inputTable, input.getWeight() != null ? input.getWeight().toString() : "N/A");
                addTableCell(inputTable, input.getColor() != null ? input.getColor() : "N/A");
                addTableCell(inputTable, input.getDateTime() != null ? input.getDateTime().toString() : "N/A");
            }

            document.add(inputTable);

            Paragraph outputsTitle = new Paragraph("Процессын гаралтууд", sectionFont);
            document.add(outputsTitle);

            PdfPTable outputTable = new PdfPTable(7);
            outputTable.setWidthPercentage(100);
            outputTable.setSpacingBefore(10f);
            outputTable.setSpacingAfter(10f);
            outputTable.setWidths(new float[]{2, 3, 2, 3, 2, 3, 2});

            addTableHeader(outputTable, "Гаралтын дугаар", "Процессын дугаар", "Төрөл", "Материал", "Жин", "Өнгө", "Огноо цаг");

            for (ProcessOutput output : processOutputs) {
                addTableCell(outputTable, output.getId().toString());
                addTableCell(outputTable, output.getProcessId().toString());
                addTableCell(outputTable, output.getType().toString());
                addTableCell(outputTable, output.getMaterial());
                addTableCell(outputTable, output.getWeight() != null ? output.getWeight().toString() : "N/A");
                addTableCell(outputTable, output.getColor() != null ? output.getColor() : "N/A");
                addTableCell(outputTable, output.getDateTime() != null ? output.getDateTime().toString() : "N/A");
            }

            document.add(outputTable);

            Paragraph logsTitle = new Paragraph("Процессын бүртгэлүүд", sectionFont);
            document.add(logsTitle);

            PdfPTable logTable = new PdfPTable(8);
            logTable.setWidthPercentage(100);
            logTable.setSpacingBefore(10f);
            logTable.setSpacingAfter(10f);
            logTable.setWidths(new float[]{2, 3, 3, 2, 3, 3, 3, 3});

            addTableHeader(logTable, "Бүртгэлийн дугаар", "Захиалгын дугаар", "Процесс нэр", "Хэрэглэгчийн нэр", "Оролтын материал", "Гаралтын материал", "Процесс эхлэх цаг", "Процесс дуусах цаг");

            for (ProcessLog log : processLogs) {
                addTableCell(logTable, log.getId().toString());
                addTableCell(logTable, log.getOrderId().toString());
                addTableCell(logTable, log.getProcessName());
                addTableCell(logTable, log.getUsername() != null ? log.getUsername() : "N/A");
                addTableCell(logTable, log.getInputMaterial() != null ? log.getInputMaterial() : "N/A");
                addTableCell(logTable, log.getOutputMaterial() != null ? log.getOutputMaterial() : "N/A");
                addTableCell(logTable, log.getProcessStartTime() != null ? log.getProcessStartTime().toString() : "N/A");
                addTableCell(logTable, log.getProcessEndTime() != null ? log.getProcessEndTime().toString() : "N/A");
            }

            document.add(logTable);

            document.close();
        } catch (Exception e) {
            throw new ServiceException(ErrorResponse.NO_CONTENT);
        }

        return out.toByteArray();
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header));
            headerCell.setBackgroundColor(Color.LIGHT_GRAY);
            headerCell.setPadding(5);
            table.addCell(headerCell);
        }
    }

    private void addTableCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(5);
        table.addCell(cell);
    }
}