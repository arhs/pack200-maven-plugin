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

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.StringUtils;

import java.io.IOException;

/**
 * Base for plugin Mojo.
 *
 * @author Cyril Schumacher
 * @version 1.0
 * @since 2014-11-20
 */
public class AbstractPluginMojo extends AbstractMojo {

    //<editor-fold desc="Fields section.">

    /**
     * Command.
     */
    private CommandLine command;

    /**
     * Executor.
     */
    private DefaultExecutor executor;

    /**
     * Debug mode.
     */
    @Parameter
    public boolean debug;

    //</editor-fold>

    //<editor-fold desc="Constructors section.">

    /**
     * Constructor.
     *
     * @param command Command name.
     */
    protected AbstractPluginMojo(String command) {
        this(CommandLine.parse(command), new DefaultExecutor());
    }

    /**
     * Constructor.
     *
     * @param command  pack200 command.
     * @param executor Executor.
     */
    protected AbstractPluginMojo(CommandLine command, DefaultExecutor executor) {
        this.command = command;
        this.executor = executor;
    }

    //</editor-fold>

    //<editor-fold desc="Methods section.">

    /**
     * Executes command.
     *
     * @throws IOException Execution of subprocess failed or the subprocess returned a exit value indicating a failure
     *                     {@code Executor.setExitValue(int)}.
     * @see AbstractPluginMojo#command
     * @see AbstractPluginMojo#executor
     */
    private void executeCommand() throws IOException {
        if (debug) {
            getLog().info("Executable: " + command.getExecutable());
            getLog().info("Arguments: " + StringUtils.join(command.getArguments(), " "));
        }

        executor.execute(command);
    }

    /**
     * Adds a value argument.
     *
     * @param value Argument value.
     */
    protected void addArgument(String value) {
        command.addArgument(value);
    }

    /**
     * Adds a argument with its value.
     *
     * @param name  Argument name.
     * @param value Argument value.
     * @see AbstractPluginMojo#addArgument(String, String, String)
     */
    protected void addArgument(String name, String value) {
        addArgument(name, value, "=");
    }

    /**
     * Adds a argument with its value and using a assignment operator.
     *
     * @param name                  Argument name.
     * @param value                 Argument value.
     * @param assignmentOperator    Assignment operator.
     */
    protected void addArgument(String name, String value, String assignmentOperator) {
        addArgument(String.format("%s%s%s", name, assignmentOperator, value));
    }

    /**
     * Perform whatever build-process behavior this Mojo implements.
     *
     * @throws MojoExecutionException If an unexpected problem occurs. Throwing this exception
     *                                causes a <b>BUILD ERROR</b> message to be displayed.
     */
    public void execute() throws MojoExecutionException {
        try {
            executeCommand();
        } catch (IOException e) {
            throw new MojoExecutionException("Command is failed.", e);
        }
    }

    //</editor-fold>

}
