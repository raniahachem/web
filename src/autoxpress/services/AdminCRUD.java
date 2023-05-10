/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;




import autoxpress.utils.MyConnection;
import autoxpress.entities.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rania
 */
public class AdminCRUD {
    
    private Connection cnx;

    public AdminCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    // Create
    public void addAdmin(Admin a) {
        try {
            String requete = "INSERT INTO admin (email_ad, mdp_ad) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, a.getEmail_ad());
            pst.setString(2, a.getMdp_ad());
            pst.executeUpdate();
            System.out.println("Admin ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // Read
    public List<Admin> getAdminList() {
        List<Admin> adminList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM admin";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Admin a = new Admin(rs.getInt("id_admin"), rs.getString("email_ad"), rs.getString("mdp_ad"));
                adminList.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return adminList;
    }

    // Update
    public void updateAdmin(Admin a) {
        try {
            String requete = "UPDATE admin SET email_ad=?, mdp_ad=? WHERE id_admin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, a.getEmail_ad());
            pst.setString(2, a.getMdp_ad());
            pst.setInt(3, a.getId());
            pst.executeUpdate();
            System.out.println("Admin mis à jour avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // Delete
    public void deleteAdmin(int id) {
        try {
            String requete = "DELETE FROM admin WHERE id_admin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Admin supprimé avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
