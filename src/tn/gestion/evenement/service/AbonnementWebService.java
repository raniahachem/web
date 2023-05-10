package tn.gestion.evenement.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tn.gestion.evenement.enitite.Abonnement;

public class AbonnementWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public AbonnementWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<Abonnement> getAlAbonnement() {
        String url = BASE_URL + "/abonnement";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Abonnement> categories = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        Abonnement abon = new Abonnement(
                                jsonEvent.getInt("id"),
                                jsonEvent.getString("typeAb"),
                                jsonEvent.getString("modePaiementAb"),
                                jsonEvent.getInt("prixAb")+""
                        );
                        categories.add(abon);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return categories;
    }

    public void newAbonnement(Abonnement c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/abonnement/add");
        this.connection.setHttpMethod("POST");
        
        connection.addArgument("typeAb", c.getPrixAb());
        connection.addArgument("prixAb", c.getPrixAb());
        connection.addArgument("modePaiementAb", c.getModePaiementAb());
        
        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void editAbonnement(Abonnement c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/abonnement/"+c.getId());
        this.connection.setHttpMethod("PUT");
        
        connection.addArgument("typeAb", c.getPrixAb());
        connection.addArgument("prixAb", c.getPrixAb());
        connection.addArgument("modePaiementAb", c.getModePaiementAb());
        
        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void delAbonnement(Abonnement c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/abonnement/"+c.getId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

}
