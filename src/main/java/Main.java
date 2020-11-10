import constant.AppConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/SortingVisualization.fxml"));
        primaryStage.setTitle(AppConstants.APP_NAME);
        primaryStage.setScene(new Scene(root, AppConstants.SCENE_WIDTH, AppConstants.SCENE_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
