/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Offre;
import autoxpress.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherOffreController implements Initializable {

    @FXML
    private Button btnStat;
    @FXML
    private TextField filterField;
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
    private Button btnoffre;
    @FXML
    private Button btnres1;
    @FXML
    private Button btnlivr1;
    @FXML
    private Button btnrec1;
    @FXML
    private Button btnavi;
    @FXML
    private ImageView image1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FilteredList<Offre> filteredData = new FilteredList<>(data, b -> true);
            
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(offre -> {
                    
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (String.valueOf(offre.getId_offre()).indexOf(lowerCaseFilter) != -1){
                        return true;
                    }else if (String.valueOf(offre.getId_conducteur()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else if (offre.getDestination().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                            return true;}
                    else if (offre.getPt_depart().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }else if (String.valueOf(offre.getPrix()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }else if (offre.getType_vehicule().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else
                    return false;
                });
            });
            
            //3
            SortedList<Offre> sortedData = new SortedList<>(filteredData);
            //4
            sortedData.comparatorProperty().bind(table_offres.comparatorProperty());
            //5 
            table_offres.setItems(sortedData);
            
            ///////////////////select ligne 
             
        // TODO
        
        File file = new File("/image/logoauto2.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        image1.setImage(new Image(localURL));
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
    
     FilteredList<Offre> filteredData = new FilteredList<>(data, b -> true);
            
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(offre -> {
                    
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (String.valueOf(offre.getId_offre()).indexOf(lowerCaseFilter) != -1){
                        return true;
                    }else if (String.valueOf(offre.getId_conducteur()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else if (offre.getDestination().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                            return true;}
                    else if (offre.getPt_depart().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }else if (String.valueOf(offre.getPrix()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }else if (offre.getType_vehicule().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else
                    return false;
                });
            });
            
            //3
            SortedList<Offre> sortedData = new SortedList<>(filteredData);
            //4
            sortedData.comparatorProperty().bind(table_offres.comparatorProperty());
            //5 
            table_offres.setItems(sortedData);
    }

    @FXML
    private void btnStat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
         
        Stage prStage = new Stage();
        loader.setLocation(getClass().getResource("graph.fxml"));
         
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();
    }

    private void btn_aff_offfre(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("OffreAffClient.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }


   @FXML
    private void reclamation(ActionEvent event) {
               try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

     



    @FXML
    private void movetohome(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("Loginclient.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }


    @FXML
    private void offre(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("AfficherOffre.fxml"));
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
    private void livraison(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("LivraisonClient.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void avi(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("AjoutAvis.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
    
}
