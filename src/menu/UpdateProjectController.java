/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static menu.LoginViewController.id;
import static menu.ProjectController.pr;
import static menu.ProjectController.stat;
import pi.dao.classes.ProjetDAO;
import pi.entities.Projet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UpdateProjectController implements Initializable {
   @FXML TextField titleText;
   @FXML TextArea descriptionText;
   @FXML TextField imagetext;
   @FXML TextField vdText;
   @FXML TextField budgetText;
   @FXML private ComboBox<String> cmbSecteur;
   private ObservableList<String> options;
   String t1,dm1,desc1,im1,vid1;
   float bdg;
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirComboBox();
        titleText.setText(pr.getTitreProjet());
        descriptionText.setText(pr.getDescriptionProjet());
        cmbSecteur.setValue(pr.getDomaineProjet());
        budgetText.setText(Float.toString(pr.getBudgetProjet()));
        ProjetDAO p=new ProjetDAO();
        imagetext.setText(p.findPictureById(pr.getId()));
        vdText.setText(p.findVideoById(pr.getId()));
        
          
    } 
    public void remplirComboBox()
    {
        options=FXCollections.observableArrayList();
        cmbSecteur.setItems(options);
        options.add("Développement Logiciel");
        options.add("Immobilier");
    }
       public void UpdateProject(ActionEvent event)
    {
        t1=titleText.getText();
        dm1=cmbSecteur.getValue();
        desc1=descriptionText.getText();
        im1=imagetext.getText();
        vid1=vdText.getText();
        bdg=Float.parseFloat(budgetText.getText());
        Projet p1=new Projet(t1, dm1, desc1, bdg,im1 ,vid1, id);
        System.err.println(id);
        ProjetDAO pd=new ProjetDAO();
        pd.updateProjet(p1, pr.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Requested will be processed by the admin");
        alert.showAndWait();
        ProjectController.stat.afficherProjets();
       
    }
    
}
