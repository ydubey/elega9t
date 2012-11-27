/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilities {

    public static List<String> split(String str) {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(str);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }
        return matchList;
    }

    public static String replace(String patternString, String str, ReplacementProvider replacementProvider) {
        StringBuffer stringBuffer = new StringBuffer();
        if(str != null) {
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                String propertyName = matcher.group(1);
                String value = replacementProvider.getReplacement(propertyName);
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(value == null ? "" : value));
            }
            matcher.appendTail(stringBuffer);
        }
        return stringBuffer.toString();
    }

    public static String join(List<String> values) {
        return join(values, ", ");
    }

    public static String join(List<String> values, String separator) {
        StringBuilder joined = new StringBuilder();
        for (int i = 0, valuesSize = values.size(); i < valuesSize; i++) {
            String value = values.get(i);
            joined.append(value);
            if(i < values.size() -1) {
                joined.append(separator);
            }
        }
        return joined.toString();
    }

}
