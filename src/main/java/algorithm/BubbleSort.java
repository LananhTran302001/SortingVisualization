package algorithm;

import animation.SwapAnimation;
import control.RectNodeArray;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BubbleSort {

    private static int i = 0;
    private static int j = 0;

    public static void startBubbleSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        i = 0;
        j = 0;
        SequentialTransition bubbleSort = SwapAnimation.getSwap(rectArr, 0, 1, byAscendOrder, rectLine);
        bubbleSort.setOnFinished(event -> setNextSwap(rectArr, byAscendOrder, rectLine));
        bubbleSort.play();
    }


    private static void setNextSwap(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        if (i < rectArr.size() - 2 || j < rectArr.size() - 2 - i) {
            if (j < rectArr.size() - i - 2) {
                j++;
            } else {
                rectArr.getAt(j + 1).setColor(Color.GRAY);
                i++;
                j = 0;
            }
            SequentialTransition nextSwap = SwapAnimation.getSwap(rectArr, j, j + 1, byAscendOrder, rectLine);
            nextSwap.setOnFinished(event -> setNextSwap(rectArr, byAscendOrder, rectLine));
            nextSwap.play();
        } else {
            rectArr.getAt(0).setColor(Color.GRAY);
            rectArr.getAt(1).setColor(Color.GRAY);
            System.out.println("Done");
        }
    }
}
