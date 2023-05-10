/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class MyConnection {
    
     public static Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         
    }
    
    private String url="jdbc:mysql://localhost:3306/autoxpress";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyConnection instance; 

    public MyConnection() {
        try{
        cnx =(Connection) DriverManager.getConnection(url,login, pwd);
         System.out.println("Connexion etablie!");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnection getInstance() {
        if (instance == null ){
            instance = new MyConnection();
        }
        return instance; 
    }
    
    
}
