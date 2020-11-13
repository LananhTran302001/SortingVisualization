package algorithm;

import animation.SwapAnimation;
import control.RectNodeArray;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SelectionSort {
    private static int i = 0;
    private static int j = 0;
    private static int maxIndex = 0;

    public static void startSelectionSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        i = rectArr.size() - 1;
        j = 1;
        SequentialTransition selectionSort = SwapAnimation.getSwapByAscend(rectArr, 0, 1, rectLine);
        selectionSort.setOnFinished(event -> setNextSwap(rectArr, rectLine));
        selectionSort.play();

    }


    private static void setNextSwap(RectNodeArray rectArr, HBox rectLine) {
        if (i < 2 || j < i) {
            if (j < i - 1) {
                j++;
            } else {
                rectArr.getAt(j + 1).setColor(Color.GRAY);
                i++;
                j = 0;
            }
            SequentialTransition nextSwap = SwapAnimation.getSwapByAscend(rectArr, j, j + 1, rectLine);
            nextSwap.setOnFinished(event -> setNextSwap(rectArr, rectLine));
            nextSwap.play();
        } else {
            rectArr.getAt(0).setColor(Color.GRAY);
            rectArr.getAt(1).setColor(Color.GRAY);
            System.out.println("Done");
        }
    }
}
