package autoxpress.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 21622
 */
public class Client extends User {
    private int id_client;
    private String nom_client;
    private String prenom_client;
    private int cin_client;
    private String ville_client;
    private int telephone_client;
    private String email_client;
    private String mdp_client;
    

    public Client() {
    }

    public Client(String nom_client, String prenom_client, int cin_client, String ville_client, int telephone_client, String email_client, String mdp_client) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.cin_client = cin_client;
        this.ville_client = ville_client;
        this.telephone_client = telephone_client;
        this.email_client = email_client;
        this.mdp_client = mdp_client;
    }

    public Client(int id_client, String nom_client, String prenom_client, int cin_client, String ville_client, int telephone_client, String email_client, String mdp_client) {
        this.id_client = id_client;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.cin_client = cin_client;
        this.ville_client = ville_client;
        this.telephone_client = telephone_client;
        this.email_client = email_client;
        this.mdp_client = mdp_client;
    }

    public int getId_client() {
        return id_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public int getCin_client() {
        return cin_client;
    }

    public String getVille_client() {
        return ville_client;
    }

    public int getTelephone_client() {
        return telephone_client;
    }

    public String getEmail_client() {
        return email_client;
    }

    public String getMdp_client() {
        return mdp_client;
    }
public static Client client=new Client();

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public void setCin_client(int cin_client) {
        this.cin_client = cin_client;
    }

    public void setVille_client(String ville_client) {
        this.ville_client = ville_client;
    }

    public void setTelephone_client(int telephone_client) {
        this.telephone_client = telephone_client;
    }

    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }

    public void setMdp_client(String mdp_client) {
        this.mdp_client = mdp_client;
    }

    @Override
    public String toString() {
        return "Client{" + "id_client=" + id_client + ", nom_client=" + nom_client + ", prenom_client=" + prenom_client + ", cin_client=" + cin_client + ", ville_client=" + ville_client + ", telephone_client=" + telephone_client + ", email_client=" + email_client + ", mdp_client=" + mdp_client + '}';
    }
    
    
}
