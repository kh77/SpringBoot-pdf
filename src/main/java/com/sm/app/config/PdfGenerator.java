package com.sm.app.config;

import com.sm.app.model.Product;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PdfGenerator extends PdfConfig{

    /**
     * Generate Pdf
     */
    public void generatePdfReport() {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(getPdfNameWithDate()));
            document.open();
            addLogo(document);
            addDocTitle(document);
            createTable(document,noOfColumns);
            addFooter(document);
            document.close();
            System.out.println("------------------PDF generated-----------------------");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * Logo in pdf
     * @param document
     */
    private void addLogo(Document document) {
        try {
            Image img = Image.getInstance(logoImgPath);
            img.scalePercent(logoImgScale[0], logoImgScale[1]);
            img.setAlignment(Element.ALIGN_RIGHT);
            document.add(img);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addDocTitle(Document document) throws DocumentException {
        String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
        Paragraph p1 = new Paragraph();
        leaveEmptyLine(p1, 1);
        p1.add(new Paragraph(reportFileName, COURIER));
        p1.setAlignment(Element.ALIGN_CENTER);
        leaveEmptyLine(p1, 1);
        // Paragraph Text
        p1.add(new Paragraph("Report generated on " + localDateString, COURIER_SMALL));
        document.add(p1);
    }

    private void createTable(Document document, int noOfColumns) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        leaveEmptyLine(paragraph, 3);

        // Paragraph
        document.add(paragraph);

        // Number of columns in table
        PdfPTable table = new PdfPTable(noOfColumns);

        for(int i=0; i < noOfColumns; i++) {
            PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cell);
        }

        table.setHeaderRows(1);
        getProductData(table);
        document.add(table);
    }

    /**
     * Product data to be displayed in table
     * @param table
     */
    private void getProductData(PdfPTable table) {

        List<Product> list = Product.getProducts();
        for (Product employee : list) {

            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(employee.getId().toString());
            table.addCell(employee.getName());
            table.addCell(employee.getCode());
            table.addCell(currencySymbol + employee.getPrice().toString());
        }

    }

}