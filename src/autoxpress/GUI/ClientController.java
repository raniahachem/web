/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Client;
import autoxpress.services.ClientCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class ClientController implements Initializable {
private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";
    @FXML
    private Button btnuti;
    @FXML
    private Button btnuti2;
    @FXML
    private Button btnres;
    @FXML
    private Button btnrec;
    @FXML
    private Button btncontrat;
    @FXML
    private Button btnveh;
    @FXML
    private Button btnlivr;
    @FXML
    private TableView<Client> tabclient;
    @FXML
    private TableColumn<Client, ?> tabid_client;
    @FXML
    private TableColumn<Client, ?> tabnom_client;
    @FXML
    private TableColumn<Client, ?> tabprenom_client;
    @FXML
    private TableColumn<Client, ?> tabcin_client;
    @FXML
    private TableColumn<Client, ?> tabville_client;
    @FXML
    private TableColumn<Client, ?> tabtelephone_client;
    @FXML
    private TableColumn<Client, ?> tabemail_client;
    @FXML
    private TableColumn<Client, ?> tabmdp_client;
    @FXML
    private Button btndelete_client;
    @FXML
    private Button affclient;
    @FXML
    private TextField tftid_client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reclamation(ActionEvent event) {
            try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("Recback.fxml"));
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


    @FXML
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


    @FXML
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

    @FXML
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

    @FXML
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
    private void movetohome(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("Loginadmin.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
        
    }

    @FXML
    private void runa(MouseEvent event) {
    }

    @FXML
    private void click(MouseDragEvent event) {
    }

    

    @FXML
    private void delete_client(ActionEvent event) {
        ClientCRUD pcd = new ClientCRUD();

        if (tftid_client.getText().isEmpty()) {
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
            pcd.DeleteClient(Integer.parseInt(tftid_client.getText()));
            System.out.println("Client supprimé !");
            //  updatetabclient();
        }

        //clean(event);
    }

    @FXML
    private void afficherclient(ActionEvent event) {
        // Appel de la méthode ClientList() pour récupérer la liste des clients
    List<Client> clients = ClientList();
    
    // Création d'une observable list pour stocker les clients
    ObservableList<Client> observableClients = FXCollections.observableArrayList(clients);

    // Associer l'observable list à la table view
    tabclient.setItems(observableClients);

    // Configurer les colonnes de la table view
    tabid_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
    tabnom_client.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
    tabprenom_client.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
    tabcin_client.setCellValueFactory(new PropertyValueFactory<>("cin_client"));
    tabville_client.setCellValueFactory(new PropertyValueFactory<>("ville_client"));
    tabtelephone_client.setCellValueFactory(new PropertyValueFactory<>("telephone_client"));
    tabemail_client.setCellValueFactory(new PropertyValueFactory<>("email_client"));
    tabmdp_client.setCellValueFactory(new PropertyValueFactory<>("mdp_client"));
    }

    
    public List<Client> ClientList() {

        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client cd = new Client();
                cd.setId_client(rs.getInt("id_client"));
                cd.setNom_client(rs.getString("nom_client"));
                cd.setPrenom_client(rs.getString("prenom_client"));
                cd.setCin_client(rs.getInt("cin_client"));
                cd.setVille_client(rs.getString("ville_client"));
                cd.setTelephone_client(rs.getInt("telephone_client"));
                cd.setEmail_client(rs.getString("email_client"));
                cd.setMdp_client(rs.getString("mdp_client"));
                clients.add(cd);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clients;
    }

    @FXML
    private void movetomenu(ActionEvent event) {
    }

}
