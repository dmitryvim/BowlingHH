import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mhty on 09.02.16.
 */
public class Player {
    final public static int FRAMES_COUNT = 10;

    private ArrayList<Frame> frames;
    private Integer frameNumber;
    private Userable user;

    public Player(Userable user) {
        this.user = user;
        frames = new ArrayList<>(FRAMES_COUNT);
        frameNumber = 0;
        frames.add(new Frame());
    }

    public boolean throwBall(int keggleCount) {
        if (!gameFinished()) {
            if (getActiveFrame().isClosed() && frameNumber < FRAMES_COUNT - 1) {
                frameNumber++;
                frames.add(new Frame(frameNumber == FRAMES_COUNT - 1));
            }
            getActiveFrame().throwBall(keggleCount);
        }
        return !gameFinished();
    }

    private Frame getActiveFrame() {
        return frames.get(frameNumber);
    }

    public int getScore() {
        return getScore(frameNumber);
    }

    public int getScore(int frameNumber) {
        int score = 0;
        for (int frameIndex = 0; frameIndex <= frameNumber && frameIndex < frames.size(); frameIndex++) {
            int nextThrow = 0;
            int nextNextThrow = 0;
            if (frameIndex + 1 < frames.size() && frames.get(frameIndex + 1).size() > 0) {
                nextThrow = frames.get(frameIndex + 1).ballTrows.getFirst();
                if (frames.get(frameIndex + 1).size() > 1) {
                    nextNextThrow = frames.get(frameIndex + 1).ballTrows.get(1);
                } else if (frameIndex + 2 < frames.size() && frames.get(frameIndex + 2).size() > 0) {
                    nextNextThrow = frames.get(frameIndex + 2).ballTrows.getFirst();
                }
            }
            score = frames.get(frameIndex).points(score, nextThrow, nextNextThrow);
        }
        return score;
    }

    public boolean gameFinished() {
        return (frames.size() == FRAMES_COUNT && frames.get(FRAMES_COUNT - 1).isClosed());
    }

    public int getFrameNumber() {
        return frameNumber + (getActiveFrame().isClosed() ? 1 : 0);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();

        for (int frameIndex = 0; frameIndex < frames.size(); frameIndex++) {
            stringBuffer.append(frames.get(frameIndex));
        }
        stringBuffer.append("\n");

        for (int frameIndex = 0; frameIndex <= frameNumber; frameIndex++) {
            stringBuffer.append(String.format("%4d", getScore(frameIndex)));
        }

        stringBuffer.append("\n Количество фреймов: " +  (frameNumber + 1) +
                            "\n Количество очков: " +  getScore() + "\n\n");
        return stringBuffer.toString();
    }

    public String getName() {
        return user.getName();
    }

    public void finishGame(int place) {
        user.setGameResult(getScore(), place);
    }
}
