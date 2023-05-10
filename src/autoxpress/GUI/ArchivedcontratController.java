/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Contrat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import autoxpress.services.ContratCRUD;

/**
 * FXML Controller class
 *
 * @author BKHmidi
 */
public class ArchivedcontratController implements Initializable {

    @FXML
    private TableView<Contrat> TabFilter;
    @FXML
    private TableColumn<?, ?> id_contrattab1;
    @FXML
    private TableColumn<?, ?> id_contab1;
    @FXML
    private TableColumn<?, ?> id_adtab1;
    @FXML
    private TableColumn<?, ?> ddtab1;
    @FXML
    private TableColumn<?, ?> dftab1;
    @FXML
    private TableColumn<?, ?> prixtab1;
    @FXML
    private TableColumn<?, ?> statuttab1;
     private ContratCRUD contratCRUD;
    @FXML
    private ImageView imglogo;
  ObservableList<Contrat> list;
    int index = -1;
    @FXML
    private Button backcontratbtn;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        contratCRUD = new ContratCRUD();
        tabfilter();
        TabFilter.setItems(FXCollections.observableArrayList(contratCRUD.archiveContrats(list)));
    }    

    @FXML
    private void getSelected(MouseEvent event) {
   

    }
          public void tabfilter(){
    ContratCRUD cv = new ContratCRUD();
      id_contrattab1.setCellValueFactory(new PropertyValueFactory<>("id_contrat"));
        id_adtab1.setCellValueFactory(new PropertyValueFactory<>("id_admin"));
        id_contab1.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));
        ddtab1.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dftab1.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prixtab1.setCellValueFactory(new PropertyValueFactory<>("prix"));
        statuttab1.setCellValueFactory(new PropertyValueFactory<>("statut"));

        list = cv.getDataC();
        TabFilter.setItems(list);
    
    }
          
          
          
        @FXML
    private void contrat(ActionEvent event) throws IOException {
        //redirection vers liste contrat
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listeContrat.fxml")); 
    Parent root = loader.load();
    ListeContratController dc = loader.getController();
    
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
        
    }
      
          
          
}
