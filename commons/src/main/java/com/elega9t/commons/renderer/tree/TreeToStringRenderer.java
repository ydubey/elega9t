/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.tree;

import com.elega9t.commons.entity.EntityLoadException;
import com.elega9t.commons.entity.EntityNode;
import com.elega9t.commons.renderer.Renderer;
import com.elega9t.commons.renderer.table.Border;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TreeToStringRenderer implements Renderer<EntityNode> {

    private final Border border;

    public TreeToStringRenderer(Border border) {
        this.border = border;
    }

    @Override
    public String render(EntityNode data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream);
        render(data, out);
        return new String(byteArrayOutputStream.toByteArray());
    }

    @Override
    public void render(EntityNode data, PrintStream out) {
        render(data, out, 0, false, new ArrayList<Integer>());
    }

    protected void render(EntityNode data, PrintStream out, int level, boolean isLast, List<Integer> parentLevelsNotTerminated) {
        for(int index=0; index<level; index++) {
            final char space = border.getSpace();
            out.print(space);
            out.print(space);
            out.print(space);
            if(index != level - 1 && parentLevelsNotTerminated.contains(index)) {
                out.print(border.getVertical());
            }
        }
        if(level > 0) {
            if(isLast) {
                out.print(border.getBottomLeft());
            } else {
                out.print(border.getRowSeparatorStart());
            }
            out.print(border.getHorizontal());
            out.print(border.getHorizontal());
        }
        out.print(data.toString());
        out.println();
        final int childCount = data.getChildCount();
        for(int index=0; index< childCount; index++) {
            isLast = index == childCount - 1;
            List<Integer> parentLevels = new ArrayList<Integer>(parentLevelsNotTerminated);
            if(!isLast) {
                parentLevels.add(level);
            }
            render(data.getChildAt(index), out, level + 1, isLast, parentLevels);
        }
    }

    public static void main(String[] args) throws IOException, EntityLoadException {
        new TreeToStringRenderer(Border.SINGLE).render(new FolderEntityNode(), System.out);
    }

}
