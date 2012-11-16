package uk.ydubey.formatter.numtoword;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThreeDigitNumberInWordsFormatterTest {

    private ThreeDigitNumberInWordsFormatter test = ThreeDigitNumberInWordsFormatter.getInstance();

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
    public void canFormat_100_inWords() throws Exception {
        assertEquals("one hundred", test.format(100));
    }

    @Test
    public void canFormat_387_inWords() throws Exception {
        assertEquals("three hundred and eighty seven", test.format(387));
    }

    @Test
    public void canFormat_999_inWords() throws Exception {
        assertEquals("nine hundred and fifteen", test.format(915));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfNumberIsLessThan0() throws Exception {
        test.format(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfNumberIsGreaterThan999() throws Exception {
        test.format(1000);
    }

}
