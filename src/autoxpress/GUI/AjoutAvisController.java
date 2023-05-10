/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.entities.Avis;
import autoxpress.services.AvisCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class AjoutAvisController implements Initializable {

    private ImageView etoile1;
    private ImageView etoile2;
    private ImageView etoile3;
    private ImageView etoile4;
    private ImageView etoile5;
    @FXML
    private TextField idConducteur;
    private TextField idNote;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnajouternote;
         private AvisCRUD acrd = new AvisCRUD();
    @FXML
    private TextArea txtMoyennes;
    @FXML
    private Button btnnote;
    @FXML
    private Label message;
    @FXML
    private Rating rating;
    @FXML
    private Button btnoffre;
    @FXML
    private Button btnres1;
    @FXML
    private Button btnlivr1;
    @FXML
    private Button btnrec1;
    @FXML
    private Button btnavi;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ob, Number old, Number newT) {
            message.setText("Rating:" +newT);
            }
        });
        
    }
/*private int note = 0;

    private void selectionnerNote(MouseEvent event) {
        
        if (event.getSource() == etoile1) {
            note = 1;
            etoile1.setImage(new Image("etoile_pleine.png"));
            etoile2.setImage(new Image("etoile_vide.png"));
            etoile3.setImage(new Image("etoile_vide.png"));
            etoile4.setImage(new Image("etoile_vide.png"));
            etoile5.setImage(new Image("etoile_vide.png"));
        } else if (event.getSource() == etoile2) {
            note = 2;
            etoile1.setImage(new Image("etoile_pleine.png"));
            etoile2.setImage(new Image("etoile_pleine.png"));
            etoile3.setImage(new Image("etoile_vide.png"));
            etoile4.setImage(new Image("etoile_vide.png"));
            etoile5.setImage(new Image("etoile_vide.png"));
        } else if (event.getSource() == etoile3) {
            note = 3;
            etoile1.setImage(new Image("etoile_pleine.png"));
            etoile2.setImage(new Image("etoile_pleine.png"));
            etoile3.setImage(new Image("etoile_pleine.png"));
            etoile4.setImage(new Image("etoile_vide.png"));
            etoile5.setImage(new Image("etoile_vide.png"));
        } else if (event.getSource() == etoile4) {
            note = 4;
            etoile1.setImage(new Image("etoile_pleine.png"));
            etoile2.setImage(new Image("etoile_pleine.png"));
            etoile3.setImage(new Image("etoile_pleine.png"));
            etoile4.setImage(new Image("etoile_pleine.png"));
            etoile5.setImage(new Image("etoile_vide.png"));
            } 
        else if (event.getSource() == etoile5) {
note = 5;
etoile1.setImage(new Image("etoile_pleine.png"));
etoile2.setImage(new Image("etoile_pleine.png"));
etoile3.setImage(new Image("etoile_pleine.png"));
etoile4.setImage(new Image("etoile_pleine.png"));
etoile5.setImage(new Image("etoile_pleine.png"));
}
}*/
    
    @FXML
    private void retourner(ActionEvent event) {
    try {
        Parent reclamationsParent = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
        Scene reclamationsScene = new Scene(reclamationsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(reclamationsScene);
        window.show();
    } catch (IOException e) {
    }
}

    /*@FXML
    private void ajouterNote(ActionEvent event) {
        // Récupérer l'id_conducteur depuis la zone de texte idConducteur
    int id_c = Integer.parseInt(idConducteur.getText());
        int note = Integer.parseInt(message.getText().substring(7));

    // Récupérer la note depuis la zone de texte idNote
    //int notec = Integer.parseInt(idNote.getText());
    // Vérifier que la note est comprise entre 1 et 5
    if (notec < 1 || notec > 5) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("La note doit être comprise entre 1 et 5");
        alert.showAndWait();
        return;
    }

    // Ajouter la note à la base de données
    acrd.ajouterAvis(new Avis(id_c, note));

    // Afficher un message de confirmation
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("La note a été ajoutée avec succès");
    alert.showAndWait();

    // Effacer les zones de texte
    idConducteur.setText("");
    idNote.setText("");
    }*/

    @FXML
    private void afficherMoyennes(ActionEvent event) {
      // Calculer les moyennes des notes pour chaque id_conducteur
    Map<Integer, Double> moyennes = acrd.calculerMoyenne();

    // Afficher les moyennes pour chaque id_conducteur dans la zone de texte "txtMoyennes"
    StringBuilder sb = new StringBuilder();
    for (int id_conducteur : moyennes.keySet()) {
        double moyenne = moyennes.get(id_conducteur);
        sb.append("id_conducteur ").append(id_conducteur).append(": ").append(moyenne).append("\n");
    }
    txtMoyennes.setText(sb.toString());   
    }
    
    
    @FXML
private void ajouterNote(ActionEvent event) {
// Récupérer l'id_client depuis la zone de texte idConducteur
    int id_c = Integer.parseInt(idConducteur.getText());

    // Récupérer la note depuis le label message et la convertir en entier
    int notec = (int) rating.getRating();
    // Ajouter la note à la base de données
    acrd.ajouterAvis(new Avis(id_c, notec));

    // Afficher un message de confirmation
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("La note a été ajoutée avec succès");
    alert.showAndWait();

    // Effacer les zones de texte
    idConducteur.setText("");
}

    @FXML
    private void reclamation(ActionEvent event) {
               try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
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
            Parent avisParent = FXMLLoader.load(getClass().getResource("Loginclient.fxml"));
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
            Parent avisParent = FXMLLoader.load(getClass().getResource("AfficherOffre.fxml"));
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
    private void livraison(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("LivraisonClient.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void avi(ActionEvent event) {
        try {
            Parent avisParent = FXMLLoader.load(getClass().getResource("AjoutAvis.fxml"));
            Scene avisScene = new Scene(avisParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(avisScene);
            window.show();
        } catch (IOException e) {
        }
    }

}
