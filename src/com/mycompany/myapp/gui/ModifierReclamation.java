/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
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

/**
 *
 * @author rania
 */
public class ModifierReclamation extends Form {

    private Form previous;
    private ComboBox<String> combo1;
    private ComboBox<String> combo2;
    private ComboBox<String> combo3;
    private ComboBox<String> typeReclamationComboBox;
    private TextField descriptionReclamationTextField;
    private Picker dateTimePicker;
    private TextField clientComboBox;

    public ModifierReclamation(Form previous, Reclamation v) {

        setTitle("Update Reclamation");
        setLayout(BoxLayout.y());

        this.previous = previous;

        typeReclamationComboBox = new ComboBox<>("Technique", "Service", "Autre");
        dateTimePicker = new Picker();
        dateTimePicker.setType(Display.PICKER_TYPE_DATE);
        dateTimePicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        /*dateTimePicker = new Picker();
dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
dateTimePicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));*/
        descriptionReclamationTextField = new TextField("", "Description");
        clientComboBox = new TextField("", "votre id");

        // fill the fields with the data of the selected Reclamation
        typeReclamationComboBox.setSelectedItem(v.getType());
        dateTimePicker.setDate(v.getDateR());
        descriptionReclamationTextField.setText(String.valueOf(v.getDescription())); 
        clientComboBox.setText(String.valueOf(v.getId_client()));

        Button btnModifier = new Button("Edit");
        Button btnAnnuler = new Button("Cancel");

        btnModifier.addActionListener(evt -> {
            v.setDescription (descriptionReclamationTextField.getText());
            v.setType(typeReclamationComboBox.getSelectedItem());
            v.setDateR(dateTimePicker.getDate());
            v.setIdclient(Integer.parseInt(clientComboBox.getText()));

            try {
                if (ServiceReclamation.getInstance().modifier(v)) {
                    new ListReclamationForm(previous).show();
                } else {
                    Dialog.show("Error", "Unable to update transaction", new Command("OK"));
                }
            } catch (Exception e) {
                Dialog.show("Error", "Unable to update transaction: " + e.getMessage(), new Command("OK"));
            }
        });

        btnAnnuler.addActionListener(e -> {
            new ListReclamationForm(previous).show();
        });

        addAll(typeReclamationComboBox, descriptionReclamationTextField, dateTimePicker, clientComboBox, btnModifier, btnAnnuler);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
