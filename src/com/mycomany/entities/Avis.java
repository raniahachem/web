/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author BKHmidi
 */
public class Avis {
    private int id;
    private int rating;
    private int id_client;
    private int id_conducteur;

    public Avis(int id, int rating, int id_client, int id_conducteur) {
        this.id = id;
        this.rating = rating;
        this.id_client = id_client;
        this.id_conducteur = id_conducteur;
    }
     public Avis( int rating, int id_client, int id_conducteur) {
        this.rating = rating;
        this.id_client = id_client;
        this.id_conducteur = id_conducteur;
    }
     public Avis(){}
     public Avis(int rating){        this.rating = rating;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdClient() {
        return id_client;
    }

    public void setIdClient(int id_client) {
        this.id_client = id_client;
    }

    public int getIdConducteur() {
        return id_conducteur;
    }

    public void setIdConducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }
    
}
