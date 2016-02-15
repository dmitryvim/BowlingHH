import org.junit.Before;
import org.junit.Test;

/**
 * Created by mhty on 10.02.16.
 */
class FrameValues {
    int frameIndex;
    int[] keggleCount;
    int[] scoreInMoment;
    int[] scoreInNThrows;

    public int getScoreInNThrows(int count) {
        if (count < scoreInNThrows.length) {
            return scoreInNThrows[count];
        } else {
            return scoreInNThrows[scoreInNThrows.length - 1];
        }
    }

    public FrameValues(int frameIndex, int[] keggleCount, int[] scoreInMoment, int[] scoreInNThrows) {
        this.frameIndex = frameIndex;
        this.keggleCount = keggleCount;
        this.scoreInMoment = scoreInMoment;
        this.scoreInNThrows = scoreInNThrows;
    }


//    Подход	1	2	3	4	5	6	7	8	9	10
//    Броски	9-0	3-5	6-1	3-6	8-1	5-3	2-5	8-0	7-1	8-1
//    Очки	    9	17	24	33	42	50	57	65	73	82
    public static FrameValues[] testSet1 = {
            new FrameValues(1,    new int[]{9, 0},    new int[]{9, 9},        new int[]{9, 9, 9, 9}),
            new FrameValues(2,    new int[]{3, 5},    new int[]{12, 17},      new int[]{17, 17, 17}),
            new FrameValues(3,    new int[]{6, 1},    new int[]{23, 24},      new int[]{24, 24, 24}),
            new FrameValues(4,    new int[]{3, 6},    new int[]{27, 33},      new int[]{33, 33, 33}),
            new FrameValues(5,    new int[]{8, 1},    new int[]{41, 42},      new int[]{42, 42, 42}),
            new FrameValues(6,    new int[]{5, 3},    new int[]{47, 50},      new int[]{50, 50, 50}),
            new FrameValues(7,    new int[]{2, 5},    new int[]{52, 57},      new int[]{57, 57, 57}),
            new FrameValues(8,    new int[]{8, 0},    new int[]{65, 65},      new int[]{65, 65, 65}),
            new FrameValues(9,    new int[]{7, 1},    new int[]{72, 73},      new int[]{73, 73, 73}),
            new FrameValues(10,   new int[]{8, 1},    new int[]{81, 82},      new int[]{82})
    };

//    Подход	1	2	3	4	5	6	7	8	9	10
//    Броски	9-0	3/	6-1	3/	8-1	5/	0/	8-0	7/	8/8
//    Очки	    9	25	32	50	59	69	87	95	113	131

    public static FrameValues[] testSet2 = {
            new FrameValues(1,    new int[]{9, 0},    new int[]{9, 9},             new int[]{9, 9, 9}),
            new FrameValues(2,    new int[]{3, 7},    new int[]{12, 19},           new int[]{19, 25, 25}),
            new FrameValues(3,    new int[]{6, 1},    new int[]{31, 32},           new int[]{32, 32, 32}),
            new FrameValues(4,    new int[]{3, 7},    new int[]{35, 42},           new int[]{42, 50, 50}),
            new FrameValues(5,    new int[]{8, 1},    new int[]{58, 59},           new int[]{59, 59, 59}),
            new FrameValues(6,    new int[]{5, 5},       new int[]{64, 69},        new int[]{69, 69, 69}),
            new FrameValues(7,    new int[]{0, 10},      new int[]{69, 79},        new int[]{79, 87, 87}),
            new FrameValues(8,    new int[]{8, 0},       new int[]{95, 95},        new int[]{85, 95, 95}),
            new FrameValues(9,    new int[]{7, 3},       new int[]{102, 105},      new int[]{105, 113, 113}),
            new FrameValues(10,   new int[]{8, 2, 8},    new int[]{121, 123, 131}, new int[]{131})
    };


//    Подход	1	2	3	4	5	6	7	8	9	10
//    Броски	X	3/	6-1	X	X	X	2/	9-0	7/	XXX
//    Очки	    20	36	43	73	95	115	134	143	163	193

    public static FrameValues[] testSet3 = {
            new FrameValues(1,    new int[]{10},          new int[]{10},             new int[]{10, 13, 20}),
            new FrameValues(2,    new int[]{3, 7},        new int[]{16, 30},         new int[]{30, 36, 36}),
            new FrameValues(3,    new int[]{6, 1},        new int[]{42, 43},         new int[]{43, 43, 43}),
            new FrameValues(4,    new int[]{10},          new int[]{53},             new int[]{53, 63, 73}),
            new FrameValues(5,    new int[]{10},          new int[]{73},             new int[]{73, 93, 95}),
            new FrameValues(6,    new int[]{10},          new int[]{103},            new int[]{103, 107, 115}),
            new FrameValues(7,    new int[]{2, 8},        new int[]{109, 125},       new int[]{125, 134, 134}),
            new FrameValues(8,    new int[]{9, 0},        new int[]{143, 143},       new int[]{143, 143, 143}),
            new FrameValues(9,    new int[]{7, 3},        new int[]{150, 153},       new int[]{153, 163, 163}),
            new FrameValues(10,   new int[]{10, 10, 10},  new int[]{173, 183, 193},  new int[]{193})
    };


}