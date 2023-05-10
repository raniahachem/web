/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.entities;


import java.util.Date;

/**
 *
 * @author amal
 */
public class payement {
 
    private int paymentId;
    private int clientId;
    private String modePayment;
    private float prixCourse;
    private Date paymentDate ;

    public payement() {
    }

    public payement(int clientId, String modePayment, float prixCourse , Date paymentDate) {
        this.clientId = clientId;
        this.modePayment = modePayment;
        this.prixCourse = prixCourse;
        this.paymentDate =  paymentDate ;
    }
    

    public payement(int paymentId, int clientId, String modePayment, float prixCourse , Date paymentDate) {
        this.paymentId = paymentId;
        this.clientId = clientId;
        this.modePayment = modePayment;
        this.prixCourse = prixCourse;
        this.paymentDate=  paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getClientId() {
        return clientId;
    }

    public String getModePayment() {
        return modePayment;
    }

    public float getPrixCourse() {
        return prixCourse;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
    

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setModePayment(String modePayment) {
        this.modePayment = modePayment;
    }

    public void setPrixCourse(float prixCourse) {
        this.prixCourse = prixCourse;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "payement{" + "paymentId=" + paymentId + ", clientId=" + clientId + ", modePayment=" + modePayment + ", prixCourse=" + prixCourse + ", paymentDate=" + paymentDate + '}';
    }
    

   
    
}
