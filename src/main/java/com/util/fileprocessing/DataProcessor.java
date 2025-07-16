package com.util.fileprocessing;

import com.FileUtilCtf;
import com.util.Stats;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.FileUtilCtf.integerStats;
import static com.FileUtilCtf.floatStats;
import static com.FileUtilCtf.stringStats;

public class DataProcessor {
    private final List<DataHandler> handlers;

    public DataProcessor() {
        this.handlers = Arrays.asList(
                new IntegerHandler(),
                new FloatHandler(),
                new StringsHandler()
        );
    }

    public void processLine(String line,
                            BufferedWriter intWriter,
                            BufferedWriter floatWriter,
                            BufferedWriter stringWriter) throws IOException {
        for (DataHandler handler : handlers) {
            boolean handled = false;

            if (handler instanceof IntegerHandler) {
                handled = handler.handle(line, intWriter, integerStats);
            } else if (handler instanceof FloatHandler) {
                handled = handler.handle(line, floatWriter, floatStats);
            } else if (handler instanceof StringsHandler) {
                handled = handler.handle(line, stringWriter, stringStats);
            }

            if (handled) break;
        }
    }
}
