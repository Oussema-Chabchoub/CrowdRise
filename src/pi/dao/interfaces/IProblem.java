/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import pi.entities.Problem;
import pi.entities.Projet;
import pi.entities.commentaireprojet;

/**
 *
 * @author User
 */
public interface IProblem {
      
    
    void displayProblem(ObservableList<Problem> listeproblemes);
    
     void addProblem (Problem problem);
    
     void removeProblem (int id) ;
    
     void updateProblem (Problem problem) ;
    
    Problem findProblemBySector (String Sector) ;
    
    Problem findProblemByTitle (String title) ;
    
    String onchangeState (String etat) ;
    ObservableList<Problem> findProblems(String search,int id);
}
    
   

 
    
