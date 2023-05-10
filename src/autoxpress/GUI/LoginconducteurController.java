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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class LoginconducteurController implements Initializable {

    @FXML
    private TextField mailconducteur;
    @FXML
    private TextField mdpconducteur;
    @FXML
    private Button btnconnectco;
    @FXML
    private Button compteconducteur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addconnectcond(ActionEvent event) {
         LoginController r=  new LoginController();
       String email= mailconducteur.getText();
      String mdp=mdpconducteur.getText();
        
        r.conducteurLogin(email,mdp);
         
    }

    @FXML
    private void retourtohome(ActionEvent event) {
        try {
        Parent avisParent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene avisScene = new Scene(avisParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(avisScene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
    private void comptecon(ActionEvent event) {
        
           try {
        Parent avisParent = FXMLLoader.load(getClass().getResource("inscriptionconducteur.fxml"));
        Scene avisScene = new Scene(avisParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(avisScene);
        window.show();
    } catch (IOException e) {
    }
        
    }
    
}
