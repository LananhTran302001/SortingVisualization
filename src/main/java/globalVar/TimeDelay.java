package globalVar;

public class TimeDelay {

    public static double JUMP_DURATION = 0.5;

    public static void setJumpDuration(double second) {
        if (second > 0) {
            JUMP_DURATION = second;
        }
    }

    public static double SWAP_DURATION = 0.5;

    public static void setSwapDuration(double second) {
        if (second > 0) {
            SWAP_DURATION = second;
        }
    }

}
