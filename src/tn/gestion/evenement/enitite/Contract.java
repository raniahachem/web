package tn.gestion.evenement.enitite;


public class Contract {
    
    private int id_contrat;
    private int id_conducteur;
    private int id_admin;
    private String date_debut;
    private String date_fin;
    private int prix;
    private String qr_code;
    private String statut;

    public Contract() {
    }

    public Contract(int id_contrat, int id_conducteur, int id_admin, String date_debut, String date_fin, int prix, String qr_code, String statut) {
        this.id_contrat = id_contrat;
        this.id_conducteur = id_conducteur;
        this.id_admin = id_admin;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.qr_code = qr_code;
        this.statut = statut;
    }

    public Contract(int id_conducteur, int id_admin, String date_debut, String date_fin, int prix, String qr_code, String statut) {
        this.id_conducteur = id_conducteur;
        this.id_admin = id_admin;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.qr_code = qr_code;
        this.statut = statut;
    }
    
    public int getId_contrat() {
        return id_contrat;
    }

    public void setId_contrat(int id_contrat) {
        this.id_contrat = id_contrat;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getId_conducteur() {
        return id_conducteur;
    }

    public void setId_conducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    @Override
    public String toString() {
        return "Contract{" + "id_contrat=" + id_contrat + ", id_conducteur=" + id_conducteur + ", id_admin=" + id_admin + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix=" + prix + ", qr_code=" + qr_code + ", statut=" + statut + '}';
    }
    
}
