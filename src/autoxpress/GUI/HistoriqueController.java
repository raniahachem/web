/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Reservation;
import autoxpress.entities.historique_reservation;
import autoxpress.entities.payement;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import autoxpress.services.historique_reservationCRUD;
import autoxpress.services.payementCRUD;
import autoxpress.services.reservationCRUD;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class HistoriqueController implements Initializable {

    @FXML
    private TableView<historique_reservation> tbHistorique;
    @FXML
    private Label historiqueRes;
    @FXML
    private TableColumn<?, ?> id_reservation;
    @FXML
    private TableColumn<?, ?> id_historique_reservation;
    @FXML
    private TableColumn<?, ?> id_conducteur;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> date_depart_reelle;
    @FXML
    private TableColumn<?, ?> date_arrive_reelle;
    @FXML
    private TableColumn<?, ?> lieu_depart;
    @FXML
    private TableColumn<?, ?> lieu_destination;
    @FXML
    private TableColumn<?, ?> avis_client;
    @FXML
    private TableColumn<?, ?> status_reservation;
    @FXML
    private TableColumn<?, ?> id_client;
    @FXML
    private TableColumn<?, ?> action;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox<Integer> tfIdRes;
    @FXML
    private TextField tfDepRelle;
    @FXML
    private TextField tfArriveReelle;
    @FXML
    private TextArea tfAvisClient;
    @FXML
    private TextField tfStatus;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listeHistorique();

    }

    // public void listeHistorique() {

    // historique_reservationCRUD hrc = new historique_reservationCRUD();
    // reservationCRUD hrcd = new reservationCRUD();
    // tbHistorique.getColumns().clear();

    // id_reservation.setCellValueFactory(new
    // PropertyValueFactory<>("id_reservation"));
    // id_historique_reservation.setCellValueFactory(new
    // PropertyValueFactory<>("id_historique_reservation"));
    // id_conducteur.setCellValueFactory(new
    // PropertyValueFactory<>("id_conducteur"));

    // date.setCellValueFactory(new PropertyValueFactory<>("date"));
    // date_depart_reelle.setCellValueFactory(new
    // PropertyValueFactory<>("date_depart_reelle"));
    // date_arrive_reelle.setCellValueFactory(new
    // PropertyValueFactory<>("date_arrive_reelle"));
    // lieu_depart.setCellValueFactory(new PropertyValueFactory<>("lieu_depart"));
    // lieu_destination.setCellValueFactory(new
    // PropertyValueFactory<>("lieu_destination"));
    // avis_client.setCellValueFactory(new PropertyValueFactory<>("avis_client"));
    // status_reservation.setCellValueFactory(new
    // PropertyValueFactory<>("status_reservation"));
    // id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
    // TableColumn<historique_reservation, Void> deleteColumn = new
    // TableColumn<>("action");

    // deleteColumn.setCellFactory(column -> {
    // return new TableCell<historique_reservation, Void>() {
    // private final Button deleteButton = new Button("Delete");

    // {
    // deleteButton.setOnAction(event -> {
    // historique_reservation reservation =
    // getTableView().getItems().get(getIndex());
    // historique_reservationCRUD rsc = new historique_reservationCRUD();

    // rsc.delete(reservation.getId_historique_reservation());
    // System.out.println("Delete reservation with id " +
    // reservation.getId_historique_reservation());
    // listeHistorique();
    // });
    // }

    // @Override
    // protected void updateItem(Void item, boolean empty) {
    // super.updateItem(item, empty);
    // if (empty) {
    // setGraphic(null);
    // } else {
    // setGraphic(deleteButton);
    // }
    // }
    // };
    // });

    // List<historique_reservation> list = hrc.historique_reservationList();
    // tbHistorique.setItems(FXCollections.observableArrayList(list));
    // List<Reservation> lists = hrcd.getAll();
    // for (Reservation c : lists) {

    // tfIdRes.getItems().add(c.getId_reservation());

    // }
    // }
    public void listeHistorique() {
        historique_reservationCRUD hrc = new historique_reservationCRUD();
        reservationCRUD hrcd = new reservationCRUD();

        id_reservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        id_historique_reservation.setCellValueFactory(new PropertyValueFactory<>("id_historique_reservation"));
        id_conducteur.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date_depart_reelle.setCellValueFactory(new PropertyValueFactory<>("date_depart_reelle"));
        date_arrive_reelle.setCellValueFactory(new PropertyValueFactory<>("date_arrive_reelle"));
        lieu_depart.setCellValueFactory(new PropertyValueFactory<>("lieu_depart"));
        lieu_destination.setCellValueFactory(new PropertyValueFactory<>("lieu_destination"));
        avis_client.setCellValueFactory(new PropertyValueFactory<>("avis_client"));
        status_reservation.setCellValueFactory(new PropertyValueFactory<>("status_reservation"));
        id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));

        // Check if tbHistorique already has a delete column
        boolean deleteColumnExists = false;
        for (TableColumn column : tbHistorique.getColumns()) {
            if (column.getText().equals("Action")) {
                deleteColumnExists = true;
                break;
            }
        }

        if (!deleteColumnExists) {
            TableColumn<historique_reservation, Void> deleteColumn = new TableColumn<>("Action");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<historique_reservation, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            historique_reservation reservation = getTableView().getItems().get(getIndex());
                            historique_reservationCRUD rsc = new historique_reservationCRUD();

                            rsc.delete(reservation.getId_historique_reservation());
                            System.out.println(
                                    "Delete reservation with id " + reservation.getId_historique_reservation());
                            listeHistorique();
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            });
            tbHistorique.getColumns().add(deleteColumn);
        }

        List<historique_reservation> list = hrc.historique_reservationList();
        tbHistorique.setItems(FXCollections.observableArrayList(list));
        List<Reservation> lists = hrcd.getAll();
        for (Reservation c : lists) {
            tfIdRes.getItems().add(c.getId_reservation());
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        // Load the FXML file for the "historique" interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        try {
            Parent root = loader.load();
            // Get the controller for the "historique" interface
            // ReservationController rc = loader.getController();
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
        int Id_reservation = tfIdRes.getValue();
        reservationCRUD data = new reservationCRUD();
        Reservation datas = data.getById(Id_reservation);

        String Avis_client = tfAvisClient.getText();
        String Depart_reelle = tfDepRelle.getText();
        String Arrive_reelle = tfArriveReelle.getText();

        int Id_conducteur = datas.getId_conducteur();
        String Lieu_destination = datas.getPoint_de_depart();
        String Status = tfStatus.getText();
        historique_reservationCRUD rsc = new historique_reservationCRUD();

        historique_reservation hr = new historique_reservation(
                Id_reservation, Depart_reelle, Arrive_reelle, Id_conducteur,
                Lieu_destination, Avis_client, Status,
                datas.getId_client());

        rsc.addHistorique_Reservation(hr);
        listeHistorique();
         int s = 5; // define and assign a value to s
        Notifications notificationBuilder = Notifications.create()
                .title("Alert")
                .text("saved SUCCESSFULLY")
                .graphic(null)
                .hideAfter(Duration.seconds(s))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("saved successfully ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

}
