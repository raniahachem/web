/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autoxpress.entities;

/**
 *
 * @author abdel
 */
public class Abonnement {
    private int idAb;
    private String typeAb;
    private float prixAb;
    private String modePaiementAb;

    public Abonnement(int idAb, String typeAb, float prixAb, String modePaiementAb) {
        this.idAb = idAb;
        this.typeAb = typeAb;
        this.prixAb = prixAb;
        this.modePaiementAb = modePaiementAb;
    }

    public Abonnement(String typeAb, float prixAb, String modePaiementAb) {
        this.typeAb = typeAb;
        this.prixAb = prixAb;
        this.modePaiementAb = modePaiementAb;
    }

    public Abonnement(int idAb) {
        this.idAb = idAb;
    }

    public int getIdAb() {
        return idAb;
    }

    public void setIdAb(int idAb) {
        this.idAb = idAb;
    }

    public String getTypeAb() {
        return typeAb;
    }

    public void setTypeAb(String typeAb) {
        this.typeAb = typeAb;
    }

    public float getPrixAb() {
        return prixAb;
    }

    public void setPrixAb(float prixAb) {
        this.prixAb = prixAb;
    }

    public String getModePaiementAb() {
        return modePaiementAb;
    }

    public void setModePaiementAb(String modePaiementAb) {
        this.modePaiementAb = modePaiementAb;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "idAb=" + idAb + ", typeAb=" + typeAb + ", prixAb=" + prixAb + ", modePaiementAb=" + modePaiementAb + '}';
    }

    

    
    
    
}
