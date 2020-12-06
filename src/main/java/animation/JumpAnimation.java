package animation;

import control.RectNodeArray;
import globalVar.AppConstants;
import globalVar.TimeDelay;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class JumpAnimation {

    private static TranslateTransition getJumpAction(StackPane sprite, int toHeight) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.setToY(toHeight);

        return transition;
    }

    public static TranslateTransition getJumpUpAction(StackPane sprite) {
        return getJumpAction(sprite, AppConstants.JUMP_HEIGHT);
    }

    public static TranslateTransition getJumpUpAction(StackPane sprite, int destination) {
        return getJumpAction(sprite, destination);
    }

    public static TranslateTransition getJumpDownAction(StackPane sprite) {
        return getJumpAction(sprite, -AppConstants.JUMP_HEIGHT);
    }

    public static TranslateTransition getJumpBackAction(StackPane sprite) {
        return getJumpAction(sprite, 0);
    }

    protected static TranslateTransition getJumpUpBackAction(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.setToY(AppConstants.JUMP_HEIGHT);

        return transition;
    }

    protected static TranslateTransition getJumpDownBackAction(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.setToY(-AppConstants.JUMP_HEIGHT);

        return transition;
    }


    public static SequentialTransition getWaveAction(RectNodeArray rectArr, int fromIndex, int toIndex, boolean byAscendOrder) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        int index = fromIndex;
        int oldIndex;
        for (int i = fromIndex; i <= toIndex; i++) {

            boolean changeIndex;
            if (byAscendOrder) {
                // By ascend order -> find maxIndex
                changeIndex = (i == fromIndex) || (rectArr.getValueAt(index) < rectArr.getValueAt(i));
            } else {
                // By descend order -> find minIndex
                changeIndex = (i == fromIndex) || (rectArr.getValueAt(index) > rectArr.getValueAt(i));
            }

            if (changeIndex) {
                oldIndex = index;
                index = i;

                TranslateTransition turnRed = getJumpUpBackAction(rectArr.getViewAt(index));
                int finalIndex = index;
                int finalOldIndex = oldIndex;
                turnRed.setOnFinished(event -> {
                    rectArr.getAt(finalOldIndex).setOriginalColor();
                    rectArr.getAt(finalIndex).setColor(Color.RED);
                });
                sequentialTransition.getChildren().add(turnRed);
            } else {
                sequentialTransition.getChildren().add(getJumpUpBackAction(rectArr.getViewAt(i)));
            }
        }

        return sequentialTransition;
    }

    public static void playJumpingUp(StackPane sprite) {
        getJumpUpAction(sprite).play();
    }

    public static void playJumpingBack(StackPane sprite) {
        getJumpBackAction(sprite).play();
    }

    public static void playJumpingUpBack(StackPane sprite) {
        getJumpUpBackAction(sprite).play();
    }

}
