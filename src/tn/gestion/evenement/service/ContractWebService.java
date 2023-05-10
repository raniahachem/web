package tn.gestion.evenement.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tn.gestion.evenement.enitite.Abonnement;
import tn.gestion.evenement.enitite.Contract;

public class ContractWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public ContractWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<Contract> getAllContract() {
        String url = BASE_URL + "/contrat";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Contract> events = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        Contract event = new Contract(
                                jsonEvent.getInt("id_contrat"),
                                jsonEvent.getInt("id_conducteur"),
                                jsonEvent.getInt("id_admin"),
                                jsonEvent.getJSONObject("date_debut").getString("date"),
                                jsonEvent.getJSONObject("date_fin").getString("date"),
                                jsonEvent.getInt("prix"),
                                jsonEvent.getString("qr_code"),
                                jsonEvent.getString("statut")
                        );
                        events.add(event);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                // handle error
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return events;
    }

    public void newContract(Contract e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/contrat/add");
        this.connection.setHttpMethod("POST");
        
        connection.addArgument("id_contrat", e.getId_admin()+"");
        connection.addArgument("id_admin", e.getId_admin()+"");
        connection.addArgument("date_debut", e.getDate_debut());
        connection.addArgument("date_fin", e.getDate_fin());
        connection.addArgument("prix", e.getPrix()+"");
        connection.addArgument("statut", e.getStatut());
        connection.addArgument("qr_code", e.getQr_code()+"");

        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void editContrat(Contract e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/contrat/"+e.getId_contrat());
        this.connection.setHttpMethod("PUT");
        
        connection.addArgument("id_contrat", e.getId_admin()+"");
        connection.addArgument("id_admin", e.getId_admin()+"");
        connection.addArgument("date_debut", e.getDate_debut());
        connection.addArgument("date_fin", e.getDate_fin());
        connection.addArgument("prix", e.getPrix()+"");
        connection.addArgument("statut", e.getStatut());
        connection.addArgument("qr_code", e.getQr_code()+"");
        
        System.out.println(BASE_URL + "/evenement/"+e.getId_contrat());
        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void delContract(Contract e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/contrat/"+e.getId_contrat());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

}
