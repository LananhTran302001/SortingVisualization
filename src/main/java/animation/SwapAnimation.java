package animation;

import constant.AppConstants;
import constant.TimeDelay;
import control.RectNodeArray;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class SwapAnimation {
    public static void playSwapByAscend(RectNodeArray rectArr, int index01, int index02) {
        TranslateTransition transition01 = new TranslateTransition();
        transition01.setNode(rectArr.getViewAt(index01));
        transition01.setDuration(Duration.seconds(TimeDelay.JUMP_DURATION));
        transition01.setAutoReverse(true);
        transition01.setCycleCount(2);
        transition01.setToY(AppConstants.JUMP_HEIGHT);


        transition01.play();
    }



}
