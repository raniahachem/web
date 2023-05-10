/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

/**
 *
 * @author user
 */
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import autoxpress.entities.Conducteur;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author 21622
 */
public class ConducteurPdfGenerator {
    private static final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private static final String user = "root";
    private static final String password = "";
   public void generatePdf(List<Conducteur> conducteurList) {
    try {
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM Conducteur";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream("conducteurs.pdf"));
        document.open();
        
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("CIN");
        table.addCell("Nom");
        table.addCell("Prénom");
        table.addCell("Email");
        table.addCell("Ville");
        table.addCell("Téléphone");
        table.addCell("Type de permis");
        table.addCell("Mot de passe");
        table.addCell("Image");
        
        while (rs.next()) {
            table.addCell(Integer.toString(rs.getInt("cin_conducteur")));
            table.addCell(rs.getString("nom_conducteur"));
            table.addCell(rs.getString("prenom_conducteur"));
            table.addCell(rs.getString("email_conducteur"));
            table.addCell(rs.getString("ville_conducteur"));
            table.addCell(Integer.toString(rs.getInt("telephone_conducteur")));
            table.addCell(rs.getString("type_de_permis"));
            table.addCell(rs.getString("mdp_conducteur"));
            table.addCell(rs.getString("image_conducteur"));
        }
        
        document.add(new Paragraph("Liste des conducteurs"));
        document.add(table);
        
        document.close();
        pstmt.close();
        conn.close();
        
        System.out.println("Fichier PDF généré avec succès !");
    } catch (Exception e) {
        System.err.println("Erreur lors de la génération du fichier PDF : " + e.getMessage());
        e.printStackTrace();
    }
   }
}
