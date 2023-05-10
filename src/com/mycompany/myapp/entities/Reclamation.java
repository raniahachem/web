/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

public class Reclamation {
    private int id;
    private String type;
    private Date date_r;
    private String description;
    private int id_client;


    public Reclamation(int id, String type, Date date_r, String description, int id_client) {
        this.id = id;
        this.type = type;
        //this.Duree = Duree;
        this.date_r = date_r;
        this.description = description;
        this.id_client = id_client;
    }


    public Date getDateR() {
        return date_r;
    }

    public void setDateR(Date date_r) {
        this.date_r = date_r;
    }




    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getId_client() {
        return this.id_client;
    }

    public void setIdclient(int id_client) {
        this.id_client = id_client;
    }

    public Reclamation(int i, String s) {
        this.id = this.id;
    }

    public Reclamation() {
    }



    public Reclamation(String type, Date date_r, String description, int id_client) {
        this.type = type;
        this.date_r = date_r;
        this.description = description;
        this.id_client = id_client;

    }
    public Reclamation(String type,Date date_r, String description) {
        this.type = type;
        this.date_r = date_r;
        this.description = description;
        this.id_client = id_client;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Reclamation{id=" + this.id + ", type=" + this.type + ", date_r=" + this.date_r + ", description=" + this.description + '}';
    }


}