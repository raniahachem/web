package com.mycompany.gui;

import com.codename1.components.SpanButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceRating;
import java.io.UnsupportedEncodingException;

public class AddRatingForm extends BaseForm {
    private int rating;
    private SpanButton[] starButtons;
    private ServiceRating service;
        Form current;

    public AddRatingForm(Resources res) {
        super("Rate Us", BoxLayout.y());
        setLayout(new BorderLayout());
        
        
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
                super.addSideMenu(res);

                getStyle().setBgColor(0xFF191C24);

        
    
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        
      
        
        
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        
        
        
        
        service = new ServiceRating();

        // Add a header to the form
        getToolbar().setTitleCentered(true);
        getToolbar().setTitle("Rate Your driver");

        // Add menu commands
        Command settingsCommand = new Command("Settings");
        Command sCommand = new Command("Profile");

        getToolbar().addCommandToOverflowMenu(settingsCommand);
        getToolbar().addCommandToOverflowMenu(sCommand);

        // Add stars
        Container starsContainer = new Container(new FlowLayout(CENTER, CENTER));
        starButtons = new SpanButton[5];
        for (int i = 0; i < starButtons.length; i++) {
            int starNumber = i + 1;
            starButtons[i] = new SpanButton();
            starButtons[i].setUIID("StarButton");
            starButtons[i].setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR, starButtons[i].getUnselectedStyle()));
            starButtons[i].addActionListener(evt -> setRating(starNumber));
            starsContainer.add(starButtons[i]);
            starButtons[i].getAllStyles().setFgColor(0xFFFF00);
        }
        add(BorderLayout.CENTER, starsContainer);

        // Add submit button
        Button submitButton = new Button("Send");
        submitButton.setUIID("Button");
        submitButton.addActionListener(evt -> submitRating());
        Container south = FlowLayout.encloseCenter(submitButton);
        add(BorderLayout.SOUTH, south);

        
        
        
        
        
        
    }

    private void submitRating() {
        // Save the rating to a database or perform some other action with it
        // For this example, we'll just show a message indicating the rating value
        Dialog.show("Thank you for your feedback!", "Rating: " + rating + "/5", "OK", null);
        try {
            ServiceRating.getInstance().addRating(rating);
        } catch (UnsupportedEncodingException ex) {
        }
        // Reset the star rating
        rating = 0;
        for (int i = 0; i < starButtons.length; i++) {
            if (i < rating) {
                starButtons[i].setIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR, starButtons[i].getUnselectedStyle()));
            } else {
                starButtons[i].setIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR_BORDER, starButtons[i].getUnselectedStyle()));
            }
        }
    }

  


    private void setRating(int starNumber) {
    rating = starNumber;
    for (int i = 0; i < starButtons.length; i++) {
        if (i < rating) {
            starButtons[i].setIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR, starButtons[i].getUnselectedStyle()));
        } else {
            starButtons[i].setIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR_BORDER, starButtons[i].getUnselectedStyle()));
        }
    }
}
}
