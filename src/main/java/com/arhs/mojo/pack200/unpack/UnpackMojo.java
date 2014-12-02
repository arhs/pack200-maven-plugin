package com.arhs.mojo.pack200.unpack;

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

import com.arhs.mojo.pack200.AbstractPluginMojo;
import com.arhs.mojo.pack200.pack.AbstractPackMojo;
import com.arhs.mojo.pack200.packing.options.DeflateHint;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

/**
 * Goal for unpacking the jar.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-11-20
 */
@Mojo(name = "unpack")
@Execute(goal = "unpack", phase = LifecyclePhase.VERIFY)
public class UnpackMojo extends AbstractPluginMojo {

    //<editor-fold desc="Fields section.">

    /**
     * Preserves the input information.
     *
     * @see com.arhs.mojo.pack200.packing.options.DeflateHint
     */
    @Parameter
    public DeflateHint deflateHint;

    /**
     * Name of the generated JAR.
     * <p>
     * This property is required and the default value is the name of JAR file generated:
     * <code>${project.build.finalName}.jar</code>.
     * </p>
     */
    @Parameter(defaultValue = "${project.build.finalName}.jar", required = true)
    public String inputFile;

    /**
     * Name of the log output file.
     */
    @Parameter
    public File logFile;

    /**
     * Pptions to the Java Virtual Machine.
     */
    @Parameter
    public List<String> options;

    /**
     * Name of the output file.
     * <p>
     * The default value is the name of JAR file generated followed by the extension <code>.pack.gz</code>:
     * <code>${project.build.finalName}.jar.pack.gz</code>.
     * This property is required.
     * </p>
     */
    @Parameter(defaultValue = "${project.build.directory}/${project.build.finalName}.jar.pack.gz", required = true, readonly = true)
    public File outputFile;

    /**
     * Specifies quiet operation with no messages.
     * <p>The default value is: <code>false</code>.</p>
     */
    @Parameter(defaultValue = "false")
    public boolean quiet;

    /**
     * Remove input file after unpacking.
     * <p>
     * The default value is <code>false</code>.
     * </p>
     */
    @Parameter(defaultValue = "false")
    public boolean removePackFile;

    /**
     * The directory for the resulting file.
     * <p>This property is required and the default value is: <code>${project.build.directory}</code>.</p>
     */
    @Parameter(defaultValue = "${project.build.directory}", required = true)
    public File target;

    /**
     * Outputs minimal messages.
     */
    @Parameter(defaultValue = "false")
    public boolean verbose;

    //</editor-fold>

    //<editor-fold desc="Constructors section.">

    /**
     * Constructor.
     */
    protected UnpackMojo() {
        super("unpack200");
    }

    //</editor-fold>

    //<editor-fold desc="Methods section.">

    //<editor-fold desc="Private methods section.">

    /**
     * Adds a "deflate-hint" argument.
     *
     * @see AbstractPackMojo#deflateHint
     */
    private void addDeflateHint() {
        if (deflateHint != null) {
            addArgument("--deflate-hint", deflateHint.name().toLowerCase());
        }
    }

    /**
     * Adds a JAR file argument.
     *
     * @see AbstractPackMojo#target
     * @see AbstractPackMojo#inputFile
     */
    private void addJarFile() {
        File jarFile = new File(target, inputFile);
        addArgument(jarFile.getAbsolutePath());
    }

    /**
     * Adds the log output file if this exists.
     *
     * @see AbstractPackMojo#logFile
     */
    private void addLogFileArgument() {
        if (logFile != null) {
            addArgument("--log-file", logFile.toString());
        }
    }

    /**
     * Adds a "options" argument.
     *
     * @see AbstractPackMojo#options
     */
    private void addOptions() {
        if ((options != null) && (options.size() > 0)) {
            for (String option : options) {
                addArgument("-J", option);
            }
        }
    }

    /**
     * Adds the output file if this exists.
     */
    private void addOutputFileArgument() {
        if (outputFile != null) {
            addArgument(outputFile.toString());
        }
    }

    /**
     * Adds a "quiet" argument.
     *
     * @see AbstractPackMojo#quiet
     */
    private void addQuietArgument() {
        if (quiet && !verbose) {
            addArgument("--quiet");
        }
    }

    /**
     * Adds a "remove-pack-file" argument.
     *
     * @see UnpackMojo#removePackFile
     */
    private void addRemovePackFile() {
        if (removePackFile) {
            addArgument("--remove-pack-file");
        }
    }

    /**
     * Adds a "verbose" argument.
     *
     * @see AbstractPackMojo#verbose
     */
    private void addVerboseArgument() {
        if (verbose && !quiet) {
            addArgument("--verbose");
        }
    }

    //</editor-fold>

    /**
     * Perform whatever build-process behavior this Mojo implements.
     *
     * @throws MojoExecutionException If an unexpected problem occurs. Throwing this exception
     *                                causes a <b>BUILD ERROR</b> message to be displayed.
     */
    public void execute() throws MojoExecutionException {
        // Options.
        addDeflateHint();
        addRemovePackFile();
        addVerboseArgument();
        addQuietArgument();
        addLogFileArgument();

        // Input JAR file.
        addJarFile();

        // Output JAR file.
        addOutputFileArgument();

        // Execute command.
        super.execute();
    }

    //</editor-fold>

}
