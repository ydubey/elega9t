/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

public class DefaultUniqueEntity extends DefaultEntity implements UniqueEntity {

    private final String id;

    public DefaultUniqueEntity(String id, String name) {
        super(name);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUniqueName() {
        return getName() + "[" + id + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DefaultUniqueEntity that = (DefaultUniqueEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

}
