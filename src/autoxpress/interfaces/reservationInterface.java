/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.interfaces;


import java.util.List;

/**
 *
 * @author amal
 */
public interface reservationInterface<R> {
    public void addReservation(R r);

    public List<R> entitiesList();

    public void updateReservation(R r, String id);
}
