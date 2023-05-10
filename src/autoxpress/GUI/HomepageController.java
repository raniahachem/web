/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author 
 */
public class HomepageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void movetoadmin(ActionEvent event) {
         try {
        Parent avisParent = FXMLLoader.load(getClass().getResource("Loginadmin.fxml"));
        Scene avisScene = new Scene(avisParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(avisScene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
    private void movetoclient(ActionEvent event) {
         try {
        Parent avisParent = FXMLLoader.load(getClass().getResource("Loginclient.fxml"));
        Scene avisScene = new Scene(avisParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(avisScene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
    private void movetoconducteur(ActionEvent event) {
         try {
        Parent avisParent = FXMLLoader.load(getClass().getResource("Loginconducteur.fxml"));
        Scene avisScene = new Scene(avisParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(avisScene);
        window.show();
    } catch (IOException e) {
    }
    }
    
}
