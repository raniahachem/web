/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Reservation;
import autoxpress.entities.Client;
import autoxpress.entities.historique_reservation;
import autoxpress.interfaces.ClientInterface;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import autoxpress.services.ClientCRUD;
import autoxpress.services.historique_reservationCRUD;
import autoxpress.services.reservationCRUD;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class ReservationController implements Initializable {

    @FXML
    private Button btnConfirmerReservation;
    @FXML
    private TextField tfIdOffre;
    @FXML
    private ComboBox<Integer> tfIdClient;

    @FXML
    private TextField tfPointDepart;
    @FXML
    private TextField tfPointArrive;
    @FXML
    private TextField tfIdConcucteur;
    @FXML
    private TextField tfNombrePlaces;
    @FXML
    private Button btnHistorique;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfIdRes;
    @FXML
    private Button btnPayer;
    @FXML
    private TextField numerotel;
    @FXML
    private Button btnmeteo;
    @FXML
    private Button btnback;
    private ImageView image1;
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
        getclients();
        
        File file = new File("C:/Users/user/Downloads/cov.jpg");
            String localURL = "";
            try {
                localURL = file.toURI().toURL().toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            image1.setImage(new Image(localURL));
    }

    @FXML
    private void confirmer(ActionEvent event) {

        int resNbPlace = Integer.parseInt(tfNombrePlaces.getText());
        int resId_offre = Integer.parseInt(tfIdOffre.getText());
        int resIdClient = tfIdClient.getValue();
        String resPointDepart = tfPointDepart.getText();
        String resPointArrive = tfPointArrive.getText();
        int resIdConducteur = Integer.parseInt(tfIdConcucteur.getText());

        reservationCRUD rsc = new reservationCRUD();
        Reservation rs = new Reservation(resNbPlace, resId_offre, resIdClient,
                resPointDepart, resPointArrive,
                resIdConducteur);
        rsc.addReservation(rs);

        String num = "+216" + numerotel.getText();
        System.out.println(num);
        String message = String.format("Autoxpress : Cher client d'id numero  " + tfIdClient.getValue() + " , Votre réservation de :" + Integer.parseInt(tfNombrePlaces.getText()) + " places a été confirmée ,de  " + tfPointDepart.getText() + " vers " + tfPointArrive.getText() + " avec le conducteur d ID numero :" + Integer.parseInt(tfIdConcucteur.getText()));
        rsc.send(num, message);

    }

    @FXML
    private void showHistorique(ActionEvent event) throws IOException {
        // Load the FXML file for the "historique" interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("historique.fxml"));
        try {
            Parent root = loader.load();

            // Get the controller for the "historique" interface
            HistoriqueController hc = loader.getController();
            // Pass any necessary data to the controller

            // Set the "historique" interface as the root of the scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void delete(ActionEvent event) {

        reservationCRUD rcd = new reservationCRUD();

        if (tfIdRes.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun client supprimée !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer le client ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            rcd.deleteReservation(Integer.parseInt(tfIdRes.getText()));
            System.out.println("Client supprimé !");
            // updatetabclient();
        }
        int s = 5; // define and assign a value to s
        Notifications notificationBuilder = Notifications.create()
                .title("Alert")
                .text("deleted successfully SUCCESSFULLY")
                .graphic(null)
                .hideAfter(Duration.seconds(s))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("deleted successfully ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void updateReservation(ActionEvent event) {
        try {
            // Load the update interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
            Parent root = loader.load();

            // Get the controller for the "update" interface
            UpdateController uc = loader.getController();

            // Pass any necessary data to the controller
            // uc.setData(data);
            // Set the "update" interface as the root of the scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void getclients() {
        ClientCRUD hrc = new ClientCRUD();
        List<Client> list = hrc.getAll();
        System.out.println(list);
        for (Client c : list) {
            tfIdClient.getItems().add(c.getId_client());

        }

        // Set the list of client names as the data source for the combo box
    }

    @FXML
    private void payer(ActionEvent event) {
        try {
            // Load the payement interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("payement.fxml"));
            Parent root = loader.load();

            // Get the controller for the "update" interface
            PayementController pc = loader.getController();

            // Pass any necessary data to the controller
            // uc.setData(data);
            // Set the "update" interface as the root of the scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void meteo(ActionEvent event) {
        try {
            // Load the update interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("meteo.fxml"));
            Parent root = loader.load();

            // Get the controller for the "update" interface
            MeteoController mc = loader.getController();

            // Pass any necessary data to the controller
            // uc.setData(data);
            // Set the "update" interface as the root of the scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
         
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("menuclient.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

///////////////////////////////////////////////


    private void menucontrat(ActionEvent event) {
          try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("listeContrat.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }


    private void livAdmin(ActionEvent event) {
         try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("livraisonConducteur.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }


    private void clients(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("client.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    private void vehicule(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("VehiculeAdmin.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    private void conducteur(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("menuadmin.fxml"));
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
