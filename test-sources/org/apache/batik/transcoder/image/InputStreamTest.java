/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.transcoder.image;

import java.awt.image.BufferedImage;

import java.io.InputStream;
import java.io.IOException;

import java.net.URL;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;

/**
 * Test the ImageTranscoder input with a InputStream.
 *
 * @author <a href="mailto:Thierry.Kormann@sophia.inria.fr">Thierry Kormann</a>
 * @version $Id$
 */
public class InputStreamTest extends AbstractImageTranscoderTest {

    /** The URI of the input image. */
    protected String inputURI;

    /** The URI of the reference image. */
    protected String refImageURI;

    /**
     * Constructs a new <tt>InputStreamTest</tt>.
     *
     * @param inputURI the URI of the input image
     * @param the URI of the reference image
     */
    public InputStreamTest(String inputURI, String refImageURI) {
	this.inputURI = inputURI;
	this.refImageURI = refImageURI;
    }

    /**
     * Creates the <tt>TranscoderInput</tt>.
     */
    protected TranscoderInput createTranscoderInput() {
	try {
	    URL url = resolveURL(inputURI);
	    InputStream istream = url.openStream();
	    TranscoderInput input = new TranscoderInput(istream);
	    input.setURI(url.toString()); // Needed for external resources
	    return input;
	} catch (IOException ex) {
            throw new IllegalArgumentException(inputURI);
	}
    }

    /**
     * Returns the reference image for this test.
     */
    protected BufferedImage getReferenceImage() {
	return createBufferedImage(resolveURL(refImageURI));
    }
}