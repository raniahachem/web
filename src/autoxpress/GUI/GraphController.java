/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import autoxpress.utils.MyConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GraphController implements Initializable {

    @FXML
    private BarChart<String, Number> BarChart;
    @FXML
    private AnchorPane main_form;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*try {
            // TODO
            chart();
        } catch (SQLException ex) {
            Logger.getLogger(GraphController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        afficherStatistiquesVoitures();
    }    
    
    
    /*public void chart() throws SQLException{
        
      String chartSql = "SELECT type_vehicule, COUNT(*) AS nombre FROM offre GROUP BY type";

        
        try{
        XYChart.Series chartData = new XYChart.Series();
      Statement st = Myconnexion.getInstance().getCnx()
                .createStatement();
        ResultSet rs = st.executeQuery(chartSql); 
        
        while(rs.next()){
            chartData.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
        }
        BarChart.getData().add(chartData);
        }catch(Exception e){e.printStackTrace();}
    }        

}*/
    private void afficherStatistiquesVoitures() {
        // Récupération des données de la base de données MySQL 
        try {
             
        
         String chartSql =("SELECT Type_vehicule, COUNT(*) AS nombre FROM offre GROUP BY Type_vehicule");
               Statement stmt = MyConnection.getInstance().getCnx()
                .createStatement();
               
            ResultSet rs = stmt.executeQuery(chartSql);

            // Création d'un objet Series de JavaFX
            XYChart.Series<String, Number> chartData = new XYChart.Series<>();
            while(rs.next()){
                final CategoryAxis xAxis = new CategoryAxis();
final NumberAxis yAxis = new NumberAxis();
               BarChart.setTitle("Nombre de voitures disponibles par type");
xAxis.setLabel("Type de voiture");
yAxis.setLabel("Nombre de voitures");
            chartData.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
        }
        BarChart.getData().add(chartData);
        }catch(Exception e){}
    }        
        
    }
    
    
    
