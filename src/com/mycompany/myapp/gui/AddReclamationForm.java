/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.Date;

/**
 *
 * @author rania
 */
public class AddReclamationForm extends Form{
    private ComboBox<String> typeReclamationComboBox;
    private TextField descriptionReclamationTextField;
    private Picker dateTimePicker;
    private TextField clientComboBox;

    public AddReclamationForm(Form previous) {
        setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());

        typeReclamationComboBox = new ComboBox<>("technique", "service", "autre");
        dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE);
        dateTimePicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        /*dateTimePicker = new Picker();
dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
dateTimePicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));*/
descriptionReclamationTextField = new TextField("", "Description");
        clientComboBox = new TextField("", "votre id");

        Button btnValider = new Button("Add Reclamation");

        btnValider.addActionListener(evt -> {

            
            String typeReclamation = typeReclamationComboBox.getSelectedItem();
            //Date date_r = dateTimePicker.getDate();
            Date date_r = dateTimePicker.getDate();

            String descriptionReclamation = descriptionReclamationTextField.getText();
            int idclient = Integer.parseInt(clientComboBox.getText());

            Reclamation Rec = new Reclamation(typeReclamation, date_r, descriptionReclamation, idclient);

            if (ServiceReclamation.getInstance().addTask(Rec)) {
                Dialog.show("Success", "Reclamation ajoutée avec succés", new Command("OK"));
            } else {
                Dialog.show("ERROR", "Echec d'envoi de la réclamation", new Command("OK"));
            }

        });

        addAll(typeReclamationComboBox, descriptionReclamationTextField, dateTimePicker, clientComboBox, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
