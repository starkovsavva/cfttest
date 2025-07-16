package com.util.cli;

import static com.FileUtilCtf.fullStats;
class FullStatsCommand implements Command {
    @Override
    public void execute(String[] args, IntWrapper index) {
        fullStats = true;
    }
}
