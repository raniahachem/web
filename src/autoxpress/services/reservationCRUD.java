package autoxpress.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import autoxpress.entities.Reservation;
import autoxpress.interfaces.reservationInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import autoxpress.utils.MyConnection;

public class reservationCRUD implements reservationInterface<Reservation> {
    // Twilio account details
    private final String ACCOUNT_SID = "AC57ec279cbfaf1d8704d1e28dd84fcc8a";
    private final String AUTH_TOKEN = "de361e3ee6021c768e54f8c1c1b6c088";
    private final String FROM_NUMBER = "+15673131932";
    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    public void updateReservation(Reservation reservation, String id) {
        String sql = "UPDATE reservation SET nb_place = ?, id_offre = ?, id_client = ?, point_de_depart = ?, point_arrive = ?, id_conducteur = ? WHERE id_reservation = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getNb_place());
            pstmt.setInt(2, reservation.getId_offre());
            pstmt.setInt(3, reservation.getId_client());
            pstmt.setString(4, reservation.getPoint_de_depart());
            pstmt.setString(5, reservation.getPoint_arrive());
            pstmt.setInt(6, reservation.getId_conducteur());
            pstmt.setString(7, id);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("La réservation a été mise à jour avec succès !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete a reservation from the database
    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservation WHERE id_reservation = ? ";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
            System.out.println("Reservation supprimée");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reservation> entitiesList() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id_reservation = rs.getInt("id_reservation");
                int nb_place = rs.getInt("nb_place");
                int id_offre = rs.getInt("id_offre");
                LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
                int id_client = rs.getInt("id_client");
                String point_de_depart = rs.getString("point_de_depart");
                String point_arrive = rs.getString("point_arrive");
                int id_conducteur = rs.getInt("id_conducteur");
                Reservation r = new Reservation(id_reservation, nb_place, id_offre, date, id_client, point_de_depart,
                        point_arrive, id_conducteur);
                reservations.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reservations;
    }

    public Reservation getById(int id) {
        Reservation reser = new Reservation();
        String sql = "SELECT * FROM reservation where id_reservation=" + id;
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                Reservation reserv = new Reservation(rs.getInt("nb_place"), rs.getInt("id_offre"),
                        rs.getInt("id_client"), rs.getString("point_de_depart"), rs.getString("point_arrive"),
                        rs.getInt("id_conducteur"));
                return reserv;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reser;
    }

    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> clients = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reservation H = new Reservation();
                H.setId_reservation(rs.getInt("id_reservation"));

                clients.add(H);
            }
            return clients;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }

    public void addReservation(Reservation r) {
        String sql = "INSERT INTO reservation(id_reservation, nb_place, id_offre, id_client, point_de_depart, point_arrive, id_conducteur) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, r.getId_reservation());
            pstmt.setInt(2, r.getNb_place());
            pstmt.setInt(3, r.getId_offre());
            pstmt.setInt(4, r.getId_client());
            pstmt.setString(5, r.getPoint_de_depart());
            pstmt.setString(6, r.getPoint_arrive());
            pstmt.setInt(7, r.getId_conducteur());
            pstmt.executeUpdate();
            System.out.println("La réservation a été ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public void send(String number, String message) {
        // Initialize the Twilio API client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send the message using the Twilio API
        Message twilioMessage = Message.creator(
                new PhoneNumber(number), // destination phone number
                new PhoneNumber(FROM_NUMBER), // Twilio phone number
                message)
            .create();

        // Print the message SID as confirmation
        System.out.println("Message sent: " + twilioMessage.getSid());
    }

}