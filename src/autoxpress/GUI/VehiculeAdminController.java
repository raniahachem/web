/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package autoxpress.GUI;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

//---------------PDF--------------------//
import java.util.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message.RecipientType;

//---------------PDF--------------------//
//---------------QR--------------------//
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.QRCodeWriter;

//---------------QR--------------------//
import QRCodeGenerator.QRCodeGenerator;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import autoxpress.entities.Abonnement;
import autoxpress.entities.Vehicule;
import autoxpress.services.AbonnementCRUD;
import autoxpress.services.VehiculeCRUD;
import autoxpress.utils.MyConnection;
import java.awt.Desktop;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import static jdk.nashorn.internal.objects.NativeRegExp.source;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class VehiculeAdminController implements Initializable {

    @FXML
    private TextField tf_Immatriculation;
    private TextField tf_TypeV;
    private TextField tf_Marque;
    @FXML
    private TextField tf_Cin_Conducteur;
    @FXML
    private TextField tf_Etat;
    @FXML
    private TextField tf_Kilometrage;

    @FXML
    private TextField tf_ImageV;
    @FXML
    private TableColumn<Vehicule, String> Immatriculation_R;
    @FXML
    private TableColumn<Vehicule, String> Type_Du_Vehicule_R;
    @FXML
    private TableColumn<Vehicule, String> Marque_R;
    @FXML
    private TableColumn<Vehicule, Integer> Cin_Conducteur_R;
    @FXML
    private TableColumn<Vehicule, Integer> Etat_R;
    @FXML
    private TableColumn<Vehicule, Integer> Kilometrage_R;
    @FXML
    private TableColumn<Vehicule, String> Image_R;
    @FXML
    private TableView<Vehicule> TabVehicule_R;

    ObservableList<Vehicule> listV;
    ObservableList<Vehicule> datalistV;
    ObservableList<Abonnement> listAb;
    ObservableList<Abonnement> datalistAb;
    int index = -1;
    @FXML
    private TextField tf_SearchV;
    private TextField tf_type_ab;
    private TextField tf_prix_ab;
    private TextField tf_mode_paiement_ab;
    @FXML
    private Button btn_SupprimerV;
    @FXML
    private ChoiceBox<String> cb_typeV;
    @FXML
    private ChoiceBox<String> cb_marqueV;
    private TextField tf_StatVoiture;
    private TextField tf_StatBus;
    private TextField tf_StatVan;
    private TextField tf_StatCamion;
    @FXML
    private ImageView image1;
    private ImageView image2;
    @FXML
    private Button QRcodeV;
    @FXML
    private Button pdfV;
    private Button browseImageV;
    @FXML
    private Button BtnStatisticV;
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    //cb_marqueV.getItems().addAll("Alfa Romeo", "Mercedes", "Ford", "Clio");
    public void initialize(URL url, ResourceBundle rb) {

        File file = new File("C:/Users/abdel/OneDrive/Bureau/PiDev/Design/unnamed.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        image1.setImage(new Image(localURL));


        cb_typeV.getItems().addAll("Voiture", "Bus", "Van", "Camion");
        cb_typeV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Voiture":
                    cb_marqueV.getItems().setAll("audi", "bmw", "chevrolet", "ferrari", "ford", "hyundai", "mercedes", "toyota", "volkswagen");
                    break;
                case "Bus":
                    cb_marqueV.getItems().setAll("Blue Bird", "Thomas Built Buses", "Gillig", "New Flyer", "Prevost", "MCI", "Van Hool", "Volvo Buses", "Irizar", "MAN", "Scania", "Mercedes-Benz", "Setra", "Neoplan", "Temsa", "Bova", "Optare", "Alexander Dennis", "Ashok Leyland");
                    break;
                case "Van":
                    cb_marqueV.getItems().setAll("Mercedes-Benz", "Ford", "Chevrolet", "Dodge", "Nissan", "Ram", "Toyota", "Volkswagen", "Fiat", "GMC");
                    break;
                case "Camion":
                    cb_marqueV.getItems().setAll("Ford", "Chevrolet", "Ram", "GMC", "Toyota", "Nissan", "Jeep", "Dodge", "Freightliner", "Kenworth", "Peterbilt", "Volvo", "Mack", "International");
                    break;
            }
        });


        // Initialize the QRcodeV button event handler
        QRcodeV.setOnAction(e -> {
            // Get the data for the selected row
            int indexV = TabVehicule_R.getSelectionModel().getSelectedIndex();
            if (indexV <= -1) {
                return;
            }
            String immatriculation = Immatriculation_R.getCellData(indexV);
            String typeVehicule = Type_Du_Vehicule_R.getCellData(indexV);
            String marqueVehicule = Marque_R.getCellData(indexV);
            String cinConducteur = Cin_Conducteur_R.getCellData(indexV).toString();
            String etat = Etat_R.getCellData(indexV).toString();
            String kilometrage = Kilometrage_R.getCellData(indexV).toString();
            String image = Image_R.getCellData(indexV);

            // Create a QR code based on the retrieved data
            String qrText = "Immatriculation: " + immatriculation + "\n"
                    + "Type du véhicule: " + typeVehicule + "\n"
                    + "Marque du véhicule: " + marqueVehicule + "\n"
                    + "CIN du conducteur: " + cinConducteur + "\n"
                    + "État: " + etat + "\n"
                    + "Kilométrage: " + kilometrage + "\n"
                    + "Image: " + image;

            // Display the QR code in a new window
            Stage qrStage = new Stage();
            qrStage.setTitle("QR Code for Selected Vehicle");
            qrStage.initModality(Modality.APPLICATION_MODAL);

            ImageView qrImageView = new ImageView();
            qrImageView.setImage(QRCodeGenerator.generateQRCode(qrText, 200, 200));

            VBox vbox = new VBox(qrImageView);
            vbox.setAlignment(Pos.CENTER);

            Scene qrScene = new Scene(vbox, Color.WHITE);
            qrStage.setScene(qrScene);
            qrStage.show();
        });

        //cb_marqueV.getItems().addAll("Alfa Romeo", "Mercedes", "Ford", "Clio");
        UpdateTableV();
        SearchV();
        //StatisticV();

    }

    private void showImageChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        File fileImageV = fileChooser.showOpenDialog(browseImageV.getScene().getWindow());
        if (fileImageV != null) {
            tf_ImageV.setText(fileImageV.getPath());
        }
    }

    private static Image toJavaFXImage(java.awt.image.BufferedImage awtImage, javafx.scene.paint.Color background) {
        return SwingFXUtils.toFXImage(awtImage, null);
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
    private void SupprimerV(ActionEvent event) {
        String immatriculation = tf_Immatriculation.getText();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the vehicle with this immatriculation: " + immatriculation + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            VehiculeCRUD vcd = new VehiculeCRUD();
            vcd.deleteVehicule(immatriculation);
            vcd.deleteVehiculeImage(immatriculation + ".jpg");
            UpdateTableV();
            SearchV();
        }

    }

   

    public void UpdateTableV() {
        VehiculeCRUD vcd = new VehiculeCRUD();

        Immatriculation_R.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        Type_Du_Vehicule_R.setCellValueFactory(new PropertyValueFactory<>("type_du_vehicule"));
        Marque_R.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Cin_Conducteur_R.setCellValueFactory(new PropertyValueFactory<>("Cin_conducteur"));
        Etat_R.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Kilometrage_R.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        Image_R.setCellValueFactory(new PropertyValueFactory<>("imageV"));

        listV = vcd.getDataV();
        TabVehicule_R.setItems(listV);
    }

    @FXML
    private void generatePDF() throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Vehicules.pdf"));
        document.open();
        PdfPTable table = new PdfPTable(6);
        addTableHeader(table);
        addRows(table);
        document.add(table);
        document.close();

        // Open the generated PDF in the default viewer application
        File pdfFile = new File("Vehicules.pdf");
        if (pdfFile.exists()) {
            Desktop.getDesktop().open(pdfFile);
        } else {
            throw new FileNotFoundException("PDF file not found");
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Immatriculation", "Type du Vehicule", "Marque", "Cin Conducteur", "Etat", "Kilometrage")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        for (Vehicule vehicule : listV) {
            table.addCell(vehicule.getImmatriculation());
            table.addCell(vehicule.getType_du_vehicule());
            table.addCell(vehicule.getMarque());
            table.addCell(String.valueOf(vehicule.getCin_conducteur()));
            table.addCell(String.valueOf(vehicule.getEtat()));
            table.addCell(String.valueOf(vehicule.getKilometrage()));
        }
    }

    @FXML
    private void getSelectedV(javafx.scene.input.MouseEvent event) {
        int indexV = -1;
        indexV = TabVehicule_R.getSelectionModel().getSelectedIndex();
        if (indexV <= -1) {
            return;
        }
        tf_Immatriculation.setText(Immatriculation_R.getCellData(indexV));
        cb_typeV.setValue(Type_Du_Vehicule_R.getCellData(indexV));
        cb_marqueV.setValue(Marque_R.getCellData(indexV));
        tf_Cin_Conducteur.setText(Cin_Conducteur_R.getCellData(indexV).toString());
        tf_Etat.setText(Etat_R.getCellData(indexV).toString());
        tf_Kilometrage.setText(Kilometrage_R.getCellData(indexV).toString());
        tf_ImageV.setText(Image_R.getCellData(indexV));

        // Retrieve the data for the selected row
        String immatriculation = Immatriculation_R.getCellData(indexV);
        String typeVehicule = Type_Du_Vehicule_R.getCellData(indexV);
        String marqueVehicule = Marque_R.getCellData(indexV);
        String cinConducteur = Cin_Conducteur_R.getCellData(indexV).toString();
        String etat = Etat_R.getCellData(indexV).toString();
        String kilometrage = Kilometrage_R.getCellData(indexV).toString();
        String image = Image_R.getCellData(indexV);

        // Create a QR code based on the retrieved data
        String qrText = "Immatriculation: " + immatriculation + "\n"
                + "Type du véhicule: " + typeVehicule + "\n"
                + "Marque du véhicule: " + marqueVehicule + "\n"
                + "CIN du conducteur: " + cinConducteur + "\n"
                + "État: " + etat + "\n"
                + "Kilométrage: " + kilometrage + "\n"
                + "Image: " + image;

        // Add event handler for the QR button
        QRcodeV.setOnAction(e -> {
            // Display the QR code in a new window
            Stage qrStage = new Stage();
            qrStage.setTitle("QR Code for Selected Vehicle");
            qrStage.initModality(Modality.APPLICATION_MODAL);

            ImageView qrImageView = new ImageView();
            qrImageView.setImage(QRCodeGenerator.generateQRCode(qrText, 200, 200));

            VBox vbox = new VBox(qrImageView);
            vbox.setAlignment(Pos.CENTER);

            Scene qrScene = new Scene(vbox, Color.WHITE);
            qrStage.setScene(qrScene);
            qrStage.show();
        });
    }

//    private void getSelectedV(javafx.scene.input.MouseEvent event) {
//        int indexV = -1;
//        indexV = TabVehicule_R.getSelectionModel().getSelectedIndex();
//        if (indexV <= -1) {
//            return;
//        }
//        tf_Immatriculation.setText(Immatriculation_R.getCellData(indexV));
//        cb_typeV.setValue(Type_Du_Vehicule_R.getCellData(indexV));
//        cb_marqueV.setValue(Marque_R.getCellData(indexV));
//        tf_Cin_Conducteur.setText(Cin_Conducteur_R.getCellData(indexV).toString());
//        tf_Etat.setText(Etat_R.getCellData(indexV).toString());
//        tf_Kilometrage.setText(Kilometrage_R.getCellData(indexV).toString());
//        tf_ImageV.setText(Image_R.getCellData(indexV));
//    }
    public void SearchV() {
        VehiculeCRUD vcd = new VehiculeCRUD();
        Immatriculation_R.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        Type_Du_Vehicule_R.setCellValueFactory(new PropertyValueFactory<>("type_du_vehicule"));
        Marque_R.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Cin_Conducteur_R.setCellValueFactory(new PropertyValueFactory<>("Cin_conducteur"));
        Etat_R.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Kilometrage_R.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        Image_R.setCellValueFactory(new PropertyValueFactory<>("imageV"));

        datalistV = vcd.getDataV();
        TabVehicule_R.setItems(datalistV);
        FilteredList<Vehicule> filteredData = new FilteredList<>(datalistV, b -> true);
        tf_SearchV.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Vehicule Vehicule) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Vehicule.getImmatriculation().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Vehicule.getType_du_vehicule().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Vehicule.getMarque().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Vehicule.getImageV().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    try {
                        if (Vehicule.getCin_conducteur() == Integer.parseInt(lowerCaseFilter)) {
                            return true;
                        } else if (Vehicule.getEtat() == Integer.parseInt(lowerCaseFilter)) {
                            return true;
                        } else if (Vehicule.getKilometrage() == Integer.parseInt(lowerCaseFilter)) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        // Handle the exception (e.g. print an error message)
                    }
                }

                return false;
            });
        });
        SortedList<Vehicule> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TabVehicule_R.comparatorProperty());
        TabVehicule_R.setItems(sortedData);
    }

    @FXML
    void handleBtnStatisticV(ActionEvent event) {
        // Call the StatisticV method to calculate the statistics
        ObservableList<Vehicule> vehicules = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM VEHICULE";
            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Vehicule vehicule = new Vehicule(rs.getString("immatriculation"),
                        rs.getString("type_du_vehicule"), rs.getString("marque"),
                        rs.getInt("cin_conducteur"), rs.getInt("etat"),
                        rs.getInt("kilometrage"), rs.getString("imageV"));
                vehicules.add(vehicule);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        double voitureCount = 0;
        double busCount = 0;
        double vanCount = 0;
        double camionCount = 0;

        for (Vehicule vehicule : vehicules) {
            if (vehicule.getType_du_vehicule().equals("Voiture")) {
                voitureCount++;
            } else if (vehicule.getType_du_vehicule().equals("Bus")) {
                busCount++;
            } else if (vehicule.getType_du_vehicule().equals("Van")) {
                vanCount++;
            } else if (vehicule.getType_du_vehicule().equals("Camion")) {
                camionCount++;
            }
        }

        double totalCount = voitureCount + busCount + vanCount + camionCount;
        double voiturePercentage = (voitureCount / totalCount) * 100;
        double busPercentage = (busCount / totalCount) * 100;
        double vanPercentage = (vanCount / totalCount) * 100;
        double camionPercentage = (camionCount / totalCount) * 100;

        // Create a new window to display the statistics
        Stage stage = new Stage();
        stage.setTitle("Vehicle Statistics");

        // Create four text fields to display the statistics
        TextField voitureTextField = new TextField();
        voitureTextField.setText(String.format("%.2f%%", voiturePercentage));
        voitureTextField.setEditable(false);

        TextField busTextField = new TextField();
        busTextField.setText(String.format("%.2f%%", busPercentage));
        busTextField.setEditable(false);

        TextField vanTextField = new TextField();
        vanTextField.setText(String.format("%.2f%%", vanPercentage));
        vanTextField.setEditable(false);

        TextField camionTextField = new TextField();
        camionTextField.setText(String.format("%.2f%%", camionPercentage));
        camionTextField.setEditable(false);

        // Create a grid pane to hold the text fields
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new javafx.geometry.Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Voiture:"), voitureTextField);
        gridPane.addRow(1, new Label("Bus:"), busTextField);
        gridPane.addRow(2, new Label("Van:"), vanTextField);
        gridPane.addRow(3, new Label("Camion:"), camionTextField);

        // Create a scene and add the grid pane to it
        Scene scene = new Scene(gridPane);

        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }


    
    ///////////////////////////////////////////////////////////
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

    @FXML
    private void gotomenuadmin(ActionEvent event) {
    }

}
