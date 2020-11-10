package control;

import constant.AppConstants;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class DeleteBox {

    private Label message = new Label();
    private Button deleteButton = new Button("Delete");
    private int index = -1;
    private boolean acceptance = false;

    public int getIndexDelete(int size) {

        final Stage deleteWindow = new Stage();

        deleteWindow.initModality(Modality.APPLICATION_MODAL);
        deleteWindow.setTitle("Delete");
        deleteWindow.setMinWidth(AppConstants.BOX_MIN_WIDTH);

        TextField inputText = new TextField();
        inputText.setOnKeyReleased(event -> {
            try {
                index = Integer.parseInt(inputText.getText());

                if (0 <= index && index < size) {
                    message.setText("Are you sure to delete arr[" + inputText.getText() + "] ?");
                    deleteButton.setDisable(false);

                } else {
                    message.setText("Out of bound!");
                    deleteButton.setDisable(true);
                }
            } catch (Exception e) {
                index = -1;
                message.setText("Please type a number");
                deleteButton.setDisable(true);
            }

        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            index = -1;
            deleteWindow.close();
        });

        deleteButton.setDisable(true);
        deleteButton.setOnAction(event -> deleteWindow.close());

        HBox input = new HBox(AppConstants.BOX_SPACE);
        input.getChildren().addAll(new Label("Index: "), inputText);

        HBox buttons = new HBox(AppConstants.BOX_SPACE);
        buttons.getChildren().addAll(cancelButton, deleteButton);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(input, message, buttons);
        layout.setPadding(AppConstants.BOX_PADDING);
        layout.setSpacing(AppConstants.BOX_SPACE);

        Scene scene = new Scene(layout);

        deleteWindow.setScene(scene);
        deleteWindow.showAndWait();

        return index;
    }

    public boolean acceptClear() {
        final Stage clearWindow = new Stage();

        clearWindow.initModality(Modality.APPLICATION_MODAL);
        clearWindow.setTitle("Clear");
        clearWindow.setMinWidth(AppConstants.BOX_MIN_WIDTH);

        Label label = new Label("Are you sure to clear all ?\n");

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> clearWindow.close());

        Button deleteButton = new Button("Clear");
        deleteButton.setOnAction(event -> {
            acceptance = true;
            clearWindow.close();
        });

        HBox buttons = new HBox(AppConstants.BOX_SPACE);
        buttons.getChildren().addAll(cancelButton, deleteButton);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(label, buttons);
        layout.setPadding(AppConstants.BOX_PADDING);
        layout.setSpacing(AppConstants.BOX_SPACE);

        Scene scene = new Scene(layout);

        clearWindow.setScene(scene);
        clearWindow.showAndWait();

        return acceptance;
    }

}
