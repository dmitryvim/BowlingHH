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
            new FrameValues(1,    new int[]{9, 0},    new int[]{9, 9},        new int[]{0, 9, 9, 9, 9, 9}),
            new FrameValues(2,    new int[]{3, 5},    new int[]{12, 17},      new int[]{0, 9, 9, 12, 17, 17, 17, 17, 17, 17}),
            new FrameValues(3,    new int[]{6, 1},    new int[]{23, 24},      new int[]{}),
            new FrameValues(4,    new int[]{3, 6},    new int[]{27, 33},      new int[]{}),
            new FrameValues(5,    new int[]{8, 1},    new int[]{41, 42},      new int[]{}),
            new FrameValues(6,    new int[]{5, 3},    new int[]{47, 50},      new int[]{}),
            new FrameValues(7,    new int[]{2, 5},    new int[]{52, 57},      new int[]{}),
            new FrameValues(8,    new int[]{8, 0},    new int[]{65, 65},      new int[]{}),
            new FrameValues(9,    new int[]{7, 1},    new int[]{72, 73},      new int[]{}),
            new FrameValues(10,   new int[]{8, 1},    new int[]{81, 82},      new int[]{0, 9, 9, 12, 17, 23, 24, 27, 33, 41, 42, 47, 50, 52, 57, 65, 65, 72, 73, 81, 82})
    };

//    Подход	1	2	3	4	5	6	7	8	9	10
//    Броски	9-0	3/	6-1	3/	8-1	5/	0/	8-0	7/	8/8
//    Очки	    9	25	32	50	59	69	87	95	113	131

    public static FrameValues[] testSet2 = {
            new FrameValues(1,    new int[]{9, 0},    new int[]{9, 9},             new int[]{}),
            new FrameValues(2,    new int[]{3, 7},    new int[]{12, 19},           new int[]{0, 9, 9, 12, 19, 25, 25, 25, 25}),
            new FrameValues(3,    new int[]{6, 1},    new int[]{31, 32},           new int[]{}),
            new FrameValues(4,    new int[]{3, 7},    new int[]{35, 42},           new int[]{}),
            new FrameValues(5,    new int[]{8, 1},    new int[]{58, 59},           new int[]{}),
            new FrameValues(6,    new int[]{5, 5},       new int[]{64, 69},        new int[]{0, 9, 9, 12, 19, 31, 32, 35, 42, 58, 59, 64, 69, 69, 69}),
            new FrameValues(7,    new int[]{0, 10},      new int[]{69, 79},        new int[]{}),
            new FrameValues(8,    new int[]{8, 0},       new int[]{95, 95},        new int[]{}),
            new FrameValues(9,    new int[]{7, 3},       new int[]{102, 105},      new int[]{}),
            new FrameValues(10,   new int[]{8, 2, 8},    new int[]{121, 123, 131}, new int[]{0, 9, 9, 12, 19, 31, 32, 35, 42, 58, 59, 64, 69, 69, 79, 95, 95, 102, 105, 121, 123, 131})
    };


//    Подход	1	2	3	4	5	6	7	8	9	10
//    Броски	X	3/	6-1	X	X	X	2/	9-0	7/	XXX
//    Очки	    20	36	43	73	95	115	134	143	163	193
    public static FrameValues[] testSet3 = {
            new FrameValues(1,    new int[]{10},          new int[]{10},             new int[]{0, 10, 13, 20, 20, 20, 20}),
            new FrameValues(2,    new int[]{3, 7},        new int[]{16, 30},         new int[]{}),
            new FrameValues(3,    new int[]{6, 1},        new int[]{42, 43},         new int[]{}),
            new FrameValues(4,    new int[]{10},          new int[]{53},             new int[]{}),
            new FrameValues(5,    new int[]{10},          new int[]{73},             new int[]{0, 10, 16, 30, 42, 43, 53, 73, 93, 95, 95, 95}),
            new FrameValues(6,    new int[]{10},          new int[]{103},            new int[]{0, 10, 16, 30, 42, 43, 53, 73, 103, 107, 115}),
            new FrameValues(7,    new int[]{2, 8},        new int[]{109, 125},       new int[]{}),
            new FrameValues(8,    new int[]{9, 0},        new int[]{143, 143},       new int[]{}),
            new FrameValues(9,    new int[]{7, 3},        new int[]{150, 153},       new int[]{}),
            new FrameValues(10,   new int[]{10, 10, 10},  new int[]{173, 183, 193},  new int[]{0, 10, 16, 30, 42, 43, 53, 73, 103, 109, 125, 143, 143, 150, 153, 173, 183, 193})
    };

    public static FrameValues[] testSetZeros = {
            new FrameValues(1,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(2,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(3,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(4,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(5,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(6,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(7,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(8,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(9,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
            new FrameValues(10,    new int[]{0, 0},        new int[]{0, 0},           new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})
    };


    public static FrameValues[] testSetMax = {
            new FrameValues(1, new int[]{10}, new int[]{10}, new int[]{}),
            new FrameValues(2, new int[]{10}, new int[]{30}, new int[]{}),
            new FrameValues(3, new int[]{10}, new int[]{60}, new int[]{}),
            new FrameValues(4, new int[]{10}, new int[]{90}, new int[]{}),
            new FrameValues(5, new int[]{10}, new int[]{120}, new int[]{}),
            new FrameValues(6, new int[]{10}, new int[]{150}, new int[]{}),
            new FrameValues(7, new int[]{10}, new int[]{180}, new int[]{}),
            new FrameValues(8, new int[]{10}, new int[]{210}, new int[]{}),
            new FrameValues(9, new int[]{10}, new int[]{240}, new int[]{}),
            new FrameValues(10, new int[]{10, 10, 10}, new int[]{270, 290, 300}, new int[]{})
    };
}