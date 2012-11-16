package uk.ydubey.formatter.numtoword;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberInWordsFormatterTest {

    private NumberInWordsFormatter test = NumberInWordsFormatter.getInstance();

    @Test
    public void canFormat0InWords() throws Exception {
        assertEquals("", test.format(0));
    }

    @Test
    public void canFormatOneDigitNumbersInWords() throws Exception {
        assertEquals("three", test.format(3));
    }

    @Test
    public void canFormatTwoDigitNumbersInWords() throws Exception {
        assertEquals("fifty six", test.format(56));
    }

    @Test
    public void canFormatThreeDigitNumbersInWords() throws Exception {
        assertEquals("three hundred and eighty seven", test.format(387));
    }

    @Test
    public void canFormatFourDigitNumbersWithNoHundredsInWords() throws Exception {
        assertEquals("seven thousand and nineteen", test.format(7019));
    }

    @Test
    public void canFormatFourDigitNumbersInWords() throws Exception {
        assertEquals("seven thousand two hundred and ten", test.format(7210));
    }

    @Test
    public void canFormatFiveDigitNumbersInWords() throws Exception {
        assertEquals("forty eight thousand six hundred and sixty seven", test.format(48667));
    }

    @Test
    public void canFormatSixDigitNumbersInWords() throws Exception {
        assertEquals("one hundred and fifty two thousand nine hundred and forty five", test.format(152945));
    }

    @Test
    public void canFormatSevenDigitNumbersInWords() throws Exception {
        assertEquals("nine million three hundred and sixty seven thousand three hundred and eighty four", test.format(9367384));
    }

    @Test
    public void canFormatEightDigitNumbersInWords() throws Exception {
        assertEquals("sixty million three hundred and seventy two thousand nine hundred and five", test.format(60372905));
    }

    @Test
    public void canFormatNineDigitNumbersInWords() throws Exception {
        assertEquals("nine hundred and seventy three million nine hundred and fifty seven thousand three hundred and eighty five", test.format(973957385));
    }

    @Test
    public void canFormatNineDigitNumbersWithNoHundredsInWords() throws Exception {
        assertEquals("eight million five thousand and six", test.format(8005006));
    }

    @Test
    public void canFormatNineDigitNumbersWithNoHundredsAndThousandsInWords() throws Exception {
        assertEquals("five million and two", test.format(5000002));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfNumberGreaterThan999999999() throws Exception {
        test.format(1000000000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfNumberIsLessThan0() throws Exception {
        test.format(-1);
    }

}
