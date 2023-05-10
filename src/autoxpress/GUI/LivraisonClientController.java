/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Livraison;
import autoxpress.services.LivraisonCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LivraisonClientController implements Initializable {

    @FXML
    private TextField tflid_livraison;
    @FXML
    private TextField tfladdresse_liv;
    @FXML
    private TextField tfldestinataire;
    @FXML
    private TextField tflpoids;
    @FXML
    private TextField tflcontenu;
    @FXML
    private Button btnsuppliv;
    @FXML
    private Button btnAjouterLiv;
    @FXML
    private Button btnModifierLiv;
    @FXML
    private TextField tflprix_liv;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimerLivraison(ActionEvent event) {
         Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setContentText("Livraison a été supprimée avec succès");
        
        alert.showAndWait();
        
        int  resid_livraison=Integer.valueOf( tflid_livraison.getText());
        String resaddresse_liv= tfladdresse_liv.getText();
        String resdestinataire=tfldestinataire.getText();
        float respoids=Float.valueOf(tflpoids.getText());
        String rescontenu=tflcontenu.getText();
        float  resprix_liv=Float.valueOf( tflprix_liv.getText());
        LivraisonCRUD ocd= new LivraisonCRUD();
        Livraison l = new Livraison(resid_livraison, resaddresse_liv, resdestinataire,respoids, rescontenu,resprix_liv  );
        ocd.SupprimerLivraison(l);
        System.out.println("Done!");
    }

    @FXML
    private void AjouterLivraison(ActionEvent event) {
           
            
        int  resid_livraison=Integer.valueOf( tflid_livraison.getText());
        String resaddresse_liv= tfladdresse_liv.getText();
        String resdestinataire=tfldestinataire.getText();
        float respoids=Float.valueOf(tflpoids.getText());
        String rescontenu=tflcontenu.getText();
        float  resprix_liv=Float.valueOf( tflprix_liv.getText());
        
        
        
        LivraisonCRUD ocd= new LivraisonCRUD();
        Livraison l = new Livraison(resid_livraison, resaddresse_liv, resdestinataire,respoids, rescontenu,resprix_liv  );
        ocd.addLivraison(l);
        System.out.println("Done!");
        
        
        
          
    }

    @FXML
    private void ModifierLivraison(ActionEvent event) {
        
         Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setContentText("Livraison a été modifiée avec succès");
        
        alert.showAndWait();
        
        int  resid_livraison=Integer.valueOf( tflid_livraison.getText());
        String resaddresse_liv= tfladdresse_liv.getText();
        String resdestinataire=tfldestinataire.getText();
        float respoids=Float.valueOf(tflpoids.getText());
        String rescontenu=tflcontenu.getText();
        float  resprix_liv=Float.valueOf( tflprix_liv.getText());
        LivraisonCRUD ocd= new LivraisonCRUD();
        Livraison l = new Livraison(resid_livraison, resaddresse_liv, resdestinataire,respoids, rescontenu,resprix_liv  );
        ocd.ModifierLivraison(l);
        System.out.println("Done!");
    }

    private void btn_ret(ActionEvent event) {
         try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("menuclient.fxml"));
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
