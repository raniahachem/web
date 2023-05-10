/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;
import java.util.Date;

/**
 *
 * @author BKHmidi
 */
public class Commentaire {
    private int id;
    private String contenu;
    private Date date;
    private int id_conducteur;
    private int id_client;
    private int id_avis;

    public Commentaire(int id, String contenu, Date date, int id_conducteur, int id_client, int id_avis) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.id_conducteur = id_conducteur;
        this.id_client = id_client;
        this.id_avis = id_avis;
    }
    
       public Commentaire(int id, String contenu, int id_conducteur, int id_client, int id_avis) {
        this.id = id;
        this.contenu = contenu;
        this.id_conducteur = id_conducteur;
        this.id_client = id_client;
        this.id_avis = id_avis;
    }
   public Commentaire( String contenu, Date date, int id_conducteur, int id_client, int id_avis) {
        this.contenu = contenu;
        this.date = date;
        this.id_conducteur = id_conducteur;
        this.id_client = id_client;
        this.id_avis = id_avis;}
    
        public Commentaire( String contenu) {
        this.contenu = contenu;
       
    }

    public Commentaire() {
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdConducteur() {
        return id_conducteur;
    }

    public void setIdConducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    public int getIdClient() {
        return id_client;
    }

    public void setIdClient(int id_client) {
        this.id_client = id_client;
    }

    public int getIdAvis() {
        return id_avis;
    }

    public void setIdAvis(int id_avis) {
        this.id_avis = id_avis;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", contenu=" + contenu + ", date=" + date + ", id_conducteur=" + id_conducteur + ", id_client=" + id_client + ", id_avis=" + id_avis + '}';
    }
}