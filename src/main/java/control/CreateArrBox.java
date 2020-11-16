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

import java.util.ArrayList;
import java.util.List;

public class CreateArrBox {

    private List<Integer> input = null;

    public List<Integer> getNewArray() {

        final Stage createNewWindow = new Stage();

        createNewWindow.initModality(Modality.APPLICATION_MODAL);
        createNewWindow.setTitle("New array");
        createNewWindow.setMinWidth(AppConstants.BOX_MIN_WIDTH);

        Label label = new Label("MAKE NEW ARRAY");

        TextField arrayText = new TextField();
        arrayText.setPrefWidth(280);
        arrayText.setText(null);
        arrayText.setStyle("-fx-prompt-text-fill: GREY;");
        arrayText.setPromptText("Ex:12,1,9,8,120,90");


        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            input = null;
            createNewWindow.close();
        });


        Button createButton = new Button("Create");
        createButton.setOnAction(event -> {
            input = readFromText(arrayText.getText());
            createNewWindow.close();
        });

        HBox inputReader = new HBox(10);
        inputReader.getChildren().addAll(new Label("Array: "), arrayText);

        HBox buttons = new HBox(AppConstants.BOX_SPACE);
        buttons.getChildren().addAll(cancelButton, createButton);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(label, inputReader, buttons);
        layout.setPadding(AppConstants.BOX_PADDING);
        layout.setSpacing(AppConstants.BOX_SPACE);

        Scene scene = new Scene(layout);

        createNewWindow.setScene(scene);
        createNewWindow.showAndWait();

        return input;
    }

    private List<Integer> readFromText(String str) {
        if (str == null) {
            return null;
        }

        List<Integer> array = null;
        String[] numbers = str.split(",");

        try {
            array = new ArrayList<Integer>();
            for (String num : numbers) {
                int temp = Integer.parseInt(num);
                if (temp > 0 && temp < AppConstants.VALUE_UPPER_BOUND) {
                    if (array.size() < AppConstants.MAX_TIMES_RANDOM) {
                        array.add(temp);
                    } else {
                        break;
                    }
                } else {
                    new AlertBox().popUp("Numbers in array must be positive and less than " + AppConstants.VALUE_UPPER_BOUND);
                    array = null;
                    break;
                }
            }
            return array;

        } catch (Exception e) {
            new AlertBox().popUp("\tCan not read array properly\t");
            return null;
        }
    }
}
