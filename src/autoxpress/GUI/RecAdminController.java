/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Reclamation;
import autoxpress.services.ReclamationCRUD;
import autoxpress.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;



/**
 * FXML Controller class
 *
 * @author rania
 */
public class RecAdminController implements Initializable {

    @FXML
    private TableView<Reclamation> tableRec;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private Button btnstat;
    @FXML
    private Button supprimerA;
    
    private ReclamationCRUD rcrd = new ReclamationCRUD();
    //public ObservableList<Reclamation> data = FXCollections.observableArrayList();
    public ObservableList<Reclamation> data = FXCollections.observableArrayList();

    @FXML
    private Button btnrafraichira;
    
// Association de l'ObservableList au TableView

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //show(event);
    }    
    @FXML
    private void statistiquesA(ActionEvent event) {
        ObservableList<Reclamation> reclamations = tableRec.getItems();
    Map<String, Integer> statistiques = new HashMap<>();

    // Calcul des statistiques
    for (Reclamation r : reclamations) {
        String type = r.getType();
        if (statistiques.containsKey(type)) {
            statistiques.put(type, statistiques.get(type) + 1);
        } else {
            statistiques.put(type, 1);
        }
    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    int totalReclamations = 0;
    for (Map.Entry<String, Integer> entry : statistiques.entrySet()) {
        String type = entry.getKey();
        int nbReclamations = entry.getValue();
        totalReclamations += nbReclamations;
        pieChartData.add(new PieChart.Data(type + " (" + nbReclamations + ")", nbReclamations));
    }

    // Calcul des pourcentages
    for (PieChart.Data data : pieChartData) {
        double pourcentage = (data.getPieValue() / totalReclamations) * 100;
        String label = data.getName() + " - " + String.format("%.2f", pourcentage) + "%";
        data.setName(label);
    }

    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques de réclamations par type");

    Stage stage = new Stage();
    Scene scene = new Scene(new Group(chart), 600, 400);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void supprimerreclamationA(ActionEvent event) {
        // Vérifier si une réclamation est sélectionnée
    if (tableRec.getSelectionModel().getSelectedItem() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Veuillez sélectionner une réclamation à supprimer");
        alert.showAndWait();
        return;
    }

    // Afficher une boîte de dialogue de confirmation
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Voulez-vous vraiment supprimer cette réclamation ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Récupérer l'ID de la réclamation sélectionnée dans la vue de la table
        //Object id = tableRec.getSelectionModel().getSelectedItem();
int id = tableRec.getSelectionModel().getSelectedItem().getId();
    // Supprimer la réclamation de la base de données
    rcrd.supprimerReclamation(id);
// Rafraîchir la liste de données
    data.clear();
    // Rafraîchir la vue de la table
    tableRec.refresh();
    show(event);
    }
    }


    @FXML
//public ObservableList<Reclamation> show(ActionEvent event){
public void show(ActionEvent event){
    //ArrayList<Reclamation> myList = new ArrayList();
       try {
            String requete="SELECT * FROM reclamation"; 
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            // Supprimer les données existantes avant de les recharger
        data.clear();
            while(rs.next()){
Reclamation r = new Reclamation(rs.getInt("id"), rs.getString("type"), rs.getString("date_r"), rs.getString("description"));
                data.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                  
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableRec.setItems(data);       
}

private void trierDate(MouseEvent event) {
        TableColumn<Reclamation, Date> dateColumn = (TableColumn<Reclamation, Date>) event.getSource();
        tableRec.getSortOrder().clear(); // Supprimer tout tri précédent
        data.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
    }

    @FXML
    private void retourmenuadmin(ActionEvent event) {
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

