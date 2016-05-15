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
import javafx.util.Duration;
import static menu.ProblemController.pr;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import pi.dao.classes.ProblemClass;
import pi.entities.Problem;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UpdateProblemController implements Initializable {

    @FXML TextField titleProblem;
    @FXML TextField descriptionProblem;
    @FXML TextField AskedProfile;
    @FXML private ComboBox<String> cmbSecteur;
    private ObservableList<String> options;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirComboBox();
        titleProblem.setText(pr.getTitre());
        descriptionProblem.setText(pr.getDescProbleme());
        AskedProfile.setText(pr.getProfilDemande());
    }   
     public void remplirComboBox()
    {
        options=FXCollections.observableArrayList();
        cmbSecteur.setItems(options);
        options.add("Développement Logiciel");
        options.add("Immobilier");
    }
     public void UpdateProblem(ActionEvent event)
    {
        Problem p=new Problem();
        ProblemClass pd=new ProblemClass();
        p.setTitre(titleProblem.getText());
        p.setDomaineProbleme(cmbSecteur.getValue());
        p.setProfilDemande(AskedProfile.getText());
        p.setDescProbleme(descriptionProblem.getText());
        pd.updateProblem(p, pr.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your problem is updated successfully");
        alert.showAndWait();
        String title = "updated ";
        String message = "You've successfully updated this problem";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification(title,message,notification);
        tray.showAndDismiss(Duration.seconds(5));

    }
}
