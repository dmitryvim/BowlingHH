import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class PlayerTest {
    Player player;
    Userable user;


    @Before
    public void playerCreate() {
        user = Mockito.mock(Userable.class);
        player = Player.build(user);
    }



    private void testGetScoreInMoment(FrameValuesForTest[] testSet) {
        for (FrameValuesForTest aTestSet : testSet) {
            for (int j = 0; j < aTestSet.keggleCount.length; j++) {
                player.throwBall(aTestSet.keggleCount[j]);
                assertEquals(aTestSet.scoreInMoment[j], player.getScore());
            }
        }
    }

    private void testGetScoreInThrows(FrameValuesForTest[] testSet) {
        int throwCount = 0;
        assertEqualsScoreInNThrows(testSet, throwCount);
        for (FrameValuesForTest aTestSet : testSet) {
            for (int throwIndex = 0; throwIndex < aTestSet.keggleCount.length; throwIndex++) {
                player.throwBall(aTestSet.keggleCount[throwIndex]);
                throwCount++;
                assertEqualsScoreInNThrows(testSet, throwCount);
            }
        }
    }

    private void assertEqualsScoreInNThrows(FrameValuesForTest[] testSet, int throwCount) {
        for (int i = 0; i < testSet.length; i++) {
            if (throwCount < testSet[i].scoreInNThrows.length) {
                assertEquals(testSet[i].scoreInNThrows[throwCount], player.getScore(i));
            }
        }
    }


    private void testGetFrameNumber(FrameValuesForTest[] testSet) {
        for (int i = 0; i < testSet.length; i++) {
            for (int j = 0; j < testSet[i].keggleCount.length; j++) {
                assertEquals(i, player.getFrameNumber());
                player.throwBall(testSet[i].keggleCount[j]);
            }
        }
    }

    private void testGameFinish(FrameValuesForTest[] testSet) {
        for (FrameValuesForTest aTestSet : testSet) {
            for (int j = 0; j < aTestSet.keggleCount.length; j++) {
                assertEquals(false, player.gameFinished());
                player.throwBall(aTestSet.keggleCount[j]);
            }
        }
        assertEquals(true, player.gameFinished());
    }

    @Test
    public void testGetName() {
        when(user.getName()).thenReturn("User 314");
        assertEquals("User 314", player.getName());

        when(user.getName()).thenReturn("Dima");
        assertEquals("Dima", player.getName());
    }

    @Test
    public void testGetScoreInMoment1() throws Exception {
        testGetScoreInMoment(FrameValuesForTest.testSet1);
    }

    @Test
    public void testGetScoreInMoment2() throws Exception {
        testGetScoreInMoment(FrameValuesForTest.testSet2);
    }

    @Test
    public void testGetScoreInMoment3() throws Exception {
        testGetScoreInMoment(FrameValuesForTest.testSet3);
    }

    @Test
    public void testGetScoreInThrows1() throws Exception {
        testGetScoreInThrows(FrameValuesForTest.testSet1);
    }

    @Test
    public void testGetScoreInThrows2() throws Exception {
        testGetScoreInThrows(FrameValuesForTest.testSet2);
    }

    @Test
    public void testGetScoreInThrows3() throws Exception {
        testGetScoreInThrows(FrameValuesForTest.testSet3);
    }

    @Test
    public void testGameFinish1() throws Exception {
        testGameFinish(FrameValuesForTest.testSet1);
    }

    @Test
    public void testGameFinish2() throws Exception {
        testGameFinish(FrameValuesForTest.testSet2);
    }

    @Test
    public void testGameFinish3() throws Exception {
        testGameFinish(FrameValuesForTest.testSet3);
    }

    @Test
    public void testGetFrameNumber1() throws Exception {
        testGetFrameNumber(FrameValuesForTest.testSet1);
    }

    @Test
    public void testGetFrameNumber2() throws Exception {
        testGetFrameNumber(FrameValuesForTest.testSet2);
    }

    @Test
    public void testGetFrameNumber3() throws Exception {
        testGetFrameNumber(FrameValuesForTest.testSet3);
    }

    ///

    @Test
    public void testGetScoreAllStrike() {
        testGetScoreInMoment(FrameValuesForTest.testSetMax);
        testGetScoreInThrows(FrameValuesForTest.testSetMax);
    }

    @Test
    public void testGetScoreAllZero() {
        testGetScoreInMoment(FrameValuesForTest.testSetZeros);
        testGetScoreInThrows(FrameValuesForTest.testSetZeros);
    }
}