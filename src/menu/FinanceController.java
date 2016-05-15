/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.lang.reflect.Member;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import static menu.LoginViewController.id;
import static menu.ProjectController.pr;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import pi.dao.classes.MembreDAO;
import pi.dao.classes.ProjetDAO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FinanceController implements Initializable {

    @FXML Label budget;
   @FXML Label realisation;
   @FXML Slider slider;
   @FXML Label somme;
   @FXML Label credit;
   int a;
    @FXML
    private Button finance1;
    @FXML
    private ImageView finance;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        realisation.setText(Float.toString(pr.getCollecteEnCours()));
        budget.setText(Float.toString(pr.getBudgetProjet()));
        credit.setText(new MembreDAO().getSolde(id)+"");

        
        slider.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               int sd= new MembreDAO().getSolde(id);
               int sb=(int) (pr.getBudgetProjet()-pr.getCollecteEnCours()); 
               if(sd<sb){
                 a=(int) (event.getX()*(sd/120));
                if(a<0)a=0;
                if(a>sd)a=sd;
               somme.setText(a+"");}
                else{
                   
                    a=(int) (event.getX()*(sb/120));
                if(a<0)a=0;
                if(a>sb)a=sb;
               somme.setText(a+"");
                    
                    
                }
            }
        });
    
    
    finance1.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
         if(a!=0)
         {
                new ProjetDAO().finance(id, pr.getId(), a);
             String title = "Financed ";
        String message = "You've successfully financed this project";
        NotificationType notification = NotificationType.SUCCESS;

        TrayNotification tray = new TrayNotification(title,message,notification);
      
         tray.showAndDismiss(Duration.seconds(5));
            
            
         }
         else
         {
         String title = "Cannot Finance ";
        String message = "You can't finance this project ";
        NotificationType notification = NotificationType.WARNING;

        TrayNotification tray = new TrayNotification(title,message,notification);
      
         tray.showAndDismiss(Duration.seconds(5));
         }
            
            
            }
        });
    
    
    
    }
    
    
    
}
