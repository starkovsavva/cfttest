package com.util.cli;

import static com.FileUtilCtf.filePrefix;
class FilePrefixCommand implements Command {
    @Override
    public void execute(String[] args, IntWrapper index) throws IllegalArgumentException {
        if (index.value + 1 >= args.length) {
            throw new IllegalArgumentException("Missing value for -p option");
        }
        filePrefix = args[++index.value];
    }
}