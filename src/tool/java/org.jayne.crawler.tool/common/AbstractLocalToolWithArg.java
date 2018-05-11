package org.jayne.crawler.tool.common;

import org.apache.commons.cli.*;

import java.io.PrintWriter;

/**
 *
 */
public abstract class AbstractLocalToolWithArg extends AbstractLocalTool {

    @Override
    public final void usage(PrintWriter out) {
        Options options = new Options();
        prepareOptions(options);
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(out, HelpFormatter.DEFAULT_WIDTH, getClass().getSimpleName(), null, options,
                HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD, null);
    }

    protected abstract void prepareOptions(Options options);

    @Override
    public final void exec(String[] args) throws Exception {
        Options options = new Options();
        prepareOptions(options);
        CommandLineParser parser = new PosixParser();
        CommandLine commandLine = parser.parse(options, args);
        exec(commandLine);
    }

    protected abstract void exec(CommandLine commandLine) throws Exception;
}
