/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package autoxpress.services;
import java.io.FileOutputStream;
import autoxpress.entities.Reclamation;
import java.util.List;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 *
 * @author rania
 */
public class PDFGenerator {
     public void generatePDF(List<Reclamation> reclamations, String fileName) {
Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        
        // Ajouter un titre en bleu au-dessus du tableau
        Paragraph title = new Paragraph("Les Réclamations", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(33, 150, 243)));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        // Ajouter des couleurs de fond et de bordure personnalisées pour la table
        table.getDefaultCell().setBackgroundColor(new BaseColor(217, 237, 247));
        table.getDefaultCell().setBorderColor(new BaseColor(33, 150, 243));
        table.getDefaultCell().setBorderWidth(2f);
        
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("ID"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Type"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Date"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Description"));
        cell.setBackgroundColor(new BaseColor(176, 224, 230));
        cell.setBorderWidth(2f);
        table.addCell(cell);
        
        for (Reclamation r : reclamations) {
            table.addCell(String.valueOf(r.getId()));
            table.addCell(r.getType());
            table.addCell(r.getDate());
            table.addCell(r.getDescription());
        }
        document.add(table);
        document.close();
    } catch (Exception e) {
    }
}
}
