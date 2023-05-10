/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autoxpress.services;

import autoxpress.entities.Abonnement;
import autoxpress.entities.Vehicule;
import autoxpress.interfaces.EntityCRUD;
import autoxpress.utils.MyConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author abdel
 */
public class AbonnementCRUD implements EntityCRUD<Abonnement> {

    int id = 0;

//    public int addEntity2(Abonnement ab) {
//        try {
//            String requete = "INSERT INTO ABONNEMENT (id_ab,type_ab,prix_ab,mode_paiement_ab) VALUES (?, ?, ?, ?)";
//
//            PreparedStatement st = new Myconnection().getCnx().prepareStatement(requete);
//            st.setInt(1, ab.getId_ab());
//            st.setString(2, ab.getType_ab());
//            st.setFloat(3, ab.getPrix_ab());
//            st.setString(4, ab.getMode_paiement_ab());
//            st.executeUpdate(requete, Statement.RETURN_GENERATED_KEYS);
//            ResultSet rs = st.getGeneratedKeys(); // get the auto-generated ID
//            if (rs.next()) {
//                id = rs.getInt(1); // assign the ID to id variable
//            }
//            st.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Abonnement Ajouté!");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
//        return id;
//    }
    public int addEntity2(Abonnement ab) {
        int id = 0;
        try {
            String requete = "INSERT INTO abonnement (typeAb,prixAb,modePaiementAb) "
                    + "VALUES (?,?,?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, ab.getTypeAb());
            pst.setFloat(2, ab.getPrixAb());
            pst.setString(3, ab.getModePaiementAb());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            System.out.println("Abonnement added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public ObservableList<Abonnement> getDataAb() {
        ObservableList<Abonnement> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM abonnement";
            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Abonnement abonnement = new Abonnement(rs.getInt("idAb"),
                        rs.getString("typeAb"), rs.getFloat("prixAb"),
                        rs.getString("modePaiementAb"));
                list.add(abonnement);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return list;

    }

    public void updateAbonnement(Abonnement ab) {
        try {
            String requete = "UPDATE ABONNEMENT Set typeAb=?, prixAb=?, modePaiementAb=? WHERE idAb=?";
            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            st.setInt(1, ab.getIdAb());
            st.setString(2, ab.getTypeAb());
            st.setFloat(3, ab.getPrixAb());
            st.setString(4, ab.getModePaiementAb());

            st.executeUpdate();
            System.out.println("Abonnement Modifié!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    public void deleteAbonnement(int id_ab) {
        String requete = "DELETE FROM ABONNEMENT Where idAb=?";
        try {

            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            st.setInt(1, id_ab);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Supprimé");
            System.out.println("Abonnement Supprimé!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    @Override
    public List<Abonnement> entitiesList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addEntity(Abonnement t) {

    }

}
