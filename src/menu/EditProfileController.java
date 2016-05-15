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
import javafx.scene.control.TextField;
import javafx.util.Duration;
import static menu.LoginViewController.id;
import static menu.ProjectController.pr;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import pi.dao.classes.MembreDAO;
import pi.dao.classes.ProjetDAO;
import pi.entities.Membre;
import pi.entities.Projet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EditProfileController implements Initializable {
    @FXML
    private TextField Image;
    @FXML
    private TextField NumTel;
    @FXML
    private TextField Rib;
    @FXML
    private TextField Competence;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Mail;
    @FXML
    private TextField password;
    @FXML
    private TextField Pseudo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MembreDAO m=new MembreDAO();
        Image.setText(m.findPictureById(id));
        Competence.setText(m.findCompetenceById(id));
        Pseudo.setText(m.DisplayNameById(id));
        Nom.setText(m.DisplayNomById(id));
        Prenom.setText(m.DisplayPrenomById(id));
        Mail.setText(m.DisplayMailById(id));
        password.setText(m.DisplayPasswordById(id));
        Rib.setText(m.DisplayRibById(id)+"");
        NumTel.setText(m.DisplayCellById(id)+"");
        
    }    
        public void UpdateMember(ActionEvent event)
    {
       Membre m1=new Membre();
       m1.setNomMembre(Nom.getText());
       m1.setPrenomMembre(Prenom.getText());
       m1.setAdresseMailMembre(Mail.getText());
       m1.setNomUtilisateurMembre(Pseudo.getText());
       m1.setMotDePasseMembre(password.getText());
       m1.setCompetenceMembre(Competence.getText());
       m1.setRibMembre(Integer.parseInt(Rib.getText()));
       m1.setNumTelMembre(Integer.parseInt(NumTel.getText()));
       m1.setImageMembre(Image.getText());
       MembreDAO md=new MembreDAO();
       md.updateMembre(m1, id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your account has been successfully updated ");
        alert.showAndWait();
         String title = "Updated ";
        String message = "You've successfully updated your account";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification(title,message,notification);
        tray.showAndDismiss(Duration.seconds(5));
       

    }
}
