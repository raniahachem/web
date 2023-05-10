/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.payement;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import autoxpress.services.payementCRUD;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class PayementController implements Initializable {

    @FXML
    private TextField txt_idclient;
    @FXML
    private TextField txt_modepayment;
    @FXML
    private TextField txt_prixcourse;
    @FXML
    private Button btn_back;
    @FXML
    private TableView<payement> tab_payment;
    @FXML
    private TableColumn<payement, String> tab_id;
    @FXML
    private TableColumn<payement, String> tab_idclient;
    @FXML
    private TableColumn<payement, String> tab_modepayment;
    @FXML
    private TableColumn<payement, String> tab_prixcourse;
    @FXML
    private TableColumn<payement, Date> tab_datepayment;
    @FXML
    private Button btn_payer;
    @FXML
    private DatePicker txt_datepayment;
    @FXML
    private Button jours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the payments table view
        tab_id.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        tab_idclient.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        tab_modepayment.setCellValueFactory(new PropertyValueFactory<>("modePayment"));
        tab_prixcourse.setCellValueFactory(new PropertyValueFactory<>("prixCourse"));
        tab_datepayment.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        // Populate the payments table view with data from the database
        payementCRUD p = new payementCRUD();
        List<payement> payments = p.getAll();
        ObservableList<payement> paymentList = FXCollections.observableArrayList(payments);
        tab_payment.setItems(paymentList);

        txt_datepayment.setValue(LocalDate.now());

        

    }

    @FXML
private void showDaysSincePayment(ActionEvent event) {
    // Get the selected payment from the table view
    payement selectedPayment = tab_payment.getSelectionModel().getSelectedItem();
    if (selectedPayment == null) {
        // If no payment is selected, show an error message and return
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a payment from the table first.");
        alert.showAndWait();
        return;
    }

    // Calculate the number of days between the selected payment date and the current date
    java.sql.Date paymentDate = (java.sql.Date) selectedPayment.getPaymentDate();
    LocalDate currentDate = LocalDate.now();
    java.sql.Date currentDateSql = java.sql.Date.valueOf(currentDate);
    long daysSincePayment = ChronoUnit.DAYS.between(paymentDate.toLocalDate(), currentDateSql.toLocalDate());

    // Show the number of days in an alert
    Alert alert = new Alert(Alert.AlertType.INFORMATION, "It has been " + daysSincePayment + " days since the payment was made.");
    alert.showAndWait();
}





    @FXML
    private void back(ActionEvent event) {

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
    private void payer(ActionEvent event) {
        try {
            // Get the payment details from the text fields
            int clientId = Integer.parseInt(txt_idclient.getText());
            String modePayment = txt_modepayment.getText();
            float prixCourse = Float.parseFloat(txt_prixcourse.getText());
            LocalDate localDatePayment = txt_datepayment.getValue();
            Date datePayment = java.sql.Date.valueOf(localDatePayment);
            // Create a new payment object with the details
            payement p = new payement(0, clientId, modePayment, prixCourse, datePayment);

            // Add the payment to the database
            payementCRUD c = new payementCRUD();
            c.addPayment(p);
            // Initialize the payments table view
            tab_id.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
            tab_idclient.setCellValueFactory(new PropertyValueFactory<>("clientId"));
            tab_modepayment.setCellValueFactory(new PropertyValueFactory<>("modePayment"));
            tab_prixcourse.setCellValueFactory(new PropertyValueFactory<>("prixCourse"));
            tab_datepayment.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

            // Populate the payments table view with data from the database
            List<payement> payments = c.getAll();
            ObservableList<payement> paymentList = FXCollections.observableArrayList(payments);
            tab_payment.setItems(paymentList);

            txt_datepayment.setValue(LocalDate.now());
            // Refresh the payments table view
            // TODO: Implement this part based on your code
        } catch (NumberFormatException e) {
            // Handle the case where one of the text fields is not a valid number
            System.out.println("Invalid input: " + e.getMessage());
        }
         int s = 5; // define and assign a value to s
        Notifications notificationBuilder = Notifications.create()
                .title("Alert")
                .text("Payment added  SUCCESSFULLY")
                .graphic(null)
                .hideAfter(Duration.seconds(s))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("payment added  successfully ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
      
}
