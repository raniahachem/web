/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

import java.util.Date;

/**
 *
 * @author BKHmidi
 */
public class Contrat {
    
  private int id_contrat;
    private int id_conducteur;
    private int id_admin;
    private Date date_debut;
    private Date date_fin;
    private int prix;
    private String statut;
    private String qr_code;

    public Contrat() {
    }

    public Contrat(int id_contrat, int id_conducteur, int id_admin, Date date_debut, Date date_fin, int prix, String statut,String qr_code) {
        this.id_contrat = id_contrat;
        this.id_conducteur = id_conducteur;
        this.id_admin = id_admin;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.statut = statut;
        this.qr_code= qr_code;
    }

    public Contrat(int id_conducteur, int id_admin, Date date_debut, Date date_fin, int prix, String statut) {
        this.id_conducteur = id_conducteur;
        this.id_admin = id_admin;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.statut = statut;
    }

   
      public Contrat(int id_conducteur, int id_admin, Date date_debut, Date date_fin, int prix, String statut,String qr_code) {
        
        this.id_conducteur = id_conducteur;
        this.id_admin = id_admin;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.statut = statut;
        this.qr_code= qr_code;
    }

    public Contrat(int id_contrat, int id_conducteur, int id_admin, Date date_debut, Date date_fin, int prix, String statut) {
        this.id_contrat = id_contrat;
        this.id_conducteur = id_conducteur;
        this.id_admin = id_admin;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.statut = statut;
    }

    public Contrat(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }



    public int getId_contrat() {
        return id_contrat;
    }

    public void setId_contrat(int id_contrat) {
        this.id_contrat = id_contrat;
    }


 

    public int getId_conducteur() {
        return id_conducteur;
    }

    public void setId_conducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Contrat{" + "id_contrat=" + id_contrat + ", id_conducteur=" + id_conducteur + ", id_admin=" + id_admin + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix=" + prix + ", statut=" + statut + ", qr_code=" + qr_code + '}';
    }


  
   
            

}

