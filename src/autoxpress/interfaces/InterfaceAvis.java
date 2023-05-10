/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.interfaces;


import autoxpress.entities.Avis;
import java.util.List;

/**
 *
 * @author rania
 */
public interface InterfaceAvis {
    public void ajouterAvis(Avis a);

    public void modifierAvis(Avis a);
    //public void modifierReclamation(Reclamation r, int id);

    public void supprimerAvis(int id);
        //public void supprimerReclamation(int id) ;


    public List<Avis> afficherAvis();
    
}
