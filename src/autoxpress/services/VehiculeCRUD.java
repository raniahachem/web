/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autoxpress.services;

import autoxpress.entities.Vehicule;
import autoxpress.interfaces.EntityCRUD;
import autoxpress.utils.MyConnection;
import java.io.File;
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
public class VehiculeCRUD implements EntityCRUD<Vehicule> {

    public void addEntity2(Vehicule v) {
        try {
            String requete = "INSERT INTO VEHICULE (immatriculation,type_du_vehicule,marque,"
                    + "cin_conducteur,etat,kilometrage,imageV) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            st.setString(1, v.getImmatriculation());
            st.setString(2, v.getType_du_vehicule());
            st.setString(3, v.getMarque());
            st.setInt(4, v.getCin_conducteur());
            st.setInt(5, v.getEtat());
            st.setInt(6, v.getKilometrage());
            st.setString(7, v.getImageV());

            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehicule Ajouté Avec Succés!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public void addEntity(Vehicule v) {
        try {
            String requete = "INSERT INTO VEHICULE (immatriculation,type_du_vehicule,marque, "
                    + "cin_conducteur,etat,kilometrage,imageV) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Vehicule ajouté");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

//    @Override
//    public List<Vehicule> entitiesList() {
//        ArrayList<Vehicule> myList = new ArrayList();
//        try {
//            String requete = "SELECT * FROM VEHICULE";
//            Statement st = Myconnection.getInstance().getCnx().createStatement();
//            ResultSet rs = st.executeQuery(requete);
//            while (rs.next()) {
//                Vehicule v = new Vehicule();
//                v.setImmatriculation(rs.getString(1));
//                v.setType_du_vehicule(rs.getString(2));
//                v.setMarque(rs.getString(3));
//                v.setCin_conducteur(rs.getInt(4));
//                v.setEtat(rs.getInt(5));
//                v.setKilometrage(rs.getInt(6));
//                v.setImageV(rs.getString(7));
//                myList.add(v);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return myList;
//
//    }
    public ObservableList<Vehicule> getDataV() {
        ObservableList<Vehicule> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM VEHICULE";
            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Vehicule vehicule = new Vehicule(rs.getString("immatriculation"),
                        rs.getString("type_du_vehicule"), rs.getString("marque"),
                        rs.getInt("cin_conducteur"), rs.getInt("etat"),
                        rs.getInt("kilometrage"), rs.getString("imageV"));
                list.add(vehicule);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return list;
    }

    public void updateVehicule(Vehicule v) {
        try {
            String requete = "UPDATE vehicule Set type_du_vehicule=?, marque=?, cin_conducteur=?, "
                    + "etat=?, kilometrage=?, imageV=? WHERE immatriculation=?";
            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            st.setString(1, v.getImmatriculation());
            st.setString(2, v.getType_du_vehicule());
            st.setString(3, v.getMarque());
            st.setInt(4, v.getCin_conducteur());
            st.setInt(5, v.getEtat());
            st.setInt(6, v.getKilometrage());
            st.setString(7, v.getImageV());

            st.executeUpdate();
            System.out.println("Vehicule Modifié!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }

    }

    public void deleteVehicule(String immatriculation) {
        String requete = "DELETE FROM Vehicule Where immatriculation=?";
        try {

            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            st.setString(1, immatriculation);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Supprimé");
            System.out.println("Vehicule Supprimé!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    public boolean immatriculationExists(String immatriculation) {
        try {
            String requete = "SELECT COUNT(*) FROM vehicule WHERE immatriculation = ?";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);

            ps.setString(1, immatriculation);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Vehicule> entitiesList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteVehiculeImage(String resImmatriculation) {
        String filePath = "C:\\xampp\\htdocs\\imageV\\" + resImmatriculation;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
