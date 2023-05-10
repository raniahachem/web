/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Messages;
import autoxpress.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class ReponseController implements Initializable {

    @FXML
    private TableView<Messages> tableReclamations;
   @FXML
private TableColumn<Messages, Timestamp> coldate;
@FXML
private TableColumn<Messages, String> colmessage;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherMessages();
    }    

    @FXML
    private void afficherReclamationSelectionnee(MouseEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
public List<Messages> getMessagesFromDatabase() {
    List<Messages> messagesList = new ArrayList<>();
    try {
        String requete = "SELECT * FROM message";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Messages r = new Messages(rs.getString("contenu"), rs.getTimestamp("date_message"));
            messagesList.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return messagesList;
}

private void afficherMessages() {
    List<Messages> messagesList = getMessagesFromDatabase();

    // Créez une ObservableList à partir de la liste de messages pour le TableView
    ObservableList<Messages> observableMessagesList = FXCollections.observableArrayList(messagesList);

    // Associez les colonnes du TableView aux attributs correspondants de Messages
    coldate.setCellValueFactory(new PropertyValueFactory<>("date_message"));
    colmessage.setCellValueFactory(new PropertyValueFactory<>("contenu"));

    // Ajoutez les messages à la TableView
    tableReclamations.setItems(observableMessagesList);
}

}
