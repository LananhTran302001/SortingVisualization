package algorithm;

import animation.JumpAnimation;
import animation.SwapAnimation;
import control.RectNodeArray;
import globalVar.TimeDelay;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class InsertionSort {

    private static int i = 1;
    private static int j = 0;

    public static void startInsertionSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        i = 1;
        j = 0;
        TranslateTransition insertionSort = JumpAnimation.getJumpUpAction(rectArr.getViewAt(j));

        insertionSort.setOnFinished(event -> setNextInsert(rectArr, byAscendOrder, rectLine));

        insertionSort.play();
    }

    private static void setNextInsert(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        if (i <= rectArr.size() && j >= 0) {

            SequentialTransition nextInsert = new SequentialTransition();

            boolean insertHere;
            if (byAscendOrder) {
                // neu sap xep tang dan, insert sau pan tu nho hon no.
                insertHere = (j == 0) || (rectArr.getValueAt(j - 1) <= rectArr.getValueAt(j));
            } else {
                // neu sap xep descend order, insert sau pan tu lon hon no.
                insertHere = (j == 0) || (rectArr.getValueAt(j - 1) >= rectArr.getValueAt(j));
            }

            if (insertHere) {
                SequentialTransition insert = new SequentialTransition();

                TranslateTransition jumpBack = JumpAnimation.getJumpBackAction(rectArr.getViewAt(j));
                jumpBack.setOnFinished(event -> {
                    rectArr.getAt(j).setColor(Color.GRAY);
                    i++;
                    j = i - 1;
                    if (i <= (rectArr).size()) {
                        JumpAnimation.playJumpingUp(rectArr.getViewAt(j));
                    }
                });

                insert.getChildren().add(jumpBack);
                insert.getChildren().add(new PauseTransition(Duration.seconds(TimeDelay.PAUSE_DURATION)));

                nextInsert.getChildren().add(insert);

            } else {
                SequentialTransition movingForward = new SequentialTransition(
                        new PauseTransition(Duration.seconds(TimeDelay.PAUSE_DURATION)),
                        SwapAnimation.getUnderSwap(rectArr, j - 1, j, rectLine));

                movingForward.setOnFinished(event -> j--);

                nextInsert.getChildren().add(movingForward);
            }

            nextInsert.setOnFinished(event -> setNextInsert(rectArr, byAscendOrder, rectLine));
            nextInsert.play();

        } else {
            System.out.println("Done");
        }
    }



}
