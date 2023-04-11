package com.example.testhtmltopdf;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;


@Controller
public class MyController {
    private final TemplateEngine templateEngine;

    public MyController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    @GetMapping("/Myfile")  // we can use this route to create view page by html and thymeleaf ,
    public String myPage(Model model) {

        int score = 55;
        model.addAttribute("score", score);
        String name = "haidar";
        model.addAttribute("name", name);
        float num1 = 18;
        model.addAttribute("num1", num1);
        return "TestHtmlToPdf";
    }
    @GetMapping("/generate-pdf")  // in my project i'm using this route , just i cannot convert a javascript code the html converter cannot read the script tag!!
    public ResponseEntity<InputStreamResource> generatePdf(Model model) throws IOException, java.io.IOException {
        // Load the Thymeleaf template
        String template = "TestHtmlToPdf.html";
        Context context = new Context();
        int k = 80;
        double num1 = 5.2;
        double num2 = 7.2;
        double num3 = 3.2;
        double num4 = 3.2;
        context.setVariable("name", "   un variable  ");
        context.setVariable("score", k);
        context.setVariable("num1", num1);
        context.setVariable("num2", num2);
        context.setVariable("num4", num4);

        // Process the Thymeleaf template with the dynamic variables
        String html = templateEngine.process(template, context );

        // Convert the HTML content to a PDF file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)), outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        // Return the PDF file as a response
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}
