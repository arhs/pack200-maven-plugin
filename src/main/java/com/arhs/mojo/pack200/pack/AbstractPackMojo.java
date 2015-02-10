package com.arhs.mojo.pack200.pack;

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

import com.arhs.mojo.pack200.AbstractPluginMojo;
import com.arhs.mojo.pack200.packing.options.DeflateHint;
import com.arhs.mojo.pack200.packing.options.ModificationTime;
import com.arhs.mojo.pack200.packing.options.UnknownAttribute;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Base for goal for packaging the jar.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-11-20
 */
public abstract class AbstractPackMojo extends AbstractPluginMojo {

    //<editor-fold desc="Fields section.">

    /**
     * Specifies a configuration file.
     */
    @Parameter
    public File configFile;

    /**
     * Preserves the input information.
     *
     * @see com.arhs.mojo.pack200.packing.options.DeflateHint
     */
    @Parameter
    public DeflateHint deflateHint;

    /**
     * Specifies how hard to try to pack the JAR file.
     */
    @Parameter
    public String effort;

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
     * Preserve the order of files in the input file.
     * <p>The default value is: <code>false</code>.</p>
     */
    @Parameter(defaultValue = "false")
    public boolean keepFileOrder;

    /**
     * Name of the log output file.
     */
    @Parameter
    public File logFile;

    /**
     * Transmit modtimes.
     *
     * @see com.arhs.mojo.pack200.packing.options.ModificationTime
     */
    @Parameter
    public ModificationTime modificationTime;

    /**
     * Disables GZIP compression.
     * <p>The default value is: <code>false</code>.</p>
     */
    @Parameter(defaultValue = "false")
    public boolean noGzip;

    /**
     * Allows pack200 to reorder the elements of the JAR files.
     * <p>The default value is: <code>false</code>.</p>
     */
    @Parameter(defaultValue = "false")
    public boolean noKeepFileOrder;

    /**
     * Options to the Java Virtual Machine.
     */
    @Parameter
    public List<String> options;

    /**
     * Specifies quiet operation with no messages.
     * <p>The default value is: <code>false</code>.</p>
     */
    @Parameter(defaultValue = "false")
    public boolean quiet;

    /**
     * Sets a target segment size.
     */
    @Parameter
    public int segmentLimit;

    /**
     * Strips attributes used for debugging from the output.
     * <p>The default value is: <code>false</code>.</p>
     */
    @Parameter(defaultValue = "false")
    public boolean stripDebug;

    /**
     * Unknown attribute is passed through with the specified action.
     *
     * @see com.arhs.mojo.pack200.packing.options.UnknownAttribute
     */
    @Parameter
    public UnknownAttribute unknownAttribute;

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
    protected AbstractPackMojo() {
        super("pack200");
    }

    //</editor-fold>

    //<editor-fold desc="Methods section.">

    /**
     * Adds the configuration file if this exists.
     *
     * @see AbstractPackMojo#configFile
     */
    private void addConfigFileArgument() {
        if (configFile != null) {
            if (configFile.exists()) {
                addArgument("--config-file", configFile.toString());
            } else if (debug) {
                getLog().warn("The configuration file doesn't exist: " + configFile);
            }
        }
    }

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
     * Adds a "effort" argument.
     *
     * @see AbstractPackMojo#effort
     */
    private void addEffort() {
        if (StringUtils.isNotEmpty(effort)) {
            addArgument("--effort", effort);
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
     * Adds a "keep-file-order" argument.
     *
     * @see AbstractPackMojo#keepFileOrder
     */
    private void addKeepFileOrder() {
        if (keepFileOrder && !noKeepFileOrder) {
            addArgument("--keep-file-order");
        }
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
                addArgument("-J", option, "");
            }
        }
    }

    /**
     * Adds a "modification-time" argument.
     *
     * @see AbstractPackMojo#modificationTime
     */
    private void addModificationTime() {
        if (modificationTime != null) {
            addArgument("--modification-time", modificationTime.name().toLowerCase());
        }
    }

    /**
     * Adds a "no-gzip" argument.
     *
     * @see AbstractPackMojo#noGzip
     */
    private void addNoGzip() {
        if (noGzip) {
            addArgument("--no-gzip");
        }
    }

    /**
     * Adds a "no-keep-file-order" argument.
     *
     * @see AbstractPackMojo#noKeepFileOrder
     */
    private void addNoKeepFileOrder() {
        if (noKeepFileOrder && !keepFileOrder) {
            addArgument("--no-keep-file-order");
        }
    }

    /**
     * Adds the output file if this exists.
     *
     * @param outputFile Output file.
     */
    private void addOutputFileArgument(File outputFile) {
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
     * Adds a "segment-limit" argument.
     *
     * @see AbstractPackMojo#segmentLimit
     */
    private void addSegmentLimit() {
        if (segmentLimit != 0) {
            addArgument("--segment-limit", String.valueOf(segmentLimit));
        }
    }

    /**
     * Adds a "strip-debug" argument.
     *
     * @see AbstractPackMojo#stripDebug
     */
    private void addStripDebug() {
        if (stripDebug) {
            addArgument("--strip-debug");
        }
    }

    /**
     * Adds a "unknown-attribute" argument.
     *
     * @see AbstractPackMojo#unknownAttribute
     */
    private void addUnknown() {
        if (unknownAttribute != null) {
            addArgument("--unknown-attribute", unknownAttribute.name().toLowerCase());
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

    /**
     * Perform whatever build-process behavior this Mojo implements.
     *
     * @see AbstractPackMojo#execute(File)
     * @throws MojoExecutionException If an unexpected problem occurs. Throwing this exception
     *                                causes a <b>BUILD ERROR</b> message to be displayed.
     */
    public void execute() throws MojoExecutionException {
        this.execute(null);
    }

    /**
     * Perform whatever build-process behavior this Mojo implements.
     *
     * @param outputFile                Output JAR file.
     * @throws MojoExecutionException   If an unexpected problem occurs. Throwing this exception
     *                                  causes a <b>BUILD ERROR</b> message to be displayed.
     */
    public void execute(File outputFile) throws MojoExecutionException {
        // Options.
        addNoGzip();
        addStripDebug();
        addNoKeepFileOrder();
        addKeepFileOrder();
        addSegmentLimit();
        addEffort();
        addDeflateHint();
        addModificationTime();
        addUnknown();
        addConfigFileArgument();
        addVerboseArgument();
        addQuietArgument();
        addLogFileArgument();
        addOptions();

        // Output JAR file.
        addOutputFileArgument(outputFile);
        // Input JAR file.
        addJarFile();

        // Execute command.
        super.execute();
    }

    //</editor-fold>
}
