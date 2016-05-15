/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import static menu.LoginViewController.id;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import pi.dao.classes.ProjetDAO;
import pi.entities.Projet;


/**
 * FXML Controller class
 *
 * 
 */
public class AddProjectController implements Initializable {
    @FXML TextField titleText;
   @FXML TextArea descriptionText;
  
   @FXML TextField vdText;
   @FXML TextField budgetText;
   @FXML private ComboBox<String> cmbSecteur;
   private ObservableList<String> options;
   String t1,dm1,desc1,vid1;
   float bdg;
   File file =null;
   FileChooser fileChooser = new FileChooser();
    @FXML
    private AnchorPane AnshorPrincipalr;
    @FXML
    private Button ButtonChoseImageToUpload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      remplirComboBox();
      ButtonChoseImageToUpload.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                   file = fileChooser.showOpenDialog(Main.stage);
                   
                }
            });
    }    
    @FXML
    public void ajouterProjet(ActionEvent event)
    {
        try {
            t1=titleText.getText();
            dm1=cmbSecteur.getValue();
            desc1=descriptionText.getText();
           
            vid1=vdText.getText();
            bdg=Float.parseFloat(budgetText.getText());
            Projet p=new Projet(t1, dm1, desc1, bdg,file.getName() ,vid1, id);
            System.err.println(id);
            ProjetDAO pd=new ProjetDAO();
            pd.addProjet(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your Requested will be processed by the admin");
            alert.showAndWait();
            clear();
            controllUploadImage httpPost = null;
            
            
            
            httpPost = new controllUploadImage();
            
            
            httpPost.setFileNames(new String[]{ file.getPath() });
            httpPost.post();
            System.out.println("=======");
            System.out.println(httpPost.getOutput());
            
            
           
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String title = "added ";
        String message = "You've successfully added a project";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification(title,message,notification);
        tray.showAndDismiss(Duration.seconds(5));
        
        
        
    }
    public void clear()
    {
        titleText.setText("");
        cmbSecteur.setPromptText("Application domain");
        descriptionText.setText("");
        budgetText.setText("");
       
        vdText.setText("");
    }
    @FXML
    public void clearButton(ActionEvent event)
    {
        titleText.setText("");
        cmbSecteur.setValue("Application domain");
        descriptionText.setText("");
        budgetText.setText("");
       
        vdText.setText("");
    }
    public void remplirComboBox()
    {
        options=FXCollections.observableArrayList();
        cmbSecteur.setItems(options);
        options.add("Développement Logiciel");
        options.add("Immobilier");
    }
}
