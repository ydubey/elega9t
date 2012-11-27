/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.cp;

import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public interface ClassPathResource {

    List<Class> listClasses(ClassFilter filter) throws IOException, ClassNotFoundException;

    List<Class> listClasses(FilenameFilter classNameFilter, ClassFilter filter) throws IOException, ClassNotFoundException;

}
