package algorithm;

import animation.MergeAnimation;
import control.RectNodeArray;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.HBox;

public class MergeSort {

    private static int current_size = 1;
    private static int left_start = 0;

    public static void startMergeSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        int mid = Math.min(left_start + current_size - 1, rectArr.size() - 1);
        int right_end = Math.min(left_start + 2 * current_size - 1, rectArr.size() - 1);

        RectNodeArray leftArr = new RectNodeArray(rectArr, left_start, mid);
        RectNodeArray rightArr = new RectNodeArray(rectArr, mid + 1, right_end);

        SequentialTransition mergeSort = MergeAnimation.getMerge(leftArr, rightArr, byAscendOrder, rectLine);

        //mergeSort.setOnFinished(event -> setNextMerge(rectArr, byAscendOrder, rectLine));

        mergeSort.play();
    }



    private static void setNextMerge(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {
        if (current_size < rectArr.size() || left_start < rectArr.size() - 1) {
            if (left_start < rectArr.size() - 1) {
                left_start += 2 * current_size;
            } else {
                current_size *= 2;
            }

            int mid = Math.min(left_start + current_size - 1, rectArr.size() - 1);
            int right_end = Math.min(left_start + 2 * current_size - 1, rectArr.size() - 1);

            RectNodeArray leftArr = new RectNodeArray(rectArr, left_start, mid);
            RectNodeArray rightArr = new RectNodeArray(rectArr, mid, right_end);

            SequentialTransition nextMerge = MergeAnimation.getMerge(leftArr, rightArr, byAscendOrder, rectLine);
            nextMerge.setOnFinished(event -> setNextMerge(rectArr, byAscendOrder, rectLine));

            nextMerge.play();
        }
    }


}
