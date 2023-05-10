/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;


import autoxpress.entities.Admin;
import autoxpress.entities.Messages;
import autoxpress.entities.Reclamation;
import autoxpress.interfaces.MessageInterface;
import autoxpress.utils.MyConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BKHmidi
 */
public class MessageCRUD implements MessageInterface<Messages> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    public void AddMessage(Messages m) {
       String sql = "INSERT INTO message(contenu, date_message, id_rec_id, id_admin_id) VALUES(?,?,?,?)";

    try (PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        pstmt.setString(1, m.getContenu());
        pstmt.setTimestamp(2, m.getDate_message());
        pstmt.setInt(3, m.getId_rec_id());
        pstmt.setInt(4, m.getId_admin_id());

        int affectedRows = pstmt.executeUpdate();
        System.out.println("message ajouté");
        if (affectedRows == 0) {
            throw new SQLException("Creating message failed, no rows affected.");
        }

        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                m.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating message failed, no ID obtained.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error adding message: " + e.getMessage());
    }
    }

    @Override
    public void UpdateMessage(Messages m) {
        try {
                    String sql = "Update INTO Message (contenu) VALUES (?)";
                PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(sql); 
            pstmt.setString(1, m.getContenu());

            pstmt.executeUpdate();
            System.out.println("message modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void DeleteMessage(int id_message) {
        
        try {String sql = "DELETE FROM Message WHERE id = ?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(sql); 
            pst.setInt(1, id_message);
            pst.executeUpdate();
            System.out.println("message supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Messages> MessageList() {
        List<Messages> messages = new ArrayList<>();
        String sql = "SELECT * FROM Message";
        try (                
                Statement stmt = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Messages m = new Messages();
                m.setId(rs.getInt("id"));
                m.setContenu(rs.getString("contenu"));
                m.setDate_message(rs.getTimestamp("date_message"));
                m.setId_rec_id(rs.getInt("id_rec_id"));            
                m.setId_admin_id(rs.getInt("id_admin_id"));
                LocalDateTime date = rs.getTimestamp("date_message").toLocalDateTime();

                // Format the LocalDateTime object using a DateTimeFormatter
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = date.format(formatter);
                m.setDate_message(Timestamp.valueOf(date));
                messages.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;

    }

    public ObservableList<Messages> getDataMsg() {
        ObservableList<Messages> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Message";
        try (Statement stmt = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Messages c = new Messages();
                c.setId(rs.getInt("id"));
                c.setContenu(rs.getString("contenu"));
                c.setId_rec_id(rs.getInt("id_rec_id"));
                c.setId_admin_id(rs.getInt("id_admin_id"));
                // Convert the Timestamp object to LocalDateTime object
                LocalDateTime date = rs.getTimestamp("date_message").toLocalDateTime();

                // Format the LocalDateTime object using a DateTimeFormatter
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = date.format(formatter);
                c.setDate_message(Timestamp.valueOf(date));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int getIdReclamation(int id) {
        int id_rec_id = 0;
        String sql = "SELECT id FROM reclamation WHERE id = ?";
        try (PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public int getIdAdmin(String email) {
        int id = 0;
        try (PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement("SELECT id_admin FROM admin WHERE email_ad = ?")) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_admin");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Messages ReadMessage(int id) {
    String sql = "SELECT m.id, c.id, d.email_ad, m.contenu, m.date_message "
            + "FROM message m, reclamation c, admin d "
            + "WHERE m.id_rec_id = c.id "
            + "AND m.id_admin_id = d.id_admin "
            + "AND m.id = ?";
    try (PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(sql)) {
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Messages m = new Messages();
            m.setId(rs.getInt("id"));
            m.setDate_message(rs.getTimestamp("date_message"));
            m.setContenu(rs.getString("contenu"));

            // Set the reclamation and admin information in the message object
            Reclamation reclamation = new Reclamation();
            reclamation.setId(rs.getInt("c.id")); // use the reclamation ID from the result set
            m.setId (reclamation.getId());

            Admin admin = new Admin();
            admin.setEmail_ad(rs.getString("email_ad"));
            m.setId(admin.getId());

            return m;
        } else {
            System.out.println("No message found with id " + id);
        }
    } catch (SQLException e) {
        System.out.println("Error reading message: " + e.getMessage());
    }
    return null;
}


  
   
}
