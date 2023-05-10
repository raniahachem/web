/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author amal
 */
public class Reservation {
    private int id_reservation;
    private int nb_place;
    private int id_offre;
    private Date date;
    private int id_client;
    private String point_de_depart;
    private String point_arrive;
    private int id_conducteur;

    public Reservation() {
    }

    public Reservation(int nb_place, int id_offre, int id_client, String point_de_depart, String point_arrive,
            int id_conducteur) {
        this.nb_place = nb_place;
        this.id_offre = id_offre;
        this.id_client = id_client;
        this.point_de_depart = point_de_depart;
        this.point_arrive = point_arrive;
        this.id_conducteur = id_conducteur;

    }

    public Reservation(int id_reservation, int nb_place, int id_offre, Date date, int id_client, String point_de_depart,
            String point_arrive, int id_conducteur, String customerName) {
        this.id_reservation = id_reservation;
        this.nb_place = nb_place;
        this.id_offre = id_offre;
        this.date = date;
        this.id_client = id_client;
        this.point_de_depart = point_de_depart;
        this.point_arrive = point_arrive;
        this.id_conducteur = id_conducteur;

    }

    public Reservation(int id_reservation, int nb_place, int id_offre, LocalDateTime date, int id_client,
            String point_de_depart, String point_arrive, int id_conducteur) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public int getNb_place() {
        return nb_place;
    }

    public int getId_offre() {
        return id_offre;
    }

    public Date getDate() {
        return date;
    }

    public int getId_client() {
        return id_client;
    }

    public String getPoint_de_depart() {
        return point_de_depart;
    }

    public String getPoint_arrive() {
        return point_arrive;
    }

    public int getId_conducteur() {
        return id_conducteur;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setPoint_de_depart(String point_de_depart) {
        this.point_de_depart = point_de_depart;
    }

    public void setPoint_arrive(String point_arrive) {
        this.point_arrive = point_arrive;
    }

    public void setId_conducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", nb_place=" + nb_place + ", id_offre=" + id_offre
                + ", date=" + date + ", id_client=" + id_client + ", point_de_depart=" + point_de_depart
                + ", point_arrive=" + point_arrive + ", id_conducteur=" + id_conducteur + '}';
    }

}
