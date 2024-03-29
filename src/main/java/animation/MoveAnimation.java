package animation;

import globalVar.TimeDelay;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MoveAnimation {

    protected static TranslateTransition getMoveRight(StackPane sprite, int destination) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(sprite);
        transition.setDuration(Duration.seconds(TimeDelay.MOVE_DURATION));
        transition.setAutoReverse(false);
        transition.setCycleCount(1);
        transition.setToX(destination);

        return transition;
    }

    protected static TranslateTransition getMoveLeft(StackPane sprite, int destination) {
        return getMoveRight(sprite, -destination);
    }


    protected static ParallelTransition getMove(StackPane sprite, int destinationX, int destinationY) {
        ParallelTransition transition = new ParallelTransition();
        transition.getChildren().add(JumpAnimation.getJumpUpAction(sprite, destinationY));
        transition.getChildren().add(MoveAnimation.getMoveRight(sprite, destinationX));

        return transition;
    }

    public static void playMovingLeft(StackPane sprite, int distance) {
        getMoveLeft(sprite, distance).play();
    }

    public static void playMovingRight(StackPane sprite, int distance) {
        getMoveRight(sprite, distance).play();
    }


}
