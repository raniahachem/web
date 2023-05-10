package com.mycompany.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Commentaire;
import com.mycompany.services.ServiceCommentaire;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class CommentsListForm extends BaseForm {
        Form current;

    public CommentsListForm(Resources res) {
          super("Comments",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
                super.addSideMenu(res);

                getStyle().setBgColor(0xFF191C24);

        
      // Add a spacer container with a preferred height of 10mm (adjust as needed)
Container spacer = BoxLayout.encloseY(new Label(), new Label(), new Label());
spacer.setUIID("Spacer");
spacer.setPreferredH(CN.convertToPixels(10f));

// Add the spacer container to the form
add(spacer);

Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        
      
        
        
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ServiceCommentaire serv = new ServiceCommentaire();
        Display.getInstance().callSerially(() -> {
        });
        List<Commentaire> comments = serv.AllComments();
        for (Commentaire comment : comments) {
            Container cnt = new Container(BoxLayout.y());
            cnt.setScrollableY(true);
            cnt.setLeadComponent(new Button());

            // Create a horizontal container to hold the comment and action buttons

            
            
                     Label commentLabel = new Label(comment.getContenu());
            commentLabel.setUIID("PopupDialog"); // set custom UIID

            Label dateLabel = new Label(comment.getDate().toString());
            dateLabel.setUIID("BottomPad"); // set custom UIID


          // Create buttons for delete and update actions
Button deleteBtn = new Button();
deleteBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, UIManager.getInstance().getComponentStyle("Button")));
deleteBtn.addActionListener(e -> {
    // Handle delete action here
    int id = comment.getId();
    // Show a confirmation dialog
    boolean confirm = Dialog.show("Confirmation", "Are you sure you want to delete this comment?", "Yes", "No");
    if (confirm) {
        serv.deleteCommentaire(id);
        // Remove the container from the UI hierarchy
        getParent().removeComponent(cnt);
                getParent().revalidate();

    }
});

Button updateBtn = new Button();
updateBtn.setIcon(FontImage.createMaterial(FontImage.MATERIAL_EDIT, UIManager.getInstance().getComponentStyle("Button")));
updateBtn.addActionListener(e -> {
    int commentId = comment.getId(); // get the ID from the comment object
    new EditForm(res, commentId).show(); // pass the ID to the EditForm constructor
});

addAll(commentLabel, dateLabel, deleteBtn,updateBtn);


            // Add buttons to the horizontal container
        }

        getToolbar().addCommandToRightBar("", FontImage.createMaterial(FontImage.MATERIAL_ADD_COMMENT, UIManager.getInstance().getComponentStyle("Command")), (evt) -> {
            new CommentsForm(res).show();
        });
    }
}
