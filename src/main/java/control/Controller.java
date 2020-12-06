package control;

import algorithm.BubbleSort;
import algorithm.InsertionSort;
import algorithm.MergeSort;
import algorithm.SelectionSort;
import globalVar.AppConstants;

import globalVar.Count;
import globalVar.TimeDelay;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static boolean allowNotify = true;

    RectNodeArray numArray = new RectNodeArray();

    @FXML
    private Text pseudoCode;

    @FXML
    private ToggleButton notifyButton;

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
    private AnchorPane sortView;

    @FXML
    private HBox rectLine;

    @FXML
    private HBox indexLine;

    @FXML
    private HBox tempLine;

    @FXML
    private Slider pauseSlider;

    @FXML
    private Label pauseText;

    @FXML
    private Slider moveSlider;

    @FXML
    private Label moveText;


    public void initialize(URL location, ResourceBundle resources) {
        rectLine.setSpacing(AppConstants.SPACE);
        tempLine.setSpacing(AppConstants.SPACE);
        indexLine.setSpacing(AppConstants.SPACE);
        indexLine.setPrefHeight(AppConstants.HEIGHT_INDEX);
        Count.resetSwapCount();

        pauseText.setText("0.1");
        moveText.setText("0.1");

        pauseSlider.valueProperty().addListener((observable, oldValue, newValue) -> pauseText.textProperty().bind(Bindings.format("%.1f", pauseSlider.valueProperty())));

        moveSlider.valueProperty().addListener((observable, oldValue, newValue) -> moveText.textProperty().bind(Bindings.format("%.1f", moveSlider.valueProperty())));
    }

    @FXML
    void clickNewCreate(ActionEvent event) {
        newMenuButton.setText("Create");
        List<Integer> newList = new CreateArrBox().getNewArray();

        if (newList != null) {

            numArray.clear();
            rectLine.getChildren().clear();
            indexLine.getChildren().clear();

            for (Integer num : newList) {
                numArray.add(num);
                rectLine.getChildren().addAll(numArray.getLastRectNode().getView());
                addIndex();
            }
        } // else: user canceled creating.
        newMenuButton.setText("New");
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
        reset();
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
            // get element just been added to numArray.
            rectLine.getChildren().addAll(numArray.getLastRectNode().getView());
            addIndex();
            notify("Added successfully");

        }
        // else : user canceled adding new number.
    }

    @FXML
    void clickDelete(ActionEvent event) {
        int size = numArray.size();
        int index = new DeleteBox().getIndexDelete(size);
        if (index >= 0 && index <size) {
            rectLine.getChildren().remove(index);
            removeLastIndex();
            numArray.deleteAt(index);
            notify("Deleted successfully");
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
    void clickAscend(ActionEvent event) {
        orderButton.setText("Ascend");
    }

    @FXML
    void clickDescend(ActionEvent event) {
        orderButton.setText("Descend");
    }

    @FXML
    void clickBubbleSort(ActionEvent event) {
        algorithmButton.setText("Bubble Sort");
        pseudoCode.setText(getPseudoCode(AppConstants.BUBBLE_SORT_PSEUDO));
    }

    @FXML
    void clickInsertionSort(ActionEvent event) {
        algorithmButton.setText("Insertion Sort");
        pseudoCode.setText(getPseudoCode(AppConstants.INSERTION_SORT_PSEUDO));
    }

    @FXML
    void clickQuickSort(ActionEvent event) {
        algorithmButton.setText("Quick Sort");
    }

    @FXML
    void clickSelectionSort(ActionEvent event) {
        algorithmButton.setText("Selection Sort");
        pseudoCode.setText(getPseudoCode(AppConstants.SELECTION_SORT_PSEUDO));
    }

    @FXML
    void clickMergeSort(ActionEvent event) {
        algorithmButton.setText("Merge Sort");
        pseudoCode.setText(getPseudoCode(AppConstants.MERGE_SORT_PSEUDO));
    }

    @FXML
    void clickSort(ActionEvent event) {
        reset();
        if (algorithmButton.getText().equals("Algorithm") || orderButton.getText().equals("Order")) {
            notify("Please choose algorithm and order");
        } else if (numArray.size() == 1) {
            numArray.getAt(0).setColor(Color.GRAY);
        } else if (numArray.size() > 1) {
            boolean ascendOrder = true;
            ascendOrder = orderButton.getText().equals("Ascend");

            String algo = algorithmButton.getText();
            if (algo.equals("Bubble Sort")) {
                BubbleSort.startBubbleSort(numArray, ascendOrder, rectLine);
            } else if (algo.equals("Selection Sort")) {
                SelectionSort.startSelectionSort(numArray, ascendOrder, rectLine);
            } else if (algo.equals("Insertion Sort")) {
                InsertionSort.startInsertionSort(numArray, ascendOrder, rectLine);
            } else if (algo.equals("Merge Sort")) {
                MergeSort.startMergeSort(numArray, ascendOrder, rectLine);
            }
        }
    }


    @FXML
    void setMoveDuration(MouseEvent event) {
        Float duration = Float.parseFloat(moveText.getText());
        TimeDelay.setJumpDuration(duration);
        TimeDelay.setMoveDuration(duration);
        System.out.println("set moveDuration: " + duration);
    }



    @FXML
    void setPauseDuration(MouseEvent event) {
        Float duration = Float.parseFloat(pauseText.getText());
        TimeDelay.setPauseDuration(duration);
        System.out.println("set pauseDuration: " + duration);
    }

    @FXML
    void setNotification(ActionEvent event) {
        if (notifyButton.getText().equals("Notify On")) {
            allowNotify = false;
            notifyButton.setText("Notify Off");
        } else {
            allowNotify = true;
            notifyButton.setText("Notify On");
        }
    }

    public void addToTempLine(Node node) {
        tempLine.getChildren().addAll(node);
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
        label.setStyle("-fx-text-fill: #66fcf1;");
        indexLine.getChildren().add(label);
    }

    private void removeLastIndex() {
        int lastIndex = indexLine.getChildren().size() - 1;
        indexLine.getChildren().remove(lastIndex);
    }

    private void reset() {
        numArray.reset();
        rectLine.getChildren().setAll(numArray.getListView());
    }

    /**
     * Read pseudocode from file.
     * @param pth path to pseudocode file.
     * @return pseudocode in string.
     */
    private String getPseudoCode(String pth) {
        try {
            StringBuilder pseudo = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(pth));
            String line = reader.readLine();
            while (line != null) {
                pseudo.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();
            return pseudo.toString();
        } catch (FileNotFoundException e) {
            return "Can not open file.";
        } catch (IOException e) {
            return "Can not read file.";
        }
    }

}
