package tn.gestion.evenement.enitite;

public class Abonnement {
    private int id;
    private String typeAb;
    private String modePaiementAb;
    private String prixAb;

    public Abonnement() {
    }

    public Abonnement(int id, String typeAb, String modePaiementAb, String prixAb) {
        this.id = id;
        this.typeAb = typeAb;
        this.modePaiementAb = modePaiementAb;
        this.prixAb = prixAb;
    }

    public Abonnement(String typeAb, String modePaiementAb, String prixAb) {
        this.typeAb = typeAb;
        this.modePaiementAb = modePaiementAb;
        this.prixAb = prixAb;
    }

    public String getPrixAb() {
        return prixAb;
    }

    public void setPrixAb(String prixAb) {
        this.prixAb = prixAb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeAb() {
        return typeAb;
    }

    public void setTypeAb(String typeAb) {
        this.typeAb = typeAb;
    }

    public String getModePaiementAb() {
        return modePaiementAb;
    }

    public void setModePaiementAb(String modePaiementAb) {
        this.modePaiementAb = modePaiementAb;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idCategorie=" + id + ", nom=" + typeAb + ", descriptif=" + modePaiementAb + '}';
    }
    
    
}
