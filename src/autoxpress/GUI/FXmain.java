/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 
 */
public class FXmain extends Application {

   
    @Override
    public void start(Stage primaryStage) throws IOException {
      

        Parent root = FXMLLoader.load(getClass().getResource("reponse.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("AutoXpress");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    
 //Parent root1 = FXMLLoader.load(getClass().getResource("conducteur.fxml"));
        
        //Scene scene1 = new Scene(root1);
        
        //primaryStage.setTitle("conducteur");
       // primaryStage.setScene(scene1);
       // primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param args the command line arguments
     */
    

}
