/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Offre;
import autoxpress.services.OffreCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterOffreController implements Initializable {

    @FXML
    private Button btncreer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button SuppOffre;
    @FXML
    private ComboBox<String> type_vehicule;
    
     ObservableList<String> listcombo = FXCollections.observableArrayList("voiture", "van","Mini bus","bus");
    @FXML
    private TextField tfid_offre;
    @FXML
    private TextField tfid_conducteur;
    @FXML
    private TextField tfdestination;
    @FXML
    private TextField tfpt_depart;
    @FXML
    private TextField tfprix;
    
    Integer index;
    @FXML
    private Button btnoffre;
    @FXML
    private Button btnavi;
    @FXML
    private Button vehicule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void creer_offre(ActionEvent event) {
         Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setContentText("Offre ajoutée");
        
        alert.showAndWait();
        
         AjouterOffreController mail = new AjouterOffreController();
        mail.sendMail();
        
       
                 
  
        Integer resultatid_offre=Integer.valueOf(tfid_offre.getText());
        Integer resultid_conducteur=Integer.valueOf(tfid_conducteur.getText());
        String resultdestination=(String) tfdestination.getText();
        String resultpt_depart=(String) tfpt_depart.getText();
        Float resultprix= Float.valueOf(tfprix.getText());
        String resulttype_vehicule=(String) type_vehicule.getValue();




        
        OffreCRUD pcd= new OffreCRUD();
        Offre t = new Offre(resultatid_offre, resultid_conducteur,resultdestination,resultpt_depart,resultprix, resulttype_vehicule  );
        pcd.addEntity(t);
        
        System.out.println("Done!");
        
         
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
         Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setContentText("Offre a été modifiée avec succès");
        
        alert.showAndWait();
        
      
        
        
        int  resid_offre=Integer.valueOf( tfid_offre.getText());
        int  resid_conducteur=Integer.valueOf( tfid_conducteur.getText());

        String resdestination= tfdestination.getText();
        String respt_depart=tfpt_depart.getText();
        float resprix=Float.valueOf(tfprix.getText());
        String restype_vehicule=type_vehicule.getValue();
         
        OffreCRUD ocd= new OffreCRUD();
        Offre o = new Offre(resid_offre, resid_conducteur, resdestination,respt_depart, resprix,restype_vehicule  );
        ocd.ModifierOff(o);
        System.out.println("Done!");
    }

    @FXML
    private void SuppOffre(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setContentText("Votre offre a été supprimée avec succès");
        
        alert.showAndWait();
       
       int  resid_offre=Integer.valueOf( tfid_offre.getText());
        int  resid_conducteur=Integer.valueOf( tfid_conducteur.getText());

        String resdestination= tfdestination.getText();
        String respt_depart=tfpt_depart.getText();
        float resprix=Float.valueOf(tfprix.getText());
        String restype_vehicule=type_vehicule.getValue();
          
        OffreCRUD ocd= new OffreCRUD();
        Offre o = new Offre(resid_offre, resid_conducteur, resdestination,respt_depart, resprix,restype_vehicule  );
        ocd.SupprimerOff(o);
        System.out.println("Done!");
                
    }
    
    private String username ="ons.hamdi@esprit.tn" ;
    private String password ="slbihcflbgrggwyz";
        
    public   void sendMail(){
        System.out.println("Preparing to send email");
        
          
        
             Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        props.put("mail.smtp.ssl.trust","*");
        
       
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
return new javax.mail.PasswordAuthentication(username, password);   

            }

             
        });
        
        
     try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("votre_mail@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ons.hamdi@esprit.tn"));
            message.setSubject("Test email");
            message.setText("Votre offre a été publiée avec");
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

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

   @FXML
    private void vehicule(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("VehiculeConducteur.fxml"));
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
            Parent avisParent = FXMLLoader.load(getClass().getResource("AjouterOffre.fxml"));
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
            Parent avisParent = FXMLLoader.load(getClass().getResource("Loginconducteur.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void avi(ActionEvent event) {
    }
    
    
}
