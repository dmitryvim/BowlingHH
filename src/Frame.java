import java.util.Iterator;
import java.util.LinkedList;

public class Frame {
    final public static int KEGGLE_COUNT = 10;
    final public static int THROWS_IN_FRAME = 2;
    final public static int THROWS_IN_LAST_FRAME = 3;

    final public static char STRIKE_CHAR = 'X';
    final public static char SPARE_CHAR = '/';
    final public static char FRAME_DELIMITER_CHAR = '|';

    LinkedList<Integer> ballTrows;
    boolean isLastFrame = false;
    Frame nextFrame = null;

    private Frame() {
        ballTrows = new LinkedList<>();
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
        return (!isLastFrame && size() == 1 && ballTrows.getFirst() == KEGGLE_COUNT);
    }

    public boolean isSpare() {
        return (!isLastFrame && size() == THROWS_IN_FRAME && ballTrows.getFirst() + ballTrows.getLast() == KEGGLE_COUNT);
    }

    public int size() {
        return ballTrows.size();
    }

    public void throwBall(int keggleCount) {
        if (isClosed()) {
            throw new RuntimeException("Frame was closed.\n");
        }
        ballTrows.add(keggleCount);
    }

    public int keggleCount() {
        Iterator iterator = ballTrows.iterator();
        int sum = 0;
        while(iterator.hasNext()) {
            sum += (Integer) iterator.next();
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
                result += ballTrows.get(throwIndex);
            }
        }
        return result;
    }

    int getOneThrowKeggleCount() {
        return (size() > 0) ? ballTrows.getFirst() : 0;
    }

    private static String getStrikeString() {
        return "  " + STRIKE_CHAR + FRAME_DELIMITER_CHAR;
    }

    private String getSpareString() {
        return ballTrows.getFirst() + " " + SPARE_CHAR + FRAME_DELIMITER_CHAR;
    }

    private String getClosedFrameString() {
        return ballTrows.getFirst() + " " + ballTrows.getLast() + FRAME_DELIMITER_CHAR;
    }

    private String getOpenFrameString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object ballTrow : ballTrows) {
            stringBuilder.append(String.format("%d ", (Integer) ballTrow));
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
        Iterator iterator = ballTrows.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        int sum = 0;
        while(iterator.hasNext()) {
            int count = (Integer) iterator.next();
            sum += count;
            if (sum < KEGGLE_COUNT) {
                stringBuilder.append(String.format("%d ", count));
            } else if (count == KEGGLE_COUNT) {
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
