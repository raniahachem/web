/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;
//import twilio.Twilio;
//import twilio.rest.api.v2010.account.Message;
//import twilio.type.PhoneNumber;
      // Import the Twilio dependencies

import autoxpress.entities.Reclamation;
import autoxpress.entities.Admin;
import autoxpress.entities.Messages;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import autoxpress.services.ReclamationCRUD;
import autoxpress.services.AdminCRUD;
import autoxpress.services.MessageCRUD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BKHmidi
 */
public class SendmessageController implements Initializable {
   
    
    @FXML
    private Button btn_msg;
    @FXML
    private TextField tfcontenu;
    @FXML
    private TextField tfdest;
    @FXML
    private VBox tabmessagex;
    @FXML
    private TextField tfsearch_contrat;
    @FXML
    private Button serachButton;
    @FXML
    private VBox clientList;
    @FXML
    private ComboBox<String> cbcond;
    private int idReclamationSelectionnee;
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
    private Button btnoffre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
AdminCRUD cd = new AdminCRUD();
List<Admin> adminData = cd.getAdminList();

    // create a list of conducteur names
    List<String> adminNames = new ArrayList<>();
    for (Admin admin : adminData) {
        adminNames.add(admin.getEmail_ad());
    }

    // set the list of conducteur names as the items of the ComboBox
    cbcond.setItems(FXCollections.observableArrayList(adminNames));
    
    // add an event handler to the ComboBox
   cbcond.setOnAction(event -> selectconducteur(event));
    
    
   
    
    
   ReclamationCRUD c = new ReclamationCRUD();
  List<Reclamation> ReclamationData = c.afficherReclamation();

// loop through the list and create a label for each user
for (Reclamation reclamation : ReclamationData) {
    Label label = new Label(String.valueOf(reclamation.getId()));
    clientList.getChildren().add(label);
    label.setOnMouseClicked(event -> tfdest.setText(label.getText()));
}  




    }
    
    
    
    
    
    
    
    
    
 private void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

@FXML
private void sendMessage(ActionEvent event) { 
    String expediteur = cbcond.getValue();
int destinataire = Integer.parseInt(tfdest.getText());
String message = tfcontenu.getText();

ReclamationCRUD cd = new ReclamationCRUD();

        if (destinataire== 0) {
            showAlert(Alert.AlertType.ERROR, "reclamation non sélectionné", "Veuillez sélectionner une reclamation.");
            return;
        }

    
   
   Messages m = new Messages();
MessageCRUD pcd = new MessageCRUD();
int id_reclamation = pcd.getIdReclamation(destinataire);
int id_admin = pcd.getIdAdmin(expediteur);

m.setId_rec_id(id_reclamation);
m.setId_admin_id(id_admin);
m.setContenu(message);

Timestamp timestamp = new Timestamp(System.currentTimeMillis());

// Set the date_message field of the Message object
m.setDate_message(timestamp);

if (expediteur == null || destinataire== 0 || message.isEmpty()) {
    showAlert(Alert.AlertType.ERROR, "Champ(s) vide(s)", "Veuillez remplir tous les champs.");
    return;
}

pcd.AddMessage(m); // save the message to the database

//String phoneNumber = cd.getClientPhoneNumber(destinataire);

// Send SMS notification
//sendSms(phoneNumber);
   

    // Create a Label for the new message and add it to the VBox
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String messageText = String.format("%s -> %s : %s (%s)", expediteur, destinataire, message, m.getDate_message() != null ?
            formatter.format(m.getDate_message().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()) :
            formatter.format(LocalDateTime.now()));
    Label messageLabel = new Label(messageText);
    tabmessagex.getChildren().add(messageLabel);

    // Clear input fields
    clean(event);
    
}
public String sendSms(String phoneNumber) {
    // Retrieve phone number associated with client from database
   
     // Set up the API endpoint and credentials
    String apiUrl = "https://api.twilio.com/2010-04-01/Accounts/ACe56a03d46e756dd1a33ebd2e4bd447e6/Messages.json";
    String fromNumber = "+15673717172";
    String body = "AutoXpress:You have a new message";
    String authToken = "2f57fc64326ce163f13a9c8f98bae4fd"; // Replace with your actual auth token

    // Set up the cURL command to send the SMS message
    String[] cmd = {
        "curl",
        "-X",
        "POST",
        apiUrl,
        "--data-urlencode",
        "Body=" + body,
        "--data-urlencode",
        "From=" + fromNumber,
        "--data-urlencode",
        "To=" + "+216" + phoneNumber,
        "-u",
        "ACe56a03d46e756dd1a33ebd2e4bd447e6:" + authToken
    };

    // Execute the cURL command and retrieve the output
    try {
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Command executed successfully");
            System.out.println(output.toString());
        } else {
            System.out.println("Error executing cURL command");
        }
    } catch (IOException e) {
        System.out.println("Error executing cURL command: " + e.getMessage());
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // restore interrupted status
        System.out.println("Interrupted while waiting for cURL command to complete");
    }

    return null;

   
            
} 
           
	/*try {
        // Construct data
        String apiKey = "apikey=" + "NTQzNTUwNjc2YTYxNGYzMzU0NmQzNjY3MzU0NzYyNTY=";
        String message = "&message=" + "You have a new message ";
        String sender = "&sender=" + "AutoXpress";
 String Number = "21652259276";
      
   
        String numbers = "&numbers=" + Number;
  

        // Send data
        HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
        String data = apiKey + numbers + message + sender;
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
        conn.getOutputStream().write(data.getBytes("UTF-8"));
        final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        final StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            //stringBuffer.append(line);
            JOptionPane.showMessageDialog(null,"You have a new message "+line);
        }
        rd.close();

      
    } catch (IOException |HeadlessException e) {
        //System.out.println("Error SMS " + e);
        JOptionPane.showMessageDialog(null,e);
            //return "Error " + e;
    }   
        return null; } */

    private void clean(ActionEvent event) {
   
    tfdest.setText(null);
    tfcontenu.setText(null);
}

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void selectconducteur(ActionEvent event) {
 // get the selected conducteur from the conducteurComboBox
    String selectedConducteurName = (String) cbcond.getValue();

    // do something with the selected conducteur
    System.out.println("Selected conducteur: " + selectedConducteurName);

    }


    private void btn_aff_livconducteur(ActionEvent event) {
         try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("livraisonCondu.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    private void gererReclamations(ActionEvent event) {
            try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("recback.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    private void gererMessage(ActionEvent event) {
            try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("sendmessage_1.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }
   
public void setIdReclamation(int idReclamation) {
    this.idReclamationSelectionnee = idReclamation;
    tfdest.setText(String.valueOf(idReclamation));
}
///////////////////////////////////////////////////////////////
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
    }

    @FXML
    private void runa(MouseEvent event) {
    }

    @FXML
    private void click(MouseDragEvent event) {
    }

    @FXML
    private void offre(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("OffreAffAdmn.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

}
