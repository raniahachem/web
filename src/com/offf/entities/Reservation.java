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
public class Reservation {
    private float id;
    private float id_client_id;
    private float id_conducteur_id;
    private float id_offre_id;
    private int nb_place;
    private String date;
    private String point_de_depart,point_arrive;

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setId_client_id(float id_client_id) {
        this.id_client_id = id_client_id;
    }

    public void setId_conducteur_id(float id_conducteur_id) {
        this.id_conducteur_id = id_conducteur_id;
    }

    public void setId_offre_id(float id_offre_id) {
        this.id_offre_id = id_offre_id;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", id_client_id=" + id_client_id + ", id_conducteur_id=" + id_conducteur_id + ", id_offre_id=" + id_offre_id + ", nb_place=" + nb_place + ", date=" + date + ", point_de_depart=" + point_de_depart + ", point_arrive=" + point_arrive + '}';
    }

    public void setPoint_de_depart(String point_de_depart) {
        this.point_de_depart = point_de_depart;
    }

    public Reservation(float id_client_id, float id_conducteur_id, float id_offre_id, int nb_place, String date, String point_de_depart, String point_arrive) {
        this.id_client_id = id_client_id;
        this.id_conducteur_id = id_conducteur_id;
        this.id_offre_id = id_offre_id;
        this.nb_place = nb_place;
        this.date = date;
        this.point_de_depart = point_de_depart;
        this.point_arrive = point_arrive;
    }

    public void setPoint_arrive(String point_arrive) {
        this.point_arrive = point_arrive;
    }

    public Reservation(float id, float id_client_id, float id_conducteur_id, float id_offre_id, int nb_place, String date, String point_de_depart, String point_arrive) {
        this.id = id;
        this.id_client_id = id_client_id;
        this.id_conducteur_id = id_conducteur_id;
        this.id_offre_id = id_offre_id;
        this.nb_place = nb_place;
        this.date = date;
        this.point_de_depart = point_de_depart;
        this.point_arrive = point_arrive;
    }

    public float getId_client_id() {
        return id_client_id;
    }

    public float getId_conducteur_id() {
        return id_conducteur_id;
    }

    public float getId_offre_id() {
        return id_offre_id;
    }

    public int getNb_place() {
        return nb_place;
    }

    public String getDate() {
        return date;
    }

    public String getPoint_de_depart() {
        return point_de_depart;
    }

    public String getPoint_arrive() {
        return point_arrive;
    }
    
    
    
}
