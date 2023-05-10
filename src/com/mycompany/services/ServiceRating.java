/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycomany.entities.Avis;
import com.mycomany.entities.Commentaire;
import com.mycomany.utils.Statics;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author BKHmidi
 */
public class ServiceRating {
    
    
    
    
    
    // Singleton instance
    public static ServiceRating instance = null ;
    private static final int CONDUCTEUR_ID = 11; // replace with actual conducteur id
private static final int CLIENT_ID = 13; // replace with actual client id

    public static boolean resultOk = true;

    // Initialization of the connection request object
    private ConnectionRequest req;

    
    public static ServiceRating getInstance() {
        if(instance == null )
            instance = new ServiceRating();
        return instance ;
    }
    
    
    
    public ServiceRating() {
        req = new ConnectionRequest();
    }
    
// Add a new commentaire
public void addRating(int avis) throws UnsupportedEncodingException {
    String url = Statics.BASE_URL + "/newrating?id_client=13&id_conducteur=13&rating=" + avis;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    







// Delete a commentaire
public void deleteRating(int id) {
    String url = Statics.BASE_URL + "/delete_commentaires/" + id;
    
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}
// Update a commentaire
public void updateRating(Commentaire commentaire) throws UnsupportedEncodingException {
    String url = Statics.BASE_URL + "/update_commentaires/" + commentaire.getId()
        + "?contenu=" + commentaire.getContenu();
    
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    
    // Retrieve a list of all commentaires




public List<Commentaire> AllRating() {
    String url = Statics.BASE_URL + "/liste_commentaires";
    this.req.setUrl(url);
    this.req.setHttpMethod("GET");
    List<Commentaire> comments = new ArrayList<>();
    this.req.addResponseListener(e -> {
        if (this.req.getResponseCode() == 200) {
            String response = new String(this.req.getResponseData());
            try {
                JSONArray jsonComments = new JSONArray(response);
                for (int i = 0; i < jsonComments.length(); i++) {
                    JSONObject jsonComment = jsonComments.getJSONObject(i);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                    Date date = dateFormat.parse(jsonComment.getString("date"));
                    Commentaire comment = new Commentaire(
                            jsonComment.getInt("id"),
                            jsonComment.getString("contenu"),
                            date,
                            jsonComment.has("id_conducteur") ? jsonComment.getInt("id_conducteur") : 0,
                            jsonComment.has("id_client") ? jsonComment.getInt("id_client") : 0,
                            jsonComment.has("id_avis") ? jsonComment.getInt("id_avis") : 0
                    );
                    comments.add(comment);
                }
            } catch (JSONException | ParseException ex) {
                ex.printStackTrace();
            }
        } else {
            // handle error
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(this.req);
    return comments;
}

    
    
    
    
    
    
}
