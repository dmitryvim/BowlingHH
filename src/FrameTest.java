import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.FREE_MEM;

import static org.junit.Assert.*;

/**
 * Created by mhty on 10.02.16.
 */
public class FrameTest {

    @Test
    public void testIsClosed() throws Exception {

        Frame frame = new Frame();
        frame.throwBall(2);
        assertEquals(false, frame.isClosed());
        frame.throwBall(8);
        assertEquals(true, frame.isClosed());

        frame = new Frame();
        frame.throwBall(10);
        assertEquals(true, frame.isClosed());

        frame = new Frame(true);
        frame.throwBall(7);
        assertEquals(false, frame.isClosed());
        frame.throwBall(2);
        assertEquals(true, frame.isClosed());

        frame = new Frame(true);
        frame.throwBall(7);
        assertEquals(false, frame.isClosed());
        frame.throwBall(3);
        assertEquals(false, frame.isClosed());
        frame.throwBall(5);
        assertEquals(true, frame.isClosed());

        frame = new Frame(true);
        frame.throwBall(10);
        assertEquals(false, frame.isClosed());
        frame.throwBall(3);
        assertEquals(false, frame.isClosed());
        frame.throwBall(5);
        assertEquals(true, frame.isClosed());

        frame = new Frame(true);
        frame.throwBall(10);
        assertEquals(false, frame.isClosed());
        frame.throwBall(10);
        assertEquals(false, frame.isClosed());
        frame.throwBall(10);
        assertEquals(true, frame.isClosed());
    }

    @Test
    public void testIsStrike() throws Exception {
        Frame frame = new Frame();
        frame.throwBall(10);
        assertEquals(true, frame.isStrike());

        frame = new Frame();
        frame.throwBall(8);
        assertEquals(false, frame.isStrike());
        frame.throwBall(2);
        assertEquals(false, frame.isStrike());

        frame = new Frame(true);
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());

        frame = new Frame(true);
        frame.throwBall(8);
        assertEquals(false, frame.isStrike());
        frame.throwBall(2);
        assertEquals(false, frame.isStrike());
        frame.throwBall(10);
        assertEquals(false, frame.isStrike());
    }

    @Test
    public void testIsSpare() throws Exception {
        Frame frame = new Frame();
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());

        frame = new Frame();
        frame.throwBall(8);
        assertEquals(false, frame.isSpare());
        frame.throwBall(2);
        assertEquals(true, frame.isSpare());

        frame = new Frame(true);
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());

        frame = new Frame(true);
        frame.throwBall(8);
        assertEquals(false, frame.isSpare());
        frame.throwBall(2);
        assertEquals(false, frame.isSpare());
        frame.throwBall(10);
        assertEquals(false, frame.isSpare());
    }

    @Test
    public void testSize() throws Exception {
        Frame frame = new Frame();
        assertEquals(0, frame.size());

        frame.throwBall(10);
        assertEquals(1, frame.size());

        frame = new Frame();
        frame.throwBall(8);
        assertEquals(1, frame.size());
        frame.throwBall(2);
        assertEquals(2, frame.size());

        frame = new Frame(true);
        frame.throwBall(10);
        assertEquals(1, frame.size());
        frame.throwBall(10);
        assertEquals(2, frame.size());
        frame.throwBall(10);
        assertEquals(3, frame.size());

        frame = new Frame(true);
        frame.throwBall(8);
        assertEquals(1, frame.size());
        frame.throwBall(2);
        assertEquals(2, frame.size());
        frame.throwBall(10);
        assertEquals(3, frame.size());
    }

    @Test
    public void testThrowBall() throws Exception {
        Frame frame = new Frame();
        assertEquals(true, frame.throwBall(2));
        assertEquals(true, frame.throwBall(2));
        assertEquals(false, frame.throwBall(2));
        assertEquals(false, frame.throwBall(2));

        frame = new Frame();
        assertEquals(true, frame.throwBall(10));
        assertEquals(false, frame.throwBall(10));
        assertEquals(false, frame.throwBall(2));
        assertEquals(false, frame.throwBall(2));

        frame = new Frame(true);
        assertEquals(true, frame.throwBall(2));
        assertEquals(true, frame.throwBall(2));
        assertEquals(false, frame.throwBall(2));
        assertEquals(false, frame.throwBall(2));

        frame = new Frame(true);
        assertEquals(true, frame.throwBall(2));
        assertEquals(true, frame.throwBall(8));
        assertEquals(true, frame.throwBall(2));
        assertEquals(false, frame.throwBall(2));

        frame = new Frame(true);
        assertEquals(true, frame.throwBall(10));
        assertEquals(true, frame.throwBall(2));
        assertEquals(true, frame.throwBall(2));
        assertEquals(false, frame.throwBall(2));
    }

    @Test
    public void testKeggleCount() throws Exception {
        Frame frame = new Frame();
        assertEquals(0, frame.keggleCount());

        frame.throwBall(10);
        assertEquals(10, frame.keggleCount());

        frame = new Frame();
        frame.throwBall(8);
        assertEquals(8, frame.keggleCount());
        frame.throwBall(2);
        assertEquals(10, frame.keggleCount());

        frame = new Frame(true);
        frame.throwBall(10);
        assertEquals(10, frame.keggleCount());
        frame.throwBall(10);
        assertEquals(20, frame.keggleCount());
        frame.throwBall(10);
        assertEquals(30, frame.keggleCount());

        frame = new Frame(true);
        frame.throwBall(8);
        assertEquals(8, frame.keggleCount());
        frame.throwBall(2);
        assertEquals(10, frame.keggleCount());
        frame.throwBall(10);
        assertEquals(20, frame.keggleCount());
    }

    @Test
    public void testPoints() throws Exception {
        Frame frame = new Frame();
        assertEquals(11, frame.points(11, 0, 0));
        assertEquals(11, frame.points(11, 5, 0));
        assertEquals(11, frame.points(11, 0, 5));
        assertEquals(11, frame.points(11, 9, 5));

        frame.throwBall(3);
        assertEquals(11, frame.points(8, 0, 0));
        assertEquals(11, frame.points(8, 5, 0));
        assertEquals(11, frame.points(8, 0, 5));
        assertEquals(11, frame.points(8, 9, 5));
        frame.throwBall(4);
        assertEquals(15, frame.points(8, 0, 0));
        assertEquals(15, frame.points(8, 5, 0));
        assertEquals(15, frame.points(8, 0, 5));
        assertEquals(15, frame.points(8, 9, 5));

        frame = new Frame();
        frame.throwBall(6);
        assertEquals(14, frame.points(8, 0, 0));
        assertEquals(14, frame.points(8, 5, 0));
        assertEquals(14, frame.points(8, 0, 5));
        assertEquals(14, frame.points(8, 9, 5));
        frame.throwBall(4);
        assertEquals(13, frame.points(3, 0, 0));
        assertEquals(19, frame.points(4, 5, 0));
        assertEquals(16, frame.points(6, 0, 5));
        assertEquals(26, frame.points(7, 9, 5));

        frame = new Frame();
        frame.throwBall(10);
        assertEquals(11, frame.points(1, 0, 0));
        assertEquals(15, frame.points(2, 3, 0));
        assertEquals(19, frame.points(5, 0, 4));
        assertEquals(32, frame.points(9, 6, 7));

        frame = new Frame(true);
        frame.throwBall(2);
        assertEquals(10, frame.points(8, 0, 0));
        assertEquals(10, frame.points(8, 5, 0));
        assertEquals(10, frame.points(8, 0, 5));
        assertEquals(10, frame.points(8, 9, 5));
        frame.throwBall(2);
        assertEquals(12, frame.points(8, 0, 0));
        assertEquals(12, frame.points(8, 5, 0));
        assertEquals(12, frame.points(8, 0, 5));
        assertEquals(12, frame.points(8, 9, 5));

        frame = new Frame(true);
        frame.throwBall(2);
        frame.throwBall(8);
        assertEquals(18, frame.points(8, 0, 0));
        assertEquals(18, frame.points(8, 5, 0));
        assertEquals(18, frame.points(8, 0, 5));
        assertEquals(18, frame.points(8, 9, 5));
        frame.throwBall(2);
        assertEquals(20, frame.points(8, 0, 0));
        assertEquals(20, frame.points(8, 5, 0));
        assertEquals(20, frame.points(8, 0, 5));
        assertEquals(20, frame.points(8, 9, 5));

        frame = new Frame(true);
        frame.throwBall(10);
        assertEquals(108, frame.points(98, 0, 0));
        assertEquals(108, frame.points(98, 5, 0));
        assertEquals(108, frame.points(98, 0, 5));
        assertEquals(108, frame.points(98, 9, 5));
        frame.throwBall(10);
        assertEquals(118, frame.points(98, 0, 0));
        assertEquals(118, frame.points(98, 5, 0));
        assertEquals(118, frame.points(98, 0, 5));
        assertEquals(118, frame.points(98, 9, 5));
        frame.throwBall(10);
        assertEquals(128, frame.points(98, 0, 0));
        assertEquals(128, frame.points(98, 5, 0));
        assertEquals(128, frame.points(98, 0, 5));
        assertEquals(128, frame.points(98, 9, 5));

    }

}