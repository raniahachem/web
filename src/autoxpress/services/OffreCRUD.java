/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.services;

import autoxpress.entities.Offre;
import autoxpress.interfaces.EntityCRUD;
import autoxpress.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class OffreCRUD  implements EntityCRUD<Offre> {
    
     
    
    
    Connection cnx;

    public OffreCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void addEntity(Offre o){
        
        try {
            String requete = "INSERT INTO offre(id_offre,id_conducteur,Destination,  pt_depart,Prix,Type_vehicule)"
                    + "VALUES ('"+o.getId_offre()+"','" +o.getId_conducteur()+"','" +o.getDestination()+"   ','" +o.getPt_depart()+"','" +o.getPrix()+"','" +o.getType_vehicule()+"')"; //requete hakka donc m3aha statement
            
            Statement st =MyConnection.getInstance().getCnx()
                    .createStatement();
            
            st.executeUpdate(requete);
            System.out.println("Offre ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                                          
  
    }
    
    public void addEntity2(Offre o){
        
        try {
            String requete = "INSERT INTO offre(id_offre,id_conducteur,Destination, pt_depart,Prix,Type_vehicule)"
                    + "VALUES (?,?,? ,?,?,?)"; //requete hakka donc m3aha statement
            
            PreparedStatement st =MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
            st.setInt(1,o.getId_offre() );
            st.setInt(2, o.getId_conducteur());
            st.setString(3,o.getDestination() );
            
            st.setString(4,o.getPt_depart() );
            st.setFloat(5,o.getPrix() );
            st.setString(6,o.getType_vehicule() );
             
            
            st.executeUpdate();
            System.out.println("Offre ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                                          
  
    }
    
    @Override
    public List<Offre> entitiesList() {
        ArrayList<Offre> myList = new ArrayList<>();
        try{
        String requete = "SELECT * FROM offre";
        Statement st = MyConnection.getInstance().getCnx()
                .createStatement();
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
            Offre o = new Offre();
            o.setId_offre(rs.getInt(1));
            o.setId_conducteur(rs.getInt("id_conducteur"));
            o.setDestination(rs.getString("destination"));
            
            o.setPt_depart(rs.getString("pt_depart"));
            o.setPrix(rs.getFloat("prix"));
            o.setType_vehicule(rs.getString("type_vehicule"));
            myList.add(o);
        }
        }catch (SQLException ex ){
            System.out.println(ex.getMessage());
        }
        return myList;
        
    }
    
    
    public void ModifierOff(Offre o){
         try{
             
         
            PreparedStatement st = cnx.prepareStatement("UPDATE offre SET `id_conducteur`=?,`destination`=?  ,`pt_depart`=?,`prix`=?,`type_vehicule`=?     WHERE id_offre= '" + o.getId_offre() + "'");
            st.setInt(1, o.getId_conducteur());
            st.setString(2, o.getDestination());
              
            st.setString(3, o.getPt_depart());
            st.setFloat(4, o.getPrix());
            st.setString(5, o.getType_vehicule());
             
            st.executeUpdate();
            System.out.println("Offre modifiée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         }
    
     
    public void SupprimerOff(Offre o) {
        try {
            String query = "DELETE FROM `offre` WHERE id_offre  =" + o.getId_offre();
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.execute();
            System.out.println("Offre supprimée avec succès");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     
        	
    
}
