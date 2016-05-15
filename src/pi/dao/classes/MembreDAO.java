
package pi.dao.classes;


import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.dao.interfaces.IMember;
import pi.entities.Membre;
import pi.entities.Projet;
import pi.technique.DataSource;
/**
 *
 * @author USER
 */
public class MembreDAO implements IMember {
private Connection connection;

    public MembreDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public void addMembre(pi.entities.Membre membre) {
       
    
        String requete = "insert into user (username,nomMembre,prenomMembre,email,password,numTelMembre,ribMembre,competenceMembre,paysMembre,imageMembre,roles,dateNaissanceMembre,email_canonical,enabled,locked,expired,credentials_expired,username_canonical) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, membre.getNomUtilisateurMembre());
            ps.setString(2, membre.getNomMembre());
            ps.setString(3, membre.getPrenomMembre());
         ps.setString(18,  membre.getNomUtilisateurMembre());
          
       ps.setDate(12, membre.getDateNaissanceMembre());
           ps.setInt(15, 0);
            ps.setInt(16, 0);
            ps.setInt(17, 0);
         ps.setInt(14, 1);
             ps.setString(13, membre.getAdresseMailMembre());
           
             ps.setString(11, "a:0:{}");
            ps.setInt(6, membre.getNumTelMembre());
            
            ps.setInt(7, membre.getRibMembre());
            ps.setString(8, membre.getCompetenceMembre());
           
            
            
            ps.setString(5, membre.getMotDePasseMembre());
            ps.setString(4, membre.getAdresseMailMembre());
             ps.setString(10, membre.getImageMembre());
                        ps.setString(9, membre.getPaysMembre());

            ps.executeUpdate();
             
            System.out.println("Ajout effectué avec succès");
            pdf p=new pdf();
            p.creerPdf();
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        } catch (DocumentException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }
@Override
    public void removeMemberById(int id) {
        String requete = "delete from member where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Member supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
    }
    @Override
    public void updatMembre(pi.entities.Membre membre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pi.entities.Membre> findAll() {
        List<pi.entities.Membre> listMember = new ArrayList<>();
        String requete = "select * from member";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Membre m = new Membre();
                m.setId(resultat.getInt(1));
                m.setNomMembre(resultat.getString(2));
                m.setPrenomMembre(resultat.getString(3));
                m.setNomUtilisateurMembre(resultat.getString(4));
                m.setAdresseMailMembre(resultat.getString(5));
                m.setMotDePasseMembre(resultat.getString(6));
                m.setVilleNaissanceMembre(resultat.getString(7));
                m.setDateNaissanceMembre(resultat.getDate(8));
                m.setNationaliteMembre(resultat.getString(9));
                m.setAdresseMembre(resultat.getString(10));
                m.setVilleMembre(resultat.getString(11));
                m.setPaysMembre(resultat.getString(12));
                m.setNumTelMembre(resultat.getInt(13));
                m.setMotivationMembre(resultat.getString(14));
                m.setEtatCompteMembre(resultat.getInt(15));
                m.setTypeCompteMembre(resultat.getString(16));
                m.setRibMembre(resultat.getInt(17));
                m.setCompetenceMembre(resultat.getString(18));
                m.setCvMembre(resultat.getString(19));             
                m.setSecteurActiveMembre(resultat.getString(20));
                m.setSecteurGeoMembre(resultat.getString(21));

            

                
                
                listMember.add(m);
            }
            return listMember;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Membre " + ex.getMessage());
            return null;
        }
    }
      @Override
    public Membre findMemberById(int id) {
        Membre m = new Membre() ;
        String requete = "select * from user where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                m.setId(resultat.getInt(1));
                           }
            return m;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Membre " + ex.getMessage());
            return null;
        }
    }
     public String findMemberNameById(int id) {
        String m = null ;
        String requete = "select username from user where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                m=resultat.getString(1);
                
                           }
            return m;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Membre " + ex.getMessage());
            return null;
        }
    }
    public  int DisplayId(String Username, String Password)
    {
        int id=0;
            String requete = "select id from user where username=? AND password=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setString(1, Username);
                        ps.setString(2, Password);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            id=resultat.getInt(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
      public  String DisplayNameById(int id)
    {
        String name=null;
            String requete = "select username from user where id=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            name=resultat.getString(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
        
       public  String DisplayNomById(int id)
    {
        String pic=null;
            String requete = "select nomMembre from user where id=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            pic=resultat.getString(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;
    }
          public  String DisplayPrenomById(int id)
    {
        String pic=null;
            String requete = "select prenomMembre from user where id=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            pic=resultat.getString(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;
    }
           public  String DisplayMailById(int id)
    {
        String pic=null;
            String requete = "select email from user where id=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            pic=resultat.getString(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;
    }
    public  String DisplayPasswordById(int id)
    {
        String pic=null;
            String requete = "select password  from user where id=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            pic=resultat.getString(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;
    }
    public  int DisplayRibById(int id)
    {
        int pic=0;
            String requete = "select ribMembre  from user where id=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            pic=resultat.getInt(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;
    }
     public  int DisplayCellById(int id)
    {
        int pic=0;
            String requete = "select numTelMembre  from user where id=?";       
            try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, id);

            ResultSet resultat = ps.executeQuery();
           while (resultat.next()) {
            pic=resultat.getInt(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pic;
    }
    @Override
    public int verfierlogin(String Username, String Password) {
         //To change body of generated methods, choose Tools | Templates.
     String requete = "select * from user";
     Statement statement = null;
    try {
        statement = connection.createStatement();
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
            ResultSet resultat = null;
    try {
        resultat = statement.executeQuery(requete);
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
        while (resultat.next()) {
            int bann=resultat.getInt(10);
            String LoginRecup=resultat.getString(2);
            String MPRecup=resultat.getString(8);
            
            if (Username.equals(LoginRecup) && Password.equals(MPRecup ) && bann==0  )
            {
              
                return 1;
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    
return 0;
    }

    public void displayMembre(ObservableList<Membre> MembreList) {
         
        String requete = "select id,nomMembre,prenomMembre,email,numTelMembre,ribMembre from user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                
                MembreList.add(new Membre(resultat.getInt(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),resultat.getInt(5), resultat.getInt(6)));
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Membre " + ex.getMessage());
        }
    } 
    
     public String findPictureById(int id) {
        String m = null;
        String requete = "select imageMembre from user where id=?";
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
    public void bannMembre(int id) {
         
    }

    @Override
    public int verfierPseudo(String Pseudo) {
          String pic=null;
            String requete = "select username from user where username=?";       
            
            PreparedStatement ps = null;
    try {
        ps = connection.prepareStatement(requete);
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        ps.setString(1, Pseudo);
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

            ResultSet resultat = null;
    try {
        resultat = ps.executeQuery();
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        while (resultat.next()) {
            
            if( Pseudo == null ? resultat.getString(1) == null : Pseudo.equals(resultat.getString(1)))
                return 1;
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
      return 0;  } 

    @Override
    public int getSolde(int id) {
    
    
        String requete = "select solde from user where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                return(resultat.getInt(1));
                           }
            

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Membre " + ex.getMessage());
            return -1;
        }
        
        return -1;
    
    
       
    }
     public String findCompetenceById(int id) {
        String m = null;
        String requete = "select competenceMembre from user where id=?";
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
    
     public ObservableList<Membre> findAllMembers(String search) {
        ObservableList<Membre> listeMembres = FXCollections.observableArrayList();
        String requete = "select id,nomMembre,prenomMembre,email,numTelMembre,ribMembre from user where nomMembre like ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,'%'+ search +'%');
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                listeMembres.add(new Membre(resultat.getInt(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),resultat.getInt(5), resultat.getInt(6)));
            }
            return listeMembres;
        } catch (SQLException ex) {
            System.out.print("erreur : "+ex.getMessage());
            return null;
        }
    }
    
    public void updateMembre( Membre m,int id) {
    String requete = "update user set nomMembre=?, prenomMembre=?,email=?,username=?, password=?,competenceMembre=?,ribMembre=?,numTelMembre=?,imageMembre=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, m.getNomMembre());
            ps.setString(2, m.getPrenomMembre());
            ps.setString(3, m.getAdresseMailMembre());
            ps.setString(4, m.getNomUtilisateurMembre());
            ps.setString(5, m.getMotDePasseMembre());
            ps.setString(6, m.getCompetenceMembre());
            ps.setInt(7, m.getRibMembre());
            ps.setInt(8, m.getNumTelMembre());
            ps.setString(9,m.getImageMembre());
            ps.setInt(10, id);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    
    }
       
     public int verfierMail(String Pseudo) {
          String pic=null;
            String requete = "select email from user where email=?";       
            
            PreparedStatement ps = null;
    try {
        ps = connection.prepareStatement(requete);
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        ps.setString(1, Pseudo);
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

            ResultSet resultat = null;
    try {
        resultat = ps.executeQuery();
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        while (resultat.next()) {
            
            if( Pseudo == null ? resultat.getString(1) == null : Pseudo.equals(resultat.getString(1)))
                return 1;
            
        }
    } catch (SQLException ex) {
        Logger.getLogger(MembreDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
      return 0;  } 


}