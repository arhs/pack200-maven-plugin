package com.arhs.mojo.pack200.pack;

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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Goal for packing the jar.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-11-17
 */
@Mojo(name = "pack")
@Execute(goal = "pack", phase = LifecyclePhase.PACKAGE)
public class PackMojo extends AbstractPackMojo {

    //<editor-fold desc="Fields section.">

    /**
     * Name of the output file.
     * <p>
     * The default value is the name of JAR file generated followed by the extension <code>.pack.gz</code>:
     * <code>${project.build.directory}/${project.build.finalName}.jar.pack.gz</code>.
     * This property is required.
     * </p>
     */
    @Parameter(defaultValue = "${project.build.directory}/${project.build.finalName}.jar.pack.gz", required = true, readonly = true)
    public File outputFile;

    //</editor-fold>

    //<editor-fold desc="Methods section.">

    /**
     * Perform whatever build-process behavior this Mojo implements.
     *
     * @throws MojoExecutionException If an unexpected problem occurs. Throwing this exception
     *                                causes a <b>BUILD ERROR</b> message to be displayed.
     */
    public void execute() throws MojoExecutionException {
        super.execute(outputFile);
    }

    //</editor-fold>

}
