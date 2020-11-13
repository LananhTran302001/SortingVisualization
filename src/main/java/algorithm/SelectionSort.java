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
    private static int maxIndex = 0;

    public static void startSelectionSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        i = rectArr.size() - 1;
        for (j = 1; j < i; j++) {
            if (rectArr.getValueAt(maxIndex) < rectArr.getValueAt(j)) {
                maxIndex = j;
            }
        }
        SequentialTransition selectionSort = JumpAnimation.getSequentJumpUpDownAction(rectArr, 1, i);

        if (maxIndex != i) {
            selectionSort.getChildren().add(SwapAnimation.getSwap(rectArr, maxIndex, i, byAscendOrder, rectLine));
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
            maxIndex = 0;
            for (j = 1; j < i; j++) {
                if (rectArr.getValueAt(maxIndex) < rectArr.getValueAt(j)) {
                    maxIndex = j;
                }
            }
            SequentialTransition nextSelection = JumpAnimation.getSequentJumpUpDownAction(rectArr, 1, i);

            if (maxIndex != i) {
                nextSelection.getChildren().add(SwapAnimation.getSwap(rectArr, maxIndex, i, byAscendOrder, rectLine));
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
