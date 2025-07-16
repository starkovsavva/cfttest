package com.util.fileprocessing;

import com.util.Stats;

import java.io.BufferedWriter;
import java.io.IOException;

interface DataHandler {
    boolean handle(String line, BufferedWriter writer, Stats stats) throws IOException;

    default void writeLine(BufferedWriter writer, String line) throws IOException {
        if (writer != null) {
            writer.write(line);
            writer.newLine();
        }
    }
}
