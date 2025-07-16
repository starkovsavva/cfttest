package com.util.cli;
import static com.FileUtilCtf.shortStats;

class ShortStatsCommand implements Command {
    @Override
    public void execute(String[] args, IntWrapper index) {
        shortStats = true;
    }
}
