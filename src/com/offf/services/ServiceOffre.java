/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offf.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.offf.entities.Offre;
import com.offf.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceOffre {
    
     //singleton 
    public static ServiceOffre instance = null ;
    
     public boolean resultOK;
     public static boolean resultOkk = true;


    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceOffre getInstance() {
        if(instance == null )
            instance = new ServiceOffre();
        return instance ;
    }
    
    
    
    public ServiceOffre() {
        req = new ConnectionRequest();
        
    }
    
    //l'ajouuut
    //ajout 
    public void ajouterOffre(Offre offre) {
        
        String url =Statics.BASE_URL+"/offre/offre/new?id_conducteur_id="+offre.getId_conducteur_id()+"&Destination="+offre.getDestination()+"&pt_depart="+offre.getPt_depart()+"&prix="+offre.getPrix()+"&type_vehicule="+offre.getType_vehicule(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
     
    
    
    
    //affichage
    public ArrayList<Offre> affichageOffre(){
        ArrayList<Offre> offres = new ArrayList<>();
        String url = Statics.BASE_URL + "/offre/Alloffrejson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                try {
                    
                    JSONParser j = new JSONParser();
                    Map<String,Object> offresJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List< Map<String,Object>> list =(List< Map<String,Object>>) offresJSON.get("root");
                    for ( Map<String,Object> obj: list){
                        Offre f = new Offre();
                        if (obj != null && obj.get("id_conducteur_id") != null) {
            f.setId_conducteur_id(Float.parseFloat(obj.get("id_conducteur_id").toString()));
        }
                        f.setId(Float.parseFloat(obj.get("id").toString())); 
                        //f.setId_conducteur_id(Float.parseFloat(obj.get("id_conducteur_id").toString())); 
                        f.setDestination(obj.get("Destination").toString());
                        f.setPt_depart(obj.get("pt_depart").toString());
                        f.setType_vehicule(obj.get("type_vehicule").toString());
                        f.setPrix(Float.parseFloat(obj.get("prix").toString()));
                         

                        offres.add(f);
                    } 
                }
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    }
    
     public Offre getOffre(float id) {
        Offre offre = new Offre();
        String url = Statics.BASE_URL + "/offre/Alloffrejson?id" + (int) Math.round(id);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                try {
                    JSONParser j = new JSONParser();
                    
                    //Map<String, Object> offreJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    Map<String, Object> offreJson = null;
                       if (jsonText != null) {
                       offreJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                             }

                    //offre.setId(Float.parseFloat(offreJson.get("id").toString())); 
                    Object idObject = offreJson.get("id");
                      if (idObject != null) {
                       offre.setId(Float.parseFloat(idObject.toString()));
                              }

                    //offre.setId_conducteur_id(Float.parseFloat(offreJson.get("id_conducteur_id").toString()));
                    Object idObject2 = offreJson.get("id_conducteur_id");
                       if (idObject2 != null) {
                        offre.setId_conducteur_id(Float.parseFloat(idObject2.toString()));
                                }

                    //offre.setDestination(offreJson.get("destination").toString());
                      Object ptDepartObject2 = offreJson.get("destination");
                        if (ptDepartObject2 != null) {
                         offre.setDestination(ptDepartObject2.toString());
                               }

                    //offre.setPt_depart(offreJson.get("pt_depart").toString());
                    Object ptDepartObject = offreJson.get("pt_depart");
                      if (ptDepartObject != null) {
                       offre.setPt_depart(ptDepartObject.toString());
                                }

                    //offre.setPrix(Float.parseFloat(offreJson.get("prix").toString()));
                    Object idObject4 = offreJson.get("prix");
                       if (idObject4 != null) {
                        offre.setPrix(Float.parseFloat(idObject4.toString()));
                                }
                    //offre.setType_vehicule(offreJson.get("type_vehicule").toString());
                      Object ptDepartObject3 = offreJson.get("type_vehicule");
                        if (ptDepartObject3 != null) {
                         offre.setType_vehicule(ptDepartObject3.toString());
                               }
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offre;
    }
     
     
     
     ///delete
      public boolean deleteOffre(float id) {
        String url = Statics.BASE_URL + "/offre/deleteOffreJSON"+ (int) id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        System.out.println("" + resultOK);
        NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
      
      //Update 
    public boolean modifierOffre(Offre offre) {
        String url = Statics.BASE_URL +"/offre/updateOffreJSON/"+offre.getId()+"?pt_depart="+offre.getPt_depart()+"&prix="+offre.getPrix()+"&Destination="+offre.getDestination()+"&type_vehicule="+offre.getType_vehicule();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOkk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOkk;
        
    }
    
}
