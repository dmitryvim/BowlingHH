import java.util.Iterator;
import java.util.LinkedList;

public class Frame {
    final public static int KEGGLE_COUNT = 10;
    final public static int THROWS_IN_FRAME = 2;
    final public static int THROWS_IN_LAST_FRAME = 3;

    final public static char STRIKE_CHAR = 'X';
    final public static char SPARE_CHAR = '/';
    final public static char FRAME_DELIMITER_CHAR = '|';

    //private LinkedList<Integer> ____ballThrows;
    private int[] ballThrows;
    private int throwsCount;
    private  boolean isLastFrame = false;
    private Frame nextFrame = null;

    private Frame() {
        ballThrows = new int[3];
        throwsCount = 0;
    }

    public static Frame build() {
        return new Frame();
    }

    public static Frame buildLastFrame() {
        Frame frame = new Frame();
        frame.isLastFrame = true;
        return frame;
    }

    public boolean isClosed() {
        return (!isLastFrame && size() == THROWS_IN_FRAME)
                || (!isLastFrame && isStrike())
                || (isLastFrame && size() == THROWS_IN_FRAME && keggleCount() < KEGGLE_COUNT)
                || (isLastFrame && size() == THROWS_IN_LAST_FRAME);
    }

    public boolean isStrike() {
        return (!isLastFrame && size() == 1 && ballThrows[0] == KEGGLE_COUNT);
    }

    public boolean isSpare() {
        return (!isLastFrame && size() == THROWS_IN_FRAME && ballThrows[0] + ballThrows[1] == KEGGLE_COUNT);
    }

    public int size() {
        return throwsCount;
    }

    public Frame throwBall(int keggleCount) {
        if (isClosed()) {
            throw new RuntimeException("Frame was closed.\n");
        }

        addThrow(keggleCount);
        return this;
    }

    private void addThrow(int keggleCount) {
        ballThrows[throwsCount] = keggleCount;
        throwsCount++;
    }

    public int keggleCount() {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            sum += ballThrows[i];
        }
        return sum;
    }

    public void setNextFrame(Frame frame) {
        nextFrame = frame;
    }

    public int getPoints() {
        int sum = keggleCount();

        if (nextFrame != null) {
            if (isSpare()) {
                sum += nextFrame.getOneThrowKeggleCount();
            } else if (isStrike()) {
                sum += nextFrame.getTwoThrowsKeggleCount();
            }
        }
        return sum;
    }

    int getTwoThrowsKeggleCount() {
        int result = 0;
        if (isStrike()) {
            result += KEGGLE_COUNT;
            if (nextFrame != null) {
                result += nextFrame.getOneThrowKeggleCount();
            }
        } else {
            for (int throwIndex = 0; throwIndex < size() && throwIndex < 2; throwIndex++) {
                result += ballThrows[throwIndex];
            }
        }
        return result;
    }

    int getOneThrowKeggleCount() {
        return (size() > 0) ? ballThrows[0] : 0;
    }

    private static String getStrikeString() {
        return "  " + STRIKE_CHAR + FRAME_DELIMITER_CHAR;
    }

    private String getSpareString() {
        return ballThrows[0] + " " + SPARE_CHAR + FRAME_DELIMITER_CHAR;
    }

    private String getClosedFrameString() {
        return ballThrows[0] + " " + ballThrows[1]+ FRAME_DELIMITER_CHAR;
    }

    private String getOpenFrameString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(String.format("%d ", ballThrows[i]));
        }
        return stringBuilder.toString();
    }


    @Override
    public String toString() {
        if (isLastFrame) {
            return getTenthFrameString();
        }
        if (isStrike()) {
            return getStrikeString();
        }
        if (isSpare()) {
            return getSpareString();
        }
        if (isClosed()) {
            return getClosedFrameString();
        }
        return getOpenFrameString();
    }

    private String getTenthFrameString() {

        StringBuilder stringBuilder = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            sum += ballThrows[i];
            if (sum < KEGGLE_COUNT) {
                stringBuilder.append(String.format("%d ", ballThrows[i]));
            } else if (ballThrows[i] == KEGGLE_COUNT) {
                stringBuilder.append(STRIKE_CHAR + " ");
                sum = 0;
            } else if (sum == KEGGLE_COUNT) {
                stringBuilder.append(SPARE_CHAR + " ");
                sum = 0;
            }
        }
        return stringBuilder.toString();
    }
}
