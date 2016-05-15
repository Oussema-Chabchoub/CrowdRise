/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import pi.dao.classes.CommentDao;
import pi.dao.classes.ProjetDAO;
import pi.entities.Projet;
import pi.entities.commentaireprojet;
import static menu.LoginViewController.id;


/**
 * FXML Controller class
 *
 * @author User
 */
public class ProjectController implements Initializable {
   private ObservableList<String> options;
   private ObservableList<Projet> listeProjets;
   @FXML private TableView<Projet> tblViewProjet;
   @FXML private TableView<commentaireprojet> tblViewComments;
   @FXML private TableColumn<Projet,String> titre;
   @FXML private TableColumn<Projet,String> domaine;    
   @FXML private TableColumn<Projet,Integer> budget;
   @FXML private TableColumn<Projet,String> realisation;
   @FXML private TableColumn<commentaireprojet,String> proprietaireComment;
   @FXML private TableColumn<commentaireprojet,String> ContenuComment;
   @FXML private TableColumn deleteComment;
   @FXML private TableColumn delete;
   @FXML private TableColumn update;
   @FXML private TableColumn AddComment;
   @FXML private TableColumn updateComment;
   @FXML TextField rechercher;
   @FXML private Label description_project;
   @FXML private Label title_project;
   @FXML private Label realisation_project;
   @FXML private TextField desc;
   @FXML ImageView img1;
   @FXML ImageView del;
   @FXML private ImageView add_button_proj;
   @FXML private ImageView update_button_proj;
   @FXML private ImageView editProfile;
   @FXML Button refresh;
    public static Projet pr;
    public static commentaireprojet cmp;
    Image del1;
    Image comment;
    Image edit;
    public static ProjectController stat;

    
  



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProjets();
          ListViewEvent();
          del1=new javafx.scene.image.Image("/buttons/del1.png");
          comment=new javafx.scene.image.Image("/buttons/comment.png");
          edit=new javafx.scene.image.Image("/buttons/edit.png");

          add_button_proj.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage popupAddProject ;
                Parent root2;
                      
                    popupAddProject = new Stage();
                
                try {
                    root2=FXMLLoader.load(getClass().getResource("addProject.fxml"));
                    root2.getStylesheets().add("css/LoginStyle.css");

                    popupAddProject.setScene(new Scene(root2));
                    popupAddProject.initModality(Modality.APPLICATION_MODAL);
                    popupAddProject.initOwner(add_button_proj.getScene().getWindow());
                    popupAddProject.showAndWait();
                    
                } catch (IOException ex) {
              
                    
                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
           update_button_proj.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage popupAddProject ;
                Parent root2;
                      
                    popupAddProject = new Stage();
                
                try {
                    pr=tblViewProjet.getSelectionModel().getSelectedItem();
                    root2=FXMLLoader.load(getClass().getResource("UpdateProject.fxml"));
                    root2.getStylesheets().add("css/LoginStyle.css");

                    popupAddProject.setScene(new Scene(root2));
                    popupAddProject.initModality(Modality.APPLICATION_MODAL);
                    popupAddProject.initOwner(add_button_proj.getScene().getWindow());
                    popupAddProject.showAndWait();
                    
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
    public void afficherProjets()
    {
        listeProjets=FXCollections.observableArrayList();
        ProjetDAO pd=new ProjetDAO();
        tblViewProjet.setItems(listeProjets);
        pd.DisplayById(listeProjets,id);
        titre.setCellValueFactory(new PropertyValueFactory<>("titreProjet"));
        domaine.setCellValueFactory(new PropertyValueFactory<>("domaineProjet"));
        budget.setCellValueFactory(new PropertyValueFactory<>("budgetProjet"));
        realisation.setCellValueFactory(new PropertyValueFactory<>("collecteEnCours"));
        supprimerProjet();
        ajouterCommentaire();
        ListViewEvent();
    }
     public void supprimerProjet() {
        try {
            Callback<TableColumn<Projet, String>, TableCell<Projet, String>> cellFactory = //
                    new Callback<TableColumn<Projet, String>, TableCell<Projet, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Projet, String> param) {
                            final TableCell<Projet, String> cell = new TableCell<Projet, String>() {
                              
                            javafx.scene.control.Button btnSuppProjet = new javafx.scene.control.Button("Delete");
                           
                             @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btnSuppProjet.setGraphic(new ImageView(del1));
                                        //btnSuppProjet.setStyle("-fx-background-color:-swatch-dark-grey" +"-fx-font-size: 10px;");
                                        
                                        btnSuppProjet.setOnAction((ActionEvent event) -> {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Confirmation Dialog");
                                            alert.setContentText("You really want to remove the project : "+tblViewProjet.getSelectionModel().getSelectedItem().getTitreProjet()+"?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                            ProjetDAO pd=new ProjetDAO();
                                            pd.removeProjet(tblViewProjet.getSelectionModel().getSelectedItem().getId());
                                            }
                                            afficherProjets();
                                        });
                                        setGraphic(btnSuppProjet);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            delete.setCellFactory(cellFactory);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }
     public void supprimerCommentaire() {
          
        try {
            Callback<TableColumn<commentaireprojet, String>, TableCell<commentaireprojet, String>> cellFactory = //
                    new Callback<TableColumn<commentaireprojet, String>, TableCell<commentaireprojet, String>>() {
                        @Override
                        public TableCell call(final TableColumn<commentaireprojet, String> param) {
                            final TableCell<commentaireprojet, String> cell = new TableCell<commentaireprojet, String>() {
                                
                            javafx.scene.control.Button btnSuppComm = new javafx.scene.control.Button("Delete");
                           
                             @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                    btnSuppComm.setGraphic(new ImageView(del1));

                                        
                                       btnSuppComm.setOnAction((ActionEvent event) -> {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Confirmation Dialog");
                                            alert.setContentText("You really want to remove the comment : "+tblViewComments.getSelectionModel().getSelectedItem().getContenuCommentaireProjet()+"?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                            CommentDao pd=new CommentDao();
                                            pd.removeVoteById(tblViewComments.getSelectionModel().getSelectedItem().getId());
                                            pd.removeCommentById(tblViewComments.getSelectionModel().getSelectedItem().getId());
                                                System.out.println(tblViewComments.getSelectionModel().getSelectedItem().getId());
                                            }
                                             ObservableList<commentaireprojet> listeCommentaires;
                                             CommentDao comment=new CommentDao();
                                            listeCommentaires= comment.DisplayCommentById(tblViewProjet.getSelectionModel().getSelectedItem().getId());
                                            System.out.println(listeCommentaires);
                                            tblViewComments.setItems(listeCommentaires);
                                            ContenuComment.setCellValueFactory(new PropertyValueFactory<>("contenuCommentaireProjet"));
                                            proprietaireComment.setCellValueFactory(new PropertyValueFactory<>("username"));
                                           
                                        });
                                        setGraphic(btnSuppComm);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            deleteComment.setCellFactory(cellFactory);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }
        public void ajouterCommentaire() {
          
        try {
            Callback<TableColumn<commentaireprojet, String>, TableCell<commentaireprojet, String>> cellFactory = //
                    new Callback<TableColumn<commentaireprojet, String>, TableCell<commentaireprojet, String>>() {
                        @Override
                        public TableCell call(final TableColumn<commentaireprojet, String> param) {
                            final TableCell<commentaireprojet, String> cell = new TableCell<commentaireprojet, String>() {
                                
                            javafx.scene.control.Button btnAddComm = new javafx.scene.control.Button("Add your comment");
                           
                             @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        
                                       //btnAddComm.setStyle("-fx-background-color: \n" +"#000000,\n" +"linear-gradient(#EF4836, #EF4836);\n" +"-fx-background-insets: 0,1,2,3;\n" +"-fx-background-radius: 3,2,2,2;\n"+"-fx-padding: 12 30 12 30;\n" +"-fx-text-fill: white;\n" +"-fx-font-size: 14px;");
                                        btnAddComm.setGraphic(new ImageView(comment));
                                       btnAddComm.setOnAction((ActionEvent event) -> {
                                            Stage popupAddComment ;
                                                Parent root2;

                                                    popupAddComment = new Stage();

                                                try {
                                                    pr=tblViewProjet.getSelectionModel().getSelectedItem();
                                                    root2=FXMLLoader.load(getClass().getResource("AddComment.fxml"));
                                                    root2.getStylesheets().add("css/LoginStyle.css");
                                                    
                                                    popupAddComment.setScene(new Scene(root2));
                                                    popupAddComment.initModality(Modality.APPLICATION_MODAL);
                                                    popupAddComment.initOwner(btnAddComm.getScene().getWindow());
                                                    popupAddComment.showAndWait();

                                                } catch (IOException ex) {


                                                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            
                                           
                                        });
                                        setGraphic(btnAddComm);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            AddComment.setCellFactory(cellFactory);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }
        public void ModifierCommentaire() {
          
        try {
            Callback<TableColumn<commentaireprojet, String>, TableCell<commentaireprojet, String>> cellFactory = //
                    new Callback<TableColumn<commentaireprojet, String>, TableCell<commentaireprojet, String>>() {
                        @Override
                        public TableCell call(final TableColumn<commentaireprojet, String> param) {
                            final TableCell<commentaireprojet, String> cell = new TableCell<commentaireprojet, String>() {
                                
                            javafx.scene.control.Button btnUpdateComm = new javafx.scene.control.Button("Update");
                           
                             @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                       //btnUpdateComm.setStyle("-fx-background-color: \n" +"#000000,\n" +"linear-gradient(#EF4836, #EF4836);\n" +"-fx-background-insets: 0,1,2,3;\n" +"-fx-background-radius: 3,2,2,2;\n"+"-fx-padding: 12 30 12 30;\n" +"-fx-text-fill: white;\n" +"-fx-font-size: 14px;");
                                        btnUpdateComm.setGraphic(new ImageView(edit));
                                       btnUpdateComm.setOnAction((ActionEvent event) -> {
                                            Stage popupUpdateComment ;
                                                Parent root2;

                                                    popupUpdateComment = new Stage();

                                                try {
                                                    cmp=tblViewComments.getSelectionModel().getSelectedItem();
                                                    root2=FXMLLoader.load(getClass().getResource("UpdateComment.fxml"));
                                                    root2.getStylesheets().add("css/LoginStyle.css");

                                                    
                                                    popupUpdateComment.setScene(new Scene(root2));
                                                    popupUpdateComment.initModality(Modality.APPLICATION_MODAL);
                                                    popupUpdateComment.initOwner(btnUpdateComm.getScene().getWindow());
                                                    popupUpdateComment.showAndWait();

                                                } catch (IOException ex) {


                                                    Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            
                                           
                                        });
                                        setGraphic(btnUpdateComm);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            updateComment.setCellFactory(cellFactory);
        } catch (Exception e) {
            System.out.println("Error on Building Data");
        }
    }
      @FXML
      public void searchProjetAction() {
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
        ProjetDAO p=new ProjetDAO();
            tblViewProjet.setItems(p.findProjets(rechercher.getText(),id));
        });
    }
    public void ListViewEvent ()
 {
 
      tblViewProjet.setItems(listeProjets);
      
      tblViewProjet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Projet>() {
			

          @Override
          public void changed(ObservableValue<? extends Projet> observable, Projet oldValue, Projet newValue) {
              ObservableList<Projet> listeProjetFinal;
  ObservableList<commentaireprojet> listeCommentaires;
   CommentDao comment=new CommentDao();
         
              
              listeProjetFinal=FXCollections.observableArrayList();
                           listeCommentaires=FXCollections.observableArrayList();

              
           
            ProjetDAO prjt = new ProjetDAO();
            
          
              
                  img1.setImage(new javafx.scene.image.Image(prjt.findPictureById(tblViewProjet.getSelectionModel().getSelectedItem().getId()),true));

                  System.out.println(img1);
             
          title_project.setText(tblViewProjet.getSelectionModel().getSelectedItem().getTitreProjet());
          description_project.setText(tblViewProjet.getSelectionModel().getSelectedItem().getDescriptionProjet());
          realisation_project.setText(tblViewProjet.getSelectionModel().getSelectedItem().getCollecteEnCours()+"");
         
          
             listeCommentaires= comment.DisplayCommentById(tblViewProjet.getSelectionModel().getSelectedItem().getId());
             tblViewComments.setItems(listeCommentaires);
             ContenuComment.setCellValueFactory(new PropertyValueFactory<>("contenuCommentaireProjet"));
             proprietaireComment.setCellValueFactory(new PropertyValueFactory<>("username"));
             supprimerCommentaire();
             ModifierCommentaire();
         
          }
		});
 }

    public void setImg1(ImageView img1) {
        this.img1 = img1;
    }

    public ImageView getImg1() {
        return img1;
    }
     public void refreshButton(ActionEvent event)
    {
       afficherProjets();
       
    }
}
