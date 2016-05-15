/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import static menu.LoginViewController.id;
import notification.notification.NotificationType;
import notification.notification.TrayNotification;
import org.controlsfx.control.Rating;
import pi.dao.classes.MembreDAO;
import pi.dao.classes.ProblemClass;
import pi.dao.classes.SolutionDAO;
import pi.entities.Problem;
import pi.entities.Solution;
import pi.entities.commentaireprojet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AllProblemsController implements Initializable {

    private ObservableList<Problem> listeProblemes1;
   @FXML TableView<Problem> tblViewProbleme;
      @FXML TableView<Solution> tblViewSolutions;

   @FXML private TableColumn<Problem,String> titlePb;
   @FXML private TableColumn<Problem,String> domainPb;
   @FXML private TableColumn<Problem,String> profilPb;
   @FXML private TableColumn<Problem,String> postedBy;
    @FXML private TableColumn<Solution,String> PostedByS;
   @FXML private TableColumn<Solution,String> ContentS;
   @FXML private TableColumn<Solution,Integer> updateSolution;
   @FXML TextField rechercherPb;
   @FXML private Label description_problem;
   @FXML private Label title_problem;
   @FXML ImageView imgP;
   @FXML TextField desc;
   @FXML private Button refresh;
   @FXML private ImageView add_button_solution;
   @FXML private TableColumn<Solution,Integer> Rate;
   public static Problem prob;
   public static Solution sol;
   @FXML private ImageView editProfile;
   Image edit;

    Image del1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  edit=new javafx.scene.image.Image("/buttons/edit.png");

   afficherProblemes();
       ListViewEvent();
         add_button_solution.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage popupAddProblem ;
                Parent root2;
                      
                    popupAddProblem = new Stage();
                
                try {
                    prob=tblViewProbleme.getSelectionModel().getSelectedItem();
                    root2=FXMLLoader.load(getClass().getResource("AddSolution.fxml"));
                                        root2.getStylesheets().add("css/LoginStyle.css");

                    popupAddProblem.setScene(new Scene(root2));
                    popupAddProblem.initModality(Modality.APPLICATION_MODAL);
                    popupAddProblem.initOwner(add_button_solution.getScene().getWindow());
                    popupAddProblem.showAndWait();
                    
                } catch (IOException ex) {
              
                    
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
          editProfile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage popupeditProfile ;
                Parent root2;
                      
                    popupeditProfile = new Stage();
                
                try {
                    
                    root2=FXMLLoader.load(getClass().getResource("editProfile.fxml"));
                    root2.getStylesheets().add("css/LoginStyle.css");

                    popupeditProfile.setScene(new Scene(root2));
                    popupeditProfile.initModality(Modality.APPLICATION_MODAL);
                    popupeditProfile.initOwner(editProfile.getScene().getWindow());
                    popupeditProfile.showAndWait();
                    
                } catch (IOException ex) {
              
                    
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
    
    }  
    public void ModifierSolution() {
          
        try {
            Callback<TableColumn<Solution, String>, TableCell<Solution, String>> cellFactory = //
                    new Callback<TableColumn<Solution, String>, TableCell<Solution, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Solution, String> param) {
                            final TableCell<Solution, String> cell = new TableCell<Solution, String>() {
                                
                            javafx.scene.control.Button btnUpdateSol = new javafx.scene.control.Button("Update");
                           
                             @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    System.out.println("aa"+item);
                                    if (empty) {
                                            
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btnUpdateSol.setGraphic(new ImageView(edit));
                                       btnUpdateSol.setOnAction((ActionEvent event) -> {
                                            Stage popupUpdateSol ;
                                                Parent root2;

                                                    popupUpdateSol = new Stage();

                                                try {
                                                    sol=tblViewSolutions.getSelectionModel().getSelectedItem();
                                                    System.out.println("looo"+tblViewSolutions.getSelectionModel().getSelectedItem());
                                                    root2=FXMLLoader.load(getClass().getResource("updateSolution.fxml"));
                                                    root2.getStylesheets().add("css/LoginStyle.css");

                                                    
                                                    popupUpdateSol.setScene(new Scene(root2));
                                                    popupUpdateSol.initModality(Modality.APPLICATION_MODAL);
                                                    popupUpdateSol.initOwner(btnUpdateSol.getScene().getWindow());
                                                    popupUpdateSol.showAndWait();

                                                } catch (IOException ex) {


                                                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            
                                           
                                        });
                                        setGraphic(btnUpdateSol);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            //updateSolution.setCellFactory();
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }
      public void afficherProblemes()
    {
        listeProblemes1=FXCollections.observableArrayList();
        ProblemClass pb=new ProblemClass();
        tblViewProbleme.setItems(listeProblemes1);
        pb.displayProblem(listeProblemes1);
        titlePb.setCellValueFactory(new PropertyValueFactory<>("titre"));
        domainPb.setCellValueFactory(new PropertyValueFactory<>("domaineProbleme"));
        profilPb.setCellValueFactory(new PropertyValueFactory<>("profilDemande"));
        postedBy.setCellValueFactory(new PropertyValueFactory<>("username"));

    } 
        @FXML
      public void searchProblemAction() {
        rechercherPb.textProperty().addListener((observable, oldValue, newValue) -> {
        ProblemClass pb =new ProblemClass();
            tblViewProbleme.setItems(pb.findAllProblems(rechercherPb.getText()));
        });
    }
      
          public void ListViewEvent ()
 {
 
      tblViewProbleme.setItems(listeProblemes1);
      
      tblViewProbleme.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Problem>() {
			

          @Override
          public void changed(ObservableValue<? extends Problem> observable, Problem oldValue,Problem newValue) {
              ObservableList<Problem> listeProblemeFinal;
               ObservableList<Solution> solutions;
                SolutionDAO s=new SolutionDAO();
                MembreDAO m=new MembreDAO();
             listeProblemeFinal=FXCollections.observableArrayList();
            ProblemClass prob = new ProblemClass();
            
           listeProblemeFinal= prob.DisplayById(tblViewProbleme.getSelectionModel().getSelectedItem().getId());
           
           File imagefile=null;
           
           imagefile =new File(m.findPictureById(prob.findMembreId(tblViewProbleme.getSelectionModel().getSelectedItem().getId())));
              System.out.println(prob.findPictureById(tblViewProbleme.getSelectionModel().getSelectedItem().getId()));
           if(!imagefile.exists())
           {
                imagefile =new File(prob.findPictureById(tblViewProbleme.getSelectionModel().getSelectedItem().getId()));
           }
           
                  imgP.setImage(new javafx.scene.image.Image(m.findPictureById(prob.findMembreId(tblViewProbleme.getSelectionModel().getSelectedItem().getId())),true));
                  System.out.println(imgP);
             
          title_problem.setText(tblViewProbleme.getSelectionModel().getSelectedItem().getTitre());
          description_problem.setText(tblViewProbleme.getSelectionModel().getSelectedItem().getDescProbleme());
          solutions=s.DisplaySolutionById(tblViewProbleme.getSelectionModel().getSelectedItem().getId());
          tblViewSolutions.setItems(solutions);
          ContentS.setCellValueFactory(new PropertyValueFactory<>("descriptionSolutionProbleme"));
          PostedByS.setCellValueFactory(new PropertyValueFactory<>("username"));
         Rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
         updateSolution.setCellValueFactory(new PropertyValueFactory<>("membre_id"));
         updateSolution.setCellFactory(new Callback<TableColumn<Solution, Integer>, TableCell<Solution, Integer>>() {

                  @Override
                  public TableCell<Solution, Integer> call(TableColumn<Solution, Integer> param) {
                 return new TableCell<Solution,Integer>()
                 {

                     @Override
                     protected void updateItem(Integer item, boolean empty) {
                         super.updateItem(item, empty); 
                         
                                                    javafx.scene.control.Button btnUpdateSol = new javafx.scene.control.Button("Update");//To change body of generated methods, choose Tools | Templates.
                         if (empty || id!=item) {
                                            
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btnUpdateSol.setGraphic(new ImageView(edit));
                                       btnUpdateSol.setOnAction((ActionEvent event) -> {
                                            Stage popupUpdateSol ;
                                                Parent root2;

                                                    popupUpdateSol = new Stage();

                                                try {
                                                    sol=tblViewSolutions.getSelectionModel().getSelectedItem();
                                                    System.out.println("looo"+tblViewSolutions.getSelectionModel().getSelectedItem());
                                                    root2=FXMLLoader.load(getClass().getResource("updateSolution.fxml"));
                                                    root2.getStylesheets().add("css/LoginStyle.css");

                                                    
                                                    popupUpdateSol.setScene(new Scene(root2));
                                                    popupUpdateSol.initModality(Modality.APPLICATION_MODAL);
                                                    popupUpdateSol.initOwner(btnUpdateSol.getScene().getWindow());
                                                    popupUpdateSol.showAndWait();

                                                } catch (IOException ex) {


                                                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            
                                           
                                        });
                                        setGraphic(btnUpdateSol);
                                        setText(null);
                                    }
                     
                     }
                     
                     
                     
                 };
                  
                  }
              });
         //ModifierSolution();
          Rate.setCellFactory(new Callback<TableColumn<Solution, Integer>, TableCell<Solution, Integer>>() {

                  @Override
                  public TableCell<Solution, Integer> call(TableColumn<Solution, Integer> param) {
                  
                  return new TableCell<Solution,Integer>()
                  {

                      @Override
                      protected void updateItem(Integer item, boolean empty) {
                          super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                    
                          
                          
                          
                          if(empty)
                          setGraphic(null);
                      
                      
                      
                      else
                          {
                                  Rating r=new Rating(5,item);
                      r.setDisable(true);
                      r.setOnMouseClicked(new EventHandler<MouseEvent>() {

                              @Override
                              public void handle(MouseEvent event) {
                            
                                  if(tblViewSolutions.getSelectionModel().getSelectedIndex()!=-1){
                                  
                                  SolutionDAO sd=new SolutionDAO();
                                  sd.rateSolution(solutions.get(tblViewSolutions.getSelectionModel().getSelectedIndex()).getId(),(int) r.getRating() );
                               String title = "Rated ";
        String message = "You've successfully Rated this solution";
        NotificationType notification = NotificationType.SUCCESS;

        TrayNotification tray = new TrayNotification(title,message,notification);
      
         tray.showAndDismiss(Duration.seconds(5));
                                  }
                                  }
                          });
                              setGraphic(r);
                      
                          }
                      }
                      
                  };
                  }
              });

         
          }
		});
 }
  public void refreshButton(ActionEvent event)
    {
       afficherProblemes();
    }
}
