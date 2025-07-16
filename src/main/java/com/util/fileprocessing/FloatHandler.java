package com.util.fileprocessing;

import com.util.Stats;

import java.io.BufferedWriter;
import java.io.IOException;

public class FloatHandler implements DataHandler {
    @Override
    public boolean handle(String line, BufferedWriter writer, Stats stats) throws IOException {
        try {
            Double value = Double.parseDouble(line);
            writeLine(writer, line);
            stats.update(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
