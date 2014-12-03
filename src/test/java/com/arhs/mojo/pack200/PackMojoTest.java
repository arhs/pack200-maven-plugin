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

import com.arhs.mojo.pack200.pack.PackMojo;

import java.io.File;

/**
 * Unit tests for {@code PackMojo} class.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-12-02
 */
public class PackMojoTest extends AbstractMojoTest {

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

    /**
     * Pack mojo object.
     */
    private PackMojo packMojo;

    //</editor-fold>

    //<editor-fold desc="Methods section.">

    //<editor-fold desc="Private methods section.">

    /**
     * Test for create a compressed JAR file.
     *
     * @param pomFile       POM file.
     * @throws Exception    If the input JAR file couldn't be copied or that mojo object couldn't be executed.
     */
    private void testPack(String pomFile) throws Exception {
        // Get mojo object.
        packMojo = createMojoByPomFile(pomFile, "pack");

        // Create a JAR file by the "inputFile" parameter.
        inputJarFile = copyJar(packMojo.target, packMojo.inputFile, JAR_FILE_ORIGINAL);
        packMojo.execute();

        // Checks if the input JAR file exists.
        assertTrue("No input JAR file was created.", inputJarFile.exists());
        assertTrue("No output JAR file was created.", packMojo.outputFile.exists());
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
        packMojo.outputFile.delete();
    }

    //</editor-fold>

    /**
     * Test for create a compressed JAR file and create a log file.
     * @throws Exception
     */
    public void testPackLogFile() throws Exception {
        testPack("src/test/resources/pom/pack-log.xml");

        // Checks if log file exists.
        assertTrue("No log file was created.", packMojo.logFile.exists());

        // Clean generated file.
        packMojo.logFile.delete();
    }
    /**
     * Test for create a compressed JAR file and create a log file.
     * @throws Exception
     */
    public void testPackOptions() throws Exception {
        testPack("src/test/resources/pom/pack-options.xml");
    }

    //</editor-fold>

}
