package tn.gestion.evenement.forms;

import com.codename1.ui.Button;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Date;
import tn.gestion.evenement.enitite.Abonnement;
import tn.gestion.evenement.enitite.Contract;
import tn.gestion.evenement.service.AbonnementWebService;
import tn.gestion.evenement.service.ContractWebService;

public class newContratForm extends BaseForm {
        AbonnementWebService serviceCat = new AbonnementWebService();

    public newContratForm() {
        this.init(Resources.getGlobalResources());

        TextField id_contratField = new TextField("", "id_contrat");
        TextField id_adminField = new TextField("", "id_admin");
        PickerComponent datePicker = PickerComponent.createDate(new Date());
        PickerComponent datePicker2 = PickerComponent.createDate(new Date());
        TextField prixField = new TextField("", "prix");
        TextField statutField = new TextField("", "statut");
        TextField qr_codeField = new TextField("", "qr_code");
        
        this.add(id_contratField)
                .add(id_adminField)
                .add(prixField)    
                .add(datePicker)
                .add(datePicker2)
                .add(qr_codeField);
                
        Button submitButton = new Button("Submit");

        submitButton.addActionListener(s
                -> {
            String id_c = id_contratField.getText();
            String id_a = id_adminField.getText();
            String dateDebut = datePicker.getPicker().getDate().toString();
            String dateFin = datePicker2.getPicker().getDate().toString();
            int prix = Integer.parseInt(prixField.getText());
            String statut = statutField.getText();
            String qr_code = qr_codeField.getText();

            Contract newEvent = new Contract();
            newEvent.setId_conducteur(Integer.valueOf(id_c));
            newEvent.setId_admin(Integer.valueOf(id_a));
            newEvent.setDate_debut(dateDebut);
            newEvent.setDate_fin(dateFin);
            newEvent.setPrix(prix);
            newEvent.setStatut(statut);
            newEvent.setQr_code(qr_code);
            
            ContractWebService service = new ContractWebService();
            service.newContract(newEvent);
        }
        );
        this.add(submitButton);
        Button goToFormButton = new Button("Go Back");
        goToFormButton.addActionListener(e -> {
            getContratForm myForm = new getContratForm();
            myForm.show();
        });
        this.add(goToFormButton);
    }

}
