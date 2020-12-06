package animation;

import control.RectNodeArray;
import element.RectNode;
import globalVar.AppConstants;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MergeAnimation {

    private static List<RectNode> lastArr = new ArrayList<>();
    private static int i;
    private static int j;
    private static int start_left = 0;

    private static SequentialTransition getMerge(RectNodeArray leftArr, RectNodeArray rightArr, boolean byAscendOrder, HBox rectLine) {
        SequentialTransition mergeAni = new SequentialTransition();

        int distance;
        if (i < leftArr.size() && j < rightArr.size()) {
            if (leftArr.getValueAt(i) < rightArr.getValueAt(j) == byAscendOrder) {
                distance = leftArr.getDistance(i, start_left);
                mergeAni.getChildren().add(MoveAnimation.getMove(leftArr.getViewAt(i), distance, 0));
                lastArr.add(leftArr.getAt(i));
                i++;
            } else {
                distance = leftArr.getDistance(leftArr.size() + j, start_left);
                mergeAni.getChildren().add(MoveAnimation.getMove(rightArr.getViewAt(i), distance, 0));
                lastArr.add(rightArr.getAt(j));
                j++;
            }
        } else if (i < leftArr.size()) {
            distance = leftArr.getDistance(i, start_left);
            mergeAni.getChildren().add(MoveAnimation.getMove(leftArr.getViewAt(i), distance, 0));
            lastArr.add(leftArr.getAt(i));
            i++;
        } else if (j < rightArr.size()) {
            distance = leftArr.getDistance(leftArr.size() + j, start_left);
            mergeAni.getChildren().add(MoveAnimation.getMove(rightArr.getViewAt(i), distance, 0));
            lastArr.add(rightArr.getAt(j));
            j++;
        }
        start_left++;

        mergeAni.setOnFinished(event -> setNextMerge(leftArr, rightArr, byAscendOrder, rectLine));

        return mergeAni;
    }

    private static SequentialTransition getBeforeMerge(RectNodeArray leftArr, RectNodeArray rightArr, boolean byAscendOrder, HBox rectLine) {
        SequentialTransition beforeMerge = new SequentialTransition();
        for (i = 0; i < leftArr.size(); i++) {
            RectNode rect = leftArr.getAt(i);
            TranslateTransition transition = JumpAnimation.getJumpUpAction(rect.getView());
            transition.setOnFinished(event -> rect.setColor(Color.valueOf(AppConstants.LEFT_COLOR)));
            beforeMerge.getChildren().add(transition);
        }
        for (i = 0; i < rightArr.size(); i++) {
            RectNode rect = rightArr.getAt(i);
            TranslateTransition transition = JumpAnimation.getJumpUpAction(rect.getView());
            transition.setOnFinished(event -> rect.setColor(Color.valueOf(AppConstants.RIGHT_COLOR)));
            beforeMerge.getChildren().add(transition);
        }

        i = 0;
        j = 0;
        start_left = 0;

       beforeMerge.getChildren().add(getMerge(leftArr, rightArr, byAscendOrder, rectLine));

        return beforeMerge;
    }



    private static void setNextMerge(RectNodeArray leftArr, RectNodeArray rightArr, boolean byAscendOrder, HBox rectLine) {
        if (i < leftArr.size() || j < rightArr.size()) {
            SequentialTransition nextMerge = getMerge(leftArr, rightArr, byAscendOrder, rectLine);
            nextMerge.setOnFinished(event -> setNextMerge(leftArr, rightArr, byAscendOrder, rectLine));
            nextMerge.play();
        } else {
            System.out.println("Done");
        }
    }

}
