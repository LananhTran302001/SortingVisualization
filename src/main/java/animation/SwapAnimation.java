package animation;

import control.RectNodeArray;

import globalVar.Count;
import globalVar.TimeDelay;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class SwapAnimation {

    private static void underSwap(RectNodeArray rectArr, int index01, int index02, HBox rectLine, int halfDistance) {
        System.out.println("Size: " + rectArr.size());
        System.out.println(rectArr.getViewAt(index01).getLayoutX());
        System.out.println(rectArr.getViewAt(index02).getLayoutX());
        System.out.println("Under swap");


        rectArr.swap(index01, index02);
        //rectArr.getViewAt(index01).setLayoutX(halfDistance);
        //rectArr.getViewAt(index02).setLayoutX(-halfDistance);
        System.out.println(rectArr.getViewAt(index01).getLayoutX());
        System.out.println(rectArr.getViewAt(index02).getLayoutX());
        System.out.println("Size after: " + rectArr.size());


        rectLine.getChildren().setAll(rectArr.getListView());
    }

    private static SequentialTransition getSwap(RectNodeArray rectArr, int index01, int index02, boolean byAscendOrder, HBox rectLine) {
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

            //beforeSwap.setOnFinished(event -> underSwap(rectArr, index01, index02, rectLine, distance / 2));

            Count.countSwap();
            sequentialTransition.getChildren().add(beforeSwap);

            TranslateTransition moveNext01 = MoveAnimation.getMoveLeft(rectArr.getViewAt(index01), distance / 2);
            TranslateTransition moveNext02 = MoveAnimation.getMoveRight(rectArr.getViewAt(index02), distance / 2);
            ParallelTransition afterSwap = new ParallelTransition(moveNext01, moveNext02);

            //sequentialTransition.getChildren().add(afterSwap);
        }

        PauseTransition pause02 = new PauseTransition(Duration.seconds(TimeDelay.PAUSE_DURATION));
        //sequentialTransition.getChildren().add(pause02);

        TranslateTransition jumpDown01 = JumpAnimation.getJumpDownAction(rectArr.getViewAt(index01));
        TranslateTransition jumpDown02 = JumpAnimation.getJumpDownAction(rectArr.getViewAt(index02));
        ParallelTransition jumpDown = new ParallelTransition(jumpDown01, jumpDown02);

        //sequentialTransition.getChildren().add(jumpDown);

        return sequentialTransition;
    }

    protected static SequentialTransition getSwapByAscend(RectNodeArray rectArr, int index01, int index02, HBox rectLine) {
        return getSwap(rectArr, index01, index02, true, rectLine);
    }

    public static void playSwapByAscend(RectNodeArray rectArr, int index01, int index02, HBox rectLine) {
        getSwapByAscend(rectArr, index01, index02, rectLine).play();
        if (rectArr.getViewAt(index01).getLayoutX() != 0) {
            System.out.println("Swapped by ascend order");
        }
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
