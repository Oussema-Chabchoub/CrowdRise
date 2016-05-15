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
import static menu.AllProblemsController.prob;
import static menu.AllProjectsController.pr1;
import static menu.LoginViewController.id;
import pi.dao.classes.CommentDao;
import pi.dao.classes.SolutionDAO;
import pi.entities.Solution;
import pi.entities.commentaireprojet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddSolutionController implements Initializable {

  @FXML TextField contenuSolution;
    @FXML Button ajouterSolution;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    public void ajouterSolution(ActionEvent event)
    { 
        Solution s=new Solution(contenuSolution.getText(), id, prob.getId());
        SolutionDAO sol=new SolutionDAO();
        sol.addSolution(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Solution is added successfully");
        alert.showAndWait();
        clear();
    }
    public void clear()
    {
        contenuSolution.setText("");
    }
     public void clearButton(ActionEvent event)
    { 
                contenuSolution.setText("");

    }
    
}