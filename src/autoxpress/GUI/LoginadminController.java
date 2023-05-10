/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class LoginadminController implements Initializable {

    @FXML
    private TextField bynmail_ad;
    @FXML
    private TextField btnmdp_ad;
    @FXML
    private Button btnconn_ad;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//    @FXML
//    private void connectadm(ActionEvent event) {
//          LoginController r=  new LoginController();
//       String email= bynmail_ad.getText();
//      String mdp=btnmdp_ad.getText();
//        
//        r.adminLogin(email,mdp);
//         try {
//        Parent avisParent = FXMLLoader.load(getClass().getResource("menuadmin.fxml"));
//        Scene avisScene = new Scene(avisParent);
//        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
//        window.setScene(avisScene);
//        window.show();
//    } catch (IOException e) {
//    }
//         movetomenu(event);
//    }
    @FXML
private void connectadm(ActionEvent event) throws IOException {
    String mail = bynmail_ad.getText();
    String pwd = btnmdp_ad.getText();

    // Vérifier si les champs sont remplis
    if (mail.isEmpty() || pwd.isEmpty()) {
        label.setText("Please fill in all the required fields.");
    } else {
        System.out.println("Connexion réussie!");
        LoginController r = new LoginController();
        try {
            Mail.envoyer(mail);
            System.out.println("Le message a été envoyé avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
            e.printStackTrace();
        }
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("menuadmin.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
}
        

    


    @FXML
    private void movetohome(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    private void movetomenu(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
}
