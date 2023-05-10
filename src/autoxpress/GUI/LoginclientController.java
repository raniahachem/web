/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Client;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import autoxpress.services.ClientCRUD;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class LoginclientController implements Initializable {

    @FXML
    private TextField btnmdb_cl;
    @FXML
    private TextField btnmail_cl;
    @FXML
    private Button btnconnectcl;
    @FXML
    private Button compteclient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add_connecter(ActionEvent event) {

        LoginController r = new LoginController();
        String email = btnmail_cl.getText();
        String mdp = btnmdb_cl.getText();

        r.clientLogin(email, mdp);

    } 
    
   /* LoginController s = new LoginController();
    String mail = tf_mail.getText();
    String pwd = tf_pwd.getText();

    s.connect (tf_mail.getText(), tf_pwd.getText());*/

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
    private void comptecl(ActionEvent event) {
           try {
        Parent avisParent = FXMLLoader.load(getClass().getResource("Inscriptionclient.fxml"));
        Scene avisScene = new Scene(avisParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(avisScene);
        window.show();
    } catch (IOException e) {
    }
        
 
      
}
}