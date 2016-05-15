/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;

import pi.dao.interfaces.IProjetDAO;
import pi.entities.Projet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.entities.Problem;
import pi.technique.DataSource;


/**
 *
 * @author User
 */
public class ProjetDAO implements IProjetDAO{
    private Connection connection;
    
    public ProjetDAO()
    {
                connection = DataSource.getInstance().getConnection();

        
    }


    @Override
    public void addProjet(Projet p) {
        
    try {
            String req = "insert into projet (titreProjet,domaineProjet,descriptionProjet,budgetProjet,imageProjet,videoProjet,	membre_id) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getTitreProjet());
            ps.setString(2, p.getDomaineProjet());
            ps.setString(3, p.getDescriptionProjet());
            ps.setFloat(4, p.getBudgetProjet());
            ps.setString(5, p.getImageProjet());
            ps.setString(6, p.getVideoProjet());
            ps.setInt(7,p.getId_membre());
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(SolutionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void removeProjet(int id) {
     String requete = "delete from projet where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    public void AcceptProjet(int id) {
     String requete = "update projet set etatProjet='Accepté' where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Accepté");
        } catch (SQLException ex) {
            System.out.println("erreur lors du traitement de la demande " + ex.getMessage());
        }
    }
    public void RefuseProjet(int id) {
     String requete = "update projet set etatProjet='Refusé' where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Refusé");
        } catch (SQLException ex) {
            System.out.println("erreur lors du traitement de la demande " + ex.getMessage());
        }
    }

    @Override
    public void updateProjet( Projet p,int id) {
    String requete = "update projet set titreProjet=?, domaineProjet=?,descriptionProjet=?, budgetProjet=?, imageProjet=?,videoProjet=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, p.getTitreProjet());
            ps.setString(2, p.getDomaineProjet());
            ps.setString(3, p.getDescriptionProjet());
            ps.setFloat(4, p.getBudgetProjet());
            ps.setString(5, p.getImageProjet());
            ps.setString(6, p.getVideoProjet());
            ps.setInt(7, id);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    
    }
    
  

   
     public  void DisplayAllProjetBO(ObservableList<Projet> listeProjets) {
        String requete = "select id,titreProjet, domaineProjet,descriptionProjet, budgetProjet,collecteEnCours,etatProjet from projet order by dateProject DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                listeProjets.add(new Projet(resultat.getInt(1),resultat.getString(2), resultat.getString(3), resultat.getString(4),resultat.getFloat(5), resultat.getFloat(6),resultat.getString(7)));
                
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des projets " + ex.getMessage());
        }
    }
     
    public ObservableList<Projet> findProjets(String search,int id) {
        ObservableList<Projet> listeProjets = FXCollections.observableArrayList();
        String requete = "select id,titreProjet, domaineProjet,descriptionProjet, budgetProjet,collecteEnCours,etatProjet from projet where domaineProjet like ? AND membre_id=? AND etatProjet='Accepté'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,'%'+ search +'%');
            ps.setInt(2, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            listeProjets.add(new Projet(resultat.getInt(1),resultat.getString(2), resultat.getString(3), resultat.getString(4),resultat.getFloat(5), resultat.getFloat(6),resultat.getString(7)));

                
            }
            return listeProjets;
        } catch (SQLException ex) {
            System.out.print("erreur : "+ex.getMessage());
            return null;
        }
    }
     public ObservableList<Projet> findAllProjets(String search) {
        ObservableList<Projet> listeProjets = FXCollections.observableArrayList();
        String requete = "select id,titreProjet, domaineProjet,descriptionProjet, budgetProjet,collecteEnCours,etatProjet from projet where domaineProjet like ? AND  etatProjet='Accepté' order by dateProject DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,'%'+ search +'%');
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            listeProjets.add(new Projet(resultat.getInt(1),resultat.getString(2), resultat.getString(3), resultat.getString(4),resultat.getFloat(5), resultat.getFloat(6),resultat.getString(7)));

                
            }
            return listeProjets;
        } catch (SQLException ex) {
            System.out.print("erreur : "+ex.getMessage());
            return null;
        }
    }
   
    public void DisplayAll(ObservableList<Projet> listeProjets) {
         MembreDAO m=new MembreDAO();
        String requete = "select id,titreProjet,domaineProjet,descriptionProjet,budgetProjet,collecteEnCours,membre_id from projet where etatProjet='Accepté' order by dateProject DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
        while (resultat.next()) {
                
              listeProjets.add(new Projet(resultat.getInt(1),resultat.getString(2), resultat.getString(3),resultat.getString(4),resultat.getFloat(5),resultat.getFloat(6),m.DisplayNameById(resultat.getInt(7))));
             
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des projets " + ex.getMessage());
        }
    }
     @Override
    public void DisplayById(ObservableList<Projet> listeProjets,int id) {
        String requete = "select id,titreProjet,domaineProjet,descriptionProjet,budgetProjet,collecteEnCours from projet where membre_id=? AND etatProjet='Accepté' order by dateProject DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            listeProjets.add(new Projet(resultat.getInt(1),resultat.getString(2), resultat.getString(3),resultat.getString(4),resultat.getFloat(5),resultat.getFloat(6)));

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Projet findProjetById(int id) {
Projet m = new Projet();
        String requete = "select * from projet where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            MembreDAO me=new MembreDAO();
            while (resultat.next()) {
                m.setId(resultat.getInt(1));
                m.setMembre(me.findMemberById(resultat.getInt(2)));
                m.setDomaineProjet(resultat.getString(3));
                m.setDescriptionProjet(resultat.getString(4));
                m.setBudgetProjet(resultat.getInt(5));
                m.setCollecteEnCours(resultat.getInt(6));
                m.setNbrInteresses(resultat.getInt(7));
                m.setImageProjet(resultat.getString(8));
                m.setVideoProjet(resultat.getString(9));
                m.setTitreProjet(resultat.getString(10));
                m.setEtatProjet(resultat.getString(11));
                m.setDateProject(resultat.getDate(12));
                
            }
            return m;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du membre " + ex.getMessage());
            return null;
        }    }
    
     public String findPictureById(int id) {
        String m = null;
        String requete = "select imageProjet from projet where id=?";
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
    
     public String findVideoById(int id) {
        String m = null;
        String requete = "select videoProjet from projet where id=?";
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
    @Override
    public ObservableList<Projet> DisplayMyProjects(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finance(int idu,int idp, int fond) {
    
        String requete = "select solde from user where id=?";
        String requete1 = "select collecteencours from projet where id=? ";
        
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idu);

            PreparedStatement ps1 = connection.prepareStatement(requete1);
            ps1.setInt(1, idp);

            ResultSet resultat = ps.executeQuery();
            ResultSet resultat1 = ps1.executeQuery();
            int a = 0 ;
            while (resultat.next()) {
             a = resultat.getInt(1);
            }
            int b = 0 ;
            while (resultat1.next()) {
             b= resultat1.getInt(1);
            }
        int aa=a-fond;
        int bb=b+fond;
            System.out.println(aa+"   "+ bb);
            String req= "update user set solde=? where id=?"  ;
        String req1="update projet set collecteencours=? where id = ?";
        
        PreparedStatement pss= connection.prepareStatement(req);
                pss.setInt(1, aa);
                pss.setInt(2, idu);
                pss.executeUpdate();
        PreparedStatement pcc =connection.prepareStatement(req1);
        pcc.setInt(1, bb);
        pcc.setInt(2, idp);
        pcc.executeUpdate();
        
            
            
            
            
            
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du membre " + ex.getMessage());
            
        }
        
    
    }
}
