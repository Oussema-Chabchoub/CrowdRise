package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Cursor;
        import javafx.scene.ImageCursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import static menu.LoginViewController.id;
import pi.dao.classes.MembreDAO;


public class Controller implements Initializable{

    @FXML
    private StackPane stackPane ;

    @FXML
    private BorderPane borderPane;

    @FXML
    protected ImageView browserImage, menuButton, portrait, settings, streams, favorites, share, search, about, logOut ;

    @FXML
    private Label title;

    @FXML
    private VBox menuRoot;

    private Separator[] separators = new Separator[8];

    @FXML
    private Separator sep_1, sep_2, sep_3, sep_4, sep_5, sep_6, sep_7, sep_8 ;
    @FXML
    private AnchorPane AnshorPrincipalr;
    @FXML Label User;

    public void initSeparators() {
        separators[0] = sep_1;
        separators[1] = sep_2;
        separators[2] = sep_3;
        separators[3] = sep_4;
        separators[4] = sep_5;
        separators[5] = sep_6;
        separators[6] = sep_7;
        separators[7] = sep_8;
    }

    private boolean isOpened = false;

    private static final int DEFAULT_STARTING_X_POSITION = 0;
    private static final int DEFAULT_ENDING_X_POSITION = -120;

    private final AnimationGenerator animationGenerator;
    private final ImageEditor imageEditor;
    private Browser browser;

    public Controller() {
        animationGenerator = new AnimationGenerator();
        imageEditor = new ImageEditor();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSeparators();
        setImages();
        closeAnimation();
        MembreDAO m=new MembreDAO();
        User.setText(m.DisplayNameById(id));
    }

    private void setImages() {
         
        imageEditor.setImageOn(menuButton, "/buttons/Menu-50.png", 80, 40);
        imageEditor.setImageOn(browserImage, "/buttons/home.png", 35, 18);
        imageEditor.setImageOn(portrait, "/menu/3848,344x,Ingenieur-commercial1.jpg", 150, 150);
        imageEditor.setRectangularClipOf(portrait, 15, 15);
        imageEditor.setImageOn(settings, "/buttons/43500-200.png", 35, 18);
        imageEditor.setImageOn(streams, "/buttons/noun_3373.png", 35, 18);
        imageEditor.setImageOn(favorites, "/buttons/icon_15557.png", 35, 18);
        imageEditor.setImageOn(share, "/buttons/email-icon-vector-27630.png", 35, 18);
        imageEditor.setImageOn(search, "/buttons/18397-200.png", 35, 18);
        imageEditor.setImageOn(about, "/buttons/Info-50.png", 35, 18);
        imageEditor.setImageOn(logOut, "/buttons/Shutdown-52.png", 35, 18);
    }

    private void openAnimation() {
        animationGenerator.applyTranslateAnimationOn(menuRoot, 500, DEFAULT_ENDING_X_POSITION, DEFAULT_STARTING_X_POSITION);
        animationGenerator.applyTranslateAnimationOn(stackPane, 500, DEFAULT_ENDING_X_POSITION, DEFAULT_STARTING_X_POSITION);
        animationGenerator.applyFadeAnimationOn(portrait, 500, 0.1f, 1.0f, null);
        animateSeparators(0.1f, 1.0f);
        rotateMenuIcon();
        isOpened = true;
    }


    public void closeAnimation() {
        animationGenerator.applyTranslateAnimationOn(menuRoot, 500, DEFAULT_STARTING_X_POSITION, DEFAULT_ENDING_X_POSITION);
        animationGenerator.applyTranslateAnimationOn(stackPane, 500, DEFAULT_STARTING_X_POSITION , DEFAULT_ENDING_X_POSITION);
        animationGenerator.applyFadeAnimationOn(portrait, 500, 1.0f, 0.1f, null);
        animateSeparators(1.0, 0.1f);
        rotateMenuIcon();
        isOpened = false;
    }

    @FXML
    private void setOnMenuClick() {
        if(isOpened)
            closeAnimation();
        else
            openAnimation();
    }

    @FXML
    private void setOnBrowserClicked() {
        closeAnimation();
        title.setText("Home");
        title.setOnMouseClicked(event->{title.setTextFill(Color.RED);});
                if(stackPane.getChildren().contains(browser)) {
            stackPane.getChildren().remove(browser);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 1.0f, 0f, null);
        }else{
            browser = new Browser();
            stackPane.getChildren().add(browser);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }
    }
    
    private void setOnProjectsClicked() {
        closeAnimation();
        if(stackPane.getChildren().contains(this.stackPane)) {
            stackPane.getChildren().remove(this.stackPane);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 1.0f, 0f, null);
        }else{
          
            stackPane.getChildren().add(stackPane);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }
    }
   
    
    
   

    @FXML
    private void profileSelected() {
        try {
            closeAnimation();
            Parent profileView = FXMLLoader.load(getClass().getResource("profile.fxml"));
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(profileView);
            animationGenerator.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void rotateMenuIcon() {
        animationGenerator.applyRotationOn(menuButton, 500, 180f, 1);
    }

    private void animateSeparators(double from, double to) {
        for(Separator separator : separators)
            animationGenerator.applyFadeAnimationOn(separator, 500, from, to, null);
    }

    @FXML
    private void logoutSelected() {
        try {
            Parent loginView = FXMLLoader.load(getClass().getResource("login.fxml"));
            loginView.getStylesheets().add("css/LoginStyle.css");
            animationGenerator.applyFadeAnimationOn(borderPane, 2000, 1.0f, 0f, event -> {
                Main.stage.setScene(new Scene(loginView, 800, 700));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ProjectClikAction(MouseEvent event) {
        title.setText("Your Projects");
        loadFXMLintoPaneData("Project.fxml");
        
    }
    @FXML
    private void ProblemClikAction(MouseEvent event) {
        title.setText("Your Ideas");
        loadFXMLintoPaneData("Problem.fxml");
        
    }
    @FXML
     private void AllProblemsClikAction(MouseEvent event) {
        title.setText("All Ideas");
        loadFXMLintoPaneData("AllProblems.fxml");
        
    }
     @FXML
      private void AllProjectsClikAction(MouseEvent event) {
        title.setText("All Projects");
        loadFXMLintoPaneData("AllProjects.fxml");
        
    } 
        @FXML
      private void StatisticsClikAction(MouseEvent event) {
        title.setText("Statistics");
            loadFXMLintoPaneData("statistique.fxml");
       
        
    }
       @FXML
      private void AllmembersClikAction(MouseEvent event) {
        title.setText("Our Members");
            loadFXMLintoPaneData("members.fxml");    
    }
     
       public void loadFXMLintoPaneData(String name) {
        AnchorPane p = null;

        URL location = Controller.class.getResource(name);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        try {
            p = fxmlLoader.load();
        } catch (IOException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("test");
        }
        AnshorPrincipalr.getChildren().clear();
        AnshorPrincipalr.getChildren().addAll(p);
    }





}
