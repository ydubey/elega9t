package uk.ydubey.formatter.numtoword;

import java.util.ArrayList;
import java.util.List;

public class NumberInWordsFormatter extends ThreeDigitNumberInWordsFormatter {

    private static final NumberInWordsFormatter INSTANCE = new NumberInWordsFormatter();

    private static final String[] IN_WORDS = {
            "thousand",
            "million"
    };

    protected NumberInWordsFormatter() {
    }

    public static NumberInWordsFormatter getInstance() {
        return INSTANCE;
    }

    @Override
    public int getLimit() {
        return 999999999;
    }

    @Override
    protected String parseAndFormat(int number) {
        return parseAndFormat(number, 0);
    }

    private String parseAndFormat(int number, int index) {
        if(number <= super.getLimit()) {
            return super.parseAndFormat(number);
        } else {
            List<String> numberInWordsParts = new ArrayList<String>();

            int thousandsAndAbove = number / 1000;
            numberInWordsParts.add(parseAndFormat(thousandsAndAbove, index + 1));
            if(thousandsAndAbove % 1000 > 0) {
                numberInWordsParts.add(IN_WORDS[index]);
            }

            int hundredsTensAndUnits = number % 1000;
            String hundredsTensAndUnitsInWords = super.format(hundredsTensAndUnits);

            if(index==0 && hundredsTensAndUnitsInWords.length() > 0 && hundredsTensAndUnits < 100) {
                numberInWordsParts.add("and");
            }
            numberInWordsParts.add(hundredsTensAndUnitsInWords);

            return join(numberInWordsParts);
        }
    }

}
