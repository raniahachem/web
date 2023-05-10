/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

 
import java.util.Date;
import java.util.Objects;
import javafx.scene.control.TextField;

/**
 *
 * @author user
 */
public class Offre {
    private int id_offre;
    private int id_conducteur;
    private String Destination;
    private Date hr_depart;
    private String pt_depart;
    private float Prix;
    private String Type_vehicule;

    public Offre() {
    }

    public Offre(int id_conducteur, String Destination,   String pt_depart, float Prix, String Type_vehicule) {
        this.id_conducteur = id_conducteur;
        this.Destination = Destination;
       
        this.pt_depart = pt_depart;
        this.Prix = Prix;
        this.Type_vehicule = Type_vehicule;
    }

     
   
public Offre(int id_offre, int id_conducteur, String Destination,   String pt_depart, float Prix, String Type_vehicule) {
        this.id_offre = id_offre;
        this.id_conducteur = id_conducteur;
        this.Destination = Destination;
       
        this.pt_depart = pt_depart;
        this.Prix = Prix;
        this.Type_vehicule = Type_vehicule;
    }
    
    
     

    public Offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public Offre(TextField resid_offre, TextField resid_conducteur, TextField resDestination, TextField respt_depart, TextField resPrix, TextField restype_vehicule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     

    
    public int getId_offre() {
        return id_offre;
    }

    public int getId_conducteur() {
        return id_conducteur;
    }

    public String getDestination() {
        return Destination;
    }

    

    

    public String getPt_depart() {
        return pt_depart;
    }

    public float getPrix() {
        return Prix;
    }

    public String getType_vehicule() {
        return Type_vehicule;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setId_conducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

     

    

    public void setPt_depart(String pt_depart) {
        this.pt_depart = pt_depart;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public void setType_vehicule(String Type_vehicule) {
        this.Type_vehicule = Type_vehicule;
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", id_conducuteur=" + id_conducteur + ", Destination=" + Destination +  ", hr_depart=" + hr_depart +   ", pt_depart=" + pt_depart + ", Prix=" + Prix + ", Type_vehicule=" + Type_vehicule + '}';
    }
    
    
    
     
    
}
