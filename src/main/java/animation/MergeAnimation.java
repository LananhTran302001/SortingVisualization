package animation;

import control.RectNodeArray;
import element.RectNode;
import globalVar.AppConstants;
import globalVar.TimeDelay;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class MergeAnimation {


    private static void underMerge(RectNodeArray oldArr, int[] newArr, HBox rectLine) {
        int n = oldArr.size();
        for (int i = 0; i < n; i++) {
            oldArr.updateNodeAt(i, newArr[i]);
        }
        rectLine.getChildren().clear();
        rectLine.getChildren().addAll(oldArr.getListView());
    }

    private static SequentialTransition getTempMerge(int start_left, RectNodeArray leftArr, RectNodeArray rightArr,
                                                     int[] newArr, boolean byAscendOrder) {
        SequentialTransition merge = new SequentialTransition();

        int i = 0;
        int j = 0;
        int curr = start_left;

        while (i < leftArr.size() && j < rightArr.size()) {
            if (leftArr.getValueAt(i) < rightArr.getValueAt(j) == byAscendOrder) {
                int distance = leftArr.getDistance(i, start_left);
                merge.getChildren().add(MoveAnimation.getMove(leftArr.getViewAt(i), distance, 0));
                newArr[curr] = leftArr.getValueAt(i);
                i++;
            } else {
                int distance = leftArr.getDistance(leftArr.size() + j, start_left);
                merge.getChildren().add(MoveAnimation.getMove(rightArr.getViewAt(j), distance, 0));
                newArr[curr] = rightArr.getValueAt(j);
                j++;
            }
            curr++;
        }

        while (i < leftArr.size()) {
            int distance = leftArr.getDistance(i, start_left);
            merge.getChildren().add(MoveAnimation.getMove(leftArr.getViewAt(i), distance, 0));
            newArr[curr] = leftArr.getValueAt(i);
            i++;
            curr++;
        }

        while (j < rightArr.size()) {
            int distance = leftArr.getDistance(leftArr.size() + j, start_left);
            merge.getChildren().add(MoveAnimation.getMove(rightArr.getViewAt(j), distance, 0));
            newArr[curr] = rightArr.getValueAt(j);
            j++;
            curr++;
        }

        return merge;
    }

    private static SequentialTransition getBeforeMerge(RectNodeArray leftArr, RectNodeArray rightArr, boolean byAscendOrder, HBox rectLine) {
        SequentialTransition beforeMerge = new SequentialTransition();
        for (int i = 0; i < leftArr.size(); i++) {
            RectNode rect = leftArr.getAt(i);
            TranslateTransition transition = JumpAnimation.getJumpUpAction(rect.getView());
            transition.setOnFinished(event -> rect.setColor(Color.valueOf(AppConstants.LEFT_COLOR)));
            beforeMerge.getChildren().add(transition);
        }
        for (int i = 0; i < rightArr.size(); i++) {
            RectNode rect = rightArr.getAt(i);
            TranslateTransition transition = JumpAnimation.getJumpUpAction(rect.getView());
            transition.setOnFinished(event -> rect.setColor(Color.valueOf(AppConstants.RIGHT_COLOR)));
            beforeMerge.getChildren().add(transition);
        }

        beforeMerge.getChildren().add(new PauseTransition(Duration.seconds(TimeDelay.PAUSE_DURATION)));

        return beforeMerge;
    }

    public static SequentialTransition getMerge(int left_start, RectNodeArray leftArr, RectNodeArray rightArr,
                                                RectNodeArray numArr, boolean byAscendOrder, HBox rectLine) {
        int n = numArr.size();
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[i] = numArr.getValueAt(i);
        }
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().add(getBeforeMerge(leftArr, rightArr, byAscendOrder, rectLine));
        SequentialTransition sq = getTempMerge(left_start, leftArr, rightArr, newArr, byAscendOrder);
        //sq.setOnFinished(event -> underMerge(numArr, newArr, rectLine));

        sequentialTransition.getChildren().add(sq);
        sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(TimeDelay.PAUSE_DURATION)));
        return sequentialTransition;
    }

}
