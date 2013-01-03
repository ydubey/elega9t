/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.docking;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class RotatedLabel extends JLabel {

    /** Serialisation ID. */
    private static final long serialVersionUID = -5589305224172247331L;

    /** The text direction. */
    private final DockLocation direction;

    private boolean needsRotate;

    public RotatedLabel(DockLocation direction) {
        this.direction = direction;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        switch (direction) {
            case RIGHT:
            case LEFT:
                return new Dimension(preferredSize.height, preferredSize.width);
            default:
                return preferredSize;
        }
    }

    @Override
    public Dimension getSize() {
        if (!needsRotate) {
            return super.getSize();
        }
        Dimension size = super.getSize();
        switch (direction) {
            case LEFT:
            case RIGHT:
                return new Dimension(size.height, size.width);
            default:
                return super.getSize();
        }
    }

    @Override
    public int getHeight() {
        return getSize().height;
    }

    @Override
    public int getWidth() {
        return getSize().width;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gr = (Graphics2D) g.create();
        switch (direction) {
            case LEFT:
                gr.translate(0, getSize().getHeight());
                gr.transform(AffineTransform.getQuadrantRotateInstance(-1));
                break;
            case RIGHT:
                gr.transform(AffineTransform.getQuadrantRotateInstance(1));
                gr.translate(0, -getSize().getWidth());
                break;
            default:
        }
        needsRotate = direction != DockLocation.BOTTOM;
        super.paintComponent(gr);
        needsRotate = false;
    }

}
