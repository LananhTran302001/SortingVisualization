package globalVar;

public class TimeDelay {

    public static double JUMP_DURATION = 0.1;

    public static void setJumpDuration(double second) {
        if (second > 0) {
            JUMP_DURATION = second;
        }
    }

    public static double MOVE_DURATION = 0.1;

    public static void setMoveDuration(double second) {
        if (second > 0) {
            MOVE_DURATION = second;
        }
    }

    public static double PAUSE_DURATION = 0.1;

    public static void setPauseDuration(double second) {
        if (second > 0) {
            PAUSE_DURATION = second;
        }
    }

}
