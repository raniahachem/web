/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Commentaire;
import com.mycompany.services.ServiceCommentaire;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author BKHmidi
 */
public class EditForm extends BaseForm{
     private TextArea commentField;
    private Label errorLabel;
    private Resources theme;

  public EditForm(Resources res, int commentId) {
          super("FeedBack",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        setLayout(new BorderLayout());
        getStyle().setBgColor(0xFF191C24);
              theme= res;


        // Add a header to the form
        getToolbar().setTitleCentered(true);
        getToolbar().setTitle("Update Comment");

       

        commentField = new TextArea(5, 20);
        commentField.setHint("Type your comment here");
        Container center = BoxLayout.encloseY(commentField);

        errorLabel = new Label("");
        errorLabel.setUIID("WelcomeMessage");
        Container errorContainer = BoxLayout.encloseY(errorLabel);

        add(BorderLayout.CENTER, BorderLayout.center(center).add(BorderLayout.SOUTH, errorContainer));

        Button submitButton = new Button("Update");
        submitButton.setUIID("Button");
        submitButton.addActionListener(evt -> {
            try {
                submitComment(commentId);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        Container south = FlowLayout.encloseCenter(submitButton);
        add(BorderLayout.SOUTH, south);
        
    }

  

  
  
private void submitComment(int commentId) throws UnsupportedEncodingException {
    String commentText = commentField.getText().trim();
    ServiceCommentaire serv = new ServiceCommentaire();

    // Call the API to update the comment
    Commentaire commentaire = new Commentaire();
    commentaire.setId(commentId); // Set the id of the comment to update
    commentaire.setContenu(commentText);
    serv.updateCommentaire(commentaire);

    Dialog.show("Congrats!", "Your comment was updated", "OK", null);

    // Go back to the comments list form
    new CommentsListForm(theme).show();
}


}
