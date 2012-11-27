/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.cp;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public abstract class AbstractClassPathResource implements ClassPathResource {

    @Override
    public List<Class> listClasses(ClassFilter filter) throws IOException, ClassNotFoundException {
        return listClasses(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        }, filter);
    }

}