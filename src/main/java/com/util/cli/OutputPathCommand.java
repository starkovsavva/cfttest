package com.util.cli;

import static com.FileUtilCtf.outputPath;
public class OutputPathCommand implements Command {
    @Override
    public void execute(String[] args, IntWrapper index) throws IllegalArgumentException {
        if (index.value + 1 >= args.length) {
            throw new IllegalArgumentException("Missing value for -o option");
        }
        outputPath = args[++index.value];
    }
}