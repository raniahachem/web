/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

import autoxpress.entities.Avis;
import autoxpress.interfaces.InterfaceAvis;
import autoxpress.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rania
 */
public class AvisCRUD implements InterfaceAvis{

    @Override
    public void ajouterAvis(Avis a) {
try {

            String requete1 = "INSERT INTO avis(id_conducteur,note) VALUES(?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete1);
            pst.setInt(1, a.getId_conducteur());
            pst.setInt(2, a.getNote());
            pst.executeUpdate();
            System.out.println("Avi ajouté avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }


    @Override
    public void modifierAvis(Avis a) {
try {
            String requete = "UPDATE avis SET id_conducteur=?, note=? WHERE id_avis=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, a.getId_conducteur());
            pst.setInt(2, a.getNote());
            pst.setInt(3, a.getId_avis());
            pst.executeUpdate();
            System.out.println("Avis modifié");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimerAvis(int id_avis) {
 try {
            String requete = "DELETE FROM avis WHERE id_avis=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id_avis);
            pst.executeUpdate();
            System.out.println("Avis supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Avis> afficherAvis() {
List<Avis> avisList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM avis";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Avis avis = new Avis(rs.getInt("id_avis"), rs.getInt("id_conducteur"), rs.getInt("note"));
                avisList.add(avis);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return avisList;
    }  
    
    public static Map<Integer, Double> calculerMoyenne() {
    // Créer une nouvelle carte pour stocker les moyennes par id_conducteur
    Map<Integer, Double> moyennes = new HashMap<>();

    try 
    {
        // Définir la requête SQL pour calculer la moyenne des notes par id_conducteur
        String requete = "SELECT id_conducteur, AVG(note) AS moyenne FROM avis GROUP BY id_conducteur";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        // Parcourir le résultat de la requête et stocker chaque moyenne dans la carte
        while (rs.next()) {
            int id_conducteur = rs.getInt("id_conducteur");
            double moyenne = rs.getDouble("moyenne");
            moyennes.put(id_conducteur, moyenne);
        }
    } catch (SQLException e) {
    }

    // Retourner la carte de moyennes
    return moyennes;
}

}
