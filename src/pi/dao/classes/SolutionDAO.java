/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.dao.interfaces.ISolutionDAO;
import pi.entities.Solution;
import pi.entities.commentaireprojet;
import pi.technique.DataSource;

/**
 *
 * @author Ghassen
 */
public class SolutionDAO implements ISolutionDAO{

    private Connection connection;

    public SolutionDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public void addSolution(Solution solution) {
        
        try {
            String req = "insert into SolutionProbleme (descriptionSolutionProbleme,membre_id,probleme_id) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, solution.getDescriptionSolutionProbleme());
            ps.setInt(2, solution.getMembre_id());
            ps.setInt(3, solution.getProbleme_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SolutionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public void removeSolutionByProblem(int id) {
    String requete = "delete from solutionprobleme where probleme_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("solution supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void updateSolution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void rateSolution(int id,int rate)
    {
        String requete = "update solutionprobleme set rate=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, rate);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("solution rated!");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        
    }
    public void updateContentSolution(Solution solution,int id) {
    String requete = "update solutionprobleme set descriptionSolutionProbleme=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(2, id);
            ps.setString(1, solution.getDescriptionSolutionProbleme());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    
    @Override
    public void DisplayAllSolution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ObservableList<Solution> DisplaySolutionById(int id) {
        ObservableList<Solution> Solutions = FXCollections.observableArrayList();
                MembreDAO m=new MembreDAO();
                
        String requete = "select id,descriptionSolutionProbleme,membre_id,rate from solutionprobleme where probleme_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
               Solution s=new Solution(resultat.getInt(1),resultat.getString(2),m.DisplayNameById(resultat.getInt(3)));
               s.setRate(resultat.getInt(4));
               s.setMembre_id(resultat.getInt(3));
            Solutions.add(s);
               

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Solutions;    }

    @Override
    public void removeSolution(int id) {
        String requete = "delete from solutionprobleme where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("solution supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }    }
    
}
