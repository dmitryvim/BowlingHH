import java.util.ArrayList;

public class Player {
    final public static int FRAMES_COUNT = 10;
    final private static String DEFAULT_USER_NAME = "user";

    private ArrayList<Frame> frames;
    private Integer activeFrameIndex;
    private Userable user;

    private Player(Userable user) {
        this.user = user;
        frames = new ArrayList<>(FRAMES_COUNT);
        activeFrameIndex = -1;
        addFrame();
    }

    public static Player build(Userable user) {
        return new Player(user);
    }

    public static Player build() {
        return build(null);
    }

    public void throwBall(int keggleCount) {
        if (gameFinished()) {
            throw new RuntimeException("Game was finished!\n");
        }
        if (getActiveFrame().isClosed()) {
            addFrame();
        }
        getActiveFrame().throwBall(keggleCount);
    }

    private void addFrame() {

        activeFrameIndex++;
        frames.add(activeFrameIndex == FRAMES_COUNT - 1 ? Frame.buildLastFrame() : Frame.build());
        if (activeFrameIndex > 0) {
            frames.get(activeFrameIndex - 1).setNextFrame(getActiveFrame());
        }


    }

    private Frame getActiveFrame() {
        return frames.get(activeFrameIndex);
    }

    public int getScore() {
        return getScore(activeFrameIndex);
    }

    public int getScore(int frameNumber) {
        int score = 0;
        for (int frameIndex = 0; frameIndex <= frameNumber && frameIndex < frames.size(); frameIndex++) {
            score += frames.get(frameIndex).getPoints();
        }
        return score;
    }

    public boolean gameFinished() {
        return (frames.size() == FRAMES_COUNT && frames.get(FRAMES_COUNT - 1).isClosed());
    }

    public int getFrameNumber() {
        return activeFrameIndex + (getActiveFrame().isClosed() ? 1 : 0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Frame frame : frames) {
            stringBuilder.append(frame);
        }
        stringBuilder.append("\n");

        for (int frameIndex = 0; frameIndex <= activeFrameIndex; frameIndex++) {
            stringBuilder.append(String.format("%3d|", getScore(frameIndex)));
        }
        stringBuilder.append("\n");

        stringBuilder.append("\n Количество фреймов: " + (activeFrameIndex + 1) +
                "\n Количество очков: " + getScore() + "\n\n");
        return stringBuilder.toString();
    }

    public String getName() {
        return user.getName();
    }

    public void finishGame(int place) {
        user.setGameResult(getScore(), place);
    }

    public static void main(String[] args) {
        FrameValuesForTest[] testSet = FrameValuesForTest.testSetMax;
        Player player = Player.build();

        System.out.println(player);
        for (FrameValuesForTest aTestSet : testSet) {
            for (int j = 0; j < aTestSet.keggleCount.length; j++) {
                player.throwBall(aTestSet.keggleCount[j]);
                System.out.println(player);
            }
        }
        System.out.println(player);
    }

}
