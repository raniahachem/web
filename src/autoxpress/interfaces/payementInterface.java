/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.interfaces;


import autoxpress.entities.payement;

/**
 *
 * @author amal
 */
public interface payementInterface <p> {
    public void addPayment(payement payment);
    
    public void updatePayement(payement payment, String id);

public void deletePayment(int paymentId);
    
}
