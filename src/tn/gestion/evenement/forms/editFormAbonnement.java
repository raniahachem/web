package tn.gestion.evenement.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import tn.gestion.evenement.enitite.Abonnement;
import tn.gestion.evenement.service.AbonnementWebService;

public class editFormAbonnement extends BaseForm {

    AbonnementWebService service = new AbonnementWebService();
    public editFormAbonnement(Abonnement e) throws ParseException {
        this.init(Resources.getGlobalResources());
        TextField typeAbField = new TextField(e.getTypeAb(), "typeAb");
        TextField modePaiementAbField = new TextField(e.getModePaiementAb(), "modePaiementAb");
        TextField prixAbField = new TextField(e.getPrixAb(), "prixAb");

        this.add(typeAbField);
        this.add(modePaiementAbField);
        this.add(prixAbField);
        
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(s-> {
            String nom = prixAbField.getText();
            String descriptif = modePaiementAbField.getText();
            int prixAb = Integer.valueOf(prixAbField.getText());

            Abonnement newAb = new Abonnement();
            newAb.setId(e.getId());
            newAb.setTypeAb(nom);
            newAb.setModePaiementAb(descriptif);
            newAb.setPrixAb(prixAb+"");
            service.editAbonnement(newAb);
            getAbonnementForm myForm = new getAbonnementForm();
            myForm.show();
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getAbonnementForm myForm = new getAbonnementForm();
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            service.delAbonnement(e);
            getAbonnementForm myForm = new getAbonnementForm();
            myForm.show();
        });
        this.add(deleteButton);
        this.add(goToFormButton);
        this.add(submitButton);
    }

}
