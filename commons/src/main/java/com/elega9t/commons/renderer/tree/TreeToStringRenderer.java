/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.tree;

import com.elega9t.commons.entity.EntityNode;
import com.elega9t.commons.renderer.Renderer;
import com.elega9t.commons.renderer.table.Border;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreeToStringRenderer implements Renderer<EntityNode> {

    private final Border border;

    public TreeToStringRenderer(Border border) {
        this.border = border;
    }

    @Override
    public String render(EntityNode data) {
        StringBuilder rendered = new StringBuilder();
        render(rendered, data, 0, false, new ArrayList<Integer>());
        return rendered.toString();
    }

    protected void render(StringBuilder rendered, EntityNode data, int level, boolean isLast, List<Integer> parentLevelsNotTerminated) {
        for(int index=0; index<level; index++) {
            final char space = border.getSpace();
            rendered.append(space).append(space).append(space);
            if(index != level - 1 && parentLevelsNotTerminated.contains(index)) {
                rendered.append(border.getVertical());
            }
        }
        if(level > 0) {
            if(isLast) {
                rendered.append(border.getBottomLeft());
            } else {
                rendered.append(border.getRowSeparatorStart());
            }
            rendered.append(border.getHorizontal()).append(border.getHorizontal());
        }
        rendered.append(data.getName()).append("\n");
        final int childCount = data.getChildCount();
        for(int index=0; index< childCount; index++) {
            isLast = index == childCount - 1;
            List<Integer> parentLevels = new ArrayList<Integer>(parentLevelsNotTerminated);
            if(!isLast) {
                parentLevels.add(level);
            }
            render(rendered, data.getChild(index), level + 1, isLast, parentLevels);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new TreeToStringRenderer(Border.SINGLE).render(new FolderEntityNode()));
    }

}
