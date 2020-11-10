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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBox {

    private int input;
    private List<Integer> randomList = new ArrayList<Integer>();
    private List<Integer> returnList = null;

    private String str = "Random array: ";
    private Label message = new Label();
    private Button createButton = new Button("Create");

    public List<Integer> getRandomArr() {
        final Stage randomWindow = new Stage();

        randomWindow.initModality(Modality.APPLICATION_MODAL);
        randomWindow.setTitle("Random");
        randomWindow.setMinWidth(AppConstants.BOX_MIN_WIDTH);


        Label label = new Label("   GENERATE RANDOM ARRAY");

        TextField numberText = new TextField();
        numberText.setPrefWidth(50);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> randomWindow.close());

        createButton.setDisable(true);
        createButton.setOnAction(event -> {
            returnList = randomList;
            randomWindow.close();
        });

        Button randomButton = new Button("Random");
        randomButton.setOnAction(event -> {
            try {
                String numStr = numberText.getText();
                input = Integer.parseInt(numStr);

                randomList.clear();
                str = "Random array: ";

                if (input > 0 && input < AppConstants.MAX_TIMES_RANDOM) {
                    for (int i = 0; i < input; i++) {
                        int temp = 1 + Math.abs(new Random().nextInt(AppConstants.MAX_VALUE_RANDOM));
                        randomList.add(temp);
                        str = str + temp + " ";
                    }
                    message.setWrapText(true);
                    message.setText(str);
                    createButton.setDisable(false);

                } else {
                    createButton.setDisable(true);
                    message.setText("Number must be positive and less than " + AppConstants.MAX_TIMES_RANDOM);
                }

            } catch (Exception e) {
                createButton.setDisable(true);
                message.setText("Number must be an integer");
            }

        });


        HBox inputReader = new HBox(AppConstants.BOX_SPACE);
        inputReader.getChildren().addAll(new Label("Number(less than " + AppConstants.MAX_TIMES_RANDOM + "): "), numberText);

        HBox buttons = new HBox(AppConstants.BOX_SPACE);
        buttons.getChildren().addAll(cancelButton, randomButton, createButton);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(AppConstants.BOX_SPACE);
        layout.getChildren().addAll(label, inputReader, message, buttons);
        layout.setPadding(AppConstants.BOX_PADDING);

        Scene scene = new Scene(layout);

        randomWindow.setScene(scene);
        randomWindow.showAndWait();

        return returnList;
    }
}
