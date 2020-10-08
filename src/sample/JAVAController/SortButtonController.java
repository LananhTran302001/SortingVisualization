package sample.JAVAController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class SortButtonController {

    @FXML
    private ToggleButton bubbleSButton;

    @FXML
    private ToggleButton selectionSButton;

    @FXML
    private ToggleButton insertionSButton;

    @FXML
    private ToggleButton mergeSButton;

    @FXML
    private ToggleButton heapSButton;

    @FXML
    private ToggleButton quickSButton;

    @FXML
    private Label titleSpeed;

    @FXML
    private Slider speedSlider;

    public void setAllOff() {
        bubbleSButton.setSelected(false);
        selectionSButton.setSelected(false);
        insertionSButton.setSelected(false);
        mergeSButton.setSelected(false);
        heapSButton.setSelected(false);
        quickSButton.setSelected(false);
    }

    public void select(ToggleButton x) {
        this.setAllOff();
        x.setSelected(true);
    }

    @FXML
    void clickBubbleSort(MouseEvent event) {
        select(bubbleSButton);
    }

    @FXML
    void clickSelectionSort(MouseEvent event) {
        select(selectionSButton);
    }

    @FXML
    void clickInsertionSort(MouseEvent event) {
        select(insertionSButton);
    }

    @FXML
    void clickMergeSort(MouseEvent event) {
        select(mergeSButton);
    }

    @FXML
    void clickHeapSort(MouseEvent event) {
        select(heapSButton);
    }

    @FXML
    void clickQuickSort(MouseEvent event) {
        select(quickSButton);
    }
}

