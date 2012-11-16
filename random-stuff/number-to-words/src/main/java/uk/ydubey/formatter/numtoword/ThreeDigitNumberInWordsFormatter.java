package uk.ydubey.formatter.numtoword;

import java.util.ArrayList;
import java.util.List;

public class ThreeDigitNumberInWordsFormatter extends TwoDigitNumberInWordsFormatter {

    private static final ThreeDigitNumberInWordsFormatter INSTANCE = new ThreeDigitNumberInWordsFormatter();

    protected ThreeDigitNumberInWordsFormatter() {
    }

    public static ThreeDigitNumberInWordsFormatter getInstance() {
        return INSTANCE;
    }

    @Override
    public int getLimit() {
        return 999;
    }

    protected String parseAndFormat(int number) {
        if(number <= super.getLimit()) {
            return super.parseAndFormat(number);
        } else {
            List<String> numberInWordsParts = new ArrayList<String>();

            final int hundreds = number / 100;

            numberInWordsParts.add(super.parseAndFormat(hundreds));
            numberInWordsParts.add("hundred");

            String tensAndUnitsInWords = super.parseAndFormat(number % 100);
            if(tensAndUnitsInWords.length() > 0) {
                numberInWordsParts.add("and");
                numberInWordsParts.add(tensAndUnitsInWords);
            }

            return join(numberInWordsParts);
        }
    }

}
