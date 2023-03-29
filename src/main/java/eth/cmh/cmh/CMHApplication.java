package eth.cmh.cmh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CMHApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CMHApplication.class.getResource("cmh.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("CMH - a Github Commit Map hacker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}