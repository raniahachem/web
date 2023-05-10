/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3a18.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VehiculeController implements Initializable {

    @FXML
    private ImageView image1;
    @FXML
    private Button browseImageV;
    @FXML
    private TextField tf_Immatriculation;
    @FXML
    private TextField tf_Cin_Conducteur;
    @FXML
    private TextField tf_Etat;
    @FXML
    private TextField tf_Kilometrage;
    @FXML
    private TextField tf_ImageV;
    @FXML
    private Button btn_ajouterV;
    @FXML
    private Button btn_modifierV;
    @FXML
    private Button btn_SupprimerV;
    @FXML
    private TableView<?> TabVehicule_R;
    @FXML
    private TableColumn<?, ?> Immatriculation_R;
    @FXML
    private TableColumn<?, ?> Type_Du_Vehicule_R;
    @FXML
    private TableColumn<?, ?> Marque_R;
    @FXML
    private TableColumn<?, ?> Cin_Conducteur_R;
    @FXML
    private TableColumn<?, ?> Etat_R;
    @FXML
    private TableColumn<?, ?> Kilometrage_R;
    @FXML
    private TableColumn<?, ?> Image_R;
    @FXML
    private TextField tf_SearchV;
    @FXML
    private ChoiceBox<?> cb_typeV;
    @FXML
    private ChoiceBox<?> cb_marqueV;
    @FXML
    private Button QRcodeV;
    @FXML
    private Button pdfV;
    @FXML
    private Button BtnStatisticV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterV(ActionEvent event) {
    }

    @FXML
    private void modifierV(ActionEvent event) {
    }

    @FXML
    private void SupprimerV(ActionEvent event) {
    }

    @FXML
    private void getSelectedV(MouseEvent event) {
    }

    @FXML
    private void generatePDF(ActionEvent event) {
    }

    @FXML
    private void handleBtnStatisticV(ActionEvent event) {
    }

    @FXML
    private void gotomenuadmin(ActionEvent event) {
    }
    
}
