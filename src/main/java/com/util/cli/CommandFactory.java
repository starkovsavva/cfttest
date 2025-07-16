package com.util.cli;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("-o", new OutputPathCommand());
        commands.put("-p", new FilePrefixCommand());
        commands.put("-a", new AppendModeCommand());
        commands.put("-s", new ShortStatsCommand());
        commands.put("-f", new FullStatsCommand());
    }

    public static Command getCommand(String option) {
        return commands.get(option);
    }
}