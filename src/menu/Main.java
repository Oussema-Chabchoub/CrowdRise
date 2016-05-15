package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("CrowdRise");
        scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
        root.getStylesheets().add("css/LoginStyle.css");
    }

    public static void main(String[] args) {
        launch(args);
    }
}


