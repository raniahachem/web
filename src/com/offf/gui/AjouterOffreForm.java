/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offf.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.offf.entities.Offre;
import com.offf.services.ServiceOffre;
import com.codename1.ui.events.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author user
 */
public class AjouterOffreForm extends BaseForm {
    
    
    Form current;
    public AjouterOffreForm(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter une Offre");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
         
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

         swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Offres", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          ListOffreForm a = new ListOffreForm(res);
           a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        //
        
      TextField id_conducteur_id = new TextField("", "id_conducteur_id!!");
      id_conducteur_id.setUIID("TextFieldBlack");
      addStringValue("id_conducteur_id", id_conducteur_id);

        
        TextField Destination = new TextField("", "Destination!!");
        Destination.setUIID("TextFieldBlack");
        addStringValue("Destination",Destination);
        
        TextField pt_depart = new TextField("", "entrer pt_depart!!");
        pt_depart.setUIID("TextFieldBlack");
        addStringValue("pt_depart",pt_depart);
        
         TextField type_vehicule = new TextField("", "entrer type_vehicule!!");
        type_vehicule.setUIID("TextFieldBlack");
        addStringValue("type_vehicule",type_vehicule);
        
          TextField prix = new TextField("", "prix!!");
          prix.setUIID("TextFieldBlack");
          addStringValue("prix", prix);
         
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((ActionEvent e) -> {
            
            
            try {
                
                if(Destination.getText().equals("") || pt_depart.getText().equals("") || prix.getText().equals("") || type_vehicule.getText().equals("") || id_conducteur_id.getText().equals("")) {
                    Dialog.show("Veuillez remplir tous les champs!","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                     
                    //njibo iduser men session (current user)
                    Offre o = new Offre(
                                   Integer.parseInt(String.valueOf(id_conducteur_id.getText())),
                                  String.valueOf(Destination.getText()).toString(),
                                  String.valueOf(pt_depart.getText()).toString(),
                                   Integer.parseInt(String.valueOf(prix.getText())),
                                  String.valueOf(type_vehicule.getText()).toString()
                            );
                    
                    System.out.println("data  offre == "+o);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceOffre.getInstance().ajouterOffre(o);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new ListOffreForm(res).show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer ,   String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        
         
        
         if (spacer == null) {
        // Gérer l'erreur ou afficher un message d'erreur
        return;
    }
         
          if (res == null) {
        // Gérer l'erreur ou afficher un message d'erreur
        return;
    }
        
         
        
        Container page1 = 
                LayeredLayout.encloseIn(
                 
                         
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
         
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
    public void getOffre(String id) {
    String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=bc707c1f4e36344270536a932b5f6a58";
}
   
    
}
