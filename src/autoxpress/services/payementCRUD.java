/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

import autoxpress.entities.payement;
import autoxpress.interfaces.payementInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amal
 */
public class payementCRUD implements payementInterface {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    public static List<payement> getAll() {
        List<payement> payments = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/autoxpress", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM payement");
            while (rs.next()) {
                payement payment = new payement(rs.getInt("paymentId"), rs.getInt("clientId"), rs.getString("modePayment"), rs.getFloat("prixCourse"), rs.getDate("paymentDate"));
                payments.add(payment);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public static payement getById(int paymentId) {
        payement payment = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/autoxpress", "username", "password");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM payement WHERE paymentId = ?");
            ps.setInt(1, paymentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                payment = new payement(rs.getInt("paymentId"), rs.getInt("clientId"), rs.getString("modePayment"), rs.getFloat("prixCourse"), rs.getDate("paymentDate"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public void updatePayement(payement payement, String id) {
        String sql = "UPDATE payement SET paymentId = ?, clientId = ?, modePayment = ?, prixCourse = ?  WHERE paymentId = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payement.getPaymentId());
            pstmt.setInt(2, payement.getClientId());

            pstmt.setString(3, payement.getModePayment());
            pstmt.setFloat(4, payement.getPrixCourse());

            pstmt.setString(5, id);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Payment updated successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addPayment(payement payment) {
        String sql = "INSERT INTO payement(paymentId, clientId, modePayment, prixCourse,paymentDate) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getPaymentId());
            pstmt.setInt(2, payment.getClientId());
            pstmt.setString(3, payment.getModePayment());
            pstmt.setFloat(4, payment.getPrixCourse());
            pstmt.setDate(5, (Date) payment.getPaymentDate());
            pstmt.executeUpdate();
            System.out.println("Payment added successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        String sql = "DELETE FROM payement WHERE paymentId = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, paymentId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Payment with ID = " + paymentId + " has been deleted successfully!");
            } else {
                System.out.println("Payment with ID = " + paymentId + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
