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
public class Conducteur {
     private int id_conducteur;
    private int cin_conducteur;
    private String nom_conducteur;
    private String prenom_conducteur;
    private String ville_conducteur;
    private int telephone_conducteur;
    private String email_conducteur;
    private String mdp_conducteur;
    private String type_de_permis;
    private String image_conducteur;

    public Conducteur() {
    }

    public Conducteur(int id_conducteur, int cin_conducteur, String nom_conducteur, String prenom_conducteur, String ville_conducteur, int telephone_conducteur, String email_conducteur, String mdp_conducteur, String type_de_permis, String image_conducteur) {
        this.id_conducteur = id_conducteur;
        this.cin_conducteur = cin_conducteur;
        this.nom_conducteur = nom_conducteur;
        this.prenom_conducteur = prenom_conducteur;
        this.ville_conducteur = ville_conducteur;
        this.telephone_conducteur = telephone_conducteur;
        this.email_conducteur = email_conducteur;
        this.mdp_conducteur = mdp_conducteur;
        this.type_de_permis = type_de_permis;
        this.image_conducteur = image_conducteur;
    }

    public Conducteur(int cin_conducteur, String nom_conducteur, String prenom_conducteur, String ville_conducteur, int telephone_conducteur, String email_conducteur, String mdp_conducteur, String type_de_permis, String image_conducteur) {
        this.cin_conducteur = cin_conducteur;
        this.nom_conducteur = nom_conducteur;
        this.prenom_conducteur = prenom_conducteur;
        this.ville_conducteur = ville_conducteur;
        this.telephone_conducteur = telephone_conducteur;
        this.email_conducteur = email_conducteur;
        this.mdp_conducteur = mdp_conducteur;
        this.type_de_permis = type_de_permis;
        this.image_conducteur = image_conducteur;
    }

    public int getId_conducteur() {
        return id_conducteur;
    }

    public void setId_conducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    public int getCin_conducteur() {
        return cin_conducteur;
    }

    public void setCin_conducteur(int cin_conducteur) {
        this.cin_conducteur = cin_conducteur;
    }

    public String getNom_conducteur() {
        return nom_conducteur;
    }

    public void setNom_conducteur(String nom_conducteur) {
        this.nom_conducteur = nom_conducteur;
    }

    public String getPrenom_conducteur() {
        return prenom_conducteur;
    }

    public void setPrenom_conducteur(String prenom_conducteur) {
        this.prenom_conducteur = prenom_conducteur;
    }

    public String getVille_conducteur() {
        return ville_conducteur;
    }

    public void setVille_conducteur(String ville_conducteur) {
        this.ville_conducteur = ville_conducteur;
    }

    public int getTelephone_conducteur() {
        return telephone_conducteur;
    }

    public void setTelephone_conducteur(int telephone_conducteur) {
        this.telephone_conducteur = telephone_conducteur;
    }

    public String getEmail_conducteur() {
        return email_conducteur;
    }

    public void setEmail_conducteur(String email_conducteur) {
        this.email_conducteur = email_conducteur;
    }

    public String getMdp_conducteur() {
        return mdp_conducteur;
    }

    public void setMdp_conducteur(String mdp_conducteur) {
        this.mdp_conducteur = mdp_conducteur;
    }

    public String getType_de_permis() {
        return type_de_permis;
    }

    public void setType_de_permis(String type_de_permis) {
        this.type_de_permis = type_de_permis;
    }

    public String getImage_conducteur() {
        return image_conducteur;
    }

    public void setImage_conducteur(String image_conducteur) {
        this.image_conducteur = image_conducteur;
    }

}
