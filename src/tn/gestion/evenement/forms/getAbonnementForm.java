package tn.gestion.evenement.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import tn.gestion.evenement.enitite.Abonnement;
import tn.gestion.evenement.service.AbonnementWebService;

public class getAbonnementForm extends BaseForm {

    private MultiList eventList;

    public getAbonnementForm() {
        this.init(Resources.getGlobalResources());
        eventList = new MultiList(new DefaultListModel<>());
        add(eventList);
        getAllAbonnement();
    }

    private void getAllAbonnement() {
        AbonnementWebService service = new AbonnementWebService();
        List<Abonnement> abons = service.getAlAbonnement();
        DefaultListModel<Map<String, Object>> model = (DefaultListModel<Map<String, Object>>) eventList.getModel();
        model.removeAll();
        for (Abonnement c : abons) {
            Map<String, Object> item = new HashMap<>();
            item.put("Line1", "Type : " + c.getTypeAb());
            item.put("Line2", "Mode : " + c.getModePaiementAb());
            item.put("Line3", c.getId());
            model.addItem(item);
        }
        eventList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Map<String, Object> selectedItem = (Map<String, Object>) eventList.getSelectedItem();
                    int catId = (int) selectedItem.get("Line3");
                    Abonnement selectedEvent = null;
                    for (Abonnement c : abons) {
                        if (c.getId() == catId) {
                            selectedEvent = c;
                            break;
                        }
                    }
                    editFormAbonnement myForm2 = new editFormAbonnement(selectedEvent);
                    myForm2.show();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });

    }
}
