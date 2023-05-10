/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package autoxpress.GUI;


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
import autoxpress.entities.Vehicule;
import autoxpress.services.VehiculeCRUD;
import autoxpress.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class VehiculeConducteurController implements Initializable {

    @FXML
    private TextField tf_Immatriculation;
    @FXML
    private TextField tf_Cin_Conducteur;
    @FXML
    private TextField tf_Etat;
    @FXML
    private TextField tf_Kilometrage;

    @FXML
    private TextField tf_ImageV;
    @FXML
    private ChoiceBox<String> cb_typeV;
    @FXML
    private ChoiceBox<String> cb_marqueV;
    private ImageView image1;
    @FXML
    private Button browseImageV;
    @FXML
    private Button btn_ajouterV;
    @FXML
    private Button btn_modifierV;
    @FXML
    private Button btn_SupprimerV;
    @FXML
    private Button btnretourV;
    @FXML
    private Button btnoffre;
    @FXML
    private Button btnavi;
    @FXML
    private Button vehicule;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        browseImageV.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File fileImageV = fileChooser.showOpenDialog(browseImageV.getScene().getWindow());
            if (fileImageV != null) {
                tf_ImageV.setText(fileImageV.getPath());
            }
        });

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

        browseImageV.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File fileImageV = fileChooser.showOpenDialog(browseImageV.getScene().getWindow());
            if (fileImageV != null) {
                tf_ImageV.setText(fileImageV.getPath());
            }
        });

    }

    private void showImageChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        File fileImageV = fileChooser.showOpenDialog(browseImageV.getScene().getWindow());
        if (fileImageV != null) {
            tf_ImageV.setText(fileImageV.getPath());
        }
    }

    @FXML
    private void AjouterV(ActionEvent event) throws IOException {

        String resImmatriculation = tf_Immatriculation.getText();
        String resTypeV = cb_typeV.getValue();
        String resMarque = cb_marqueV.getValue();
        String resCin_Conducteur_str = tf_Cin_Conducteur.getText();
        String resImageV = tf_ImageV.getText();

        if (resImmatriculation == null || resImmatriculation.isEmpty()
                || resTypeV == null || resMarque == null
                || resCin_Conducteur_str == null || resCin_Conducteur_str.isEmpty()
                || tf_Etat.getText().isEmpty()
                || tf_Kilometrage.getText().isEmpty()
                || resImageV == null || resImageV.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Missing required fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the required fields");
            alert.showAndWait();
            return;
        }
        int resEtat = Integer.parseInt(tf_Etat.getText());
        int resKilometrage = Integer.parseInt(tf_Kilometrage.getText());

        //Controle de saisie VEHICULE
        String pattern = "^([1-9]\\d{0,2})TUNIS([1-9]\\d{0,3})$";
        String cinPattern = "^(0|1|00)\\d{7}$";
        String imagePattern = "^.*\\.jpg$"; // Added a pattern to match strings ending with ".jpeg"

        // Check if the entered value matches the pattern
        if (!resImmatriculation.matches(pattern)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid immatriculation format");
            alert.setHeaderText(null);
            alert.setContentText("The format should be: (number from 1 to 999)TUNIS(number from 1 to 9999)");
            alert.showAndWait();
            return;
        }

        switch (resTypeV) {
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
            default:
                break;
        }

        if (!resCin_Conducteur_str.matches(cinPattern)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Cin_Conducteur value");
            alert.setHeaderText(null);
            alert.setContentText("The value should be an integer of 8 digits starting with 0, 1, or 00");
            alert.showAndWait();
            return;
        }
        int resCin_Conducteur = Integer.parseInt(resCin_Conducteur_str);

        if (resEtat < 1 || resEtat
                > 100) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Etat value");
            alert.setHeaderText(null);
            alert.setContentText("The value should be a number between 1 and 100");
            alert.showAndWait();
            return;
        }

        if (resKilometrage < 0 || resKilometrage
                > 600000) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Kilometrage value");
            alert.setHeaderText(null);
            alert.setContentText("The value should be a number between 0 and 600000");
            alert.showAndWait();
            return;
        }

        browseImageV.fire();
        if (!resImageV.matches(imagePattern)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid ImageV format");
            alert.setHeaderText(null);
            alert.setContentText("The format should be a path ending with .jpg");
            alert.showAndWait();
            return;
        }
//        String pythonScriptPath = "C:/Users/abdel/OneDrive/Bureau/PiDev/VehiculeCRUD/vehiculeConducteurAI.py";
//        String[] cmd = {"python", pythonScriptPath, resImageV, resMarque};
//        System.out.println(resImageV);
//        System.out.println(resMarque);
//        try {
//            Process process = Runtime.getRuntime().exec(cmd);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            int exitCode = process.waitFor();
//            if (exitCode == 0) {
//                System.out.println("Image verification successful.");
//            } else {
//                System.out.println("Image verification failed.");
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            Path destinationFolder = Paths.get("C:/xampp/htdocs/imageV/");
            // Get the extension of the image file
            String extension = resImageV.substring(resImageV.lastIndexOf("."));

            // Rename the image file using the resImmatriculation variable
            String newImageName = resImmatriculation + extension;

            Path sourcePath = Paths.get(resImageV);
            Path destinationPath = destinationFolder.resolve(newImageName);
            Files.copy(sourcePath, destinationPath);
        } catch (NoSuchFileException e) {
            System.err.println("The file does not exist.");
            JOptionPane.showMessageDialog(null, "The file does not exist.");
        } catch (IOException e) {
            System.err.println("An error occurred while copying the file: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "An error occurred while copying the file: " + e.getMessage());
        }

        VehiculeCRUD vcd = new VehiculeCRUD();

        Vehicule v = new Vehicule(resImmatriculation, resTypeV, resMarque, resCin_Conducteur, resEtat, resKilometrage, resImageV);
        if (vcd.immatriculationExists(resImmatriculation)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Duplicate immatriculation");
            alert.setHeaderText(null);
            alert.setContentText("This immatriculation already exists in the database");
            alert.showAndWait();
            return;
        }
        vcd.addEntity2(v);
        try {
            // configure QR code writer
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            int width = 256; // QR code image width
            int height = 256; // QR code image height
            String qrCodeText = String.format("%s;%s;%s;%d;%d", resImmatriculation, resTypeV, resMarque, resEtat, resKilometrage); // text to encode
            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hintMap.put(EncodeHintType.MARGIN, 1); // 1 module margin

            // create QR code image
            com.google.zxing.common.BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height, hintMap);
            Image qrCodeImage = toJavaFXImage(MatrixToImageWriter.toBufferedImage(bitMatrix), null);

            // save QR code image
            Path destinationFolder = Paths.get("C:/xampp/htdocs/imageV/");
            Path qrCodeImagePath = destinationFolder.resolve(resImmatriculation + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(qrCodeImage, null), "png", qrCodeImagePath.toFile());

            // display QR code image
            ImageView qrCodeImageView = new ImageView(qrCodeImage);
            // add qrCodeImageView to your UI

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ajouté!");

        ObservableList<Vehicule> list = FXCollections.observableArrayList(
                new Vehicule(resImmatriculation, resTypeV, resMarque, resCin_Conducteur, resEtat, resKilometrage, resImageV)
        );

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
        }

    }

    @FXML
    public void modifierV() {
        try {
            String resImmatriculation = tf_Immatriculation.getText();
            String resTypeV = cb_typeV.getValue();
            String resMarque = cb_marqueV.getValue();
            String resCin_Conducteur_str = tf_Cin_Conducteur.getText();
//        int resEtat = Integer.parseInt(tf_Etat.getText());
//        int resKilometrage = Integer.parseInt(tf_Kilometrage.getText());
            String resImageV = tf_ImageV.getText();

            if (resImmatriculation == null || resImmatriculation.isEmpty()
                    || resTypeV == null || resMarque == null
                    || resCin_Conducteur_str == null || resCin_Conducteur_str.isEmpty()
                    || tf_Etat.getText().isEmpty()
                    || tf_Kilometrage.getText().isEmpty()
                    || resImageV == null || resImageV.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Missing required fields");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the required fields");
                alert.showAndWait();
                return;
            }
            int resEtat = Integer.parseInt(tf_Etat.getText());
            int resKilometrage = Integer.parseInt(tf_Kilometrage.getText());

            //Controle de saisie VEHICULE
            String pattern = "^([1-9]\\d{0,2})TUNIS([1-9]\\d{0,3})$";
            String cinPattern = "^(0|1|00)\\d{7}$";
            String imagePattern = "^.*\\.jpg$"; // Added a pattern to match strings ending with ".jpeg"

            // Check if the entered value matches the pattern
            if (!resImmatriculation.matches(pattern)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid immatriculation format");
                alert.setHeaderText(null);
                alert.setContentText("The format should be: (number from 1 to 999)TUNIS(number from 1 to 9999)");
                alert.showAndWait();
                return;
            }
            switch (resTypeV) {
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
                default:
                    break;
            }

            if (!resCin_Conducteur_str.matches(cinPattern)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Cin_Conducteur value");
                alert.setHeaderText(null);
                alert.setContentText("The value should be an integer of 8 digits starting with 0, 1, or 00");
                alert.showAndWait();
                return;
            }
            int resCin_Conducteur = Integer.parseInt(resCin_Conducteur_str);

            if (resEtat < 1 || resEtat
                    > 100) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Etat value");
                alert.setHeaderText(null);
                alert.setContentText("The value should be a number between 1 and 100");
                alert.showAndWait();
                return;
            }

            if (resKilometrage < 0 || resKilometrage
                    > 600000) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Kilometrage value");
                alert.setHeaderText(null);
                alert.setContentText("The value should be a number between 0 and 600000");
                alert.showAndWait();
                return;
            }

            if (!resImageV.matches(imagePattern)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid ImageV format");
                alert.setHeaderText(null);
                alert.setContentText("The format should be a path ending with .jpg");
                alert.showAndWait();
                return;
            }

            Path destinationFolder = Paths.get("C:/xampp/htdocs/imageV");

            try {
                Path sourcePath = Paths.get(resImageV);
                Path destinationPath = destinationFolder.resolve(sourcePath.getFileName());
                Files.copy(sourcePath, destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String requete = "UPDATE VEHICULE SET immatriculation= '" + resImmatriculation + "',type_du_vehicule='" + resTypeV + "',"
                    + "marque='" + resMarque + "', cin_conducteur='" + resCin_Conducteur + "', etat='" + resEtat + "', "
                    + "kilometrage='" + resKilometrage + "', imageV='" + resImageV + "' WHERE  immatriculation= '" + resImmatriculation + "'";

            PreparedStatement st = new MyConnection().getCnx().prepareStatement(requete);
            st.executeUpdate();
            try {
                // configure QR code writer
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                int width = 256; // QR code image width
                int height = 256; // QR code image height
                String qrCodeText = String.format("%s;%s;%s;%d;%d", resImmatriculation, resTypeV, resMarque, resEtat, resKilometrage); // text to encode
                Map<EncodeHintType, Object> hintMap = new HashMap<>();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                hintMap.put(EncodeHintType.MARGIN, 1); // 1 module margin

                // create QR code image
                com.google.zxing.common.BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height, hintMap);
                Image qrCodeImage = toJavaFXImage(MatrixToImageWriter.toBufferedImage(bitMatrix), null);

                // save QR code image
                Path qrCodeImagePath = destinationFolder.resolve(resImmatriculation + ".png");
                ImageIO.write(SwingFXUtils.fromFXImage(qrCodeImage, null), "png", qrCodeImagePath.toFile());

                // display QR code image
                ImageView qrCodeImageView = new ImageView(qrCodeImage);
                // add qrCodeImageView to your UI

            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Modifié");
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "ex");
        }
    }

    @FXML
    private void GoBrahimAy(ActionEvent event) {
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
    private void avi(ActionEvent event) {
    }

    



}
