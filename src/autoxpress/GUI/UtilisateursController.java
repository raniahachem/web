package autoxpress.GUI;

import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import autoxpress.entities.Client;
import autoxpress.entities.Conducteur;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import autoxpress.services.ClientCRUD;
import autoxpress.services.ConducteurCRUD;
import autoxpress.services.ConducteurPdfGenerator;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class UtilisateursController implements Initializable {

    @FXML
    private TabPane pageuser;
    @FXML
    private TableView<Client> tabclient;
    @FXML
    private TableColumn<?, ?> tabid_client;
    @FXML
    private TableColumn<?, ?> tabnom_client;
    @FXML
    private TableColumn<?, ?> tabprenom_client;
    @FXML
    private TableColumn<?, ?> tabcin_client;
    @FXML
    private TableColumn<?, ?> tabville_client;
    @FXML
    private TableColumn<?, ?> tabtelephone_client;
    @FXML
    private TableColumn<?, ?> tabemail_client;
    @FXML
    private TableColumn<?, ?> tabmdp_client;
    @FXML
    private Button btndelete_client;
    @FXML
    private Button affclient;
    @FXML
    private Button btnpdf;
    @FXML
    private TableView<Conducteur> tabconducteur;
    @FXML
    private TableColumn<?, ?> tabid_conducteur;
    @FXML
    private TableColumn<?, ?> tabcin_conducteur;
    @FXML
    private TableColumn<?, ?> tabnom_conducteur;
    @FXML
    private TableColumn<?, ?> tabprenom_conducteur;
    @FXML
    private TableColumn<?, ?> tabtelephone_conducteur;
    @FXML
    private TableColumn<?, ?> tabemeil_conducteur;
    @FXML
    private TableColumn<?, ?> tabville_conducteur;
    @FXML
    private TableColumn<?, ?> tabmdp_conducteur;
    @FXML
    private TableColumn<?, ?> tabtype_de_permis;
    @FXML
    private TableColumn<?, ?> tabimage_conducteur;
    @FXML
    private Button btndelete_conducteur;
    @FXML
    private Button affcond;
    @FXML
    private TextField tftid_client;
    @FXML
    private TextField tftid_conducteur;
      //ObservableList<Client> liste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConducteurCRUD conducteurDao = new ConducteurCRUD();
    List<Conducteur> conducteurList = conducteurDao.ConducteurList();
    ConducteurPdfGenerator pdfGenerator = new ConducteurPdfGenerator();
    pdfGenerator.generatePdf(conducteurList);
    List<Client> clients = ocd.ClientList();

        tabclient.getItems().setAll(clients);
         List<Conducteur> conducteur = c.ConducteurList();
        tabconducteur.getItems().setAll(conducteur);

      

    }    
     ConducteurCRUD c = new ConducteurCRUD();
        ObservableList<Conducteur> conducteurList = FXCollections.observableArrayList(c.ConducteurList());
     ClientCRUD ocd = new ClientCRUD();
        //List<Client> clients = ocd.ClientList();

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
        // Call the ClientList() method to retrieve the list of clients
    List<Client> clients = ocd.ClientList();

        tabclient.getItems().setAll(clients);

    }
        
    

    @FXML
    private void generatePdf(ActionEvent event) {
         
        
     ConducteurCRUD conducteurDao = new ConducteurCRUD();
    List<Conducteur> conducteurList = conducteurDao.ConducteurList();
    ConducteurPdfGenerator pdfGenerator = new ConducteurPdfGenerator();
    pdfGenerator.generatePdf(conducteurList);

}
    

    @FXML
    private void delete_conducteur(ActionEvent event) {
  
        ConducteurCRUD pcd = new ConducteurCRUD();
        if (tftid_conducteur.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun conducteur supprimée !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer le conducteur ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            pcd.DeleteConducteur(Integer.parseInt(tftid_conducteur.getText()));
            System.out.println("Conducteur supprimé !");
            //  updatetabclient();
        }
    }

    @FXML
    private void afficherconducteur(ActionEvent event) {
     List<Conducteur> conducteur = c.ConducteurList();
        tabconducteur.getItems().setAll(conducteur);
    }
     @FXML
    private void menu(ActionEvent event) {
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