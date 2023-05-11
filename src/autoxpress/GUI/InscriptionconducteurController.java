/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Conducteur;
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
import autoxpress.services.ConducteurCRUD;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class InscriptionconducteurController implements Initializable {

    @FXML
    private TextField tftcin_conducteur;
    @FXML
    private TextField tftnom_conducteur;
    @FXML
    private TextField tftprenom_conducteur;
    @FXML
    private TextField tfttelephone_conducteur;
    @FXML
    private TextField tftemail_conducteur;
    @FXML
    private TextField tftville_conducteur;
    @FXML
    private TextField tftmdp_conducteur;
    @FXML
    private TextField tfttype_de_permis;
    @FXML
    private TextField tftimage_conducteur;
    @FXML
    private Button btnadd_conducteur;
    @FXML
    private Button browseImageV;
    @FXML
    private ImageView image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        browseImageV.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File fileImageV = fileChooser.showOpenDialog(browseImageV.getScene().getWindow());
            if (fileImageV != null) {
                tftimage_conducteur.setText(fileImageV.getPath());
            }
        });
        
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
    private void add_conducteur(ActionEvent event) {

        int cin_conducteur = Integer.parseInt(tftcin_conducteur.getText());
        String nom_conducteur = tftnom_conducteur.getText();
        String prenom_conducteur = tftprenom_conducteur.getText();
        int telephone_conducteur = Integer.parseInt(tfttelephone_conducteur.getText());
        String email_conducteur = tftemail_conducteur.getText();
        String ville_conducteur = tftville_conducteur.getText();
        String mdp_conducteur = tftmdp_conducteur.getText();
        String type_de_permis = tfttype_de_permis.getText();
        String image_conducteur = tftimage_conducteur.getText();

        Conducteur cn = new Conducteur(0, cin_conducteur, nom_conducteur, prenom_conducteur, telephone_conducteur, email_conducteur, ville_conducteur, mdp_conducteur, type_de_permis, image_conducteur);

        // call the ConducteurCRUD method to add the new conducteur to the database
        ConducteurCRUD conducteurCRUD = new ConducteurCRUD();
        conducteurCRUD.AddConducteur(cn);

        // display a success message and clear the text fields
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Le conducteur a été ajouté avec succès.");
        alert.showAndWait();
        //clean(event);

    }

    @FXML
    private void retourtohome(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("loginconducteur.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
}
