package tn.gestion.evenement.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tn.gestion.evenement.enitite.Contract;
import tn.gestion.evenement.service.ContractWebService;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import java.util.Collections;
import java.util.Comparator;

public class getContratForm extends BaseForm {

    private MultiList listContract;
    private List<Contract> contracts;
    private TextField searchField;
    
    public getContratForm() {
        this.init(Resources.getGlobalResources());
        Button sortButton = new Button("Sort by Date");
        sortButton.addActionListener(e -> {
            Collections.sort(contracts, new Comparator<Contract>() {
                @Override
                public int compare(Contract p1, Contract p2) {
                    return p1.getStatut().compareToIgnoreCase(p2.getStatut());
                }
            });
            updateList();
        });
        addComponent(BorderLayout.south(sortButton));
        
        listContract = new MultiList(new DefaultListModel<>());
        add(listContract);
        getAllContrat();
        
    }

    private void getAllContrat() {
        ContractWebService service = new ContractWebService();
        contracts = service.getAllContract();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) listContract.getModel();
        model.removeAll();
        for (Contract event : contracts) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", "Prix : " + event.getPrix());
            item.put("Line2", "Status : " + event.getStatut());
            item.put("Line3", event.getId_contrat());
            model.addItem(item);
        }
        listContract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) listContract.getSelectedItem();
                    int eventId = (int) selectedItem.get("Line3");
                    Contract selectedEvent = null;
                    for (Contract event : contracts) {
                        if (event.getId_contrat() == eventId) {
                            selectedEvent = event;
                            break;
                        }
                    }
                    editFormContrat myForm2 = new editFormContrat(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });

        searchField = new TextField("", "Enter Contract Status");
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            try {
                String searchId = searchField.getText();
                Contract selectedPromo = null;
                for (Contract p : contracts) {
                    if (p.getStatut() == null ? searchId == null : p.getStatut().equals(searchId)) {
                        selectedPromo = p;
                        break;
                    }
                }
                if (selectedPromo != null) {
                    editFormContrat myForm2 = new editFormContrat(selectedPromo);
                    myForm2.show();
                } else {
                    Dialog.show("Error", "Contract not found", "OK", null);
                }
            } catch (NumberFormatException ex) {
                Dialog.show("Error", "Invalid ID", "OK", null);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        });
        Container searchContainer = BorderLayout.west(searchField).add(BorderLayout.EAST, searchButton);
        addComponent(searchContainer);
    }

    private void updateList() {
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) listContract.getModel();
        model.removeAll();
        for (Contract p : contracts) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", "Prix : " + p.getPrix());
            item.put("Line2", "Status : " + p.getStatut());
            item.put("Line3", p.getId_contrat());
            model.addItem(item);
        }
    }
}
