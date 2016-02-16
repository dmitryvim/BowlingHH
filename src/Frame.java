import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by mhty on 10.02.16.
 */
public class Frame {
    final public static int KEGGLE_COUNT = 10;
    final public static int THROWS_IN_FRAME = 2;
    final public static int THROWS_IN_LAST_FRAME = 3;

    LinkedList<Integer> ballTrows;
    boolean isTenthFrame;

    private Frame(boolean isTenthFrame) {
        this.isTenthFrame = isTenthFrame;
        ballTrows = new LinkedList<>();
    }

    private Frame() {
        this.isTenthFrame = false;
        ballTrows = new LinkedList<>();
    }

    public boolean isClosed() {
        return (!isTenthFrame && size() == THROWS_IN_FRAME)
                || (!isTenthFrame && isStrike())
                || (isTenthFrame && size() == THROWS_IN_FRAME && keggleCount() < KEGGLE_COUNT)
                || (isTenthFrame && size() == THROWS_IN_LAST_FRAME);
    }

    public boolean isStrike() {
        return (!isTenthFrame && size() == 1 && ballTrows.getFirst() == KEGGLE_COUNT);
    }

    public boolean isSpare() {
        return (!isTenthFrame && size() == THROWS_IN_FRAME && ballTrows.getFirst() + ballTrows.getLast() == KEGGLE_COUNT);
    }

    public int size() {
        return ballTrows.size();
    }

    public boolean throwBall(int keggleCount) {
        if (!isClosed()) {
            ballTrows.add(keggleCount);
            return true;
        }
        return false;
    }

    public int keggleCount() {
        Iterator iterator = ballTrows.iterator();
        int sum = 0;
        while(iterator.hasNext()) {
            sum += (Integer) iterator.next();
        }
        return sum;
    }

    public int points(int previousPoints, int nextThrow, int nextNextThrow) {
        int sum = previousPoints + keggleCount();
        if (isSpare()) {
            sum += nextThrow;
        }
        if (isStrike()) {
            sum += nextThrow + nextNextThrow;
        }
        return sum;
    }

    @Override
    public String toString() {
        if (isStrike()) {
            return "  X|";
        }
        if (isSpare()) {
            return ballTrows.getFirst() + " /|";
        }
        if (!isTenthFrame && isClosed()) {
            return ballTrows.getFirst() + " " + ballTrows.getLast() + "|";
        }

        Iterator iterator = ballTrows.iterator();
        StringBuffer stringBuffer = new StringBuffer();
        int sum = 0;
        while(iterator.hasNext()) {
            int count = (Integer) iterator.next();
            sum += count;
            if (sum < 10) {
                stringBuffer.append(count + " ");
            } else if (count == 10) {
                stringBuffer.append("X ");
                sum = 0;
            } else if (sum == 10) {
                stringBuffer.append("/ ");
                sum = 0;
            }
        }
        return stringBuffer.toString();
    }
}
