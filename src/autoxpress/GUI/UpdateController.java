/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package autoxpress.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import autoxpress.entities.Reservation;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import autoxpress.services.reservationCRUD;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class UpdateController implements Initializable {

    @FXML
    private TextField tfNewidOffre;
    @FXML
    private TextField tfNewidClient;
    @FXML
    private TextField tfNewptDepart;
    @FXML
    private TextField tfNewptArrive;
    @FXML
    private TextField tfNewidConducteur;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField tfNewnbPlace;
    @FXML
    private TextField tfidReservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancel(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        try {
            Parent root = loader.load();
            // Get the controller for the "historique" interface
            ReservationController rc = loader.getController();
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
    private void save(ActionEvent event) {
        reservationCRUD rcd = new reservationCRUD();

        if (tfidReservation.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun id choisi !");
            alert.showAndWait();
            return;
        }
        int resNbPlace = Integer.parseInt(tfNewnbPlace.getText());
        int resId_offre = Integer.parseInt(tfNewidOffre.getText());
        int resIdClient = Integer.parseInt(tfNewidClient.getText());
        String resPointDepart = tfNewptDepart.getText();
        String resPointArrive = tfNewptArrive.getText();
        int resIdConducteur = Integer.parseInt(tfNewidConducteur.getText());
        String id = tfidReservation.getText();
        reservationCRUD rsc = new reservationCRUD();
        Reservation rs = new Reservation(
                resNbPlace, resId_offre, resIdClient, resPointDepart, resPointArrive,
                resIdConducteur);
        rsc.updateReservation(rs, id);
        System.out.println("added successfully");

        int s = 5; // define and assign a value to s
        Notifications notificationBuilder = Notifications.create()
                .title("Alert")
                .text("UPDATED SUCCESSFULLY")
                .graphic(null)
                .hideAfter(Duration.seconds(s))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Updated successfully ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

    }
}
