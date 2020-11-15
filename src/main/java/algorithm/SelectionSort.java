package algorithm;

import animation.JumpAnimation;
import animation.SwapAnimation;
import control.RectNodeArray;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SelectionSort {
    private static int i = 0;
    private static int j = 0;
    private static int index = 0;

    public static void startSelectionSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        index = 0;

        i = rectArr.size() - 1;
        for (j = 1; j < i; j++) {
            if (byAscendOrder) {
                // By ascend order -> find maxIndex
                if (rectArr.getValueAt(index) < rectArr.getValueAt(j)) {
                    index = j;
                }
            } else {
                // By descend order -> find minIndex
                if (rectArr.getValueAt(index) > rectArr.getValueAt(j)) {
                    index = j;
                }
            }
        }
        SequentialTransition selectionSort = JumpAnimation.getWaveAction(rectArr, 0, i, byAscendOrder);

        if (index != i) {
            selectionSort.getChildren().add(SwapAnimation.getSwap(rectArr, index, i, byAscendOrder, rectLine));
        }

        selectionSort.setOnFinished(event -> {
            rectArr.getAt(i).setColor(Color.GRAY);
            setNextSelect(rectArr, byAscendOrder, rectLine);
        });
        selectionSort.play();

    }


    private static void setNextSelect(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        if (i > 1) {
            i--;
            index = 0;
            for (j = 1; j < i; j++) {
                if (byAscendOrder) {
                    // By ascend order -> find maxIndex
                    if (rectArr.getValueAt(index) < rectArr.getValueAt(j)) {
                        index = j;
                    }
                } else {
                    // By descend order -> find minIndex
                    if (rectArr.getValueAt(index) > rectArr.getValueAt(j)) {
                        index = j;
                    }
                }
            }
            SequentialTransition nextSelection = JumpAnimation.getWaveAction(rectArr, 0, i, byAscendOrder);

            if (index != i) {
                nextSelection.getChildren().add(SwapAnimation.getSwap(rectArr, index, i, byAscendOrder, rectLine));
            }
            nextSelection.setOnFinished(event -> {
                rectArr.getAt(i).setColor(Color.GRAY);
                setNextSelect(rectArr, byAscendOrder, rectLine);
            });
            nextSelection.play();

        } else {
            rectArr.getAt(0).setColor(Color.GRAY);
            rectArr.getAt(1).setColor(Color.GRAY);
            System.out.println("Done");
        }
    }
}
