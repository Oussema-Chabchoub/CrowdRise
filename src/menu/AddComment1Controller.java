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
import static menu.AllProjectsController.pr1;
import static menu.LoginViewController.id;
import pi.dao.classes.CommentDao;
import pi.entities.commentaireprojet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddComment1Controller implements Initializable {

    @FXML TextField contenuCommentaire;
    @FXML Button ajouterCommentaire;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    public void ajouterCommentaire(ActionEvent event)
    { 
        commentaireprojet com=new commentaireprojet(contenuCommentaire.getText(),pr1,id);
        CommentDao cmd=new CommentDao();
        cmd.addComment(com);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your comment is added successfully");
        alert.showAndWait();
        clear();
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
