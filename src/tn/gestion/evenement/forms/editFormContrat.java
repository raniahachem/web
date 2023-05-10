package tn.gestion.evenement.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Date;
import tn.gestion.evenement.enitite.Contract;
import tn.gestion.evenement.service.AbonnementWebService;
import tn.gestion.evenement.service.ContractWebService;

public class editFormContrat extends BaseForm {

        AbonnementWebService serviceCat = new AbonnementWebService();
        ContractWebService service = new ContractWebService();
    public editFormContrat(Contract e) throws ParseException {
        this.init(Resources.getGlobalResources());
        
        TextField id_contratField = new TextField(e.getId_conducteur()+"", "Id_conducteur");
        TextField id_adminField = new TextField(e.getId_admin()+"", "id_admin");
        PickerComponent datePicker = PickerComponent.createDate(new Date());
        PickerComponent datePicker2 = PickerComponent.createDate(new Date());
        TextField prixField = new TextField(e.getPrix()+"", "prix");
        TextField statutField = new TextField(e.getStatut(), "statut");
        TextField qr_codeField = new TextField(e.getQr_code(), "qr_code");
        
        this.add(id_contratField)
                .add(id_adminField)
                .add(prixField)    
                .add(datePicker)
                .add(datePicker2)
                .add(qr_codeField);

        Button submitButton = new Button("Submit");
        
        
        submitButton.addActionListener(s-> {
            String id_c = id_contratField.getText();
            String id_a = id_adminField.getText();
            String dateDebut = datePicker.getPicker().getDate().toString();
            String dateFin = datePicker2.getPicker().getDate().toString();
            int prix = Integer.parseInt(prixField.getText());
            String statut = statutField.getText();
            String qr_code = qr_codeField.getText();

            Contract newContat = new Contract();
            newContat.setId_contrat(e.getId_contrat());
            newContat.setId_conducteur(Integer.valueOf(id_c));
            newContat.setId_admin(Integer.valueOf(id_a));
            newContat.setDate_debut(dateDebut);
            newContat.setDate_fin(dateFin);
            newContat.setPrix(prix);
            newContat.setStatut(statut);
            newContat.setQr_code(qr_code);
            
            service.editContrat(newContat);
        }
        );
        Button goToFormButton = new Button("Go back");
        goToFormButton.addActionListener(ee -> {
            getContratForm myForm = new getContratForm();
            myForm.show();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(cc -> {
            getContratForm myForm = new getContratForm();
            service.delContract(e);
            myForm.show();
        });
        this.add(deleteButton);
        this.add(goToFormButton);
        this.add(submitButton);
    }

}
