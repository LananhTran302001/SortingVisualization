package control;

import globalVar.AppConstants;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateArrBox {
    private int input = -1;
    private Button addButton = new Button("Create");

    public int getAddNumber() {

        final Stage addWindow = new Stage();

        addWindow.initModality(Modality.APPLICATION_MODAL);
        addWindow.setTitle("Make new array");
        addWindow.setMinWidth(AppConstants.BOX_MIN_WIDTH);


        Label label = new Label("   ADD NUMBER");

        TextField numberText = new TextField();
        numberText.setPrefWidth(120);


        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> addWindow.close());

        addButton.setDisable(true);
        addButton.setOnAction(event -> addWindow.close());

        HBox inputReader = new HBox(AppConstants.BOX_SPACE);
        inputReader.getChildren().addAll(new Label("Array: "), numberText);

        HBox buttons = new HBox(AppConstants.BOX_SPACE);
        buttons.getChildren().addAll(cancelButton, addButton);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(label, inputReader, buttons);
        layout.setPadding(AppConstants.BOX_PADDING);
        layout.setSpacing(AppConstants.BOX_SPACE);

        Scene scene = new Scene(layout);

        addWindow.setScene(scene);
        addWindow.showAndWait();

        return input;
    }
}
