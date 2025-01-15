package com.example.ProductionManagementSystem.Controller;

import com.example.ProductionManagementSystem.Service.ReportService;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<byte[]> generateOrderReport(@PathVariable Integer orderId) {
        try {
            byte[] pdfContents = reportService.generateOrderReport(orderId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String filename = "Order_Report_" + orderId + ".pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContents);
        } catch (ServiceException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}