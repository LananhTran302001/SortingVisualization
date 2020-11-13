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
    public static void playJumpingUp(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.setToY(AppConstants.JUMP_HEIGHT);
        transition.play();
    }

    public static void playJumpingDown(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.setToY(0);
        transition.play();
    }

    public static void playJumpingUpDown(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.setToY(AppConstants.JUMP_HEIGHT);
        transition.play();
    }

    public static TranslateTransition getJumpUpAction(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.setToY(AppConstants.JUMP_HEIGHT);
        return transition;
    }

    protected static TranslateTransition getJumpDownAction(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.setToY(0);
        return transition;
    }

    protected static TranslateTransition getJumpUpDownAction(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.setToY(AppConstants.JUMP_HEIGHT);

        return transition;
    }

    public static SequentialTransition getSequentJumpUpDownAction(RectNodeArray rectArr, int fromIndex, int toIndex) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        int indexMax = 0;
        int oldIndexMax = 0;
        for (int i = fromIndex; i <= toIndex; i++) {
            if (rectArr.getValueAt(indexMax) < rectArr.getValueAt(i)) {
                oldIndexMax = indexMax;
                indexMax = i;

                TranslateTransition turnRed = getJumpUpDownAction(rectArr.getViewAt(indexMax));
                int finalIndexMax = indexMax;
                int finalOldIndexMax = oldIndexMax;
                turnRed.setOnFinished(event -> {
                    rectArr.getAt(finalOldIndexMax).setOriginalColor();
                    rectArr.getAt(finalIndexMax).setColor(Color.RED);
                });
                sequentialTransition.getChildren().add(turnRed);
            } else {
                sequentialTransition.getChildren().add(getJumpUpDownAction(rectArr.getViewAt(i)));
            }
        }

        return sequentialTransition;
    }
}
