/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offf.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.offf.entities.Offre;
import com.offf.services.ServiceOffre;

/**
 *
 * @author Lenovo
 */
public class ModifierOffreForm extends BaseForm {
    
    Form current;
    public ModifierOffreForm(Resources res , Offre r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Offre");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField id = new TextField(String.valueOf(r.getId()) , "id" , 20 , TextField.ANY);
        TextField id_conducteur_id = new TextField(String.valueOf(r.getId_conducteur_id()) , "id_conducteur_id" , 20 , TextField.ANY);
        TextField Destination = new TextField(r.getDestination(), "Destination" , 20 , TextField.ANY);
        TextField pt_depart = new TextField(r.getPt_depart(), "pt_depart" , 20 , TextField.ANY);
        TextField prix = new TextField(String.valueOf(r.getPrix()) , "prix" , 20 , TextField.ANY);
        TextField type_vehicule = new TextField(r.getType_vehicule(), "type_vehicule" , 20 , TextField.ANY);

 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
   
        
        
        
        
        id.setUIID("NewsTopLine");
        id_conducteur_id.setUIID("NewsTopLine");
        Destination.setUIID("NewsTopLine");
        pt_depart.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
        type_vehicule.setUIID("NewsTopLine");

        id.setSingleLineTextArea(true);
        id_conducteur_id.setSingleLineTextArea(true);
        Destination.setSingleLineTextArea(true);
        pt_depart.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);
        type_vehicule.setSingleLineTextArea(true);

        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setDestination(Destination.getText());
           r.setPt_depart(pt_depart.getText());
           r.setPrix(Float.parseFloat(String.valueOf(r.getPrix())));
           r.setType_vehicule(type_vehicule.getText());
           
           
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceOffre.getInstance().modifierOffre(r)) { // if true
           new ListOffreForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListOffreForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l1 = new Label();
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(Destination),
                createLineSeparator(),
                new FloatingHint(pt_depart),
                createLineSeparator(),
                new FloatingHint(type_vehicule),
                createLineSeparator(),
                new FloatingHint(prix),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
