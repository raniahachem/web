/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import static com.mycompany.myapp.gui.ListReclamationForm.currentV;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 *
 * @author bhk
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamations;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req, cr;
      public int resultCode;

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addTask(Reclamation v) {
        String url = Statics.BASE_URL + "/addrecjson?type="+ v.getType()+"&date_r="+v.getDateR()+"&description="+v.getDescription()+"&id_client="+v.getId_client() ;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /*public ArrayList<Reclamation> getAll() {
        reclamations = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/affjson");
        System.out.println("url = " + cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    reclamations = (ArrayList<Reclamation>) getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reclamations;
    }

    private ArrayList<Reclamation> getList() {
        reclamations = new ArrayList<>();
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> parsedJson = jsonp.parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");
            System.out.println(parsedJson);
            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();
                System.out.println(obj);

                float id = Float.parseFloat(obj.get("id").toString());
                r.setType((String) obj.get("type"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateDebutStr = obj.get("date_r").toString();
                Date dateDebut = sdf.parse(dateDebutStr);
                String formattedDateDebut = new SimpleDateFormat("yyyy-MM-dd").format(dateDebut);
                r.setDateR(new SimpleDateFormat("yyyy-MM-dd").parse(formattedDateDebut));

                // existing code omitted for brevity
                System.out.println(r.getDateR());
                r.setDescription(obj.get("description").toString());
r.setIdclient(Integer.parseInt(obj.get("id_client").toString()));
                r.setId((int) id);




                reclamations.add(r);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ParseException e) {
        }
        return reclamations;
    }*/
    
    
    
    
    
    public ArrayList<Reclamation> getAll() {
    ArrayList<Reclamation> reclamations = new ArrayList<Reclamation>();

    cr = new ConnectionRequest();
    cr.setUrl(Statics.BASE_URL + "/affjson");
    cr.setHttpMethod("GET");
    cr.addResponseListener(e -> {
        try {
            JSONParser jsonp = new JSONParser();
            Map<String, Object> parsedJson = jsonp.parseJSON(new InputStreamReader(new ByteArrayInputStream(cr.getResponseData()), "UTF-8"));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();

                r.setId(((Double) obj.get("id")).intValue());
                r.setType((String) obj.get("type"));
                r.setDateR(new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date_r").toString()));
                r.setDescription((String) obj.get("description"));
                //r.setIdclient(((Double) obj.get("id_client")).intValue());

                reclamations.add(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(cr);

    return reclamations;
}


public Reclamation getReclamationById(int id) {
    //Reclamation r = null;
cr = new ConnectionRequest();
    cr.setUrl(Statics.BASE_URL + "/showjson/" + id);
    cr.setHttpMethod("GET");
    cr.addResponseListener(e -> {
        try {
            JSONParser jsonp = new JSONParser();
            Map<String, Object> parsedJson = jsonp.parseJSON(new InputStreamReader(new ByteArrayInputStream(cr.getResponseData()), "UTF-8"));

            Reclamation r = new Reclamation();
            r.setId(((Double) parsedJson.get("id")).intValue());
            r.setType((String) parsedJson.get("type"));
            r.setDateR(new SimpleDateFormat("yyyy-MM-dd").parse(parsedJson.get("date_r").toString()));
            r.setDescription((String) parsedJson.get("description"));
            r.setIdclient(((Double) parsedJson.get("id_client")).intValue());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(cr);
        Reclamation r = null;

    return r;
}




    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/deletejson/"+id);
        System.out.println("url delete : "+cr);
        cr.setHttpMethod("POST");
        // cr.addArgument("id", String.valueOf(id));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
       public int edit(Reclamation v) {
        return manage(v );
    }
    public boolean modifier(Reclamation v) {

        String url = Statics.BASE_URL + "/editjson/"+v.getId()+"?&Type="+v.getType()+"&date_r="+v.getDateR()+"&description="+v.getDescription()+"&id_client="+v.getId_client() + "";

        req.setUrl(url);

        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public int manage(Reclamation v) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("GET");
            cr.setUrl(Statics.BASE_URL + "/editjson/?id="+v.getId()+"&Type="+v.getType()+"&date_r="+v.getDateR()+"&description"+v.getDescription()+"&id_client="+v.getId_client());

            cr.addArgument("id", String.valueOf(v.getId()));
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }
}
