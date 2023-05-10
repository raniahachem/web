/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autoxpress.entities;

/**
 *
 * @author abdel
 */
public class Vehicule {
    private String immatriculation;
    private String type_du_vehicule;
    private String marque;
    private int cin_conducteur;
    private int etat;
    private int kilometrage;
    private String ImageV;

    public Vehicule(String immatriculation, String type_du_vehicule, String marque, int cin_conducteur, int etat, int kilometrage, String ImageV) {
        this.immatriculation = immatriculation;
        this.type_du_vehicule = type_du_vehicule;
        this.marque = marque;
        this.cin_conducteur = cin_conducteur;
        this.etat = etat;
        this.kilometrage = kilometrage;
        this.ImageV = ImageV;
    }

    public Vehicule() {
    }

    public Vehicule(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    
    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getType_du_vehicule() {
        return type_du_vehicule;
    }

    public void setType_du_vehicule(String type_du_vehicule) {
        this.type_du_vehicule = type_du_vehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getCin_conducteur() {
        return cin_conducteur;
    }

    public void setCin_conducteur(int cin_conducteur) {
        this.cin_conducteur = cin_conducteur;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getImageV() {
        return ImageV;
    }

    public void setImageV(String ImageV) {
        this.ImageV = ImageV;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "immatriculation=" + immatriculation + ", type_de_voiture=" + type_du_vehicule + ", marque=" + marque + ", cin_conducteur=" + cin_conducteur + ", etat=" + etat + ", kilometrage=" + kilometrage + ", ImageV=" + ImageV + '}';
    }
    
    
    
    
}
