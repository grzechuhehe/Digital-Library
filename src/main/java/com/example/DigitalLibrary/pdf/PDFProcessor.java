package com.example.DigitalLibrary.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;

public class PDFProcessor {

    public String extractTextFromPDF(byte[] pdfBytes) throws IOException {
        try (PDDocument document = PDDocument.load(pdfBytes)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    public boolean isPDF(byte[] fileBytes) {
        // Sprawdzenie, czy plik to PDF
        return fileBytes.length > 4 && fileBytes[0] == '%' && fileBytes[1] == 'P' && fileBytes[2] == 'D' && fileBytes[3] == 'F';
    }
}
