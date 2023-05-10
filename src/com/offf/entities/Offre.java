/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offf.entities;

/**
 *
 * @author user
 */
public class Offre {
    private float id;
    private float id_conducteur_id;
    private String Destination;
     
    private String pt_depart;
    private float prix;
    private String type_vehicule;

    public Offre(String toString, String toString0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Offre(String toString, String toString0, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float getId() {
        return id;
    }

    public float getId_conducteur_id() {
        return id_conducteur_id;
    }

    public String getDestination() {
        return Destination;
    }

    public String getPt_depart() {
        return pt_depart;
    }

    public float getPrix() {
        return prix;
    }

    public String getType_vehicule() {
        return type_vehicule;
    }

    public void setId(float id) {
        this.id = id;
    }

    
    public void setId_conducteur_id(float id_conducteur_id) {
        this.id_conducteur_id = id_conducteur_id;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public void setPt_depart(String pt_depart) {
        this.pt_depart = pt_depart;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setType_vehicule(String type_vehicule) {
        this.type_vehicule = type_vehicule;
    }

    public Offre(float id, float id_conducteur_id, String Destination, String pt_depart, float prix, String type_vehicule) {
        this.id = id;
        this.id_conducteur_id = id_conducteur_id;
        this.Destination = Destination;
        this.pt_depart = pt_depart;
        this.prix = prix;
        this.type_vehicule = type_vehicule;
    }

    public Offre(float id_conducteur_id, String Destination, String pt_depart, float prix, String type_vehicule) {
        this.id_conducteur_id = id_conducteur_id;
        this.Destination = Destination;
        this.pt_depart = pt_depart;
        this.prix = prix;
        this.type_vehicule = type_vehicule;
    }

    public Offre(String Destination, String pt_depart, float prix, String type_vehicule) {
        this.Destination = Destination;
        this.pt_depart = pt_depart;
        this.prix = prix;
        this.type_vehicule = type_vehicule;
    }

    public Offre() {
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", id_conducteur_id=" + id_conducteur_id + ", Destination=" + Destination + ", pt_depart=" + pt_depart + ", prix=" + prix + ", type_vehicule=" + type_vehicule + '}';
    }
    
    
    
    
}
