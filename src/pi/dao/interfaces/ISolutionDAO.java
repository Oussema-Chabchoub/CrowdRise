/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.interfaces;

import pi.entities.Solution;

/**
 *
 * @author Ghassen
 */
public interface ISolutionDAO {
    
    public void addSolution(Solution s);
    public void removeSolution(int id);
    public void updateSolution();
    public void DisplayAllSolution();
    public void rateSolution(int id,int rate);
    
    
}
