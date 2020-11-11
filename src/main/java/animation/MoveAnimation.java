package animation;

import globalVar.TimeDelay;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MoveAnimation {

    protected static TranslateTransition getMoveRight(StackPane sprite, int distance) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.SWAP_DURATION));
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.setToX(distance);

        return transition;
    }

    protected static TranslateTransition getMoveLeft(StackPane sprite, int distance) {
        return getMoveRight(sprite, -distance);
    }


    public static void playMovingLeft(StackPane sprite, int distance) {
        getMoveLeft(sprite, distance).play();
    }

    public static void playMovingRight(StackPane sprite, int distance) {
        getMoveRight(sprite, distance).play();
    }

}
