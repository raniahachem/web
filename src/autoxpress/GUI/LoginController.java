package autoxpress.GUI;

import autoxpress.entities.Admin;
import autoxpress.entities.Client;
import autoxpress.entities.Conducteur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.text.Document;
import autoxpress.services.ConducteurCRUD;


/**
 * FXML Controller class
 *
 * @author 21622
 */
public class LoginController implements Initializable {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";
    @FXML
    private AnchorPane root;
    @FXML
    private TextField tf_mail;
    @FXML
    private Label error_mssg;
    @FXML
    private PasswordField tf_pwd;
    @FXML
    private TextField btnmail_cl;
    @FXML
    private TextField btnmdb_cl;
    @FXML
    private TextField mailconducteur;
    @FXML
    private TextField mdpconducteur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void gogo(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/gui/client.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void gog(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/gui/conducteur.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void gag(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /* public void login(String email, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Se connecter à la base de données MySQL
            conn = DriverManager.getConnection(url, user, password);

            // Requête pour vérifier le nom d'utilisateur et le mot de passe
            String sql = "SELECT COUNT(*) AS count FROM client WHERE email_client = ? AND mdp_client = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            // Récupérer le résultat de la requête
            if (rs.next() && rs.getInt("count") > 0) {
                // Connecté avec succès
                System.out.println("Connexion réussie!");
            } else {
                // Afficher un message d'erreur
                System.out.println("Nom d'utilisateur ou mot de passe incorrect!");
            }
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            e.printStackTrace();
        } finally {
            // Fermer les ressources JDBC
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }
    }*/
    public void clientLogin(String email_client, String mdp_client) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM client WHERE email_client = ? AND mdp_client = ?";
            System.out.println(email_client);
            System.out.println(mdp_client);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email_client);
            pstmt.setString(2, mdp_client);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Login successful
                System.out.println("Client login successful");
                // Do something here, like redirect to a new page or display a message
            } else {
                // Login failed
                System.out.println("Invalid email or password for client");
                // Do something here, like display an error message or clear the input fields
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            // Do something here, like display an error message
        }

    }

    public void conducteurLogin(String email_conducteur, String mdp_conducteur) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM conducteur WHERE email_conducteur = ? AND mdp_conducteur = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email_conducteur);
            pstmt.setString(2, mdp_conducteur);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Login successful
                System.out.println("Conducteur login successful");
                // Do something here, like redirect to a new page or display a message
            } else {
                // Login failed
                System.out.println("Invalid email or password for conducteur");
                // Do something here, like display an error message or clear the input fields
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            // Do something here, like display an error message
        }
    }

    public void adminLogin(String email_admin, String mdp_admin) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM admin WHERE email_ad = ? AND mdp_ad = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email_admin);
            pstmt.setString(2, mdp_admin);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Login successful
                System.out.println("Admin login successful");
                // Do something here, like redirect to a new page or display a message
            } else {
                // Login failed
                System.out.println("Invalid email or password for admin");
                // Do something here, like display an error message or clear the input fields
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            // Do something here, like display an error message
        }
    }

    @FXML
    private void se_connecte(ActionEvent event) throws IOException {
        connect(event);
    }

    public void connect(ActionEvent event) throws IOException {
        String mail = tf_mail.getText();
        String pwd = tf_pwd.getText();

        // Vérifier si les champs sont remplis
        if (mail.isEmpty() || pwd.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();
        } else {
            System.out.println("Connexion réussie!");
            try {
                Mail.envoyer(mail);
                System.out.println("Le message a été envoyé avec succès.");
                gag(event);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btn_sinscrire(ActionEvent event) {
    }
    
    
    }
