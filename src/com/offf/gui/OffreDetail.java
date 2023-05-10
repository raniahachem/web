/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offf.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.offf.entities.Offre;
import com.offf.services.ServiceOffre;

/**
 *
 * @author user
 */
public class OffreDetail extends BaseForm {

    public static float id;

    public OffreDetail(Resources res) {

        super("Offre", BoxLayout.y());
        Offre offre = ServiceOffre.getInstance().getOffre(id);
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle(offre.getDestination());
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight()), true);
if (placeholder != null && offre.getDestination() != null && offre.getPt_depart() != null) {
    URLImage urlImage = URLImage.createToStorage(placeholder, offre.getDestination() + "back-logo.jpeg", offre.getPt_depart());
} 


        Label idLabel = new Label("id offre:");
idLabel.setUIID("largeBoldSystemFont");
add(idLabel);

Label idOffre = new Label(String.valueOf(offre.getId()));
idOffre.setUIID("largePlainSystemFont");
add(idOffre);

Label idConducteurLabel = new Label("id_conducteur_id:");
idConducteurLabel.setUIID("largeBoldSystemFont");
add(idConducteurLabel);

Label idConducteurOffre = new Label(String.valueOf(offre.getId_conducteur_id()));
idConducteurOffre.setUIID("largePlainSystemFont");
add(idConducteurOffre);

Label destinationLabel = new Label("Destination:");
destinationLabel.setUIID("largeBoldSystemFont");
add(destinationLabel);

Label destinationOffre = new Label(offre.getDestination());
destinationOffre.setUIID("largePlainSystemFont");
add(destinationOffre);

Label ptDepartLabel = new Label("pt_depart:");
ptDepartLabel.setUIID("largeBoldSystemFont");
add(ptDepartLabel);

Label ptDepartOffre = new Label(offre.getPt_depart());
ptDepartOffre.setUIID("largePlainSystemFont");
add(ptDepartOffre);

Label prixLabel = new Label("prix:");
prixLabel.setUIID("largeBoldSystemFont");
add(prixLabel);

Label prixOffre = new Label(String.valueOf(offre.getPrix()));
prixOffre.setUIID("largePlainSystemFont");
add(prixOffre);

Label typeVehiculeLabel = new Label("type_vehicule:");
typeVehiculeLabel.setUIID("largeBoldSystemFont");
add(typeVehiculeLabel);

Label typeVehiculeOffre = new Label(offre.getType_vehicule());
typeVehiculeOffre.setUIID("largePlainSystemFont");
add(typeVehiculeOffre);
         
  Button btnDelete = new Button("Delete Film");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceOffre.getInstance().deleteOffre(offre.getId());
                Dialog.show("Success", "Connection accepted", new Command("OK"));
            }
        });
        addAll(btnDelete);

    }

}
