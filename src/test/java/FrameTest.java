import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.FREE_MEM;

import static org.junit.Assert.*;

public class FrameTest {
    Frame frame;

    @Test
    public void testThrowBall() {
        frame = Frame.build();
        assertEquals(frame, frame.throwBall(1));
    }

    @Test
    public void testIsClosedLess10() {
        frame = Frame.buildLastFrame();
        frame.throwBall(7);
        assertEquals(false, frame.isClosed());
        frame.throwBall(2);
        assertEquals(true, frame.isClosed());
    }

    @Test
    public void testIsClosedSpare() {
        frame = Frame.build();
        frame.throwBall(2);
        assertEquals(false, frame.isClosed());
        frame.throwBall(8);
        assertEquals(true, frame.isClosed());
    }

    @Test
    public void testIsClosedStrike() {
        frame = Frame.build();
        frame.throwBall(10);
        assertEquals(true, frame.isClosed());
    }

    @Test
    public void testIsClosedLastFrameLess10() {
        frame = Frame.buildLastFrame();
        frame.throwBall(7);
        assertEquals(false, frame.isClosed());
        frame.throwBall(2);
        assertEquals(true, frame.isClosed());
    }

    @Test
    public void testIsClosedLastFrameSpareOneMoreThrow() {
        frame = Frame.buildLastFrame();
        frame.throwBall(7);
        assertEquals(false, frame.isClosed());
        frame.throwBall(3);
        assertEquals(false, frame.isClosed());
        frame.throwBall(5);
        assertEquals(true, frame.isClosed());
    }

    @Test
    public void testIsClosedLastFrameStrikeTwoMoreThrow() {
        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals(false, frame.isClosed());
        frame.throwBall(3);
        assertEquals(false, frame.isClosed());
        frame.throwBall(5);
        assertEquals(true, frame.isClosed());
    }



    @Test
    public void testIsClosedLastFrameThreeStrike() throws Exception {
        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals(false, frame.isClosed());
        frame.throwBall(10);
        assertEquals(false, frame.isClosed());
        frame.throwBall(10);
        assertEquals(true, frame.isClosed());
    }

    @Test
    public void testIsStrike() throws Exception {
        frame = Frame.build();
        frame.throwBall(10);
        assertEquals(true, frame.isStrike());

        frame = Frame.build();
        frame.throwBall(8);
        assertEquals(false, frame.isStrike());
        frame.throwBall(2);
        assertEquals(false, frame.isStrike());

        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());

        frame = Frame.buildLastFrame();
        frame.throwBall(8);
        assertEquals(false, frame.isStrike());
        frame.throwBall(2);
        assertEquals(false, frame.isStrike());
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());
    }

    @Test
    public void testIsSpare() throws Exception {
        frame = Frame.build();
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());

        frame = Frame.build();
        frame.throwBall(8);
        assertEquals(false, frame.isSpare());
        frame.throwBall(2);
        assertEquals(true, frame.isSpare());

        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());

        frame = Frame.buildLastFrame();
        frame.throwBall(8);
        assertEquals(false, frame.isSpare());
        frame.throwBall(2);
        assertEquals(false, frame.isSpare());
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());
    }

    @Test
    public void testSize() throws Exception {
        frame = Frame.build();
        assertEquals(0, frame.size());

        frame.throwBall(10);
        assertEquals(1, frame.size());

        frame = Frame.build();
        frame.throwBall(8);
        assertEquals(1, frame.size());
        frame.throwBall(2);
        assertEquals(2, frame.size());

        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals(1, frame.size());
        frame.throwBall(10);
        assertEquals(2, frame.size());
        frame.throwBall(10);
        assertEquals(3, frame.size());

        frame = Frame.buildLastFrame();
        frame.throwBall(8);
        assertEquals(1, frame.size());
        frame.throwBall(2);
        assertEquals(2, frame.size());
        frame.throwBall(10);
        assertEquals(3, frame.size());
    }



    @Test
    public void testKeggleCount() throws Exception {
        frame = Frame.build();
        assertEquals(0, frame.keggleCount());

        frame.throwBall(10);
        assertEquals(10, frame.keggleCount());

        frame = Frame.build();
        frame.throwBall(8);
        assertEquals(8, frame.keggleCount());
        frame.throwBall(2);
        assertEquals(10, frame.keggleCount());

        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals(10, frame.keggleCount());
        frame.throwBall(10);
        assertEquals(20, frame.keggleCount());
        frame.throwBall(10);
        assertEquals(30, frame.keggleCount());

        frame = Frame.buildLastFrame();
        frame.throwBall(8);
        assertEquals(8, frame.keggleCount());
        frame.throwBall(2);
        assertEquals(10, frame.keggleCount());
        frame.throwBall(10);
        assertEquals(20, frame.keggleCount());
    }


    @Test
    public void testGetPoints() {
        Frame nextFrame;
        Frame nextNextFrame;

        frame = Frame.build();
        frame.throwBall(2);
        assertEquals(2, frame.getPoints());
        frame.throwBall(5);
        assertEquals(7, frame.getPoints());
        nextFrame = Frame.build();
        frame.setNextFrame(nextFrame);
        assertEquals(7, frame.getPoints());
        nextFrame.throwBall(5);
        assertEquals(7, frame.getPoints());

        frame = Frame.build();
        frame.throwBall(3);
        assertEquals(3, frame.getPoints());
        frame.throwBall(7);
        assertEquals(10, frame.getPoints());
        nextFrame = Frame.build();
        frame.setNextFrame(nextFrame);
        assertEquals(10, frame.getPoints());
        nextFrame.throwBall(5);
        assertEquals(15, frame.getPoints());
        nextFrame.throwBall(5);
        assertEquals(15, frame.getPoints());
        nextNextFrame = Frame.build();
        nextFrame.setNextFrame(nextNextFrame);
        nextNextFrame.throwBall(10);
        assertEquals(15, frame.getPoints());

        frame = Frame.build();
        frame.throwBall(10);
        assertEquals(10, frame.getPoints());
        nextFrame = Frame.build();
        frame.setNextFrame(nextFrame);
        assertEquals(10, frame.getPoints());
        nextFrame.throwBall(5);
        assertEquals(15, frame.getPoints());
        nextFrame.throwBall(5);
        assertEquals(20, frame.getPoints());
        nextNextFrame = Frame.build();
        nextFrame.setNextFrame(nextNextFrame);
        nextNextFrame.throwBall(10);
        assertEquals(20, frame.getPoints());

        frame = Frame.build();
        frame.throwBall(10);
        assertEquals(10, frame.getPoints());
        nextFrame = Frame.build();
        frame.setNextFrame(nextFrame);
        assertEquals(10, frame.getPoints());
        nextFrame.throwBall(10);
        assertEquals(20, frame.getPoints());
        nextNextFrame = Frame.build();
        nextFrame.setNextFrame(nextNextFrame);
        nextNextFrame.throwBall(7);
        assertEquals(27, frame.getPoints());

        frame = Frame.build();
        frame.throwBall(10);
        assertEquals(10, frame.getPoints());
        nextFrame = Frame.build();
        frame.setNextFrame(nextFrame);
        assertEquals(10, frame.getPoints());
        nextFrame.throwBall(10);
        assertEquals(20, frame.getPoints());
        nextNextFrame = Frame.build();
        nextFrame.setNextFrame(nextNextFrame);
        nextNextFrame.throwBall(10);
        assertEquals(30, frame.getPoints());
        assertEquals(20, nextFrame.getPoints());
        assertEquals(10, nextNextFrame.getPoints());

        frame = Frame.buildLastFrame();
        frame.throwBall(4);
        assertEquals(4, frame.getPoints());
        frame.throwBall(6);
        assertEquals(10, frame.getPoints());
        frame.throwBall(10);
        assertEquals(20, frame.getPoints());

        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals(10, frame.getPoints());
        frame.throwBall(6);
        assertEquals(16, frame.getPoints());
        frame.throwBall(3);
        assertEquals(19, frame.getPoints());
    }


    @Test
    public void testToString() {
        frame = Frame.build();
        frame.throwBall(10);
        assertEquals("  X|", frame.toString());

        frame = Frame.build();
        frame.throwBall(2);
        assertEquals("2 ", frame.toString());
        frame.throwBall(8);
        assertEquals("2 /|", frame.toString());

        frame = Frame.build();
        frame.throwBall(2);
        assertEquals("2 ", frame.toString());
        frame.throwBall(5);
        assertEquals("2 5|", frame.toString());

        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals("X ", frame.toString());
        frame.throwBall(10);
        assertEquals("X X ", frame.toString());
        frame.throwBall(10);
        assertEquals("X X X ", frame.toString());

        frame = Frame.buildLastFrame();
        frame.throwBall(4);
        assertEquals("4 ", frame.toString());
        frame.throwBall(6);
        assertEquals("4 / ", frame.toString());
        frame.throwBall(10);
        assertEquals("4 / X ", frame.toString());

        frame = Frame.buildLastFrame();
        frame.throwBall(10);
        assertEquals("X ", frame.toString());
        frame.throwBall(6);
        assertEquals("X 6 ", frame.toString());
        frame.throwBall(1);
        assertEquals("X 6 1 ", frame.toString());

        frame = Frame.buildLastFrame();
        frame.throwBall(1);
        assertEquals("1 ", frame.toString());
        frame.throwBall(6);
        assertEquals("1 6 ", frame.toString());
    }

}