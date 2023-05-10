/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.offf.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.offf.entities.Offre;
 import com.offf.services.ServiceOffre;
 import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class ListOffreForm extends BaseForm{
    public ListOffreForm (Resources res) {
        
        super("List Offres", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List Offres");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Image cinemaImage = res.getImage("back-logo.jpeg").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 3);
            Label cinemaLabel = new Label(cinemaImage);
            cinemaLabel.setUIID("Container");
            cinemaLabel.getAllStyles().setMarginTop(2);
            add(cinemaLabel);

        
        ArrayList<Offre> tasks = ServiceOffre.getInstance().affichageOffre();
        for (Offre a : tasks) {
         /*   TextField title = new TextField("Abonement number: " + a.getId_abonnement()+"Type Abonnement"+a.getType());
                     Button sup = new Button("Delete");
                   sup.addActionListener((evt) -> {
                   AbonnementService.getInstance().deleteAbonnement(a.getId_abonnement());
                    Dialog.show("Alert", "Delete Abonnement ?", new Command("OK"));
                    Dialog.show("Success", "Abonnement deleted successfully", new Command("OK"));
                   
                    });
                   add(title);
        }*/
         
         
              
                  Label i = new Label();
                  
                 
         Label spl = new Label("Offre id : " + "  " + a.getId()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label spl2 = new Label("offre: " + "  " + a.getDestination());
         spl2.getAllStyles().setFgColor(0x00000);
         Label sp3 = new Label("id_conducteur_id : " + "  " + a.getId_conducteur_id()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label sp4 = new Label("pt_depart : " + "  " + a.getPt_depart()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label sp6 = new Label("prix : " + "  " + a.getPrix()); 
         spl.getAllStyles().setFgColor(0x00000);
         Label sp5 = new Label("type_vehicule : " + "  " + a.getType_vehicule());
         spl2.getAllStyles().setFgColor(0x00000);
          
  
         
         
         Button sup = new Button("Delete");
                  Button upd = new Button("Update");
                 
             
                sup.addActionListener((evt) -> {
                   ServiceOffre.getInstance().deleteOffre((int) a.getId());
                    Dialog.show("Alert", "Delete Abonnement ?", new Command("OK"));
                    Dialog.show("Success", "Abonnement deleted successfully", new Command("OK"));
                     
                    });
                 upd.addActionListener((evt) -> {
                       new ModifierOffreForm(res,a).show();
                    });
                 
                  
                     
                   
        addAll(spl,spl2,sp3,sp4,sp5,sup,upd)
                ;}
        
        
        
        
        
    }
    
        
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
    
    
}