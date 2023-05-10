/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

import autoxpress.entities.historique_reservation;
import autoxpress.interfaces.historique_reservationInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amal
 */

public class historique_reservationCRUD implements historique_reservationInterface<historique_reservation> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";
    private final String SELECT_ALL_QUERY = "SELECT * FROM historique_reservation";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Close the database connection, statement, and result set
    private void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet)
            throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public ArrayList<historique_reservation> getHistoriqueReservations() {

        return new ArrayList<historique_reservation>(historique_reservationList());
    }

    public List<historique_reservation> historique_reservationList() {
        List<historique_reservation> historique = new ArrayList<>();
        String sql = "SELECT * FROM historique_reservation";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                historique_reservation H = new historique_reservation();
                H.setId_reservation(rs.getInt("id_reservation"));
                H.setId_historique_reservation(rs.getInt("id_historique_reservation"));
                H.setId_conducteur(rs.getInt("id_conducteur"));
                String dateString = rs.getString("date");
                String dateDepartReelleString = rs.getString("date_depart_reelle");
                String dateArriveReelleString = rs.getString("date_arrive_reelle");
                H.setDate_depart_reelle(dateDepartReelleString);
                H.setDate_arrive_reelle(dateArriveReelleString);

                H.setLieu_depart(rs.getString("lieu_depart"));
                H.setLieu_destination(rs.getString("lieu_destination"));
                H.setAvis_client(rs.getString("avis_client"));
                H.setStatus_reservation(rs.getString("status_reservation"));
                H.setId_client(rs.getInt("id_client"));

                historique.add(H);
            }
            return historique;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return historique;
    }

    public void addHistorique_Reservation(historique_reservation r) {
        String sql = "INSERT INTO historique_reservation (id_reservation, id_conducteur, lieu_depart,lieu_destination,avis_client,status_reservation,id_client) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, r.getId_reservation());

            pstmt.setInt(2, r.getId_conducteur());
            // pstmt.setDate(4, r.getDate_debut());
            // pstmt.setInt(4, r.getdate_depart());
            pstmt.setString(3, r.getLieu_depart());
            pstmt.setString(4, r.getLieu_destination());
            pstmt.setString(5, r.getAvis_client());
            pstmt.setString(6, r.getStatus_reservation());
            pstmt.setInt(7, r.getId_client());
            pstmt.executeUpdate();
            System.out.println("L'histrique  a été ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void delete(int id) {
        String sql = "delete from historique_reservation where id_historique_reservation=?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("L'histrique  supprimer");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
