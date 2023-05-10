/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

import autoxpress.entities.Contrat;
import autoxpress.interfaces.ContratInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BKHmidi
 */
public class ContratCRUD implements ContratInterface<Contrat> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    public void AddContrat(Contrat c) {
        String sql = "INSERT INTO contrat (id_conducteur, id_admin, date_debut, date_fin, prix, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, c.getId_conducteur());
            pstmt.setInt(2, c.getId_admin());
            pstmt.setDate(3, (Date) c.getDate_debut());
            pstmt.setDate(4, (Date) c.getDate_fin());
            pstmt.setDouble(5, c.getPrix());
            pstmt.setString(6, c.getStatut());
            pstmt.executeUpdate();
            System.out.println("Contrat ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean contratExists(Contrat c) {
        String sql = "SELECT COUNT(*) FROM contrat WHERE id_conducteur = ? AND id_admin = ? AND date_debut = ? AND date_fin = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, c.getId_conducteur());
            pstmt.setInt(2, c.getId_admin());
            pstmt.setDate(3, (Date) c.getDate_debut());
            pstmt.setDate(4, (Date) c.getDate_fin());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return (rs.getInt(1) > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void UpdateContrat(Contrat c) {
        String sql = "UPDATE contrat SET id_conducteur = ?, id_admin = ?, date_debut = ?, date_fin = ?, prix = ?, statut = ? WHERE id_contrat = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, c.getId_conducteur());
            pstmt.setInt(2, c.getId_admin());
            pstmt.setDate(3, (Date) c.getDate_debut());
            pstmt.setDate(4, (Date) c.getDate_debut());
            pstmt.setDouble(5, c.getPrix());
            pstmt.setString(6, c.getStatut());
            pstmt.setInt(7, c.getId_contrat());
            pstmt.executeUpdate();
            System.out.println("Contrat modifié");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void DeleteContrat(int id_contrat) {
        String sql = "DELETE FROM contrat WHERE id_contrat = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_contrat);
            pstmt.executeUpdate();
            System.out.println("Contrat supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Contrat> getDataC() {
        ObservableList<Contrat> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contrat";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contrat c = new Contrat();
                c.setId_contrat(rs.getInt("id_contrat"));
                c.setId_conducteur(rs.getInt("id_conducteur"));
                c.setId_admin(rs.getInt("id_admin"));
                c.setDate_debut(rs.getDate("date_debut"));
                c.setDate_fin(rs.getDate("date_fin"));
                c.setPrix(rs.getInt("prix"));
                c.setStatut(rs.getString("statut"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Contrat> contratList() {
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM contrat";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contrat c = new Contrat();
                c.setId_contrat(rs.getInt("id_contrat"));;
                c.setId_conducteur(rs.getInt("id_conducteur"));
                c.setId_admin(rs.getInt("id_admin"));
                c.setDate_debut(rs.getDate("date_debut"));
                c.setDate_fin(rs.getDate("date_fin"));
                c.setPrix(rs.getInt("prix"));
                c.setStatut(rs.getString("statut"));
                contrats.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contrats;
    }

    public Contrat findById(int id) {
        Contrat c = null;
        String sql = "SELECT * FROM contrat WHERE id_contrat = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                c = new Contrat();
                c.setId_contrat(rs.getInt("id_contrat"));
                c.setId_conducteur(rs.getInt("id_conducteur"));
                c.setId_admin(rs.getInt("id_admin"));
                c.setDate_debut(rs.getDate("date_debut"));
                c.setDate_fin(rs.getDate("date_fin"));
                c.setPrix(rs.getInt("prix"));
                c.setStatut(rs.getString("statut"));
            }
        } catch (SQLException e) {
            System.out.println("Error finding contrat by id: " + e.getMessage());
        }
        return c;
    }

    public String getQrCode(String qrCode) {

        String qr_code = "";
        String sql = " SELECT qr_code FROM contrat WHERE id_contrat = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, qrCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                qr_code = rs.getString("qr_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qr_code;
    }

    @Override
    public List<Contrat> searchContrats(Contrat c) {
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM contrat WHERE id_contrat LIKE ? OR id_conducteur LIKE ? OR id_admin LIKE ? OR statut LIKE ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the search parameter values in the statement
            stmt.setString(1, "%" + c.getId_contrat() + "%");
            stmt.setString(2, "%" + c.getId_conducteur() + "%");
            stmt.setString(3, "%" + c.getId_admin() + "%");
            stmt.setString(4, "%" + c.getStatut() + "%");

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Add the results to the list of contrats
            while (rs.next()) {
                Contrat contrat = new Contrat();
                contrat.setId_contrat(rs.getInt("id_contrat"));
                contrat.setId_conducteur(rs.getInt("id_conducteur"));
                contrat.setId_admin(rs.getInt("id_admin"));
                contrat.setDate_debut(rs.getDate("date_debut"));
                contrat.setDate_fin(rs.getDate("date_fin"));
                contrat.setPrix(rs.getInt("prix"));
                contrat.setStatut(rs.getString("statut"));
                contrats.add(contrat);
            }
        } catch (SQLException e) {
            System.out.println("Error searching for contrats: " + e.getMessage());
        }
        return contrats;
    }

    public List<Contrat> filtrerContrats(List<Contrat> contrats) {
        List<Contrat> contratsFiltres = new ArrayList<>();
        java.util.Date dateLocale = new java.util.Date();
        for (Contrat contrat : contrats) {
            
            if (contrat.getDate_fin().after(dateLocale)) { // Si la date de fin du contrat est postérieure à la date locale
                contratsFiltres.add(contrat); // On ajoute le contrat à la liste des contrats filtrés
            }
        }
        return contratsFiltres;
    }
        public List<Contrat> archiveContrats(List<Contrat> contrats) {
        List<Contrat> contratsFiltres = new ArrayList<>();
        java.util.Date dateLocale = new java.util.Date();
        for (Contrat contrat : contrats) {
            
            if (contrat.getDate_fin().before(dateLocale)) { // Si la date de fin du contrat est postérieure à la date locale
                contratsFiltres.add(contrat); // On ajoute le contrat à la liste des contrats filtrés
            }
        }
        return contratsFiltres;
    }
}
