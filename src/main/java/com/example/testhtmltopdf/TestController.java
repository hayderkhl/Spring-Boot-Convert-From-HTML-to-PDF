package com.example.testhtmltopdf;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class TestController {
    @GetMapping("/pdf") // cannot see the dynamic variables here and images and cannot work with my  TestHtmlTOPdf.html file
    public ResponseEntity<byte[]> getPdf(Model model) throws IOException {
        HtmlConverter.convertToPdf(new File("./src/main/resources/templates/TestHtmlToPdf.html"),new File("demo-html.pdf"));
//        HtmlConverter.convertToPdf(new File("./test.html"),new File("demo-html.pdf"));
//HtmlConverter.convertToPdf(new File("./src/main/resources/static/index.html"), new File("demo-html.pdf"));
        Context context = new Context();
        context.setVariable("name", "hayder");
        Context context1 = new Context();
        context1.setVariable("num1", "30");
        int score =60;
        Context context0 = new Context();
        context0.setVariable("score", score);

        byte[] pdfBytes = Files.readAllBytes(new File("demo-html.pdf").toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "demo-html.pdf");
        headers.setContentLength(pdfBytes.length);
        return new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
    }
        @GetMapping("/pie-chart")
        public String pieChart(Model model) {
            return "pieChart";
        }

}
