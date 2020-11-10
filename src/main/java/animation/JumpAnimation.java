package animation;

import constant.AppConstants;
import constant.TimeDelay;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
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

    public static void playJumpingUpDown(StackPane sprite) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.setToY(AppConstants.JUMP_HEIGHT);
        transition.play();
    }
}
