/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package autoxpress.GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import autoxpress.entities.Abonnement;
import autoxpress.services.AbonnementCRUD;
import autoxpress.utils.MyConnection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AbonnementAdminController implements Initializable {

  
    @FXML
    private ImageView image2;

    ObservableList<Abonnement> listAb;
    ObservableList<Abonnement> datalistAb;
    int index = -1;
    @FXML
    private TextField tfSearchAb;
    @FXML
    private TableColumn<Abonnement, Integer> idAbR;
    @FXML
    private TableColumn<Abonnement, String> typeAbR;
    @FXML
    private TableColumn<Abonnement, Float> prixAbR;
    @FXML
    private TableColumn<Abonnement, String> modeDePaiementAbR;
    @FXML
    private TextField tfIdAb;
    @FXML
    private ChoiceBox<String> cbTypeAb;
    @FXML
    private ChoiceBox<String> cbModePaiementAb;
    @FXML
    private TableView<Abonnement> TabAbonnementR;
    @FXML
    private TextField tf_id_ab;
    @FXML
    private ChoiceBox<?> cb_type_ab;
    @FXML
    private ChoiceBox<?> cb_mode_paiement_ab;
    @FXML
    private TableColumn<?, ?> colQrCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbTypeAb.getItems().addAll("mensuel", "annuel");
        cbModePaiementAb.getItems().addAll("Carte Bancaire", "D-17");
        File file = new File("C:/Users/abdel/OneDrive/Bureau/PiDev/Design/unnamed.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        image2.setImage(new Image(localURL));
        UpdateTableAb();
    }

    private void AjouterAb(ActionEvent event) throws javax.mail.MessagingException {
        String resTypeAb = cbTypeAb.getValue();
        float resPrixAb;
        if (resTypeAb.equals("mensuel")) {
            resPrixAb = 128.99f;
        } else {
            resPrixAb = 1089.99f;
        }

        String resMode_Paiement = cbModePaiementAb.getValue();

        AbonnementCRUD abcd = new AbonnementCRUD();

        // Add the subscription entity to the database
        int id_ab = abcd.addEntity2(new Abonnement(resTypeAb, resPrixAb, resMode_Paiement));

//        // Send an email to the admin with the subscription data
//        String to = "mouhamedibrahim.abdelbeki@esprit.tn";
//        String from = "mouhamedibrahim.abdelbeki@esprit.tn";
//        String password = "201JMT1977";
//        String host = "smtp.gmail.com";
//
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
//        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
//            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//                return new javax.mail.PasswordAuthentication(from, password);
//            }
//        });
//
//        MimeMessage message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(from));
//        message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
//        message.setSubject("New Subscription Added");
//        // Create the email body with the subscription data
//        String emailBody = "A new subscription has been added with the following details:\n\n";
//        emailBody += "Type: " + resTypeAb + "\n";
//        emailBody += "Price: " + resPrixAb + "\n";
//        emailBody += "Payment Mode: " + resMode_Paiement + "\n\n";
//        emailBody += "Subscription ID: " + id_ab;
//        message.setText(emailBody);
//        javax.mail.Transport.send(message);
//        System.out.println("Email sent successfully...");

        // Generate QR code image
        String qrCodeText = String.format("Type: %s\nPrice: %.2f\nMode of Payment: %s", resTypeAb, resPrixAb, resMode_Paiement);
        int size = 250;
        String fileType = "png";
        File qrFile = new File(id_ab + "." + fileType); // use id_ab to name the QR code file
        try {
            createQRCode(qrFile, qrCodeText, size, fileType);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Subscription added successfully! QR code saved to " + qrFile.getAbsolutePath());
            alert.showAndWait();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to generate QR code!");
            alert.showAndWait();
        }

        // Update the table and search for the subscription
        UpdateTableAb();
        SearchAb();

    }



    public void UpdateTableAb() {

        AbonnementCRUD abcd = new AbonnementCRUD();

        idAbR.setCellValueFactory(new PropertyValueFactory<>("idAb"));
        typeAbR.setCellValueFactory(new PropertyValueFactory<>("typeAb"));
        prixAbR.setCellValueFactory(new PropertyValueFactory<>("prixAb"));
        modeDePaiementAbR.setCellValueFactory(new PropertyValueFactory<>("modePaiementAb"));

        listAb = abcd.getDataAb();
        TabAbonnementR.setItems(listAb);

    }

//    private void getSelectedAb(MouseEvent event) {
//        int indexAb = -1;
//        indexAb = TabAbonnement_R.getSelectionModel().getSelectedIndex();
//        if (indexAb <= -1) {
//            return;
//        }
//
//        cb_type_ab.setValue(type_ab_R.getCellData(indexAb));
//        tf_prix_ab.setText(prix_ab_R.getCellData(indexAb).toString());
//        cb_mode_paiement_ab.setValue(mode_de_paiement_ab_R.getCellData(indexAb));
//
//    }
    private void getSelectedAb(MouseEvent event) {
        int indexAb = -1;
        indexAb = TabAbonnementR.getSelectionModel().getSelectedIndex();
        if (indexAb <= -1) {
            return;
        }
        cbModePaiementAb.setValue(modeDePaiementAbR.getCellData(indexAb));

        cbTypeAb.setValue(typeAbR.getCellData(indexAb));   
    }

    public void SearchAb() {
        AbonnementCRUD abcd = new AbonnementCRUD();

        idAbR.setCellValueFactory(new PropertyValueFactory<>("ID"));
        typeAbR.setCellValueFactory(new PropertyValueFactory<>("Type"));
        prixAbR.setCellValueFactory(new PropertyValueFactory<>("prix"));
        modeDePaiementAbR.setCellValueFactory(new PropertyValueFactory<>("mode_de_paiement"));

        datalistAb = abcd.getDataAb();
        TabAbonnementR.setItems(datalistAb);
        FilteredList<Abonnement> filteredData = new FilteredList<>(datalistAb, b -> true);
        tfSearchAb.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Abonnement Abonnement) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Abonnement.getTypeAb().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Abonnement.getModePaiementAb().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    try {
                        if (Abonnement.getIdAb()== Integer.parseInt(lowerCaseFilter)) {
                            return true;
                        } else if (Abonnement.getPrixAb()== Float.parseFloat(lowerCaseFilter)) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        // Handle the exception (e.g. print an error message)
                    }
                }

                return false;
            });
        });
        SortedList<Abonnement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TabAbonnementR.comparatorProperty());
        TabAbonnementR.setItems(sortedData);

    }

    private void createQRCode(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        // Create QR code writer
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // Set QR code encoding parameters
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
        java.util.Map<EncodeHintType, Object> hintMap = new java.util.HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        // Create QR code bit matrix
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);

        // Create QR code image
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Save QR code image to disk
        ImageIO.write(qrImage, fileType, qrFile);
    }

    @FXML
    private void gotoadminmenu(ActionEvent event) {
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


