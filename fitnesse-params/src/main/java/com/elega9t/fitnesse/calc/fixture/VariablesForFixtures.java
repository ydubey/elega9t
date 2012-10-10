/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.fitnesse.calc.fixture;

import fit.ColumnFixture;
import fit.Parse;

import java.util.*;

public class VariablesForFixtures extends ColumnFixture {

    public static final String SPLIT_REGEX = "\\s*,\\s*";

    private List<Map<String, String>> variables = new ArrayList<Map<String, String>>();

    @Override
    public void doRows(Parse rows) {
        beautify(rows.more);
        if(getArgs().length > 0) {
            initializeValues();
            Parse r = rows;
            Parse original = copy(Collections.<String, String>emptyMap(), r.more);
            r.more = null;
            for (Map<String, String> values: variables) {
                Parse copy = copy(values, original, "Scenario: <b>" + values +"</b>");
                while (r.more != null) {
                    r = r.more;
                }
                r.more = copy;
            }
        }
        super.doRows(rows);
    }

    private void beautify(Parse parse) {
        while(parse.more != null) {
            if(parse.parts.parts == null && parse.parts.more==null) {
                parse.parts.tag = parse.parts.tag.substring(0, parse.parts.tag.length()-1) + " style=\"background-color:#FFFFE6;font-weight:bold\">";
            }
            parse = parse.more;
        }
    }

    private void initializeValues() {
        String[] arguments = getArgs();
        String[] variableNames = arguments[0].split(SPLIT_REGEX);
        for(int index=1; index< arguments.length; index++) {
            String[] values = arguments[index].split(SPLIT_REGEX);
            Map<String, String> variableValues = new LinkedHashMap<String, String>();
            for (int idx=0; idx<variableNames.length; idx++) {
                if(values.length < variableNames.length) {
                    throw new IndexOutOfBoundsException("Insufficient number of values supplied for execution scenario " + index);
                }
                variableValues.put(variableNames[idx], values[idx]);
            }
            variables.add(variableValues);
        }
    }

    private static Parse copy(Map<String, String> values, Parse orig) {
        return copy(values,  orig, null);
    }

    private static Parse copy(Map<String, String> values, Parse original, String comment) {
        if (original == null) {
            return null;
        } else {
            Parse duplicate = new Parse(null, null, null, null);
            duplicate.body = original.body;
            duplicate.end = original.end;
            duplicate.leader = original.leader;
            duplicate.tag = original.tag;
            duplicate.trailer = original.trailer;
            duplicate.more = copy(values, original.more);
            duplicate.parts = copy(values, original.parts);
            if(duplicate.body != null) {
                for (String name : values.keySet()) {
                    duplicate.body = duplicate.body.replaceAll("#\\{" + name + "\\}", values.get(name));
                }
            }
            if(comment != null) {
                duplicate = addComment(comment, duplicate);
            }
            return duplicate;
        }
    }

    private static Parse addComment(String comment, Parse duplicate) {
        Parse dupWithComment = new Parse(null, null, null, null);
        dupWithComment.leader = "\n\t\t";
        dupWithComment.tag = "<td colspan=\"500\" style=\"background-color:#8FB28F;\">";
        dupWithComment.body = comment;
        dupWithComment.end = "</td>";
        dupWithComment.trailer = "\n\t";
        dupWithComment.more = duplicate;
        return dupWithComment;
    }

    // protected abstract void resetFixture();

}
