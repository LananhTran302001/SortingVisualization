package control;

import algorithm.BubbleSort;
import animation.SwapAnimation;
import globalVar.AppConstants;

import globalVar.Count;
import globalVar.TimeDelay;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static boolean allowNotify = true;

    RectNodeArray numArray = new RectNodeArray();

    @FXML
    private Text pseudoCode;

    @FXML
    private Label countSwapText;

    @FXML
    private MenuButton newMenuButton;

    @FXML
    private Button mixButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private MenuButton algorithmButton;

    @FXML
    private MenuButton orderButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button pauseButton;

    @FXML
    private AnchorPane sortView;

    @FXML
    private HBox rectLine;

    @FXML
    private HBox indexLine;

    @FXML
    private Slider swapSlider;

    @FXML
    private Label swapText;

    @FXML
    private Slider moveSlider;

    @FXML
    private Label moveText;


    public void initialize(URL location, ResourceBundle resources) {
        rectLine.setSpacing(AppConstants.SPACE);
        indexLine.setSpacing(AppConstants.SPACE);
        indexLine.setPrefHeight(10);
        Count.resetSwapCount();

        swapText.setText("0.5");
        moveText.setText("0.5");

        swapSlider.valueProperty().addListener((observable, oldValue, newValue) -> swapText.textProperty().bind(Bindings.format("%.1f", swapSlider.valueProperty())));

        moveSlider.valueProperty().addListener((observable, oldValue, newValue) -> moveText.textProperty().bind(Bindings.format("%.1f", moveSlider.valueProperty())));
    }

    @FXML
    void clickNewCreate(ActionEvent event) {
        newMenuButton.setText("Create");
    }

    @FXML
    void clickNewEmpty(ActionEvent event) {
        newMenuButton.setText("Empty");
        numArray.clear();
        rectLine.getChildren().clear();
        indexLine.getChildren().clear();
        newMenuButton.setText("New");
    }

    @FXML
    void clickNewRandom(ActionEvent event) {
        newMenuButton.setText("Random");
        List<Integer> randomList = new RandomBox().getRandomArr();

        if (randomList != null) {

            numArray.clear();
            rectLine.getChildren().clear();
            indexLine.getChildren().clear();

            for (Integer num : randomList) {
                numArray.add(num);
                rectLine.getChildren().addAll(numArray.getLastRectNode().getView());
                addIndex();
            }
        } // else: user canceled creating.
        newMenuButton.setText("New");

    }

    @FXML
    void clickMix(ActionEvent event) {
        if (numArray.size() > 1) {

            numArray.mix();
            rectLine.getChildren().setAll(numArray.getListView());

        } else {
            notify("Not enough elements to mix");
        }
    }

    @FXML
    void clickAdd(ActionEvent event) {
        int number = new AddBox().getAddNumber();
        if (number > 0) {
            numArray.add(number);
            rectLine.getChildren().addAll(numArray.getLastRectNode().getView());
            addIndex();
            notify("Added successfully");

        }
        // else : user canceled adding new number.
    }

    @FXML
    void clickAlgorithm(ActionEvent event) {

    }

    @FXML
    void clickDelete(ActionEvent event) {
        int size = numArray.size();
        int index = new DeleteBox().getIndexDelete(size);
        if (index >= 0 && index <size) {
            rectLine.getChildren().remove(index);
            removeLastIndex();
            numArray.deleteAt(index);
        }
        // else: user canceled delete.
    }

    @FXML
    void clickClear(ActionEvent event) {
        boolean acceptClear = new DeleteBox().acceptClear();
        if (acceptClear) {
            numArray.clear();
            rectLine.getChildren().clear();
            indexLine.getChildren().clear();
            notify("Cleared all");
        } // else: user canceled clearing
    }

    @FXML
    void clickOrder(ActionEvent event) {
        SwapAnimation.playSwapByAscend(numArray, 1, 2, rectLine);
    }

    @FXML
    void clickPause(ActionEvent event) {

        SwapAnimation.playSwapByAscend(numArray, 0, 2, rectLine);
    }


    int i = 0;
    int j = 1;
    @FXML
    void clickSort(ActionEvent event) {
        Count.resetSwapCount();
        BubbleSort.startBubbleSort(numArray, true, rectLine);
        /*SwapAnimation.playSwapByAscend(numArray, i, j, rectLine);
        j++;
        if (j == numArray.size() - 1) {
            i++;
            j = i+1;
        }*/
    }


    @FXML
    void setMoveDuration(MouseEvent event) {
        Float duration = Float.parseFloat(moveText.getText());
        TimeDelay.setJumpDuration(duration);
        TimeDelay.setMoveDuration(duration);
        System.out.println("set moveDuration: " + duration);
    }



    @FXML
    void setSwapDuration(MouseEvent event) {
        Float duration = Float.parseFloat(swapText.getText());
        TimeDelay.setPauseDuration(duration);
        System.out.println("set swapDuration: " + duration);
    }

    private void notify(String message) {
        System.out.println(message);

        if (allowNotify) {
            new AlertBox().display("Notification", message, "Close");
        }
    }

    private void updateIndex() {
        int lastRectIndex = numArray.size() - 1;
        int lastIndex = indexLine.getChildren().size() - 1;
        if (lastIndex < lastRectIndex) {
            addIndex();
        } else if (lastIndex > lastRectIndex) {
            removeLastIndex();
        }
    }

    private void addIndex() {
        int size = indexLine.getChildren().size();
        Label label = new Label(Integer.toString(size));
        label.setPrefWidth(AppConstants.WIDTH_UNIT);
        label.setAlignment(Pos.CENTER);
        indexLine.getChildren().add(label);
    }

    private void removeLastIndex() {
        int lastIndex = indexLine.getChildren().size() - 1;
        indexLine.getChildren().remove(lastIndex);
    }




}
