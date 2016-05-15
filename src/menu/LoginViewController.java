package menu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Cursor;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import javafx.scene.ImageCursor;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import pi.dao.classes.MembreDAO;
import pi.entities.Membre;

public class LoginViewController implements Initializable {

    @FXML
    private ImageView imgView;

    @FXML
    private GridPane root;

    @FXML
    private TextField txField;

    @FXML
    private PasswordField pwField;

    @FXML
    private Label loginLabel, registerLabel;

    private final ImageEditor imageEditor;
    private final AnimationGenerator animationGenerator;
    public static int id=0;

    public LoginViewController() {
        imageEditor = new ImageEditor();
        animationGenerator = new AnimationGenerator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationGenerator.applyFadeAnimationOn(root, 3000, 0f, 1.0f, null);
        imageEditor.setImageOn(imgView, "/buttons/MaleFemale-512.png", 75, 75);
        setOnKeyPressed();
    }

    @FXML
    private void mouseMovedLoginLabel() {
        animationGenerator.applyFadeAnimationOn(loginLabel, 500, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseMovedRegisterLabel() {
        animationGenerator.applyFadeAnimationOn(registerLabel, 500, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseExitedLoginLabel() {
        animationGenerator.applyFadeAnimationOn(loginLabel, 500, 0.7f, 1.0f, null);
    }

    @FXML
    private void mouseExitedRegisterLabel() {
        animationGenerator.applyFadeAnimationOn(registerLabel, 500, 0.7f, 1.0f, null);
    }

    @FXML
    private void login() {
        if(loginSuccessful())
            animateWhenLoginSuccess();
        else
            animateWhenBadLogin();
    }

    private boolean loginSuccessful() {
        
        MembreDAO m = new MembreDAO();
        id =m.DisplayId(txField.getText(),pwField.getText());
        return m.verfierlogin(txField.getText(), pwField.getText())==1; 
    }

    private void setOnKeyPressed() {
        root.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER) && loginSuccessful())
                animateWhenLoginSuccess();
            else
                animateWhenBadLogin();
        });
    }

    private void animateWhenLoginSuccess() {
        try {
           
            Parent main = FXMLLoader.load(getClass().getResource("sample.fxml"));
                main.getStylesheets().add("css/LoginStyle.css");
            StackPane temp = new StackPane();
            temp.getChildren().add(new ImageView(new Image("/buttons/Checkmark-50.png")));
            animationGenerator.applyFadeAnimationOn(root, 1000, 1.0f, 0f, event -> {
                temp.setOpacity(0);
                Main.stage.setScene(new Scene(temp, 800, 700));
                animationGenerator.applyFadeAnimationOn(temp, 1000, 0f, 1.0f, event1 -> {
                    animationGenerator.applyFadeAnimationOn(temp, 1000, 1.0f, 0f, event2 -> {
                        Main.stage.setScene(new Scene(main, 800, 700));
                        animationGenerator.applyFadeAnimationOn(main, 1000, 0f, 1.0f, null);
                       
        
                          String title = "Connected ";
        String message = "You've successfully connected";
        NotificationType notification = NotificationType.SUCCESS;

        TrayNotification tray = new TrayNotification(title,message,notification);
      
         tray.showAndDismiss(Duration.seconds(5));
                    });
                });
            });
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void animateWhenBadLogin() {
        try {
            StackPane temp = new StackPane();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                root.getStylesheets().add("css/LoginStyle.css");
            temp.getChildren().add(new ImageView(new Image("/buttons/Delete-50.png")));
             animationGenerator.applyFadeAnimationOn(root, 1000, 1.0f, 0f, event -> {
                temp.setOpacity(0f);
                Main.stage.setScene(new Scene(temp, 800, 700));
                    animationGenerator.applyFadeAnimationOn(temp, 1000, 0f, 1.0f, event1 -> {
                        root.setOpacity(0f);
                        Main.stage.setScene(new Scene(root, 800, 700));
                        animationGenerator.applyFadeAnimationOn(root, 1000, 0f, 1.0f, null);
            });
        });
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
   public void handle(MouseEvent event) {
                
                Stage popupRegister;
                Parent root2;
                      
                    popupRegister = new Stage();
                
                try {
                    root2=FXMLLoader.load(getClass().getResource("register.fxml"));
                    root2.getStylesheets().add("css/LoginStyle.css");

                    popupRegister.setScene(new Scene(root2));
                    popupRegister.initModality(Modality.APPLICATION_MODAL);
                    popupRegister.initOwner(registerLabel.getScene().getWindow());
                    popupRegister.showAndWait();
                    
  
                    
                } catch (IOException ex) {
              
                    
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }

}
