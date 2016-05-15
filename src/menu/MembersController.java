/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pi.dao.classes.CommentDao;
import pi.dao.classes.MembreDAO;
import pi.dao.classes.ProjetDAO;
import pi.entities.Membre;
import pi.entities.Projet;
import pi.entities.commentaireprojet;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MembersController implements Initializable {
    @FXML
    private AnchorPane AnshorPrincipalr;
    @FXML
    private TableView<Membre> tblViewMembers;
   
    @FXML
    private TextField rechercher;
    @FXML
    private Label realisation_project;
    @FXML
    private ImageView Picture;
    @FXML
    private Label skills;
    @FXML
    private Label credit;
    @FXML
    private Label email1;
    @FXML
    private TableColumn<Membre, String> name;
    @FXML
    private TableColumn<Membre, String> surname;
    @FXML
    private TableColumn<Membre,String> email;
    @FXML
    private TableColumn<Membre, Integer> rib;
    @FXML
    private TableColumn<Membre, Integer> tel;
    private ObservableList<Membre> listeMembres;
    @FXML private ImageView editProfile;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherMembres();
        ListViewEvent();
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
    public void afficherMembres()
    {
        listeMembres=FXCollections.observableArrayList();
        MembreDAO m=new MembreDAO();
        tblViewMembers.setItems(listeMembres);
        m.displayMembre(listeMembres);
        name.setCellValueFactory(new PropertyValueFactory<>("nomMembre"));
        surname.setCellValueFactory(new PropertyValueFactory<>("prenomMembre"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        rib.setCellValueFactory(new PropertyValueFactory<>("ribMembre"));
        tel.setCellValueFactory(new PropertyValueFactory<>("numTelMembre"));
    }
    public void ListViewEvent ()
    {
 
      tblViewMembers.setItems(listeMembres);
      tblViewMembers.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Membre> observable, Membre oldValue, Membre newValue) -> {
          
          MembreDAO m=new MembreDAO();
          Picture.setImage(new javafx.scene.image.Image(m.findPictureById(tblViewMembers.getSelectionModel().getSelectedItem().getId()),true));
          System.out.println(m.findPictureById(tblViewMembers.getSelectionModel().getSelectedItem().getId()));
          
          skills.setText(m.findCompetenceById(tblViewMembers.getSelectionModel().getSelectedItem().getId()));
          credit.setText(m.getSolde(tblViewMembers.getSelectionModel().getSelectedItem().getId())+"");
          email1.setText(tblViewMembers.getSelectionModel().getSelectedItem().getAdresseMailMembre());
      });
 }
     @FXML
      public void searchMemberAction() {
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
        MembreDAO m=new MembreDAO();
        tblViewMembers.setItems(m.findAllMembers(rechercher.getText()));
        });
    }
    
}
