/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.dom.svg;

import org.apache.batik.dom.AbstractDocument;

import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGAnimatedLength;
import org.w3c.dom.svg.SVGRectElement;

/**
 * This class implements {@link SVGRectElement}.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class SVGOMRectElement
    extends    SVGGraphicsElement
    implements SVGRectElement {

    /**
     * Creates a new SVGOMRectElement object.
     */
    protected SVGOMRectElement() {
    }

    /**
     * Creates a new SVGOMRectElement object.
     * @param prefix The namespace prefix.
     * @param owner The owner document.
     */
    public SVGOMRectElement(String prefix, AbstractDocument owner) {
        super(prefix, owner);
    }

    /**
     * <b>DOM</b>: Implements {@link Node#getLocalName()}.
     */
    public String getLocalName() {
        return SVG_RECT_TAG;
    }

    /**
     * <b>DOM</b>: Implements {@link SVGRectElement#getX()}.
     */
    public SVGAnimatedLength getX() {
        throw new RuntimeException(" !!! TODO: getX()");
    } 

    /**
     * <b>DOM</b>: Implements {@link SVGRectElement#getY()}.
     */
    public SVGAnimatedLength getY() {
        throw new RuntimeException(" !!! TODO: getY()");
    } 

    /**
     * <b>DOM</b>: Implements {@link SVGRectElement#getWidth()}.
     */
    public SVGAnimatedLength getWidth() {
        throw new RuntimeException(" !!! TODO: getWidth()");
    } 

    /**
     * <b>DOM</b>: Implements {@link SVGRectElement#getHeight()}.
     */
    public SVGAnimatedLength getHeight() {
        throw new RuntimeException(" !!! TODO: getHeight()");
    } 

    /**
     * <b>DOM</b>: Implements {@link SVGRectElement#getRx()}.
     */
    public SVGAnimatedLength getRx() {
        throw new RuntimeException(" !!! TODO: getRx()");
    } 

    /**
     * <b>DOM</b>: Implements {@link SVGRectElement#getRy()}.
     */
    public SVGAnimatedLength getRy() {
        throw new RuntimeException(" !!! TODO: getRy()");
    } 

    /**
     * Returns a new uninitialized instance of this object's class.
     */
    protected Node newNode() {
        return new SVGOMRectElement();
    }
}
