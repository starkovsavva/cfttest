package com.util.fileprocessing;

import com.util.Stats;

import java.io.BufferedWriter;
import java.io.IOException;

public class StringsHandler implements DataHandler{
    @Override
    public boolean handle(String line, BufferedWriter writer, Stats stats) throws IOException {
        writer.write(line);
        writer.newLine();
        stats.update(line);
        return true;
    }
}
