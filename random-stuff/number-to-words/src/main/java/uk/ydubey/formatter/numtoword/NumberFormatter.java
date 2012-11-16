package uk.ydubey.formatter.numtoword;

import java.util.List;

public abstract class NumberFormatter {

    public abstract int getLimit();

    public final String format(int number) {
        if(number < 0 || number > getLimit()) {
            throw new IllegalArgumentException(String.format("Can only format numbers between 0-%d: %d", getLimit(), number));
        }
        else {
            return parseAndFormat(number);
        }
    }

    protected static String join(List<String> items) {
        StringBuilder joined = new StringBuilder();
        for (String item : items) {
            joined.append(item);
            joined.append(" ");
        }
        return joined.toString().trim();
    }

    protected abstract String parseAndFormat(int number);

}
