/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.sendgrid.helpers.mail.objects.Email;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;

import com.google.zxing.WriterException;
import autoxpress.entities.Conducteur;

import com.google.zxing.BarcodeFormat;


import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import javafx.fxml.FXML;

import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.JOptionPane;
import autoxpress.entities.Contrat;
import java.awt.image.BufferedImage;

import java.net.URL;
import com.google.zxing.common.ByteMatrix;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.rest.content.v1.Content;
import java.awt.Color;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import java.security.GeneralSecurityException;
import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.Hashtable;

import java.util.List;

import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import autoxpress.services.ConducteurCRUD;
import autoxpress.services.ContratCRUD;
import com.google.zxing.common.BitMatrix;
import java.io.File;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BKHmidi
 */
public class ListeContratController implements Initializable {

    private String QR_API_ENDPOINT = "https://api.qrserver.com/v1/create-qr-code/?data=%s&size=%dx%d";

    @FXML
    private TextField tfidad;
    private TextField tfidcon;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tf_Contrat_D;
    @FXML
    private TableView<Contrat> TabContrat;
    @FXML
    private TableColumn<Contrat, Integer> id_contrattab;
    @FXML
    private TableColumn<Contrat, Integer> id_contab;
    @FXML
    private TableColumn<Contrat, Integer> id_adtab;
    @FXML
    private TableColumn<Contrat, Date> ddtab;
    @FXML
    private TableColumn<Contrat, Date> dftab;
    @FXML
    private TableColumn<Contrat, Integer> prixtab;
    @FXML
    private TableColumn<Contrat, String> statuttab;

    ObservableList<Contrat> list;
    int index = -1;
    @FXML
    private TextField tfidContrat;
    @FXML
    private TextField tfsearch_contrat;
    private ContratCRUD contratCRUD;
    @FXML
    private DatePicker dpdd;
    @FXML
    private DatePicker dpdf;
    @FXML
    private ComboBox<String> cbconducteur;
    @FXML
    private ComboBox<String> status;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField link;
    private TableView<Contrat> TabFilter;
    private TableColumn<?, ?> id_contrattab1;
    private TableColumn<?, ?> id_contab1;
    private TableColumn<?, ?> id_adtab1;
    private TableColumn<?, ?> ddtab1;
    private TableColumn<?, ?> dftab1;
    private TableColumn<?, ?> prixtab1;
    private TableColumn<?, ?> statuttab1;
    @FXML
    private Button btn;
    @FXML
    private Button btn_update_contrat;
    @FXML
    private Button serachButton;
    @FXML
    private Button scanQRButton;
    @FXML
    private Button btnarchived;
    @FXML
    private Button btn_Supprimer_contrat;
    @FXML
    private Button btnrec;
    @FXML
    private Button btnuti;
    @FXML
    private Button btnres;
    @FXML
    private Button btncontrat;
    @FXML
    private Button btnuti2;
    @FXML
    private Button btnveh;
    @FXML
    private Button btnlivr;
    @FXML
    private Button btnoffre;
    @FXML
    private ImageView image1;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("/image/logoauto2.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        image1.setImage(new Image(localURL));

        UpdateTabContrat();

        status.getItems().addAll("en attente", "terminé", "suspendu", "en cours");

        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Set the field as non-editable
        contratCRUD = new ContratCRUD();
        TabContrat.setItems(FXCollections.observableArrayList(contratCRUD.filtrerContrats(list)));

        ConducteurCRUD cd = new ConducteurCRUD();
        List<Conducteur> conducteurData = cd.ConducteurList();
        // create a list of conducteur ids
        List<String> conducteurId = new ArrayList<>();
        for (Conducteur conducteur : conducteurData) {
            conducteurId.add(String.valueOf(conducteur.getId_conducteur()));
        }

        // set the list of conducteur ids as the items of the ComboBox
        cbconducteur.setItems(FXCollections.observableArrayList(conducteurId));

        // add an event handler to the ComboBox
        cbconducteur.setOnAction(event -> selectcond(event));
        status.setOnAction(event -> selectstatus(event));

    }

    /* private static final String USERNAME = "nermine.tarhouni@esprit.tn";
   

    public static void envoyer(String email) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props,new Authenticator() {
         
        });
        Message message = prepareMessage(session, email);
        Transport.send(message);
        System.out.println("Message envoyé avec succès.");
    }
    
 private static Message prepareMessage(Session session, String email) throws MessagingException, AddressException, javax.mail.MessagingException {
   String contrat = "<p>Voici les détails de votre contrat:</p><ul><li>Titre: Contrat de travail</li><li>Date de début: 01/04/2023</li><li>Durée: 1 an</li></ul>";
     Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(USERNAME));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
    message.setSubject("Details de contrat");
    message.setContent(contrat, "text/html; charset=utf-8");
    return message;
}
     */
    public void envoyer(String email, int residcon, int residad, LocalDate dd, LocalDate df, int resprix, String resstatut) throws Exception {

        /* Properties props = new Properties();
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
props.put("mail.from", "AutoXpress");

Session session = Session.getDefaultInstance(props);

MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress(props.getProperty("mail.from")));

// Get the selected conductor email from the combobox
String selectedConductorEmail = "";
String selectedConductorID = (String) cbconducteur.getValue();
try {
    String query = "SELECT email_conducteur FROM conducteur WHERE id_conducteur = ?";
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/autoxpress", "root", "");
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, selectedConductorID);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
        selectedConductorEmail = rs.getString("email_conducteur");
    }
} catch (SQLException ex) {
    System.out.println("Error while fetching conductor email_conducteur: " + ex.getMessage());
}

if (!selectedConductorEmail.isEmpty()) {
    // Set the recipient email to the selected conductor email
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(selectedConductorEmail));

    message.setSubject("Contrat Details" + residcon);

    String details = 
        "Voici les details de votre nouveau contrat:\n" +
        "Conducteur ID: " + residcon + "\n" +
        "Admin ID: " + residad + "\n" +
        "Date début: " + dd.toString() + "\n" +
        "Date fin: " + df.toString() + "\n" +
        "prix: " + resprix+ "\n" +
        "Statut: " + resstatut.toString();
    
    message.setText(details);


    // Set the content of the message
   
    Transport transport = session.getTransport("smtp");
    transport.connect("smtp.gmail.com", "autoxpresstn@gmail.com", "vfhsaslvbnlyabfv");
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
} else {
    System.out.println("Selected conductor email is empty!");*/
        // Create a ByteArrayOutputStream to hold the generated PDF
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.from", "AutoXpress");

        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(props.getProperty("mail.from")));

        // Get the selected conductor email from the combobox
        String selectedConductorEmail = "";
        String selectedConductorID = (String) cbconducteur.getValue();
        try {
            String query = "SELECT email_conducteur FROM conducteur WHERE id_conducteur = ?";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/autoxpress", "root", "");
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, selectedConductorID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                selectedConductorEmail = rs.getString("email_conducteur");
            }
        } catch (SQLException ex) {
            System.out.println("Error while fetching conductor email_conducteur: " + ex.getMessage());
        }

        if (!selectedConductorEmail.isEmpty()) {
            // Set the recipient email to the selected conductor email
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(selectedConductorEmail));

            message.setSubject("Contrat Details" + residcon);

            // Create a new document to hold the PDF content
            Document document = new Document();

            // Generate the PDF content and write it to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);

            // Open the document
            document.open();

            Paragraph paragraph = new Paragraph();
// create a title paragraph with centered text
            Paragraph title = new Paragraph("Details contrat", FontFactory.getFont(FontFactory.HELVETICA, 24, new BaseColor(111, 110, 184)));
            title.setAlignment(Element.ALIGN_CENTER);

// set background color
            Rectangle rect = new Rectangle(document.getPageSize());
            rect.setBackgroundColor(BaseColor.BLUE);

// add the title to the document
            document.add(title);
// create fonts
            Font labelFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12, new BaseColor(41, 40, 85));
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("C:\\Users\\user\\Documents\\NetBeansProjects\\autoxpress1\\src\\images\\auto.png");
            logo.scaleAbsolute(50f, 50f); // set width and height
            logo.setAbsolutePosition(530f, 780f);
            document.add(logo);

            Paragraph subtitle = new Paragraph("Entre AutoXpress et le conducteur identifié par son ID " + residcon + " (Le contrat est " + resstatut + ")", fontSubtitle);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

// add body text
            Paragraph body = new Paragraph();
            body.add(new Chunk("\nObjet :\n", fontSubtitle));
            body.add(new Chunk("Le présent contrat a pour objet de permettre au conducteur d'utiliser l'application de covoiturage AutoXpress pour effectuer des trajets partagés avec d'autres utilisateurs de l'application.\n", fontBody));

            body.add(new Chunk("\nDurée :\n", fontSubtitle));
            body.add(new Chunk("Le contrat se dure du " + dd.toString() + " et prendra fin le " + df.toString() + ".\n", fontBody));

            body.add(new Chunk("\nConditions d'utilisation :\n", fontSubtitle));
            body.add(new Chunk("Le conducteur s'engage à respecter les conditions d'utilisation de l'application de covoiturage AutoXpress, telles que décrites dans les conditions générales d'utilisation de l'application. Il s'engage notamment à respecter les règles de sécurité routière en vigueur, à ne pas transporter plus de passagers que le nombre autorisé et à ne pas effectuer de trajets à des tarifs inférieurs à ceux fixés par AutoXpress.\n", fontBody));

            body.add(new Chunk("\nPaiement :\n", fontSubtitle));
            body.add(new Chunk("Pendant la durée du contrat, AutoXpress s'engage à payer au conducteur une somme fixe de " + resprix + "DT, en contrepartie de l'utilisation de l'application de covoiturage.\n", fontBody));

            body.add(new Chunk("\nRésiliation :\n", fontSubtitle));
            body.add(new Chunk("En cas de manquement grave aux conditions d'utilisation de l'application de covoiturage AutoXpress, AutoXpress se réserve le droit de résilier le contrat sans préavis. Le conducteur pourra également résilier le contrat à tout moment moyennant un préavis de 30 jours.\n", fontBody));

            body.add(new Chunk("\nConfidentialité :\n", fontSubtitle));
            body.add(new Chunk("Les parties s'engagent à respecter la confidentialité des informations échangées dans le cadre de l'utilisation de l'application de covoiturage AutoXpress. Le conducteur s'engage à ne pas divulguer les données personnelles.\n", fontBody));

            body.add(new Chunk("\nFait à Tunis, le " + dd.toString() + "\n\n", fontBody));
            body.add(new Chunk("\n  [Signature du conducteur] \n", fontBody));

            document.add(body);
// Close the document
            document.close();
// Convert the PDF content to a byte array
            byte[] pdfContent = outputStream.toByteArray();

// Create the PDF attachment
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.setFileName("Contrat.pdf");
            pdfAttachment.setContent(pdfContent, "application/pdf");

// Create the email message
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart textPart = new MimeBodyPart();
            String textContent = "Voici les détails de votre nouveau contrat:\n"
                    + "Conducteur ID: " + residcon + "\n"
                    + "Admin ID: " + residad + "\n"
                    + "Date début: " + dd.toString() + "\n"
                    + "Date fin: " + df.toString() + "\n"
                    + "Prix: " + resprix + "\n"
                    + "Statut: " + resstatut.toString() + "\n"
                    + "Veuillez trouver ci-joint le contrat AutoXpress.";
            textPart.setText(textContent);
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(pdfAttachment);

// Set the email message properties
            message.setContent(multipart);
            message.saveChanges();

// Send the email message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "autoxpresstn@gmail.com", "vfhsaslvbnlyabfv");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }

    }

    /*  Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.from", "AutoXpress");

    Session session = Session.getDefaultInstance(props);

    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(props.getProperty("mail.from")));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress("balkisshmidi1999@gmail.com"));
    
   

message.setFrom(new InternetAddress(props.getProperty("mail.from")));

// Get the selected conductor email from the combobox
String selectedConductorEmail = "";
String selectedConductorID = (String) cbconducteur.getSelectedItem();
try {
    String query = "SELECT email FROM conducteur WHERE id_conducteur = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, selectedConductorID);
    ResultSet rs = statement.executeQuery();
    if (rs.next()) {
        selectedConductorEmail = rs.getString("email");
    }
} catch (SQLException ex) {
    System.out.println("Error while fetching conductor email: " + ex.getMessage());
}

if (!selectedConductorEmail.isEmpty()) {
    // Set the recipient email to the selected conductor email
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(selectedConductorEmail));

    
    
    
    message.setSubject("Contrat Details" + residcon);
   
        String details = 
                
              "Voici les details de votre nouveau contrat:\n" +
              "Conducteur ID: " + residcon + "\n" +
              "Admin ID: " + residad + "\n" +
              "Date début: " + dd.toString() + "\n" +
              "Date fin: " + df.toString() + "\n" +
                "prix: " + resprix+ "\n" +
                "Statut: " + resstatut.toString();
    message.setText(details);
  String html = "<html><body>"
        + "<img src=\"cid:image\">"
        + "<p>Here is the image you requested.</p>"
        + "</body></html>";
// Attach an image to the email
MimeBodyPart imagePart = new MimeBodyPart();
DataSource fds = new FileDataSource("C:/Users/BKHmidi/Documents/NetBeansProjects/testxpress/src/images/auto.png"); // replace with your image file    

imagePart.setDataHandler(new DataHandler(fds));
imagePart.setHeader("Content-ID", "<image>");

Multipart multipart = new MimeMultipart();
multipart.addBodyPart(imagePart);
MimeBodyPart htmlPart = new MimeBodyPart();
htmlPart.setContent(html, "text/html");

// Add the text message to the multipart
MimeBodyPart textPart = new MimeBodyPart();

textPart.setText(details);
multipart.addBodyPart(textPart);

// Set the content of the message
message.setContent(multipart);

    Transport transport = session.getTransport("smtp");
    transport.connect("smtp.gmail.com", "nermine.tarhouni@esprit.tn", "223JFT4618");
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
}
     */
    @FXML
    private void savecontrat(ActionEvent event) {

        int residcon = Integer.parseInt(cbconducteur.getValue());
        int residad = Integer.parseInt(tfidad.getText());
        LocalDate dd = dpdd.getValue();
        LocalDate df = dpdf.getValue();
        int resprix = Integer.parseInt(tfprix.getText());
        String resstatut = status.getValue();
        ContratCRUD pcd = new ContratCRUD();
        if (dd == null || df == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout du contrat");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner les dates de début et de fin du contrat");
            alert.showAndWait();
            return;
        }
        if (cbconducteur.getValue() == null || tfidad.getText().isEmpty() || dpdd.getValue() == null || dpdf.getValue() == null
                || tfprix.getText().isEmpty() || status.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout du contrat");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // Check if start date is earlier than end date
        if (dd.isAfter(df)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout du contrat");
            alert.setHeaderText(null);
            alert.setContentText("La date de fin doit être après la date de début du contrat");
            alert.showAndWait();
            return;
        }
        Date date_debut = Date.valueOf(dd);
        Date date_fin = Date.valueOf(df);

        Contrat c = new Contrat(residcon, residad, date_debut, date_fin, resprix, resstatut);

        // Check if the Contrat already exists in the database
        if (pcd.contratExists(c)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Le contrat existe déjà");
            alert.setContentText("Le contrat que vous essayez d'ajouter existe déjà dans la base de données.");
            alert.showAndWait();

            UpdateTabContrat();
        } else {
            try {
                pcd.AddContrat(c);
                // Send an email to balkisshmidi1999@gmail.com

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Contrat ajouté");
                alert.setContentText("Le contrat a été ajouté avec succès.");
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de l'ajout du contrat");
                alert.setContentText("Une erreur est survenue lors de l'ajout du contrat dans la base de données.");
                alert.showAndWait();
            }

            UpdateTabContrat();
            String recipientEmail = "balkisshmidi1999@gmail.com";

            try {
                envoyer(recipientEmail, residcon, residad, dd, df, resprix, resstatut);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void Supprimercontrat(ActionEvent event) {

        ContratCRUD pcd = new ContratCRUD();

        list = pcd.getDataC();

        if (tf_Contrat_D.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun contrat supprimée !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer le contrat ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            pcd.DeleteContrat(Integer.parseInt(tf_Contrat_D.getText()));
            System.out.println("Contrat supprimé !");
            UpdateTabContrat();
        }

        clean(event);

    }

    private Object Myconnection() {
        return null;

    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = TabContrat.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfidContrat.setText(id_contrattab.getCellData(index).toString());
        tfidad.setText(id_adtab.getCellData(index).toString());
        cbconducteur.setValue(id_contab.getCellData(index).toString());
        tfprix.setText(prixtab.getCellData(index).toString());
        status.setValue(statuttab.getCellData(index));

// Set the value for dpdd
        String ddString = ddtab.getCellData(index).toString();
        if (ddString != null && !ddString.isEmpty()) {
            LocalDate dd = LocalDate.parse(ddString);
            dpdd.setValue(dd);
        }

// Set the value for dpdf
        String dfString = dftab.getCellData(index).toString();
        if (dfString != null && !dfString.isEmpty()) {
            LocalDate df = LocalDate.parse(dfString);
            dpdf.setValue(df);
        }

        Contrat cnt = new Contrat();

        // display the QC details
        //int idContrat = Integer.parseInt(tfidContrat.getText());
        //Contrat contrat = contratCRUD.findById(idContrat);
        //if (contrat != null) {
        // qrcodecontrat.setText(contrat.getQC().getDetails());
        // }
    }

    @FXML
    private void clean(ActionEvent event) {
        cbconducteur.setValue(null);
        tf_Contrat_D.setText(null);
        tfidcon.setText(null);
        tfidad.setText(null);
        dpdd.setValue(LocalDate.now()); // Set to empty value
        dpdf.setValue(LocalDate.now()); // Set to empty value
        tfprix.setText(null);
        status.setValue(null);

    }

    public void UpdateTabContrat() {
        ContratCRUD cv = new ContratCRUD();

        id_contrattab.setCellValueFactory(new PropertyValueFactory<>("id_contrat"));
        id_adtab.setCellValueFactory(new PropertyValueFactory<>("id_admin"));
        id_contab.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));
        ddtab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dftab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prixtab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        statuttab.setCellValueFactory(new PropertyValueFactory<>("statut"));

        list = cv.getDataC();
        TabContrat.setItems(list);

    }

    @FXML
    private void updateContrat(ActionEvent event) {

        int residcontrat = Integer.parseInt(tfidContrat.getText());
        int residcon = Integer.parseInt(cbconducteur.getValue());
        int residad = Integer.parseInt(tfidad.getText());
        LocalDate dd = dpdd.getValue();
        LocalDate df = dpdf.getValue();
        int resprix = Integer.parseInt(tfprix.getText());
        String resstatut = status.getValue();
        ContratCRUD pcd = new ContratCRUD();
        Date date_dd = java.sql.Date.valueOf(dd);
        Date date_df = java.sql.Date.valueOf(df);
        Contrat c = new Contrat(residcontrat, residcon, residad, date_dd, date_df, resprix, resstatut);

        try {
            pcd.UpdateContrat(c);

            // generate PDF document
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("contrat_updated.pdf"));
            document.open();

            // add updated information to PDF document
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Chunk("Contrat ID: " + residcontrat + "\n"));
            paragraph.add(new Chunk("Conducteur ID: " + residcon + "\n"));
            paragraph.add(new Chunk("Admin ID: " + residad + "\n"));
            paragraph.add(new Chunk("Date début: " + dd.toString() + "\n"));
            paragraph.add(new Chunk("Date fin: " + df.toString() + "\n"));
            paragraph.add(new Chunk("Prix: " + resprix + "\n"));
            paragraph.add(new Chunk("Statut: " + resstatut + "\n"));
            document.add(paragraph);

            document.close();

            JOptionPane.showMessageDialog(null, "Contrat modifié et PDF créé");
            UpdateTabContrat();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la modification du contrat");
        }
        clean(event);
    }

    @FXML
    private void search(ActionEvent event) {
        try {
            int id = Integer.parseInt(tfsearch_contrat.getText());
            ContratCRUD pcd = new ContratCRUD();
            Contrat c = pcd.findById(id);
            if (c != null) {
                list = FXCollections.observableArrayList();
                list.add(c);
                TabContrat.setItems(list);
                id_contab.setCellValueFactory(new PropertyValueFactory<>("id_contrat"));
                id_adtab.setCellValueFactory(new PropertyValueFactory<>("id_admin"));
                ddtab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
                dftab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
                prixtab.setCellValueFactory(new PropertyValueFactory<>("prix"));
                statuttab.setCellValueFactory(new PropertyValueFactory<>("statut"));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Contrat introuvable");
                alert.setContentText("Aucun contrat n'a été trouvé avec cet identifiant.");
                alert.showAndWait();
            }
            clean(event);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Identifiant de contrat invalide");
            alert.setContentText("L'identifiant de contrat doit être un entier.");
            alert.showAndWait();
        }
    }

    @FXML
    private void selectcond(ActionEvent event) {
        // get the selected conducteur from the conducteurComboBox
        String selectedCondId = (String) cbconducteur.getValue();

        // do something with the selected conducteur
        System.out.println("conducteur: " + selectedCondId);
    }

    @FXML
    private void selectstatus(ActionEvent event) {
        // get the selected status from the conducteurComboBox
        String selectedStatus = (String) status.getValue();

        // do something with the selected status
        System.out.println("Selected status: " + selectedStatus);
    }

    /*public void generateQRCode(String text, String filePath) {
        try {
            // set the size of the QR code
            int width = 350;
            int height = 350;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            ByteMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

            // save the QR code to a file
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(pngOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */
    @FXML
    private void scanQRCode(ActionEvent event) throws WriterException, FileNotFoundException, DocumentException, IOException, URISyntaxException {

        String prix = tfprix.getText();
        int residcontrat = Integer.parseInt(tfidContrat.getText());
        int residcon = Integer.parseInt(cbconducteur.getValue());
        int residad = Integer.parseInt(tfidad.getText());
        LocalDate dd = dpdd.getValue();
        LocalDate df = dpdf.getValue();
        String resstatut = status.getValue();
        String text = "Contrat ID: " + residcontrat + "\n"
                + "Conducteur ID: " + residcon + "\n"
                + "Admin ID: " + residad + "\n"
                + "Date début: " + dd.toString() + "\n"
                + "Date fin: " + df.toString() + "\n"
                + "prix: " + prix.toString() + "\n"
                + "Statut: " + resstatut;
        if (text != null && !text.isEmpty()) {
            // create QR code image from text
            BufferedImage qrCodeImage = createQRCodeImage(text);

            // create ImageView to display the image
            ImageView imageView = new ImageView(SwingFXUtils.toFXImage(qrCodeImage, null));
            imageView.setFitWidth(300);
            imageView.setFitHeight(300);
            imageView.setPreserveRatio(true);

            // create dialog box to display the image
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().setContent(imageView);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

            // wait for dialog to be closed
            dialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        // generate PDF document
                        Document document = new Document();
// generate a unique file name for the PDF document
                        String fileName = "qr_code_info_" + System.currentTimeMillis() + ".pdf";

// create PDF document with the unique file name
                        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
                        /*  // add header and footer to PDF document
                    PdfPageEventHelper eventHelper = new PdfPageEventHelper() {
                        public void onEndPage(PdfWriter writer, Document document) {
                            PdfPTable table = new PdfPTable(2);
                            table.setWidthPercentage(100);
                            table.addCell(new PdfPCell(new Phrase("Header")));
                            table.addCell(new PdfPCell(new Phrase("Footer")));
                            table.writeSelectedRows(0, -1, 0, document.top(), writer.getDirectContent());
                            table.writeSelectedRows(0, -1, 0, document.bottom() + 20, writer.getDirectContent());
                        }
                    };
                    writer.setPageEvent(eventHelper);*/

                        document.open();

// create new paragraph
                        Paragraph paragraph = new Paragraph();
// create a title paragraph with centered text
                        Paragraph title = new Paragraph("Details contrat", FontFactory.getFont(FontFactory.HELVETICA, 24, new BaseColor(111, 110, 184)));
                        title.setAlignment(Element.ALIGN_CENTER);

// set background color
                        Rectangle rect = new Rectangle(document.getPageSize());
                        rect.setBackgroundColor(BaseColor.BLUE);

// add the title to the document
                        document.add(title);
// create fonts
                        Font labelFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
                        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12, new BaseColor(41, 40, 85));
                        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
                        Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
                        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

                        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("C:\\Users\\user\\Documents\\NetBeansProjects\\autoxpress1\\src\\images\\auto.png");
                        logo.scaleAbsolute(50f, 50f); // set width and height
                        logo.setAbsolutePosition(530f, 780f);
                        document.add(logo);

                        Paragraph subtitle = new Paragraph("Entre AutoXpress et le conducteur identifié par son ID " + residcon + " (Le contrat est " + resstatut + ")", fontSubtitle);
                        subtitle.setAlignment(Element.ALIGN_CENTER);
                        document.add(subtitle);

// add body text
                        Paragraph body = new Paragraph();
                        body.add(new Chunk("\nObjet :\n", fontSubtitle));
                        body.add(new Chunk("Le présent contrat a pour objet de permettre au conducteur d'utiliser l'application de covoiturage AutoXpress pour effectuer des trajets partagés avec d'autres utilisateurs de l'application.\n", fontBody));

                        body.add(new Chunk("\nDurée :\n", fontSubtitle));
                        body.add(new Chunk("Le contrat se dure du " + dd.toString() + " et prendra fin le " + df.toString() + ".\n", fontBody));

                        body.add(new Chunk("\nConditions d'utilisation :\n", fontSubtitle));
                        body.add(new Chunk("Le conducteur s'engage à respecter les conditions d'utilisation de l'application de covoiturage AutoXpress, telles que décrites dans les conditions générales d'utilisation de l'application. Il s'engage notamment à respecter les règles de sécurité routière en vigueur, à ne pas transporter plus de passagers que le nombre autorisé et à ne pas effectuer de trajets à des tarifs inférieurs à ceux fixés par AutoXpress.\n", fontBody));

                        body.add(new Chunk("\nPaiement :\n", fontSubtitle));
                        body.add(new Chunk("Pendant la durée du contrat, AutoXpress s'engage à payer au conducteur une somme fixe de " + prix + "DT, en contrepartie de l'utilisation de l'application de covoiturage.\n", fontBody));

                        body.add(new Chunk("\nRésiliation :\n", fontSubtitle));
                        body.add(new Chunk("En cas de manquement grave aux conditions d'utilisation de l'application de covoiturage AutoXpress, AutoXpress se réserve le droit de résilier le contrat sans préavis. Le conducteur pourra également résilier le contrat à tout moment moyennant un préavis de 30 jours.\n", fontBody));

                        body.add(new Chunk("\nConfidentialité :\n", fontSubtitle));
                        body.add(new Chunk("Les parties s'engagent à respecter la confidentialité des informations échangées dans le cadre de l'utilisation de l'application de covoiturage AutoXpress. Le conducteur s'engage à ne pas divulguer les données personnelles.\n", fontBody));

                        body.add(new Chunk("\nFait à Tunis, le " + dd.toString() + "\n\n", fontBody));
                        body.add(new Chunk("\n  [Signature du conducteur] \n", fontBody));
                        document.add(body);

// add paragraph to document
                        document.add(paragraph);

// close document
                        document.close();

                    } catch (DocumentException ex) {
                        Logger.getLogger(ListeContratController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListeContratController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ListeContratController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        }

    }
private BufferedImage createQRCodeImage(String text) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // encode text as QR code
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);

        // create BufferedImage from BitMatrix
        BufferedImage qrCodeImage = new BufferedImage(300, 300, BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                int grayValue = bitMatrix.get(x, y) ? 0 : 255;
                qrCodeImage.setRGB(x, y, (grayValue << 16) | (grayValue << 8) | grayValue);
            }
        }

        return qrCodeImage;
    }







    @FXML
    private void archived(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Archivedcontrat.fxml"));
        Parent root = loader.load();
        ArchivedcontratController dc = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }








    private void menumessagess(ActionEvent event) {
          try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("message.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
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
