/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.interfaces;


import autoxpress.entities.Avis;
import autoxpress.entities.Reclamation;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author rania
 */
public interface InterfaceCRUD {

    public void ajouterReclamation(Reclamation r);

    public void modifierReclamation(Reclamation r);
    //public void modifierReclamation(Reclamation r, int id);

    public void supprimerReclamation(int id);
        //public void supprimerReclamation(int id) ;


    public List<Reclamation> afficherReclamation();
}

