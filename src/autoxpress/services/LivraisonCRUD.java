/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

import autoxpress.entities.Livraison;
import autoxpress.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class LivraisonCRUD {
    Connection cnx;

    public LivraisonCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void addLivraison(Livraison l){
        
        try {
            String requete = "INSERT INTO livraison(id_livraison,addresse_liv,destinataire, poids, contenu,prix_liv )"
                    + "VALUES ('"+l.getId_livraison()+"','" +l.getAddresse_liv()+"','" +l.getDestinataire()+"   ','" +l.getPoids()+"','"  +l.getContenu()+ "','"  +l.getPrix_liv()+ "')"; //requete hakka donc m3aha statement
            
            Statement st =MyConnection.getInstance().getCnx()
                    .createStatement();
            
            st.executeUpdate(requete);
            System.out.println("Livraison ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                                          
  
    }
    
     public void addLivraison2(Livraison l){
        
        try {
            String requete = "INSERT INTO livraison(id_livraison,addresse_liv,destinataire, poids, contenu , prix_liv)"
                    + "VALUES (?,?,?,?,?,? )"; //requete hakka donc m3aha statement
            
            PreparedStatement st =MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
            st.setInt(1,l.getId_livraison() );
            st.setString(2, l.getAddresse_liv());
            st.setString(3,l.getDestinataire() );           
            st.setFloat(4,l.getPoids() );
            st.setString(5,l.getContenu() );
            st.setFloat(6,l.getPrix_liv() );
                       
            st.executeUpdate();
            System.out.println("Livraison ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                                          
  
    }
     
     
    public List<Livraison> LivraisonList() {
        ArrayList<Livraison> myList = new ArrayList<>();
        try{
        String requete = "SELECT * FROM livraison";
        Statement st = MyConnection.getInstance().getCnx()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
           Livraison l = new Livraison();
            l.setId_livraison(rs.getInt("id_livraison"));
            l.setAddresse_liv(rs.getString("addresse_liv"));
            l.setDestinataire(rs.getString("destinataire"));
            
            l.setPoids(rs.getFloat("poids"));
           
            l.setContenu(rs.getString("contenu"));
            l.setPrix_liv(rs.getFloat("prix_liv"));
             
            myList.add(l);
        }
        }catch (SQLException ex ){
            System.out.println(ex.getMessage());
        }
        return myList;
        
    }
    
    
     
    
    public void ModifierLivraison(Livraison l){
         try{
             
         
            PreparedStatement st = cnx.prepareStatement("UPDATE livraison SET `addresse_liv`=?,`destinataire`=?  ,`poids`=?, `contenu`=? ,`prix_liv`=?    WHERE id_livraison= '" + l.getId_livraison() + "'");
            st.setString(1, l.getAddresse_liv());
            st.setString(2, l.getDestinataire());
              
            st.setFloat(3, l.getPoids());
            
            st.setString(4, l.getContenu());
            st.setFloat(5, l.getPrix_liv());
             
            st.executeUpdate();
            System.out.println("Livraison modifiée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         }
    
     
    public void SupprimerLivraison(Livraison l) {
        try {
            String query = "DELETE FROM `livraison` WHERE id_livraison  =" + l.getId_livraison();
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.execute();
            System.out.println("Livraison a été supprimée avec succès");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
