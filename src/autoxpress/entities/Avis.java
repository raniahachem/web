/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

/**
 *
 * @author rania
 */
public class Avis {
    private int id_avis;
    private int id_conducteur;
    private int note;

    public Avis() {
    }

    public Avis(int id_conducteur, int note) {
        this.id_conducteur = id_conducteur;
        this.note = note;
    }
 public Avis(int id_avis, int id_conducteur, int note) {
        this.id_avis = id_avis;
        this.id_conducteur = id_conducteur;
        this.note = note;
    }
    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getId_conducteur() {
        return id_conducteur;
    }

    public void setId_conducteur(int id_conducteur) {
        this.id_conducteur = id_conducteur;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" + "id_avis=" + id_avis + ", id_conducteur=" + id_conducteur + ", note=" + note + '}';
    }
    
    
    
}
