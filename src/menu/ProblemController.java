
package menu;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Skin;
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
import pi.entities.Projet;
import pi.entities.Solution;
import pi.entities.commentaireprojet;

public class ProblemController implements Initializable {
   private ObservableList<Problem> listeProblemes1;
   ObservableList<Solution> solutions;
   @FXML TableView<Problem> tblViewProbleme;
   @FXML TableView<Solution> tblViewSolutions;
   @FXML private TableColumn<Problem,String> titlePb;
   @FXML private TableColumn<Problem,String> domainPb;
   @FXML private TableColumn<Problem,String> profilPb;
   @FXML private TableColumn<Solution,String> PostedByS;
   @FXML private TableColumn<Solution,String> ContentS;
   @FXML private TableColumn<Solution,String> DeleteS;
   @FXML private TableColumn<Solution,Integer> Rate;
   @FXML private TableColumn deleteProblem;
   @FXML private TableColumn updateProblem;
   @FXML TextField rechercherPb;
   @FXML private Label description_problem;
   @FXML private Label title_problem;
   @FXML ImageView imgP;
   @FXML TextField desc;
   @FXML private ImageView add_button_prob;
   @FXML private Button refresh;
    Image del1;
    @FXML private ImageView editProfile;
    @FXML private ImageView editProblem;
        public static Problem pr;

    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherProblemes();
       ListViewEvent();
       del1=new javafx.scene.image.Image("/buttons/del1.png");
      add_button_prob.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage popupAddProblem ;
                Parent root2;
                      
                    popupAddProblem = new Stage();
                
                try {
                    root2=FXMLLoader.load(getClass().getResource("addProblem.fxml"));
                                        root2.getStylesheets().add("css/LoginStyle.css");

                    popupAddProblem.setScene(new Scene(root2));
                    popupAddProblem.initModality(Modality.APPLICATION_MODAL);
                    popupAddProblem.initOwner(add_button_prob.getScene().getWindow());
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
       editProblem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage popupupdateProblem ;
                Parent root2;
                      
                   popupupdateProblem  = new Stage();
                
                try {
                    pr=tblViewProbleme.getSelectionModel().getSelectedItem();
                    root2=FXMLLoader.load(getClass().getResource("updateProblem.fxml"));
                    root2.getStylesheets().add("css/LoginStyle.css");
                    popupupdateProblem .setScene(new Scene(root2));
                   popupupdateProblem .initModality(Modality.APPLICATION_MODAL);
                    popupupdateProblem .initOwner(add_button_prob.getScene().getWindow());
                   popupupdateProblem .showAndWait();
                    
                } catch (IOException ex) {
              
                    
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
    }    
      public void afficherProblemes()
    {
        listeProblemes1=FXCollections.observableArrayList();
        ProblemClass pb=new ProblemClass();
        tblViewProbleme.setItems(listeProblemes1);
        pb.displayProblemByid(listeProblemes1,id);
        titlePb.setCellValueFactory(new PropertyValueFactory<>("titre"));
        domainPb.setCellValueFactory(new PropertyValueFactory<>("domaineProbleme"));
        profilPb.setCellValueFactory(new PropertyValueFactory<>("profilDemande"));
        supprimerProblem();
    } 
        @FXML
      public void searchProblemAction() {
        rechercherPb.textProperty().addListener((observable, oldValue, newValue) -> {
        ProblemClass pb =new ProblemClass();
            tblViewProbleme.setItems(pb.findProblems(rechercherPb.getText(),id));
        });
    }
      
       public void RateProblem() {
        try { 
            Callback<TableColumn<Solution,Integer>, TableCell<Solution,Integer>> cellFactory = //
                    new Callback<TableColumn<Solution,Integer>, TableCell<Solution,Integer>>() {
                        
                         
                        @Override
                        public TableCell call(final TableColumn<Solution,Integer> param) {
                            final TableCell<Solution,Integer> cell = new TableCell<Solution,Integer>() {

                                
                              
                                @Override
                                protected void updateItem(Integer item, boolean empty) {
                                
                                if(empty)
                                {
                                    setGraphic(null);
                                    setText(null);
                                    
                                }else{
                                    
                                   
                                    
                                }
                                
                                
                                }


                                

                                
                               
                                
                                
                                
                           
                            
                                };
                            return cell;
                            
                        }
                    };
            Rate.setCellFactory(cellFactory);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
        
    }
      
        public void supprimerProblem() {
        try {
            Callback<TableColumn<Problem, String>, TableCell<Problem, String>> cellFactory = //
                    new Callback<TableColumn<Problem, String>, TableCell<Problem, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Problem, String> param) {
                            final TableCell<Problem, String> cell = new TableCell<Problem, String>() {
                                
                            javafx.scene.control.Button btnSuppProblem = new javafx.scene.control.Button("Delete");
                           
                             @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btnSuppProblem.setGraphic(new ImageView(del1));
                                        btnSuppProblem.setOnAction((ActionEvent event) -> {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Confirmation Dialog");
                                            alert.setContentText("You really want to remove the problem : "+tblViewProbleme.getSelectionModel().getSelectedItem().getTitre()+"?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                                SolutionDAO sol=new SolutionDAO();
                                                sol.removeSolutionByProblem(tblViewProbleme.getSelectionModel().getSelectedItem().getId());
                                            ProblemClass pb=new ProblemClass();
                                            pb.removeProblem(tblViewProbleme.getSelectionModel().getSelectedItem().getId());
                                            }
                                           afficherProblemes();
                                        });
                                        setGraphic(btnSuppProblem);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            deleteProblem.setCellFactory(cellFactory);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }
        public void supprimerSolution() {
        try {
            Callback<TableColumn<Solution, String>, TableCell<Solution, String>> cellFactory = //
                    new Callback<TableColumn<Solution, String>, TableCell<Solution, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Solution, String> param) {
                            final TableCell<Solution, String> cell = new TableCell<Solution, String>() {
                                
                            javafx.scene.control.Button btnSuppSoluButton = new javafx.scene.control.Button("Delete");
                           
                             @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btnSuppSoluButton.setGraphic(new ImageView(del1));
                                        btnSuppSoluButton.setOnAction((ActionEvent event) -> {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Confirmation Dialog");
                                            alert.setContentText("You really want to remove the problem : "+tblViewSolutions.getSelectionModel().getSelectedItem().getDescriptionSolutionProbleme()+"?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                                SolutionDAO sol=new SolutionDAO();
                                                sol.removeSolution(tblViewSolutions.getSelectionModel().getSelectedItem().getId());
                                          
                                            }
                                            ObservableList<Solution> solutions;
                                            SolutionDAO s=new SolutionDAO();
                                            solutions=s.DisplaySolutionById(tblViewProbleme.getSelectionModel().getSelectedItem().getId());
                                            tblViewSolutions.setItems(solutions);
                                             ContentS.setCellValueFactory(new PropertyValueFactory<>("descriptionSolutionProbleme"));
                                            PostedByS.setCellValueFactory(new PropertyValueFactory<>("username"));
                                        });
                                        setGraphic(btnSuppSoluButton);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            DeleteS.setCellFactory(cellFactory);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }
          public void ListViewEvent ()
 {
 
      tblViewProbleme.setItems(listeProblemes1);
      
      tblViewProbleme.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Problem>() {
			

          @Override
          public void changed(ObservableValue<? extends Problem> observable, Problem oldValue,Problem newValue) {
              ObservableList<Problem> listeProblemeFinal;
                
                SolutionDAO s=new SolutionDAO();
                MembreDAO m=new MembreDAO();

             listeProblemeFinal=FXCollections.observableArrayList();
            ProblemClass prob = new ProblemClass();
            
           listeProblemeFinal= prob.DisplayById(tblViewProbleme.getSelectionModel().getSelectedItem().getId());
           
           File imagefile=null;
           imagefile =new File(m.findPictureById(prob.findMembreId(tblViewProbleme.getSelectionModel().getSelectedItem().getId())));
         
                  imgP.setImage(new javafx.scene.image.Image(m.findPictureById(prob.findMembreId(tblViewProbleme.getSelectionModel().getSelectedItem().getId())),true));
                  System.out.println(imgP);
             
          title_problem.setText(tblViewProbleme.getSelectionModel().getSelectedItem().getTitre());
          description_problem.setText(tblViewProbleme.getSelectionModel().getSelectedItem().getDescProbleme());
          solutions=s.DisplaySolutionById(tblViewProbleme.getSelectionModel().getSelectedItem().getId());
          tblViewSolutions.setItems(solutions);
          ContentS.setCellValueFactory(new PropertyValueFactory<>("descriptionSolutionProbleme"));
          PostedByS.setCellValueFactory(new PropertyValueFactory<>("username"));
          Rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
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
                     // r.setDisable(true);
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
          supprimerSolution();
          //RateProblem();
          
         

         
          }
		});
 }
  public void refreshButton(ActionEvent event)
    {
       afficherProblemes();
    }
}
