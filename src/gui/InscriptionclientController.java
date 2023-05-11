/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class InscriptionclientController implements Initializable {

    @FXML
    private ImageView image1;
    @FXML
    private TextField tftid_client;
    @FXML
    private TextField tftville_client;
    @FXML
    private TextField tftnom_client;
    @FXML
    private TextField tfttelephonr_client;
    @FXML
    private TextField tftprenom_client;
    @FXML
    private TextField tftmdp_client;
    @FXML
    private TextField tftcin_client;
    @FXML
    private TextField tftemail_client;
    @FXML
    private Button btnadd_client;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add_client(ActionEvent event) {
    }

    @FXML
    private void retourtohome(ActionEvent event) {
    }
    
}
