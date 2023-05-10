package tn.gestion.evenement.forms;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

public class BaseForm extends com.codename1.ui.Form {

    public void init(Resources theme) {
        Toolbar tb = getToolbar();

        tb.getAllStyles().setBgColor(0xffffff);

        Image logo = theme.getImage("logo.png");
        Label logoLabel = new Label(logo);
        Container logoContainer = BorderLayout.center(logoLabel);
        logoContainer.setUIID("SideCommandLogo");
        tb.addComponentToSideMenu(logoContainer);

        Label taglineLabel = new Label("Gestion Evenement");
        taglineLabel.setUIID("SideCommandTagline");
        Container taglineContainer = BorderLayout.south(taglineLabel);
        taglineContainer.setUIID("SideCommand");

        tb.addComponentToSideMenu(taglineContainer);

        tb.addMaterialCommandToSideMenu("Liste Contract", FontImage.MATERIAL_HOME, e -> {
            getContratForm f = new getContratForm();
            f.show();
        });
        tb.addMaterialCommandToSideMenu("Ajouter Contract", FontImage.MATERIAL_ADD, e -> {
            newContratForm f = new newContratForm();
            f.show();
        });
        tb.addMaterialCommandToSideMenu("List Abonnement", FontImage.MATERIAL_LIST, e -> {
            getAbonnementForm f = new getAbonnementForm();
            f.show();
        });
        tb.addMaterialCommandToSideMenu("Ajouter Abonnement", FontImage.MATERIAL_ADD, e -> {
            newAbonnementForm f = new newAbonnementForm();
            f.show();
        });
    }
}
