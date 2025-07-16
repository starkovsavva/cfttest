package com.util.cli;

import static com.FileUtilCtf.appendMode;
class AppendModeCommand implements Command {
    @Override
    public void execute(String[] args, IntWrapper index) {
        appendMode = true;
    }
}
