package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Commentaire;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;


public class ServiceCommentaire {


    // Singleton instance
    public static ServiceCommentaire instance = null ;
    private static final int CONDUCTEUR_ID = 11; // replace with actual conducteur id
private static final int CLIENT_ID = 13; // replace with actual client id

    public static boolean resultOk = true;

    // Initialization of the connection request object
    private ConnectionRequest req;

    
    public static ServiceCommentaire getInstance() {
        if(instance == null )
            instance = new ServiceCommentaire();
        return instance ;
    }
    
    
    
    public ServiceCommentaire() {
        req = new ConnectionRequest();
    }
    
// Add a new commentaire
public void addCommentaire(Commentaire commentaire) throws UnsupportedEncodingException {
String url = Statics.BASE_URL + "/newcomment?id_client=13&id_conducteur=11&id_avis=13&contenu=" + commentaire.getContenu();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    


// Delete a commentaire
public void deleteCommentaire(int id) {
    String url = Statics.BASE_URL + "/delete_commentaires/" + id;
    
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}
// Update a commentaire
public void updateCommentaire(Commentaire commentaire) throws UnsupportedEncodingException {
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




public List<Commentaire> AllComments() {
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

