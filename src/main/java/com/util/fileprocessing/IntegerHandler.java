package com.util.fileprocessing;

import com.util.Stats;

import java.io.BufferedWriter;
import java.io.IOException;

class IntegerHandler implements DataHandler {
    @Override
    public boolean handle(String line, BufferedWriter writer, Stats stats) throws IOException {
        try {
            Long value = Long.parseLong(line);
            writeLine(writer, line);
            stats.update(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}