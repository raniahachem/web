/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;

/**
 *
 * @author 21622
 */
public class Admin  extends User{
    private int id;
    private String email_ad;
    private String mdp_ad;

    public Admin() {
    }

    public Admin(String email_ad, String mdp_ad) {
        this.email_ad = email_ad;
        this.mdp_ad = mdp_ad;
    }

    public Admin(int id, String email_ad, String mdp_ad) {
        this.id = id;
        this.email_ad = email_ad;
        this.mdp_ad = mdp_ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail_ad() {
        return email_ad;
    }

    public String getMdp_ad() {
        return mdp_ad;
    }

    public void setEmail_ad(String email_ad) {
        this.email_ad = email_ad;
    }

    public void setMdp_ad(String mdp_ad) {
        this.mdp_ad = mdp_ad;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", email_ad=" + email_ad + ", mdp_ad=" + mdp_ad + '}';
    }

    
    
    
    
    
    
    
}
