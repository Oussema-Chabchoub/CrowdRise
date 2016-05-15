package menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Cursor;


public class ProfileController implements Initializable{

    @FXML
    private ImageView portrait;

    @FXML
    private GridPane rootGridPane;

    @FXML
    private VBox vBox, vBoxChangeImage;

    @FXML
    private StackPane stackPaneImageHolder;

    @FXML
    private Label labelLocalSource, labelDone;

    @FXML
    private TextField txField;

    private final ImageEditor imageEditor;
    private final AnimationGenerator animationGenerator;

    private final Controller controller;

    public ProfileController(Controller controller) {
        this.controller = controller;
        imageEditor = new ImageEditor();
        animationGenerator = new AnimationGenerator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageEditor.setImageOn(portrait, "/menu/3848,344x,Ingenieur-commercial1.jpg", 170, 170);
        imageEditor.setRectangularClipOf(portrait, 15, 15);
    }

    @FXML
    private void mouseEntered(){
        animationGenerator.applyFadeAnimationOn(portrait, 500, 1.0f, 0.6f, null);
    }

    @FXML
    private void mouseExited() {
        animationGenerator.applyFadeAnimationOn(portrait, 500, 0.6f, 1.0f, null);
        imageEditor.setImageOn(portrait, "/menu/3848,344x,Ingenieur-commercial1.jpg", 170, 170);
    }

    @FXML
    private void imageClicked() {
        controller.closeAnimation();
        animationGenerator.applyFadeAnimationOn(vBox, 1000, 1.0f, 0f, event -> {
            vBoxChangeImage.setOpacity(0f);
            vBoxChangeImage.setVisible(true);
            stackPaneImageHolder.getChildren().remove(0, stackPaneImageHolder.getChildren().size());
            stackPaneImageHolder.getChildren().add(vBoxChangeImage);
            animationGenerator.applyFadeAnimationOn(vBoxChangeImage, 1000, 0f, 1.0f, null);
        });
    }

    @FXML
    private void chooseImageFromLocalSource() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(Main.stage);
        if (selectedFile != null) {
            imageEditor.setImageOn(portrait, selectedFile.toURI().toString(), 170, 170);
            imageEditor.setRectangularClipOf(portrait, 15, 15);
        }
    }

    @FXML
    private void doneClicked() {
        if(!txField.getText().isEmpty()) {
            try {
                imageEditor.setImageOn(portrait, txField.getText(), 170, 170);
                imageEditor.setRectangularClipOf(portrait, 15, 15);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        animationGenerator.applyFadeAnimationOn(vBoxChangeImage, 1000, 1.0f, 0f, event -> {
            stackPaneImageHolder.getChildren().remove(0, stackPaneImageHolder.getChildren().size());
            vBox.setOpacity(0f);
            stackPaneImageHolder.getChildren().add(vBox);
            animationGenerator.applyFadeAnimationOn(vBox, 1000, 0f, 1.0f, null);
        });
    }

    @FXML
    private void mouseEnteredLocalSource() {
        animationGenerator.applyFadeAnimationOn(labelLocalSource, 500, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseEnteredDone() {
        animationGenerator.applyFadeAnimationOn(labelDone, 500, 1.0f, 0.7f, null);
    }

    @FXML
    private void mouseExitedLocalSource() {
        animationGenerator.applyFadeAnimationOn(labelLocalSource, 500, 0.7f, 1.0f, null);
    }

    @FXML
    private void mouseExitedDone() {
        animationGenerator.applyFadeAnimationOn(labelDone, 500, 0.7f, 1.0f, null);
    }

}
