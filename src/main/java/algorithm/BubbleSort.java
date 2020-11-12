package algorithm;

import animation.SwapAnimation;
import control.RectNodeArray;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class BubbleSort {

    public static void startBubbleSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        SequentialTransition bubbleSort = new SequentialTransition();
        int n = rectArr.size();


        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (! SwapAnimation.playSwapByAscend(rectArr, i, j, rectLine)) {
                    break;
                }
            }
        }

    }
}
