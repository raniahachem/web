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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import autoxpress.services.ClientCRUD;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class InscriptionclientController implements Initializable {

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
    // get the values from the text fields
    String nom_client = tftnom_client.getText();
    String prenom_client = tftprenom_client.getText();
    int cin_client = Integer.parseInt(tftcin_client.getText());
    String ville_client = tftville_client.getText();
    int telephone_client = Integer.parseInt(tfttelephonr_client.getText());
    String email_client = tftemail_client.getText();
    String mdp_client = tftmdp_client.getText();

    // check if password is secure enough
    if (!isSecurePassword(mdp_client)) {
        // display an alert message if password is not secure enough
         // display an alert message in the label if password is not secure enough
         label.setVisible(true);
        label.setText("Le mot de passe doit contenir au moins 8 caractères, avec au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
        return;
    }

    // create a new Client object with the entered values
    Client newClient = new Client(nom_client, prenom_client, cin_client, ville_client, telephone_client, email_client, mdp_client);

    // call the ClientCRUD method to add the new client to the database
    ClientCRUD clientCRUD = new ClientCRUD();
    clientCRUD.AddClient(newClient);

    // display a success message and clear the text fields
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setContentText("Le client a été ajouté avec succès.");
    alert.showAndWait();
    //clean(event);

    }
    private boolean isSecurePassword(String password) {
    // check if password contains at least 8 characters, with at least one uppercase letter, one lowercase letter, one digit, and one special character
    String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    return password.matches(pattern);
}

    @FXML
    private void retourtohome(ActionEvent event) {
        try {
        Parent avisParent = FXMLLoader.load(getClass().getResource("loginclient.fxml"));
        Scene avisScene = new Scene(avisParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(avisScene);
        window.show();
    } catch (IOException e) {
    }
    }
    }
    

