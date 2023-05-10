/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

import autoxpress.entities.Reclamation;
import autoxpress.interfaces.InterfaceCRUD;
import autoxpress.utils.MyConnection;
import java.sql.Date;
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
 * @author rania
 */
public class ReclamationCRUD implements InterfaceCRUD {

    @Override
    public void ajouterReclamation(Reclamation r) {
 try {

            String requete1 = "INSERT INTO reclamation(type,date_r,description) VALUES(?,STR_TO_Date(?,'%Y-%m-%d'),?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, r.getType());
            pst.setString(2, r.getDate());
            pst.setString(3, r.getDescription());
            pst.executeUpdate();
            System.out.println("Reclamation ajoutée avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
public void modifierReclamation(Reclamation r) {
     try {
            String requete4;
            requete4 = " UPDATE reclamation SET type=?,date_r=?,description=? WHERE id=?" ;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1, r.getType());
               pst.setString(2, r.getDate());
               pst.setString(3, r.getDescription());
            pst.setInt(4, r.getId());
               pst.executeUpdate();
            System.out.println("Reclamation modifiée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
}


    @Override
    public void supprimerReclamation(int id) {
try {
            String req = "DELETE FROM reclamation WHERE id = " + id;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficherReclamation() {
        
         ArrayList<Reclamation> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            
              while (rs.next()) {
                Reclamation r = new Reclamation(rs.getInt("id"), rs.getString("type"), rs.getString("date_r"), rs.getString("description"));
                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        return myList;
 }

    /*@Override
    public void modifierReclamation(Reclamation r, int id) {
try {
            String rec;
    rec = "UPDATE reclamation SET `type` = '" + r.getType() + "', `Date_r` = '" + r.getDateR() + "' , `Description` = '" + r.getDescription() + "' WHERE `reclamation`.`id` = " + r.getId();
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(rec);
            System.out.println("Reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
}