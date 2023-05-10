/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Reclamation;
import autoxpress.services.PDFGenerator;
import autoxpress.services.ReclamationCRUD;
import autoxpress.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class RecbackController implements Initializable {

    @FXML
    private TableView<Reclamation> tableReclamations;
    @FXML
    private TableColumn<?, Reclamation> colType;
    @FXML
    private TableColumn<?, Reclamation> colDate;
    @FXML
    private TableColumn<?, Reclamation> colDescription;
    @FXML
    private TableColumn<?, Reclamation> idColumn;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button pdf;
    @FXML
    private Button btnrepondre;
    private ImageView image1;
public ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private Button btnstat;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }    
MyConnection cnx = null;
    Statement st = null;
    ReclamationCRUD rcd = new ReclamationCRUD();
private void trierDate(MouseEvent event) {
        TableColumn<Reclamation, Date> dateColumn = (TableColumn<Reclamation, Date>) event.getSource();
        tableReclamations.getSortOrder().clear(); // Supprimer tout tri précédent
        data.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
    }

public void show() {
        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation( rs.getInt("id"),rs.getString("type"), rs.getString("date_r"), rs.getString("description"));
                data.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableReclamations.setItems(data);
    }


    @FXML
    private void afficherReclamationSelectionnee(MouseEvent event) {
    }


    @FXML
    private void supprimerreclamation(ActionEvent event) {
        // Récupérer la ligne sélectionnée dans la table view
    Reclamation selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
    if (selectedReclamation == null) {
        // Aucune ligne n'est sélectionnée
        return;
    }
    
    // Récupérer les valeurs des colonnes "coltype", "coldate" et "coldescription" de la ligne sélectionnée
    String type = selectedReclamation.getType();
    String date_r = selectedReclamation.getDate();
    String description = selectedReclamation.getDescription();
    
    // Créer la requête SQL pour supprimer la réclamation de la base de données en fonction des valeurs des colonnes sélectionnées
    String sql = "DELETE FROM reclamation WHERE type = '" + type + "' AND date_r = '" + date_r + "' AND description = '" + description + "'";
    
    try {
        // Exécuter la requête SQL
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(sql);
        
        // Supprimer la ligne de la table view
        tableReclamations.getItems().remove(selectedReclamation);
        
        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression de réclamation");
        alert.setHeaderText(null);
        alert.setContentText("La réclamation a été supprimée avec succès.");
        alert.showAndWait();
        
        // Fermer la connexion à la base de données
        st.close();
    } catch (SQLException e) {
        // Afficher un message d'erreur en cas d'échec de la suppression
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de la suppression de réclamation");
        alert.setHeaderText(null);
        alert.setContentText("Impossible de supprimer la réclamation sélectionnée.");
        alert.showAndWait();
        e.printStackTrace();
    }
    }

    @FXML
    private void generatePDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Définir l'extension de fichier par défaut
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        // Afficher la boîte de dialogue pour enregistrer le fichier
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            PDFGenerator pdfGenerator = new PDFGenerator();
            pdfGenerator.generatePDF(data, file.getAbsolutePath());
        }
    }

    @FXML
    public void afficherStatistiques(ActionEvent event) {
        try {
            String requete = "SELECT type, COUNT(*) as nb_reclamations "
                    + "FROM reclamation "
                    + "GROUP BY type";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            int totalReclamations = 0;
            while (rs.next()) {
                String type = rs.getString("type");
                int nbReclamations = rs.getInt("nb_reclamations");
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

            rs.close();
            pst.close();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void repondrereclamation(ActionEvent event) {
        Reclamation selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
        int idReclamation = selectedReclamation.getId();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sendmessage.fxml"));
            Parent messageParent = loader.load();
            SendmessageController messageController = loader.getController();
            messageController.setIdReclamation(idReclamation);
            Scene messageScene = new Scene(messageParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(messageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

//////////////////////////////////////////////////////////////////////////////
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
