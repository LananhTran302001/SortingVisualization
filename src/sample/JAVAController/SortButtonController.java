package sample.JAVAController;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

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
    public void clickBubbleSort() {
        this.select(bubbleSButton);
    }

    @FXML
    public void clickSelectionSort() {
        this.select(selectionSButton);
    }

    @FXML
    public void clickInsertionSort() {
        this.select(insertionSButton);
    }

    @FXML
    public void clickMergeSort() {
        this.select(mergeSButton);
    }

    @FXML
    public void clickHeapSort() {
        this.select(heapSButton);
    }

    @FXML
    public void clickQuickSort() {
        this.select(quickSButton);
    }
}
