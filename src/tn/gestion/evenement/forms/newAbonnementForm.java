package tn.gestion.evenement.forms;

import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import tn.gestion.evenement.enitite.Abonnement;
import tn.gestion.evenement.service.AbonnementWebService;

public class newAbonnementForm extends BaseForm {

    public newAbonnementForm() {
        this.init(Resources.getGlobalResources());
        TextField typeAbField = new TextField("", "typeAb");
        TextField modePaiementAbField = new TextField("", "modePaiementAb");
        TextField prixAbField = new TextField("", "prixAb");

        this.add(typeAbField);
        this.add(modePaiementAbField);
        this.add(prixAbField);

        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String nom = prixAbField.getText();
            String descriptif = modePaiementAbField.getText();
            int prixAb = Integer.valueOf(prixAbField.getText());

            Abonnement newAb = new Abonnement();
            newAb.setTypeAb(nom);
            newAb.setModePaiementAb(descriptif);
            newAb.setPrixAb(prixAb+"");
            AbonnementWebService service = new AbonnementWebService();
            service.newAbonnement(newAb);
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getAbonnementForm myForm = new getAbonnementForm();
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
