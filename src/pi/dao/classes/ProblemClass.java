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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.dao.interfaces.IProblem;
import pi.entities.Problem;
import pi.entities.Projet;
import pi.technique.DataSource;

/**
 *
 * @author User
 */
public class ProblemClass implements IProblem{
    
    private Connection connection ;
    
    public ProblemClass()
    { connection = DataSource.getInstance().getConnection();
    }
    

 

    @Override
    public void addProblem(Problem problem) {
        
         try {
            String req = "insert into probleme (titre,domaineProbleme,descProbleme,profilDemande,membre_id) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            
            ps.setString(1, problem.getTitre());
            ps.setString(2, problem.getDomaineProbleme());
            ps.setString(3, problem.getDescProbleme());
            ps.setString(4, problem.getProfilDemande());
            ps.setInt(5, problem.getMembre_id());

          
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProblemClass.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void removeProblem(int id) {
        String requete = "delete from probleme where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Probleme supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void updateProblem(Problem problem) {
          String requete = "update probleme set titre=? where descProbleme=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, problem.getDescProbleme());
            ps.setString(2, problem.getTitre());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public Problem findProblemBySector(String Sector) {
           Problem problem = new Problem();
        String requete = "select titre,descProbleme from probleme where domaineProbleme=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, Sector);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              
                problem.setDescProbleme(resultat.getString(1));
                problem.setTitre(resultat.getString(2));
            }
            return problem;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche d'un probleme " + ex.getMessage());
            return null;
        }
    }
    
     public ObservableList<Problem> DisplayById(int id) {
        ObservableList<Problem> listeProblemes = FXCollections.observableArrayList();
        String requete = "select titre,descProbleme,filename from probleme where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
             listeProblemes.add(new Problem(resultat.getString(1), resultat.getString(2),resultat.getString(3)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProblemClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeProblemes;
        
    }
     public String findPictureById(int id) {
        String m = null;
        String requete = "select filename from probleme where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              m= resultat.getString(1);
            }
            return m;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du membre " + ex.getMessage());
            return null;
        }    }
     public int findMembreId(int id) {
        int m = 0;
        String requete = "select membre_id from probleme where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
              m= resultat.getInt(1);
            }
            return m;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du membre " + ex.getMessage());
            
        }    
        return 0;
     }
    @Override
    public Problem findProblemByTitle(String title) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String onchangeState(String etat) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayProblem(ObservableList<Problem> listeproblemes  ) {
        MembreDAO m=new MembreDAO();
        String requete = "SELECT id, titre, domaineProbleme , descProbleme ,profilDemande,membre_id FROM `probleme` where etat='Accepté'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
        while (resultat.next()) {
                
              listeproblemes.add(new Problem(resultat.getInt(1),resultat.getString(2), resultat.getString(3),resultat.getString(4),resultat.getString(5),m.DisplayNameById(resultat.getInt(6))));

            }
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des problemes " + ex.getMessage());
        }
    }
     public void displayProblemByid(ObservableList<Problem> listeproblemes,int id  ) {
         String requete = "SELECT id, titre, domaineProbleme , descProbleme ,profilDemande FROM `probleme` where membre_id=? AND etat='Accepté'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            listeproblemes.add(new Problem(resultat.getInt(1),resultat.getString(2), resultat.getString(3),resultat.getString(4),resultat.getString(5))); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProblemClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AcceptProblem(int id) {
     String requete = "update probleme set etat='Accepté' where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Accepté");
        } catch (SQLException ex) {
            System.out.println("erreur lors du traitement de la demande " + ex.getMessage());
        }
    }
   public void RefuseProblem(int id) {
     String requete = "update probleme set etat='Refusé' where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Accepté");
        } catch (SQLException ex) {
            System.out.println("erreur lors du traitement de la demande " + ex.getMessage());
        }
    }
    @Override
    public ObservableList<Problem> findProblems(String search,int id) {
        ObservableList<Problem> listeProblemes = FXCollections.observableArrayList();
        String requete = "select id,titre, domaineProbleme , descProbleme ,profilDemande from probleme where domaineProbleme like ? AND membre_id=? AND etat='Accepté'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,'%'+ search +'%');
            ps.setInt(2, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                listeProblemes.add(new Problem(resultat.getInt(1),resultat.getString(2),resultat.getString(3), resultat.getString(4), resultat.getString(5)));

                
            }
            return listeProblemes;
        } catch (SQLException ex) {
            System.out.print("erreur : "+ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Problem> findAllProblems(String search) {
        ObservableList<Problem> listeProblemes = FXCollections.observableArrayList();
        String requete = "select id,titre, domaineProbleme , descProbleme ,profilDemande from probleme where domaineProbleme like ? AND  etat='Accepté'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,'%'+ search +'%');
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                listeProblemes.add(new Problem(resultat.getInt(1),resultat.getString(2),resultat.getString(3), resultat.getString(4), resultat.getString(5)));

                
            }
            return listeProblemes;
        } catch (SQLException ex) {
            System.out.print("erreur : "+ex.getMessage());
            return null;
        }
    }
    public void updateProblem( Problem p,int id) {
    String requete = "update probleme set titre=?,domaineProbleme=?,descProbleme=?,profilDemande=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, p.getTitre());
            ps.setString(2, p.getDomaineProbleme());
            ps.setString(3, p.getDescProbleme());
            ps.setString(4,p.getProfilDemande() );
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    

   

    
    
}