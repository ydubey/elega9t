package com.elega9t.commons.shell;

import com.elega9t.commons.util.StringUtilities;
import com.elega9t.commons.util.ReplacementProvider;
import com.elega9t.commons.shell.intrprtr.Context;

public class Environment extends Context<String> {

    public String resolve(String str) {
        return StringUtilities.replace("\\$([a-zA-Z0-9._\\-\\?]+)", str, new ReplacementProvider() {
            @Override
            public String getReplacement(String match) {
                return getValue(match);
            }
        });
    }

}
