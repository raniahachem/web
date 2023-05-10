package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Commentaire;
import com.mycompany.services.ServiceCommentaire;
import java.io.UnsupportedEncodingException;



public class CommentsForm extends BaseForm {
    private TextArea commentField;
    private Label errorLabel;
    private Resources theme;

  public CommentsForm(Resources res) {
          super("FeedBack",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        setLayout(new BorderLayout());
        getStyle().setBgColor(0xFF191C24);
              theme= res;


        // Add a header to the form
        getToolbar().setTitleCentered(true);
        getToolbar().setTitle("Rate Your driver");

        // Add menu commands
        Command settingsCommand = new Command("Settings");
        Command sCommand = new Command("Profile");

        getToolbar().addCommandToOverflowMenu(settingsCommand);
        getToolbar().addCommandToOverflowMenu(sCommand);

        commentField = new TextArea(5, 20);
        commentField.setHint("Type your comment here");
        Container center = BoxLayout.encloseY(commentField);

        errorLabel = new Label("");
        errorLabel.setUIID("WelcomeMessage");
        Container errorContainer = BoxLayout.encloseY(errorLabel);

        add(BorderLayout.CENTER, BorderLayout.center(center).add(BorderLayout.SOUTH, errorContainer));

        Button submitButton = new Button("Done");
        submitButton.setUIID("Button");
        submitButton.addActionListener(evt -> {
            try {
                submitComment();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        Container south = FlowLayout.encloseCenter(submitButton);
        add(BorderLayout.SOUTH, south);
        
    }

private void submitComment() throws UnsupportedEncodingException {
    String commentText = commentField.getText().trim();
    if (commentText.isEmpty()) {
        errorLabel.setText("Comment field cannot be empty");
        return;
    } else {
        errorLabel.setText("");
    }

    // Call the API to submit the comment
    Commentaire commentaire = new Commentaire();
    commentaire.setContenu(commentText);
    ServiceCommentaire.getInstance().addCommentaire(commentaire);
    Dialog.show("Thanks for your comment!", "Your comment was submitted", "OK", null);
    new CommentsListForm(theme).show();
    // Refresh the form
    show();
}


}
