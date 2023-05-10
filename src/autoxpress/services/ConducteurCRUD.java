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

import autoxpress.entities.Client;
import autoxpress.entities.Conducteur;
import autoxpress.interfaces.ConducteurInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21622
 */
public class ConducteurCRUD implements ConducteurInterface<Conducteur> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    public void AddConducteur(Conducteur cl) {
        String sql = "INSERT INTO conducteur(nom_conducteur, prenom_conducteur, cin_conducteur, ville_conducteur, telephone_conducteur, email_conducteur, mdp_conducteur,type_de_permis,image_conducteur) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cl.getNom_conducteur());
            pstmt.setString(2, cl.getPrenom_conducteur());
            pstmt.setInt(3, cl.getCin_conducteur());
            pstmt.setString(4, cl.getVille_conducteur());
            pstmt.setInt(5, cl.getTelephone_conducteur());
            pstmt.setString(6, cl.getEmail_conducteur());
            pstmt.setString(7, cl.getMdp_conducteur());
            pstmt.setString(8, cl.getType_de_permis());
            pstmt.setString(9, cl.getImage_conducteur());
            pstmt.executeUpdate();
            System.out.println("Le conducteur a été ajouté avec succès !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    //UpDate

    @Override
    public void UpDateConducteur(Conducteur cl,int id) {
        String sql = "UPDATE conducteur SET nom_conducteur = ?, prenom_conducteur = ?, cin_conducteur = ?, ville_conducteur = ?, telephone_conducteur = ?, email_conducteur = ?, mdp_conducteur = ?,type_de_permis = ?,image_conducteur=? WHERE id_conducteur = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cl.getNom_conducteur());
            pstmt.setString(2, cl.getPrenom_conducteur());
            pstmt.setInt(3, cl.getCin_conducteur());
            pstmt.setString(4, cl.getVille_conducteur());
            pstmt.setInt(5, cl.getTelephone_conducteur());
            pstmt.setString(6, cl.getEmail_conducteur());
            pstmt.setString(7, cl.getMdp_conducteur());
            pstmt.setString(8, cl.getType_de_permis());
            pstmt.setString(9, cl.getImage_conducteur());
            pstmt.setInt(10,id);

            pstmt.executeUpdate();
            System.out.println("Le conducteur a été modifié ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void DeleteConducteur(int id_conducteur) {
        String sql = "DELETE FROM conducteur WHERE id_conducteur = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_conducteur);
            pstmt.executeUpdate();
            System.out.println(" Le conducteur supprimé avec succès");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Conducteur> ConducteurList() {

        List<Conducteur> Conducteur = new ArrayList<>();
        String sql = "SELECT * FROM conducteur";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Conducteur cd = new Conducteur();
                cd.setId_conducteur(rs.getInt("id_conducteur"));
                cd.setNom_conducteur(rs.getString("nom_conducteur"));
                cd.setPrenom_conducteur(rs.getString("prenom_conducteur"));
                cd.setTelephone_conducteur(rs.getInt("telephone_conducteur"));
                cd.setEmail_conducteur(rs.getString("email_conducteur"));
                cd.setVille_conducteur(rs.getString("ville_conducteur"));
                cd.setMdp_conducteur(rs.getString("mdp_conducteur"));
                cd.setType_de_permis(rs.getString("type_de_permis"));
                cd.setImage_conducteur(rs.getString("image_conducteur"));
                Conducteur.add(cd);
                System.out.println(cd);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Conducteur;
    }
    
   public Conducteur login(String email_conducteur) {
String sql = "select * from Conducteur where email_conducteur='" + email_conducteur + "'";
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet result = null;
Conducteur conducteur = null;
try {
conn = DriverManager.getConnection(url, user, password);
pstmt = conn.prepareStatement(sql);
result = pstmt.executeQuery(sql);
conducteur = new Conducteur();
while (result.next()) {
conducteur.setId_conducteur(result.getInt("id_conducteur"));
conducteur.setCin_conducteur(result.getInt("cin_conducteur"));
conducteur.setNom_conducteur(result.getString("nom_conducteur"));
conducteur.setPrenom_conducteur(result.getString("prenom_conducteur"));
conducteur.setTelephone_conducteur(result.getInt("telephone_conducteur"));
conducteur.setTelephone_conducteur(result.getInt("telephone_conducteur"));
conducteur.setEmail_conducteur(result.getString("email_conducteur"));
conducteur.setVille_conducteur(result.getString("ville_conducteur"));
conducteur.setMdp_conducteur(result.getString("mdp_conducteur"));
conducteur.setType_de_permis(result.getString("type_de_permis"));
conducteur.setImage_conducteur(result.getString("image_conducteur"));
}
} catch (SQLException e) {
// Gérer l'exception ici
e.printStackTrace();
} finally {
try {
if (result != null) {
result.close();
}
if (pstmt != null) {
pstmt.close();
}
if (conn != null) {
conn.close();
}
} catch (SQLException e) {
e.printStackTrace();
}
}
return conducteur;

    }
   
   
    public String getConductorPhoneNumber(String prenom_conducteur) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String phoneNumber = null;

    try {
        conn = DriverManager.getConnection(url, user, password);
        String query = "SELECT telephone_conducteur FROM conducteur WHERE prenom_conducteur=?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, prenom_conducteur);
        rs = stmt.executeQuery();

        if (rs.next()) {
            phoneNumber = rs.getString("telephone_conducteur");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();

    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return phoneNumber;
}
    

}

