import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mhty on 09.02.16.
 */
public class PlayerTest {
    Player player;


    @Before
    public void playerCreate() {
        player = new Player();
    }


    public void testThrowBall(FrameValues[] testSet) throws Exception {
        for (int i = 0; i < testSet.length; i++) {
            for (int j = 0; j < testSet[i].keggleCount.length; j++) {
                assertEquals(i == testSet.length - 1 && j == testSet[i].keggleCount.length - 1, !player.throwBall(testSet[i].keggleCount[j]));
            }
        }
    }

    private void testGetScoreInMoment(FrameValues[] testSet) {
        for (int i = 0; i < testSet.length; i++) {
            for (int j = 0; j < testSet[i].keggleCount.length; j++) {
                player.throwBall(testSet[i].keggleCount[j]);
                assertEquals(testSet[i].scoreInMoment[j], player.getScore());
            }
        }
    }

    private void testGetScoreInThrows(FrameValues[] testSet) {
        for (int frameIndex = 0; frameIndex < testSet.length; frameIndex++) {
            for (int throwIndex = 0; throwIndex < testSet[frameIndex].keggleCount.length; throwIndex++) {
                player.throwBall(testSet[frameIndex].keggleCount[throwIndex]);
                assertEquals(testSet[frameIndex].scoreInMoment[throwIndex], player.getScore(frameIndex));

                for (int i = 0; i < frameIndex - 1; i++) {
                    assertEquals(testSet[i].getScoreInNThrows(2), player.getScore(i));
                }
                if(frameIndex > 0) {
                    assertEquals(testSet[frameIndex - 1].getScoreInNThrows(1 + throwIndex), player.getScore(frameIndex - 1));
                }
            }
        }
    }


    private void testGetFrameNumber(FrameValues[] testSet) {
        for (int i = 0; i < testSet.length; i++) {
            for (int j = 0; j < testSet[i].keggleCount.length; j++) {
                assertEquals(i, player.getFrameNumber());
                player.throwBall(testSet[i].keggleCount[j]);
            }
        }
    }

    private void testGameFinish(FrameValues[] testSet) {
        for (int i = 0; i < testSet.length; i++) {
            for (int j = 0; j < testSet[i].keggleCount.length; j++) {
                assertEquals(false, player.gameFinished());
                player.throwBall(testSet[i].keggleCount[j]);
            }
        }
        assertEquals(true, player.gameFinished());
    }

    @Test
    public void testThrowBall1() throws Exception {
        testThrowBall(FrameValues.testSet1);
    }

    @Test
    public void testThrowBall2() throws Exception {
        testThrowBall(FrameValues.testSet2);
    }

    @Test
    public void testThrowBall3() throws Exception {
        testThrowBall(FrameValues.testSet3);
    }

    @Test
    public void testGetScoreInMoment1() throws Exception {
        testGetScoreInMoment(FrameValues.testSet1);
    }

    @Test
    public void testGetScoreInMoment2() throws Exception {
        testGetScoreInMoment(FrameValues.testSet2);
    }

    @Test
    public void testGetScoreInMoment3() throws Exception {
        testGetScoreInMoment(FrameValues.testSet3);
    }

    @Test
    public void testGetScoreInThrows1() throws Exception {
        testGetScoreInThrows(FrameValues.testSet1);
    }

    @Test
    public void testGetScoreInThrows2() throws Exception {
        testGetScoreInThrows(FrameValues.testSet2);
    }

    @Test
    public void testGetScoreInThrows3() throws Exception {
        testGetScoreInThrows(FrameValues.testSet3);
    }

    @Test
    public void testGameFinish1() throws Exception {
        testGameFinish(FrameValues.testSet1);
    }

    @Test
    public void testGameFinish2() throws Exception {
        testGameFinish(FrameValues.testSet2);
    }

    @Test
    public void testGameFinish3() throws Exception {
        testGameFinish(FrameValues.testSet3);
    }



    @Test
    public void testGetFrameNumber1() throws Exception {
        testGetFrameNumber(FrameValues.testSet1);
    }

    @Test
    public void testGetFrameNumber2() throws Exception {
        testGetFrameNumber(FrameValues.testSet2);
    }

    @Test
    public void testGetFrameNumber3() throws Exception {
        testGetFrameNumber(FrameValues.testSet3);
    }
}