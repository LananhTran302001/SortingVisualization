package algorithm;

import animation.SwapAnimation;
import control.RectNodeArray;
import globalVar.AppConstants;

import javafx.animation.SequentialTransition;
import javafx.scene.layout.HBox;

import java.io.*;


public class BubbleSort {

    private static void writeToFile(RectNodeArray rectArr, boolean byAscendOrder) {
        int n = rectArr.size();

        int[] numArr = new int[n];

        StringBuilder line = new StringBuilder();
        for (int i = 0; i < n; i++) {
            numArr[i] = rectArr.getValueAt(i);
            line.append(numArr[i]).append(" ");
        }
        line.deleteCharAt(line.length() - 1);
        line.append("\n");


        try {
            FileWriter writer = new FileWriter(AppConstants.PATH_LOGGER_FILE);
            writer.write(line.toString());
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    line = new StringBuilder();
                    if (numArr[i] > numArr[j]) {
                        int temp = numArr[i];
                        numArr[i] = numArr[j];
                        numArr[j] = temp;
                    }
                    for (int k = 0; k < n; k++) {
                        line.append(numArr[k]).append(" ");
                    }
                    line.deleteCharAt(line.length() - 1);
                    line.append("\n");

                    writer.write(line.toString());
                }
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startBubbleSort(RectNodeArray rectArr, boolean byAscendOrder, HBox rectLine) {

        writeToFile(rectArr, byAscendOrder);

        SequentialTransition bubbleSort = new SequentialTransition();
        int n = rectArr.size();
        int[] numbers = new int[n];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(AppConstants.PATH_LOGGER_FILE));



            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    String inputLine = reader.readLine();
                    String[] numbersStr = inputLine.split(" ", n);
                    for (int k = 0; k < n; k++) {
                        numbers[k] = Integer.parseInt(numbersStr[k]);
                    }
                    bubbleSort.getChildren().add(SwapAnimation.getSwapByAscend(numbers, i, j, rectLine));
                }
            }
            reader.close();
            bubbleSort.play();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
