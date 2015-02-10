package com.arhs.mojo.pack200;

/**
 * The MIT License (MIT)
 * Copyright (c) 2015 ARHS Developments SA
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

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;
import java.io.IOException;

/**
 * Base for  mojo unit tests.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-12-02
 */
public abstract class AbstractMojoTest extends AbstractMojoTestCase {

    //<editor-fold desc="Methods section.">

    //<editor-fold desc="Private methods section.">

    /**
     * Checks if the mojo obtained isn't NULL.
     *
     * @param mojo      Mojo to test.
     * @param <TMojo>   Type of mojo.
     */
    private <TMojo> void assertMojo(TMojo mojo) {
        assertNotNull("The Mojo object is null.", mojo);
    }

    /**
     * Checks if the POM file isn't NULL and is exists.
     * @param pom POM file.
     */
    private void assertPom(File pom) {
        assertNotNull("POM file is null.", pom);
        assertTrue("POM file isn't exists.", pom.exists());
    }

    //</editor-fold>

    /**
     * Create a Mojo object by POM file and goal name.
     *
     * @param pomFile       POM file.
     * @param goal          Goal name.
     * @param <TMojo>       Type of mojo object.
     * @return              Mojo object.
     * @throws Exception    If mojo object couldn't be retrieved or POM file doesn't exists.
     */
    protected  <TMojo> TMojo createMojoByPomFile(String pomFile, String goal) throws Exception {
        // Get POM file.
        final File pom = new File(getBasedir(), pomFile);
        assertPom(pom);

        // Get mojo object.
        final TMojo mojo = getMojo(goal, pom);
        assertMojo(mojo);

        return mojo;
    }

    /**
     * Creates a copy of the original JAR file.
     *
     * @param path          Path to the directory that will contain the copy.
     * @param filename      File name copy.
     * @param originalFile  Original file name.
     * @return              Path to File name copied.
     * @throws IOException  If the copy of the original file fails.
     */
    protected File copyJar(File path, String filename, String originalFile) throws IOException {
        final File inputJarFileOriginal = new File(originalFile);
        final File inputJarFile = new File(path, filename);

        FileUtils.copyFile(inputJarFileOriginal, inputJarFile);
        return inputJarFile;
    }

    /**
     * Gets mojo by goal name and POM file.
     *
     * @param goal          Goal name.
     * @param pom           POM file.
     * @param <TMojo>       Type of mojo.
     * @return              A mojo object.
     * @throws Exception    If mojo object couldn't be retrieved.
     */
    @SuppressWarnings("unchecked")
    protected <TMojo> TMojo getMojo(String goal, File pom) throws Exception {
        return (TMojo) lookupMojo(goal, pom);
    }

    //</editor-fold>

}
