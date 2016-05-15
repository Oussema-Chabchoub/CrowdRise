/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import static menu.ProjectController.cmp;
import static menu.ProjectController.pr;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import pi.dao.classes.CommentDao;
import pi.entities.commentaireprojet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UpdateCommentController implements Initializable {

        @FXML TextField contenuCommentaire;
        @FXML Button update;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenuCommentaire.setText(cmp.getContenuCommentaireProjet());
        
    }
     public void ModifierCommentaire(ActionEvent event)
    { 
        commentaireprojet com=new commentaireprojet(contenuCommentaire.getText(),pr);
        CommentDao cmd=new CommentDao();
        cmd.updateComment(com,cmp.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your comment is updated successfully");
        alert.showAndWait();
        clear();
         String title = "updated ";
        String message = "You've successfully updated this comment";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification(title,message,notification);
        tray.showAndDismiss(Duration.seconds(5));

        
         
    }
      public void clear()
    {
        contenuCommentaire.setText("");
    }
     public void clearButton(ActionEvent event)
    { 
                contenuCommentaire.setText("");

    }
    
}
