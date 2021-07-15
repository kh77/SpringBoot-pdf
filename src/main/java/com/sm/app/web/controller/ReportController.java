package com.sm.app.web.controller;

import com.sm.app.config.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generate")
public class ReportController {

	@Autowired
	PdfGenerator pdfGenerator;

	@GetMapping
	public String generatePDF() {
		pdfGenerator.generatePdfReport();
		return "success";
	}
}