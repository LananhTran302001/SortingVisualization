package control;

import constant.AppConstants;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    public void display(String title, String message, String button) {
        final Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(AppConstants.BOX_MIN_WIDTH);

        Label label = new Label(message);

        Button closeButton = new Button(button);
        closeButton.setOnAction(event -> alertWindow.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(label, closeButton);
        layout.setPadding(AppConstants.BOX_PADDING);
        layout.setSpacing(AppConstants.BOX_SPACE);

        Scene scene = new Scene(layout);

        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }

    public void popUp(String message) {
        System.out.println(message);
        this.display("Notification", message, "Close");
    }
}
