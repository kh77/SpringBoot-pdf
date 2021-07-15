package com.sm.app.config;

import com.sm.app.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Component("pdfGenerator")
public class PdfConfig {

    @Value("${pdfDir}")
    public String pdfDir;

    @Value("${reportFileName}")
    public String reportFileName;

    // not using , you can use if you want
    @Value("${reportFileNameDateFormat}")
    public String reportFileNameDateFormat;

    @Value("${localDateFormat}")
    public String localDateFormat;

    @Value("${logoImgPath}")
    public String logoImgPath;

    @Value("${logoImgScale}")
    public Float[] logoImgScale;

    @Value("${currencySymbol:}")
    public String currencySymbol;

    @Value("${product.noOfColumns}")
    public int noOfColumns;

    @Value("${product.columnNames}")
    public List<String> columnNames;

    public static Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
    public static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
    public static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

    /**
     * Pdf file name
     * @return
     */
    protected String getPdfNameWithDate() {
        String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
        return pdfDir+reportFileName+"-"+LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)+".pdf";
    }

    /**
     * Pdf footer
     * @param document
     * @throws DocumentException
     */
    protected void addFooter(Document document) throws DocumentException {
        Paragraph p2 = new Paragraph();
        leaveEmptyLine(p2, 3);
        p2.setAlignment(Element.ALIGN_MIDDLE);
        p2.add(new Paragraph(
                "------------------------" +reportFileName+" Finish------------------------",
                COURIER_SMALL_FOOTER));

        document.add(p2);
    }

    /**
     * Empty line in Pdf
     * @param paragraph
     * @param number
     */
    protected static void leaveEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
