package uk.ydubey.formatter.numtoword;

import java.util.ArrayList;
import java.util.List;

public class TwoDigitNumberInWordsFormatter extends NumberFormatter {

    private static final String[] IN_WORDS = {
        "",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen",
    };

    private static final String[] TENS_IN_WORDS = {
        "",
        "ten",
        "twenty",
        "thirty",
        "forty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety",
    };

    private static final TwoDigitNumberInWordsFormatter INSTANCE = new TwoDigitNumberInWordsFormatter();

    protected TwoDigitNumberInWordsFormatter() {
    }

    public static TwoDigitNumberInWordsFormatter getInstance() {
        return INSTANCE;
    }

    @Override
    public int getLimit() {
        return 99;
    }

    protected String parseAndFormat(int number) {
        List<String> numberInWordsParts = new ArrayList<String>();

        int unit = number % 10;
        int ten = (number % 100) / 10;

        if(number < IN_WORDS.length) {
            numberInWordsParts.add(IN_WORDS[number]);
        } else {
            numberInWordsParts.add(TENS_IN_WORDS[ten]);
            numberInWordsParts.add(IN_WORDS[unit]);
        }
        return join(numberInWordsParts);
    }

}
