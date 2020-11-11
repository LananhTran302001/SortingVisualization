package globalVar;

public class Count {

    private static int swapCount = 0;

    public static void countSwap() {
        swapCount++;
    }

    public static int getSwapCount() {
        return swapCount;
    }

    public static void resetSwapCount() {
        swapCount = 0;
    }
}
