package animation;

import control.RectNodeArray;

import globalVar.Count;
import globalVar.TimeDelay;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class SwapAnimation {

    private static void underSwap(RectNodeArray rectArr, int index01, int index02, HBox rectLine, int halfDistance) {

        System.out.println("Under swap");
        rectArr.swap(index01, index02);
        rectArr.getViewAt(index01).setTranslateX(halfDistance);
        rectArr.getViewAt(index02).setTranslateX(-halfDistance);
        rectLine.getChildren().setAll(rectArr.getListView());
    }

    public static SequentialTransition getSwap(RectNodeArray rectArr, int index01, int index02, boolean byAscendOrder, HBox rectLine) {

        SequentialTransition sequentialTransition = new SequentialTransition();

        TranslateTransition jumpUp01 = JumpAnimation.getJumpUpAction(rectArr.getViewAt(index01));
        TranslateTransition jumpUp02 = JumpAnimation.getJumpUpAction(rectArr.getViewAt(index02));
        ParallelTransition jumpUp = new ParallelTransition(jumpUp01, jumpUp02);

        sequentialTransition.getChildren().add(jumpUp);

        PauseTransition pause = new PauseTransition(Duration.seconds(TimeDelay.PAUSE_DURATION));
        sequentialTransition.getChildren().add(pause);


        boolean ascend = ((index02 - index01) * (rectArr.getValueAt(index02) - rectArr.getValueAt(index01))) > 0;
        if (ascend != byAscendOrder) {

            int distance = rectArr.getDistance(index01, index02);
            TranslateTransition move01 = MoveAnimation.getMoveRight(rectArr.getViewAt(index01), distance / 2);
            TranslateTransition move02 = MoveAnimation.getMoveLeft(rectArr.getViewAt(index02), distance / 2);
            ParallelTransition beforeSwap = new ParallelTransition(move01, move02);

            beforeSwap.setOnFinished(e1 -> {
                underSwap(rectArr, index01, index02, rectLine, distance / 2);
            });

            Count.countSwap();
            sequentialTransition.getChildren().add(beforeSwap);

            // After underSwapping, rectNode has new position
            // Now, let's come to that position (translateX = 0)
            TranslateTransition moveNext01 = MoveAnimation.getMoveLeft(rectArr.getViewAt(index01), 0);
            TranslateTransition moveNext02 = MoveAnimation.getMoveRight(rectArr.getViewAt(index02), 0);
            ParallelTransition afterSwap = new ParallelTransition(moveNext01, moveNext02);

            sequentialTransition.getChildren().add(afterSwap);
        }

        TranslateTransition jumpDown01 = JumpAnimation.getJumpDownAction(rectArr.getViewAt(index01));
        TranslateTransition jumpDown02 = JumpAnimation.getJumpDownAction(rectArr.getViewAt(index02));
        ParallelTransition jumpDown = new ParallelTransition(jumpDown01, jumpDown02);

        sequentialTransition.getChildren().add(jumpDown);



        return sequentialTransition;
    }

    public static SequentialTransition getSwapByAscend(RectNodeArray rectArr, int index01, int index02, HBox rectLine) {
        return getSwap(rectArr, index01, index02, true, rectLine);
    }

    public static boolean playSwapByAscend(RectNodeArray rectArr, int index01, int index02, HBox rectLine) {
        getSwapByAscend(rectArr, index01, index02, rectLine).play();
        if (rectArr.getViewAt(index01).getLayoutX() != 0) {
            System.out.println("Swapped by ascend order");
        }
        return true;
    }

    public static SequentialTransition getSwapByDescend(RectNodeArray rectArr, int index01, int index02, HBox rectLine) {
        return getSwap(rectArr, index01, index02, false, rectLine);
    }

    public static void playSwapByDescend(RectNodeArray rectArr, int index01, int index02, HBox rectLine) {
        getSwapByDescend(rectArr, index01, index02, rectLine).play();
        if (rectArr.getViewAt(index01).getLayoutX() != 0) {
            System.out.println("Swapped by descend order");
        }
    }

}
