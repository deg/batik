/*

   Copyright 2006  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.anim.values;

import java.util.Iterator;
import java.util.Vector;

import org.apache.batik.anim.AnimationTarget;
import org.apache.batik.dom.svg.SVGOMTransform;

import org.w3c.dom.svg.SVGTransform;

/**
 * An SVG transform list value in the animation system.
 *
 * @author <a href="mailto:cam%40mcc%2eid%2eau">Cameron McCormack</a>
 * @version $Id$
 */
public class AnimatableTransformListValue extends AnimatableValue {

    /**
     * List of transforms.
     */
    protected Vector transforms;

    /**
     * Creates a new, uninitialized AnimatableTransformListValue.
     */
    protected AnimatableTransformListValue(AnimationTarget target) {
        super(target);
    }

    /**
     * Creates a new AnimatableTransformListValue with a single transform.
     */
    public AnimatableTransformListValue(AnimationTarget target,
                                        Transform t) {
        super(target);
        this.transforms = new Vector();
        this.transforms.add(t);
    }

    /**
     * Creates a new AnimatableTransformListValue with a transform list.
     */
    public AnimatableTransformListValue(AnimationTarget target,
                                        Vector transforms) {
        super(target);
        int size = transforms.size();
        this.transforms = new Vector(size);
        for (int i = 0; i < size; i++) {
            this.transforms.setElementAt(transforms.elementAt(i), i);
        }
    }

    /**
     * Performs interpolation to the given value.
     */
    public AnimatableValue interpolate(AnimatableValue result,
                                       AnimatableValue to,
                                       float interpolation,
                                       AnimatableValue accumulation,
                                       int multiplier) {

        AnimatableTransformListValue toTransformList =
            (AnimatableTransformListValue) to;
        AnimatableTransformListValue accTransformList =
            (AnimatableTransformListValue) accumulation;

        int accSize = accumulation == null ? 0 : accTransformList.transforms.size();
        int newSize = 1 + accSize * multiplier;

        AnimatableTransformListValue res;
        if (result == null) {
            res = new AnimatableTransformListValue(target);
            res.transforms = new Vector(newSize);
            res.transforms.setSize(newSize);
        } else {
            res = (AnimatableTransformListValue) result;
            if (res.transforms == null) {
                res.transforms = new Vector(newSize);
                res.transforms.setSize(newSize);
            } else if (res.transforms.size() != newSize) {
                res.transforms.setSize(newSize);
            }
        }

        for (int i = 0; i < accSize; i++) {
            for (int j = i; j < i + multiplier; j++) {
                res.transforms.setElementAt
                    (accTransformList.transforms.elementAt(i), j);
            }
        }

        if (to != null) {
            Transform ft = (Transform) transforms.lastElement();
            int type = ft.getType();
            Transform tt = (Transform) toTransformList.transforms.lastElement();
            if (type == tt.getType()) {
                Transform t =
                    (Transform) res.transforms.elementAt(newSize - 1);
                if (t == null) {
                    t = new Transform();
                    res.transforms.setElementAt(t, newSize - 1);
                }
                float x, y, r = 0;
                switch (type) {
                    case SVGTransform.SVG_TRANSFORM_ROTATE:
                    case SVGTransform.SVG_TRANSFORM_SKEWX:
                    case SVGTransform.SVG_TRANSFORM_SKEWY:
                        r = ft.getAngle();
                        r += interpolation * (tt.getAngle() - r);
                        if (type == SVGTransform.SVG_TRANSFORM_SKEWX) {
                            t.setSkewX(r);
                            break;
                        } else if (type == SVGTransform.SVG_TRANSFORM_SKEWY) {
                            t.setSkewY(r);
                            break;
                        }
                        // fall through
                    case SVGTransform.SVG_TRANSFORM_TRANSLATE:
                    case SVGTransform.SVG_TRANSFORM_SCALE:
                        x = ft.getX();
                        y = ft.getY();
                        x += interpolation * (tt.getX() - x);
                        y += interpolation * (tt.getY() - y);
                        if (type == SVGTransform.SVG_TRANSFORM_TRANSLATE) {
                            t.setTranslate(x, y);
                        } else if (type == SVGTransform.SVG_TRANSFORM_SCALE) {
                            t.setScale(x, y);
                        } else {
                            t.setRotate(r, x, y);
                        }
                        break;
                }
            }
        } else {
            Transform ft = (Transform) transforms.lastElement();
            Transform t = (Transform) res.transforms.elementAt(newSize - 1);
            if (t == null) {
                t = new Transform();
                res.transforms.setElementAt(t, newSize - 1);
            }
            t.assign(ft);
        }

        // XXX Do better checking for changes.
        res.hasChanged = true;

        return res;
    }

    /**
     * Gets the transforms.
     */
    public Iterator getTransforms() {
        return transforms.iterator();
    }

    /**
     * Returns a zero value of this AnimatableValue's type.  This returns an
     * empty transform list.
     */
    public AnimatableValue getZeroValue() {
        return new AnimatableTransformListValue(target, new Vector(5));
    }

    /**
     * Returns the CSS text representation of the value.
     */
    public String getCssText() {
        return null;
    }

    /**
     * An SVGTransform class that exposes transform components.
     */
    public static class Transform extends SVGOMTransform {

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void setTranslate(float tx, float ty) {
            super.setTranslate(tx, ty);
            this.x = tx;
            this.y = ty;
        }

        public void setScale(float sx, float sy) {
            super.setScale(sx, sy);
            this.x = sx;
            this.y = sy;
        }

        public void setSkewX(float angle) {
            super.setSkewX(angle);
            this.angle = angle;
        }

        public void setSkewY(float angle) {
            super.setSkewY(angle);
            this.angle = angle;
        }

        protected void assign(Transform t) {
            type = t.type;
            affineTransform = t.affineTransform;
            angle = t.angle;
            x = t.x;
            y = t.y;
        }
    }
}