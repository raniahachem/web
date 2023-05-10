package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.*;
import com.codename1.ui.table.Table;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

import com.codename1.ui.Form;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.DefaultTableModel;
import java.util.ArrayList;
import com.codename1.ui.events.SelectionListener;


public class ListReclamationForm extends Form {

    /*Form previous;

    public static Reclamation currentV = null;
    Button addBtn;

    public ListReclamationForm(Form previous) {

        this.previous = previous;
        setTitle("list Subscriptions");
        setLayout(BoxLayout.y());

        addGUIs();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();

        this.refreshTheme();
    }

    private void addGUIs() {
        ArrayList<Reclamation> listreclamations = ServiceReclamation.getInstance().getAll();
        if (listreclamations.size() > 0) {
            for (Reclamation reclamation : listreclamations) {
                Component model = makeReclamationModel(reclamation);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    Label idab, type, datedebut, description, idof;

    private Container makeModelWithoutButtons(Reclamation reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reclamationModel.setUIID("containerRounded");
        idab = new Label("Subscription id : " + reclamation.getId());
        idab.setUIID("labelDefault");
        type = new Label("Subscription type : " + reclamation.getType());
        type.setUIID("labelDefault");
        description = new Label("Subscription price: " + reclamation.getDescription());
        description.setUIID("labelDefault");
        idof = new Label("offer id : " + reclamation.getId_client());
        idof.setUIID("labelDefault");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        datedebut = new Label("Start date : " + sdf.format(reclamation.getDateR()));
        datedebut.setUIID("labelDefault");
        reclamationModel.addAll(idab, type, datedebut, description, idof);
        return reclamationModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeReclamationModel(Reclamation reclamation) {

        Container reclamationModel = makeModelWithoutButtons(reclamation);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Update");
        editBtn.setUIID("buttonWhiteCenter");

        editBtn.addActionListener(action -> {
            currentV = reclamation;
            new ModifierReclamation(previous, reclamation).show();
        });

        deleteBtn = new Button("Delete");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirm deletion");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Are you sure you want to delete this subscription?"));
            Button btnClose = new Button("Cancel");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirm");

            btnConfirm.addActionListener(actionConf -> {
                ServiceReclamation.getInstance().delete(reclamation.getId());
                new ListReclamationForm(previous).show();
            });

            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);

        reclamationModel.add(btnsContainer);

        return reclamationModel;
    }*/

Form previous;
    public static Reclamation currentV = null;
    private Table table;

    public ListReclamationForm(Form previous) {
        this.previous = previous;
        setTitle("List Reclamations");
        setLayout(new BorderLayout()); // Utiliser un BorderLayout pour le Form

        table = new Table();
        table.setScrollableY(true);
        table.setUIID("table");
        // Définir les colonnes de la table
        table.setModel(new DefaultTableModel(
            new String[] {"ID", "Type", "Date", "Description"},
            getAllReclamationData()));

        // Définir l'action à effectuer lorsqu'une ligne est sélectionnée
        table.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    currentV = ServiceReclamation.getInstance().getReclamationById(Integer.parseInt((String) table.getModel().getValueAt(row, 0)));
                    new ModifierReclamation(previous, currentV).show();
                }
            }
        });

        // Ajouter la table au Form
        addComponent(BorderLayout.CENTER, table);

        // Ajouter le bouton de retour au Form
        Button backButton = new Button("Back");
        backButton.addActionListener(e -> previous.showBack());
        addComponent(BorderLayout.NORTH, backButton); // Ajouter le bouton en haut du Form
    }

    /**
     * Obtenir toutes les données à afficher dans la table
     */
    private Object[][] getAllReclamationData() {
        ArrayList<Reclamation> listReclamations = ServiceReclamation.getInstance().getAll();
        Object[][] data = new Object[listReclamations.size()][5];
        for (int i = 0; i < listReclamations.size(); i++) {
            Reclamation reclamation = listReclamations.get(i);
            data[i][0] = reclamation.getId();
            data[i][1] = reclamation.getType();
            data[i][2] = reclamation.getDateR();
            data[i][3] = reclamation.getDescription();
            //data[i][4] = reclamation.getId_client();
        }
        return data;
    }
}





