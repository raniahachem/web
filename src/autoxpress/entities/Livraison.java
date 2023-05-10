/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

import javafx.scene.control.TextField;

/**
 *
 * @author user
 */
public class Livraison {
    
     private int id_livraison;
    private String addresse_liv;
    private String destinataire;
    private float poids;
    
    private String contenu;
    
    private float  prix_liv;

    public Livraison() {
    }

    public Livraison(int id_livraison, String addresse_liv, String destinataire, float poids , String contenu,  float prix_liv) {
        this.id_livraison = id_livraison;
        this.addresse_liv = addresse_liv;
        this.destinataire = destinataire;
        this.poids = poids;
         
        this.contenu = contenu;
       
         this.prix_liv = prix_liv;
    }

    public Livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public Livraison(TextField tflid_livraison, TextField tfladdresse_liv, TextField tfldestinataire, TextField tflpoids, TextField tflcontenu, TextField tflprix_liv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public String getAddresse_liv() {
        return addresse_liv;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public float getPoids() {
        return poids;
    }

     
    public String getContenu() {
        return contenu;
    }

    

    public float getPrix_liv() {
        return prix_liv;
    }
    
    
    

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setAddresse_liv(String addresse_liv) {
        this.addresse_liv = addresse_liv;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

     
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

     

    public void setPrix_liv(float prix_liv) {
        this.prix_liv = prix_liv;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", addresse_liv=" + addresse_liv + ", destinataire=" + destinataire + ", poids=" + poids +   ", contenu=" + contenu +   ", prix_liv =" + prix_liv + '}';
    }
    
    
    
    
    
}
