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
import static menu.AllProblemsController.sol;
import static menu.ProjectController.cmp;
import static menu.ProjectController.pr;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import pi.dao.classes.CommentDao;
import pi.dao.classes.SolutionDAO;
import pi.entities.Solution;
import pi.entities.commentaireprojet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UpdateSolutionController implements Initializable {

    @FXML TextField contenuSolution;
    @FXML Button update;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenuSolution.setText(sol.getDescriptionSolutionProbleme());
        System.out.println("aaa"+sol.getDescriptionSolutionProbleme());
    }    
     public void ModifierSolution(ActionEvent event)
    { 
        Solution s=new Solution();
        s.setDescriptionSolutionProbleme(contenuSolution.getText());
        SolutionDAO sd=new SolutionDAO();
        sd.updateContentSolution(s,sol.getId());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your solution is updated successfully");
        alert.showAndWait();
        String title = "Solved ";
        String message = "You've successfully updated this solution";
        NotificationType notification = NotificationType.SUCCESS;

        TrayNotification tray = new TrayNotification(title,message,notification);
        
       
         
    }
}
