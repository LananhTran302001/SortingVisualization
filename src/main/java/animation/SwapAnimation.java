package animation;

import control.RectNodeArray;

import globalVar.Count;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

public class SwapAnimation {

    private static void underSwap(RectNodeArray rectArr, int index01, int index02) {
        rectArr.swap(index01, index02);
        rectArr.getViewAt(index01).setLayoutX(0);
        rectArr.getViewAt(index02).setLayoutX(0);
    }

    private static SequentialTransition getSwap(RectNodeArray rectArr, int index01, int index02, boolean byAscendOrder) {
        SequentialTransition sequentialTransition = new SequentialTransition();

        TranslateTransition jumpUp01 = JumpAnimation.getJumpUpAction(rectArr.getViewAt(index01));
        TranslateTransition jumpUp02 = JumpAnimation.getJumpUpAction(rectArr.getViewAt(index02));
        ParallelTransition jumpUp = new ParallelTransition(jumpUp01, jumpUp02);

        sequentialTransition.getChildren().add(jumpUp);

        boolean ascend = ((index02 - index01) * (rectArr.getValueAt(index02) - rectArr.getValueAt(index01))) > 0;
        if (ascend != byAscendOrder) {
            int distance = rectArr.getDistance(index01, index02);
            TranslateTransition move01 = MoveAnimation.getMoveRight(rectArr.getViewAt(index01), distance);
            TranslateTransition move02 = MoveAnimation.getMoveLeft(rectArr.getViewAt(index02), distance);
            ParallelTransition swap = new ParallelTransition(move01, move02);

            swap.setOnFinished(event -> underSwap(rectArr, index01, index02));
            Count.countSwap();
            sequentialTransition.getChildren().add(swap);
        }

        TranslateTransition jumpDown01 = JumpAnimation.getJumpDownAction(rectArr.getViewAt(index01));
        TranslateTransition jumpDown02 = JumpAnimation.getJumpDownAction(rectArr.getViewAt(index02));
        ParallelTransition jumpDown = new ParallelTransition(jumpDown01, jumpDown02);

        sequentialTransition.getChildren().add(jumpDown);

        return sequentialTransition;
    }

    protected static SequentialTransition getSwapByAscend(RectNodeArray rectArr, int index01, int index02) {
        return getSwap(rectArr, index01, index02, true);
    }

    public static void playSwapByAscend(RectNodeArray rectArr, int index01, int index02) {
        getSwapByAscend(rectArr, index01, index02).play();
        if (rectArr.getViewAt(index01).getLayoutX() != 0) {
            System.out.println("Swapped by ascend order");
        }
    }

    public static SequentialTransition getSwapByDescend(RectNodeArray rectArr, int index01, int index02) {
        return getSwap(rectArr, index01, index02, false);
    }

    public static void playSwapByDescend(RectNodeArray rectArr, int index01, int index02) {
        getSwapByDescend(rectArr, index01, index02).play();
        if (rectArr.getViewAt(index01).getLayoutX() != 0) {
            System.out.println("Swapped by descend order");
        }
    }

}
