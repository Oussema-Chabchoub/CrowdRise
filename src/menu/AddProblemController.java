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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import pi.dao.classes.ProblemClass;
import pi.entities.Problem;
import static menu.LoginViewController.id;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author User
 */
public class AddProblemController implements Initializable {

   @FXML
    private TextField title_problem_input;
    @FXML
    private TextField profil_inupt;
    @FXML
    private ComboBox<String> domain_input;
    @FXML
    private TextArea description_input;
    @FXML
    private Button post_input;
    @FXML
    private Button clear;
    private ObservableList<String> options;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirComboBox();
    }  
      public void ajouterProbleme(ActionEvent event)
    {
        
        Problem pb=new Problem(title_problem_input.getText(),domain_input.getValue(),description_input.getText(),profil_inupt.getText(),id);
        ProblemClass pbDAO=new ProblemClass();
        pbDAO.addProblem(pb);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Requested will be processed by the admin");
        alert.showAndWait();
        clear();
        String title = "added ";
        String message = "You've successfully added a problem";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification(title,message,notification);
        tray.showAndDismiss(Duration.seconds(5));
      
    }
    public void remplirComboBox()
    {
        options=FXCollections.observableArrayList();
        options.add("Développement Logiciel");
        options.add("Immobilier");
        domain_input.setItems(options);
       
    }
     public void clear()
    {
        title_problem_input.setText("");
        domain_input.setPromptText("Application domain");
        description_input.setText("");
        profil_inupt.setText("");
        
    }
    public void clearButton(ActionEvent event)
    {
        title_problem_input.setText("");
        domain_input.setPromptText("Application domain");
        description_input.setText("");
        profil_inupt.setText("");
    }
    
    
}
