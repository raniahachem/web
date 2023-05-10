/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.gui;

import autoxpress.entities.Client;
import autoxpress.entities.Conducteur;
import autoxpress.entities.Contrat;
import autoxpress.entities.Messages;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import autoxpress.services.ClientCRUD;
import autoxpress.services.ConducteurCRUD;
import autoxpress.services.ContratCRUD;
import autoxpress.services.MessageCRUD;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BKHmidi
 */

public class MessageController implements Initializable {
 int index = -1;
    private TextArea tabmessagex;
    @FXML
    private Button btn_delete_msg;
    @FXML
    private TableColumn<Messages, Integer> id_messagetab;
    @FXML
    private TableColumn<Messages, String> Expediteurtab;
    @FXML
    private TableColumn<Messages,String> Destinairetab;
    @FXML
    private TableColumn<Messages, Date> Heuretab;
    @FXML
    private TableColumn<Messages, String> Contenutab;
    @FXML
    private TableView<Messages> Tabmsg;
    ObservableList<Messages> list;
    @FXML
    private Button backcontratbtn;









    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTabMessage();
    }
    private int id_message;

    private void onMessageSelected(javafx.scene.input.MouseEvent event) {
   String selectedMessage = tabmessagex.getSelectedText();
    // extract the ID of the selected message from the string
    String[] parts = selectedMessage.split(" ");
    String id = parts[0];
    id_message = Integer.parseInt(id);

    }

   

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = Tabmsg.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
      
    
       

    }
    public void UpdateTabMessage() {
    MessageCRUD cv = new MessageCRUD();

    id_messagetab.setCellValueFactory(new PropertyValueFactory<>("id_message"));
    Expediteurtab.setCellValueFactory(new PropertyValueFactory<>("id_client"));
    Destinairetab.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));
    Heuretab.setCellValueFactory(new PropertyValueFactory<>("date_message"));
    Contenutab.setCellValueFactory(new PropertyValueFactory<>("contenu"));


    list = cv.getDataMsg();
    Tabmsg.setItems(list);
}

    @FXML
 

  void deletemessage(ActionEvent event) {
         int selectedIndex = Tabmsg.getSelectionModel().getSelectedIndex();
    if (selectedIndex < 0) {
        // No message selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select a message to delete.");
        alert.showAndWait();
        return;
    }
    // Get the selected message from the TableView
    Messages message = Tabmsg.getSelectionModel().getSelectedItem();

    // Display a confirmation dialog
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Message");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this message?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        // User clicked OK, delete the message from the database
      
        MessageCRUD messageCRUD = new MessageCRUD();
messageCRUD.DeleteMessage(message.getId());
  

        // Delete the message from the TableView
        Tabmsg.getItems().remove(selectedIndex);
         UpdateTabMessage() ;
    }
  }

    @FXML
    private void menuadminn(ActionEvent event) {
           try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("menuadmin.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
    
    
    
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
   
    


  

        

    

