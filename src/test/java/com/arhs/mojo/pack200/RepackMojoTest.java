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

import com.arhs.mojo.pack200.pack.RepackMojo;

import java.io.File;

/**
 * Unit tests for {@code RepackMojo} class.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-12-02
 */
public class RepackMojoTest extends AbstractMojoTest {

    //<editor-fold desc="Constants section.">

    /**
     * Original JAR file.
     */
    private static final String JAR_FILE_ORIGINAL = "src/test/resources/my-applet.original.jar";

    //</editor-fold>

    //<editor-fold desc="Fields section.">

    /**
     * Input JAR file.
     */
    private File inputJarFile;

    //</editor-fold>

    //<editor-fold desc="Methods section.">

    //<editor-fold desc="Private methods section.">

    /**
     * Test for packing and unpacking a JAR file with a specific POM file.
     *
     * @param pomFile       POM file.
     * @throws Exception    If the input JAR file couldn't be copied or that mojo object couldn't be executed.
     */
    private void testRepack(String pomFile) throws Exception {
        // Get mojo object.
        RepackMojo repackMojo = createMojoByPomFile(pomFile, "repack");

        // Create a JAR file by the "inputFile" parameter.
        inputJarFile = copyJar(repackMojo.target, repackMojo.inputFile, JAR_FILE_ORIGINAL);
        repackMojo.execute();

        // Checks if the input JAR file exists.
        assertTrue("No input JAR file was created.", inputJarFile.exists());
    }

    //</editor-fold>

    //<editor-fold desc="Protected methods section.">

    /**
     * {@inheritDoc}
     */
    protected void tearDown() throws Exception {
        super.tearDown();

        // Clean generated files.
        inputJarFile.delete();
    }

    //</editor-fold>

    /**
     * Test for packing and unpacking a JAR file.
     * @throws Exception If the input JAR file couldn't be copied or that mojo object couldn't be executed.
     */
    public void testRepack() throws Exception {
        testRepack("src/test/resources/pom/repack.xml");
    }

    /**
     * Test for packing and unpacking a JAR file with a compression level.
     * @throws Exception If the input JAR file couldn't be copied or that mojo object couldn't be executed.
     */
    public void testRepackCompressionLevel() throws Exception {
        testRepack("src/test/resources/pom/repack-compression-level.xml");
    }

    /**
     * Test for packing and unpacking a JAR file to using a configuration file.
     * @throws Exception If the input JAR file couldn't be copied or that mojo object couldn't be executed.
     */
    public void testRepackConfig() throws Exception {
        testRepack("src/test/resources/pom/repack-config.xml");
    }

    //</editor-fold>

}