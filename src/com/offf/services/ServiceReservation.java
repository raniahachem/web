/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offf.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.offf.entities.Reservation;
import com.offf.utils.Statics;

/**
 *
 * @author user
 */
public class ServiceReservation {
    
    
     //singleton 
    public static ServiceReservation instance = null ;
    
     public boolean resultOK;
     public static boolean resultOkk = true;


    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReservation getInstance() {
        if(instance == null )
            instance = new ServiceReservation();
        return instance ;
    }
    
    
    
    public ServiceReservation() {
        req = new ConnectionRequest();
        
    }
    
    //l'ajouuut
    //ajout 
    public void ajouterReservation(Reservation reservation) {
        
        String url =Statics.BASE_URL+"/reservation/reservation/new/new?id_conducteur_id="+reservation.getId_conducteur_id()+"&id_client_id="+reservation.getId_client_id()+"&id_offre_id="+reservation.getId_offre_id()+"&nb_place="+reservation.getNb_place()+"&date="+reservation.getDate()+"&point_de_depart="+reservation.getPoint_de_depart()+"&point_arrive="+reservation.getPoint_arrive(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
     
    
    
}
