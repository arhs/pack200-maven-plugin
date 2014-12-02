package com.arhs.mojo.pack200;

/**
 * The MIT License (MIT)
 * Copyright (c) 2014 ARHS Developments SA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import com.arhs.mojo.pack200.unpack.UnpackMojo;

import java.io.File;

/**
 * Unit tests for {@code UnpackMojo} class.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-12-02
 */
public class UnpackMojoTest extends AbstractMojoTest {

    //<editor-fold desc="Constants section.">

    /**
     * Original JAR file.
     */
    private static final String UNPACK_FILE_ORIGINAL = "src/test/resources/my-applet.original.jar.pack.gz";

    //</editor-fold>

    //<editor-fold desc="Methods section.">

    /**
     * Test for create a compressed JAR file.
     * @throws Exception If the input JAR file couldn't be copied or that mojo object couldn't be executed.
     */
    public void testPack() throws Exception {
        // Get mojo object.
        final UnpackMojo unpackMojo = createMojoByPomFile("src/test/resources/pom/unpack.xml", "unpack");

        // Create a JAR file by the "inputFile" parameter.
        final File inputJarFile = copyJar(unpackMojo.target, unpackMojo.inputFile, UNPACK_FILE_ORIGINAL);
        unpackMojo.execute();

        // Checks if input JAR file and output JAR file exists.
        assertTrue(inputJarFile.exists());
        assertTrue(unpackMojo.outputFile.exists());

        // Clean generated files.
        inputJarFile.delete();
        unpackMojo.outputFile.delete();
    }

    //</editor-fold>

}
