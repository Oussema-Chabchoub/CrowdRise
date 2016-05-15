/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import static com.sun.deploy.config.JREInfo.clear;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.dao.classes.MembreDAO;
import pi.dao.classes.mail;
import pi.entities.Membre;

/**
 * FXML Controller class
 *
 * @author ghassen jenana
 */
public class RegisterController implements Initializable {
 @FXML TextField Nom;
 @FXML TextField Prenom;

 @FXML PasswordField Password;
 @FXML TextField Pseudo;
 @FXML TextField Image;
 @FXML TextField NumTel;
 @FXML TextField Rib;
 @FXML TextField Competence;
 @FXML TextField Mail;
 @FXML ComboBox<String> Pays;
 @FXML DatePicker DateN;
  private ObservableList<String> options;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                remplirComboBox();

        // TODO
    }
     public void ajouterMembre(ActionEvent event) throws IOException
    {   int test =1;MembreDAO p = new MembreDAO();
        String  t1=Nom.getText();
        String  t2=Prenom.getText();
        String  t3=Mail.getText();
        String  t4=Pseudo.getText();
        String  t5=Password.getText();
       
        String  t7=Pays.getValue();
        String  t8=NumTel.getText();
        String  t9=Rib.getText();
        String  t10=Competence.getText();
        String t11=Image.getText();
        //String t12 = Date.toString();
       int a = 0,b=0;
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(t3);
if (controler.matches()){
test=1;
 
}
else {test=0;
Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("email non valide");
        alert1.showAndWait();}
        if (p.verfierMail(t3)==1) {test=0;
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Email existant");
        alert1.showAndWait();
        }
        if (p.verfierPseudo(t4)==1) {test=0;
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Information Dialog");
        alert1.setHeaderText(null);
        alert1.setContentText("Username existant");
        alert1.showAndWait();
        }
       try 
{
 // <<<<<<<<<<<<<<<<<<< ajouter
 a = Integer.parseInt(t8); 
 b= Integer.parseInt(t9);
} 
catch (NumberFormatException ex) 
{ 
JOptionPane.showMessageDialog(null,"Numero de tel et RIB doivent etre de type number et ","Probléme de format",JOptionPane.ERROR_MESSAGE); 
test =0;

}
       Membre m = new Membre(t11,t1,t2,t4,t3,t5,Date.valueOf(DateN.getValue()),t7,a,b,t10);
       
      if(test==1){ p.addMembre(m);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        mail mi =new mail();
        mi.envoyermail(t3);
        alert.setContentText("You have successfully created an acount");
        alert.showAndWait();
        
        clear();
      }
    }
     public void remplirComboBox()
    {
        options=FXCollections.observableArrayList();
        options.add("Tunisie");
        options.add("Liban");
        Pays.setItems(options);
       
    }
    

    
    
}
