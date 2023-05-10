/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoxpress.tests;

import autoxpress.entities.Livraison;
import autoxpress.services.LivraisonCRUD;
import autoxpress.utils.MyConnection;

/**
 *
 * @author user
 */
public class MainClass {

     public static void main(String[] args){
       MyConnection mc = new MyConnection();
       //Offre o1 = new Offre(100,5,"dd","dd", (float) 5.2,"ss" );
       //OffreCRUD pcd = new OffreCRUD();
        //pcd.addEntity2(o1);
       //pcd.ModifierOff(o1);
       //pcd.SupprimerOff(o1);
     //System.out.println(pcd.entitiesList());
     
    // Livraison l1=new Livraison(99,"pppp","ddd",(float) 1.2,"ff", (float) 4.5 );
     LivraisonCRUD lcd = new LivraisonCRUD();
     //lcd.addLivraison2(l1);
     //lcd.ModifierLivraison(l1);
     //lcd.SupprimerLivraison(l1);
     System.out.println(lcd.LivraisonList());
     
        
    }
    
}
