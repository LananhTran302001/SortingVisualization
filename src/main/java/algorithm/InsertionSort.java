package algorithm;

import animation.JumpAnimation;
import animation.SwapAnimation;
import control.RectNodeArray;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class InsertionSort {

    private static int i = 0;
    private static int j = 0;
    private static boolean placed = false;

    public static void startInsertionSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        i = 0;
        j = 0;

        rectArr.getAt(i).setColor(Color.GRAY);
        rectLine.getChildren().setAll(rectArr.getListView());

        i++;
        j = i - 1;
        SequentialTransition insertionSort = new SequentialTransition(
                JumpAnimation.getJumpUpAction(rectArr.getViewAt(i)),
                SwapAnimation.getUnderSwap(rectArr, j, i, rectLine)
        );

        insertionSort.play();
    }

    private static void setNextInsert(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {

    }
}
