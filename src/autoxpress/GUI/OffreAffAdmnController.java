/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Offre;
import autoxpress.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OffreAffAdmnController implements Initializable {

    @FXML
    private TableColumn<Offre, Integer> col_id_offre;
    @FXML
    private TableColumn<Offre, Integer> col_id_conducteur;
    @FXML
    private TableColumn<Offre, String> col_destination;
    @FXML
    private TableColumn<Offre, String> col_pt_depart;
    @FXML
    private TableColumn<Offre, Float> col_prix;
    @FXML
    private TableColumn<Offre, String> col_type_vehiculebu;
    @FXML
    private TableView<Offre> table_offres;
            public ObservableList<Offre> data = FXCollections.observableArrayList();
    @FXML
    private Button btnuti;
    @FXML
    private Button btnuti2;
    @FXML
    private Button btnres;
    @FXML
    private Button btnrec;
    @FXML
    private Button btncontrat;
    @FXML
    private Button btnveh;
    @FXML
    private Button btnlivr;
    @FXML
    private Button btnoffre;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherOffre(ActionEvent event) {
         try{
            String requete = "SELECT * FROM offre";
        Statement st = MyConnection.getInstance().getCnx()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()) {
            data.add(new Offre(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getString(6)));
            
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        col_id_offre.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("id_offre"));
        col_id_conducteur.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("id_conducteur"));
        col_destination.setCellValueFactory(new PropertyValueFactory<Offre, String>("destination"));
        col_pt_depart.setCellValueFactory(new PropertyValueFactory<Offre, String>("pt_depart"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Offre, Float>("prix"));
        col_type_vehiculebu.setCellValueFactory(new PropertyValueFactory<Offre, String>("type_vehicule"));



    table_offres.setItems(data);
    
    }

    @FXML
    private void reclamation(ActionEvent event) {
            try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("Recback.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
        
    }

    @FXML
    private void reservation(ActionEvent event) {
             try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("reservation.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void menucontrat(ActionEvent event) {
          try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("listeContrat.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }


    @FXML
    private void livAdmin(ActionEvent event) {
         try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("livraisonConducteur.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }


    @FXML
    private void clients(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("client.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void vehicule(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("VehiculeAdmin.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void conducteur(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("menuadmin.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
        
    }

    @FXML
    private void movetohome(ActionEvent event) {
    }

    @FXML
    private void runa(MouseEvent event) {
    }

    @FXML
    private void click(MouseDragEvent event) {
    }

    @FXML
    private void offre(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("OffreAffAdmn.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
    
}
