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
        when(user.getName()).thenReturn("user");
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
    }

    @Test
    public void testGetScoreInMomentLess10() throws Exception {
        testGetScoreInMoment(FrameValuesForTest.testSetLess10InFrame);
    }

    @Test
    public void testGetScoreInMomentSpare() throws Exception {
        testGetScoreInMoment(FrameValuesForTest.testSetWithSpares);
    }

    @Test
    public void testGetScoreInMomentStrike() throws Exception {
        testGetScoreInMoment(FrameValuesForTest.testSetWithSparesAndStrinkes);
    }

    @Test
    public void testGetScoreInThrowsLess10() throws Exception {
        testGetScoreInThrows(FrameValuesForTest.testSetLess10InFrame);
    }

    @Test
    public void testGetScoreInThrowsSpare() throws Exception {
        testGetScoreInThrows(FrameValuesForTest.testSetWithSpares);
    }

    @Test
    public void testGetScoreInThrowsStrike() throws Exception {
        testGetScoreInThrows(FrameValuesForTest.testSetWithSparesAndStrinkes);
    }

    @Test
    public void testGameFinishLess10() throws Exception {
        testGameFinish(FrameValuesForTest.testSetLess10InFrame);
    }

    @Test
    public void testGameFinishSpare() throws Exception {
        testGameFinish(FrameValuesForTest.testSetWithSpares);
    }

    @Test
    public void testGameFinishStrike() throws Exception {
        testGameFinish(FrameValuesForTest.testSetWithSparesAndStrinkes);
    }

    @Test
    public void testGetFrameNumberLess10() throws Exception {
        testGetFrameNumber(FrameValuesForTest.testSetLess10InFrame);
    }

    @Test
    public void testGetFrameNumberSpare() throws Exception {
        testGetFrameNumber(FrameValuesForTest.testSetWithSpares);
    }

    @Test
    public void testGetFrameNumberStrike() throws Exception {
        testGetFrameNumber(FrameValuesForTest.testSetWithSparesAndStrinkes);
    }

    @Test
    public void testGetScoreInMomentAllStrike() {
        testGetScoreInMoment(FrameValuesForTest.testSetMax);
    }

    @Test
    public void testGetScoreInThrowsAllStrike() {
        testGetScoreInThrows(FrameValuesForTest.testSetMax);
    }

    @Test
    public void testGetScoreInMomentAllZero() {
        testGetScoreInMoment(FrameValuesForTest.testSetZeros);
    }

    @Test
    public void testGetScoreInThrowsAllZero() {
        testGetScoreInThrows(FrameValuesForTest.testSetZeros);
    }

    @Test
    public void testToString() {
        for (FrameValuesForTest aTestSet : FrameValuesForTest.testSetLess10InFrame) {
            for (int j = 0; j < aTestSet.keggleCount.length; j++) {
                player.throwBall(aTestSet.keggleCount[j]);
            }
        }

        String expected = "user\n" +
                "9 0|3 5|6 1|3 6|8 1|5 3|2 5|8 0|7 1|8 1 \n" +
                "  9| 17| 24| 33| 42| 50| 57| 65| 73| 82|\n" +
                "\n" +
                " Количество фреймов: 10\n" +
                " Количество очков: 82\n";
        String result = player.toString();

        assertEquals(expected, result);
    }
}