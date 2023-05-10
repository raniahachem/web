/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author amal
 */
public class historique_reservation {

    private int id_reservation;
    private int id_historique_reservation;
    private int id_conducteur;
    private String date;
    private String date_depart_reelle;
    private String date_arrive_reelle;
    private String lieu_depart;
    private String lieu_destination;
    private String avis_client;
    private String status_reservation;
    private int id_client;

    public historique_reservation() {
    }

    public historique_reservation(int id_reservation, int id_historique_reservation, int id_conducteur, String date,
            String date_depart_reelle, String date_arrive_reelle, String lieu_depart, String lieu_destination,
            String avis_client, String status_reservation, int id_client) {
        this.id_reservation = id_reservation;
        this.id_historique_reservation = id_historique_reservation;
        this.id_conducteur = id_conducteur;
        this.date = date;
        this.date_depart_reelle = date_depart_reelle;
        this.date_arrive_reelle = date_arrive_reelle;
        this.lieu_depart = lieu_depart;
        this.lieu_destination = lieu_destination;
        this.avis_client = avis_client;
        this.status_reservation = status_reservation;
        this.id_client = id_client;
    }

    public historique_reservation(int id_reservation, int id_conducteur, String date, String date_depart_reelle,
            String date_arrive_reelle, String lieu_depart, String lieu_destination, String avis_client,
            String status_reservation, int id_client) {
        this.id_reservation = id_reservation;
        this.id_conducteur = id_conducteur;
        this.date = date;
        this.date_depart_reelle = date_depart_reelle;
        this.date_arrive_reelle = date_arrive_reelle;
        this.lieu_depart = lieu_depart;
        this.lieu_destination = lieu_destination;
        this.avis_client = avis_client;
        this.status_reservation = status_reservation;
        this.id_client = id_client;
    }

    public historique_reservation(int Id_reservation, String Depart_reelle, String Arrive_reelle, int Id_conducteur,
            String Lieu_destination, String Avis_client, String Status, int Id_client) {
        this.id_reservation = Id_reservation;
        this.id_conducteur = Id_conducteur;
        this.date_depart_reelle = Depart_reelle;
        this.lieu_destination = Lieu_destination;
        this.date_arrive_reelle = Arrive_reelle;

        this.avis_client = Avis_client;
        this.status_reservation = Status;
        this.id_client = Id_client;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public int getId_historique_reservation() {
        return id_historique_reservation;
    }

    public void setId_historique_reservation(int id_historique_reservation) {
        this.id_historique_reservation = id_historique_reservation;
    }

    public int getId_conducteur() {
        return id_conducteur;
    }

    public String getLocalTime() {
        return date;
    }

    public String getDate_depart_reelle() {
        return date_depart_reelle;
    }

    public String getDate_arrive_reelle() {
        return date_arrive_reelle;
    }

    public String getLieu_depart() {
        return lieu_depart;
    }

    public String getLieu_destination() {
        return lieu_destination;
    }

    public String getAvis_client() {
        return avis_client;
    }

    public String getStatus_reservation() {
        return status_reservation;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setId_conducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate_depart_reelle(String date_depart_reelle) {
        this.date_depart_reelle = date_depart_reelle;
    }

    public void setDate_arrive_reelle(String date_arrive_reelle) {
        this.date_arrive_reelle = date_arrive_reelle;
    }

    public void setLieu_depart(String lieu_depart) {
        this.lieu_depart = lieu_depart;
    }

    public void setLieu_destination(String lieu_destination) {
        this.lieu_destination = lieu_destination;
    }

    public void setAvis_client(String avis_client) {
        this.avis_client = avis_client;
    }

    public void setStatus_reservation(String status_reservation) {
        this.status_reservation = status_reservation;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "historique_reservation{" + "id_reservation=" + id_reservation + ", id_historique_reservation="
                + id_historique_reservation + ", id_conducteur=" + id_conducteur + ", date=" + date
                + ", date_depart_reelle=" + date_depart_reelle + ", date_arrive_reelle=" + date_arrive_reelle
                + ", lieu_depart=" + lieu_depart + ", lieu_destination=" + lieu_destination + ", avis_client="
                + avis_client + ", status_reservation=" + status_reservation + ", id_client=" + id_client + '}';
    }

    public void setDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

}
