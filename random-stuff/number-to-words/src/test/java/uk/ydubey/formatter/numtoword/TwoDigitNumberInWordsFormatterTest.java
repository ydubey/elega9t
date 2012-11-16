package uk.ydubey.formatter.numtoword;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TwoDigitNumberInWordsFormatterTest {

    private TwoDigitNumberInWordsFormatter test = TwoDigitNumberInWordsFormatter.getInstance();

    @Test
    public void canFormat_0_inWords() throws Exception {
        assertEquals("", test.format(0));
    }

    @Test
    public void canFormat_1_inWords() throws Exception {
        assertEquals("one", test.format(1));
    }

    @Test
    public void canFormat_10_inWords() throws Exception {
        assertEquals("ten", test.format(10));
    }

    @Test
    public void canFormat_56_inWords() throws Exception {
        assertEquals("fifty six", test.format(56));
    }

    @Test
    public void canFormat_99_inWords() throws Exception {
        assertEquals("ninety nine", test.format(99));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfNumberIsLessThan0() throws Exception {
        test.format(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfNumberIsGreaterThan99() throws Exception {
        test.format(100);
    }

}
