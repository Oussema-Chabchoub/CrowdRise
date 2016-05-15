/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;
import java.sql.Connection;
import java.sql.Date;
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
import pi.entities.commentaireprojet;
import pi.dao.interfaces.ICommentDao;
import pi.entities.Projet;
import pi.entities.Membre;
import pi.technique.DataSource;
/**
 *
 * @author jappouni
 */
public class CommentDao implements ICommentDao{
     private Connection connection;

    public CommentDao() {
        connection = DataSource.getInstance().getConnection();
    }
     @Override
    public void addComment(commentaireprojet commentaireprojet) {
        String req = "insert into comment (body,thread_id,author_id) values (?,?,?)";
         try {
             PreparedStatement ps = connection.prepareStatement(req);
             ps.setString(1,commentaireprojet.getContenuCommentaireProjet());
             ps.setInt(2,commentaireprojet.getProjet().getId());
             
             ps.setInt(3, commentaireprojet.getId_membre());
             
             ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");

         } catch (SQLException ex) {
             Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
         }
            
    }

    public void updateComment(commentaireprojet commentaireprojet,int id) {
    String requete = "update comment set body=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(2, id);
            ps.setString(1, commentaireprojet.getContenuCommentaireProjet());
            System.out.println(commentaireprojet.getContenuCommentaireProjet());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void removeCommentById(int id) {
                String requete = "delete from comment where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    public void removeVoteById(int id) {
                String requete = "delete from vote where comment_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }


    public commentaireprojet findCommentById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<commentaireprojet> DisplayAllComments(ObservableList <commentaireprojet> listeComment) {
   List<commentaireprojet> listcomment = new ArrayList<>();
        String requete = "select * from comment";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            ICommentDao commentDao = new CommentDao();
            MembreDAO m=new MembreDAO();
            ProjetDAO p=new ProjetDAO();
            while (resultat.next()) {
                commentaireprojet commentaireprojet = new commentaireprojet();
                listeComment.add(new commentaireprojet(resultat.getInt(1),
                resultat.getString(2),
                        resultat.getDate(3),
                        m.findMemberById(resultat.getInt(4)),
                p.findProjetById(resultat.getInt(5))
                ));

                
            }
            return listcomment;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }

    }
     
    public ObservableList<commentaireprojet> DisplayById(int id) {
                ObservableList<commentaireprojet> commentaireprojet = FXCollections.observableArrayList();
                MembreDAO m=new MembreDAO();
                ProjetDAO p=new ProjetDAO();
                
        String requete = "select * from comment where thread_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            commentaireprojet.add(new commentaireprojet(resultat.getInt(1),
                resultat.getString(2),
                        resultat.getDate(3),
                        m.findMemberById(resultat.getInt(4)),
                p.findProjetById(resultat.getInt(5))));

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaireprojet;
     }
   
     
    public ObservableList<commentaireprojet> DisplayCommentById(int id) {
                ObservableList<commentaireprojet> commentaireprojet = FXCollections.observableArrayList();
                MembreDAO m=new MembreDAO();
                ProjetDAO p=new ProjetDAO();
                
        String requete = "select id,body,author_id from comment where thread_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
           
           commentaireprojet.add(new commentaireprojet(resultat.getInt(1), resultat.getString(2),m.DisplayNameById(resultat.getInt(3))));
               

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commentaireprojet;
     }

    

    
    
}
