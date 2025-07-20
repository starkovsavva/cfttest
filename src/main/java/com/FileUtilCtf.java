package com;

import com.util.*;
import com.util.cli.Command;
import com.util.cli.CommandFactory;
import com.util.cli.IntWrapper;
import com.util.fileprocessing.DataProcessor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class FileUtilCtf  {
    private static final String DEFAULT_DIR = System.getProperty("user.dir");
    private static final String DEFAULT_INTS = "integers.txt";
    private static final String DEFAULT_FLOATS = "floats.txt";
    private static final String DEFAULT_STRINGS = "strings.txt";
    private static final CommandFactory commandFactory = new CommandFactory();
    public static String outputPath = "";
    public static String filePrefix = "";
    public static Boolean appendMode = false;
    public static Boolean shortStats = false;
    public static Boolean fullStats = false;

    private static final List<String> inputFiles = new ArrayList<>();

    public static Stats integerStats = new Stats();
    public static Stats floatStats = new Stats();
    public static Stats stringStats = new Stats();


    public void processStart(String[] args) {
        processArgs(args);
        processFiles();
        printStatistics();
    }

    private void processArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments provided");
        }


        IntWrapper index = new IntWrapper(0);

        while (index.value < args.length) {
            String arg = args[index.value];

            if (arg.startsWith("-")) {
                Command command = commandFactory.getCommand(arg);
                if (command == null) {
                    throw new IllegalArgumentException("Unknown option: " + arg);
                }
                command.execute(args, index);
            } else {
                inputFiles.add(arg);
            }

            index.value++;
        }

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("No input files specified");
        }

        if (!shortStats && !fullStats) {
            shortStats = true;
        }

    }


    private static void printStatistics() {
        if (shortStats || fullStats) {
            System.out.println("Statistics:");

            if (integerStats.count > 0) {
                System.out.println("\nIntegers:");
                System.out.println("  Count: " + integerStats.count);
                if (fullStats) {
                    System.out.println("  Min: " + integerStats.min);
                    System.out.println("  Max: " + integerStats.max);
                    System.out.println("  Sum: " + integerStats.sum);
                    System.out.println("  Average: " + (integerStats.sum / integerStats.count));
                }
            }

            if (floatStats.count > 0) {
                System.out.println("\nFloats:");
                System.out.println("  Count: " + floatStats.count);
                if (fullStats) {
                    System.out.println("  Min: " + floatStats.minDouble);
                    System.out.println("  Max: " + floatStats.maxDouble);
                    System.out.println("  Sum: " + floatStats.sumDouble);
                    System.out.println("  Average: " + floatStats.sumDouble.divide(BigDecimal.valueOf(floatStats.count)));

                }
            }

            if (stringStats.count > 0) {
                System.out.println("\nStrings:");
                System.out.println("  Count: " + stringStats.count);
                if (fullStats) {
                    System.out.println("  Shortest length: " + stringStats.minLength);
                    System.out.println("  Longest length: " + stringStats.maxLength);
                }
            }
        }
    }

    private void processFiles() {

        DataProcessor processor = new DataProcessor();

        try (var intWriter = createWriter(DEFAULT_INTS);
             var floatWriter = createWriter(DEFAULT_FLOATS);
             var stringWriter = createWriter(DEFAULT_STRINGS)) {

            for (String inputFile : inputFiles) {
                processSingleFile(inputFile, processor, intWriter, floatWriter, stringWriter);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating output files: " + e.getMessage(), e);
        }

    }

    private static BufferedWriter createWriter(String defaultFileName) throws IOException {
        String fileName = filePrefix + defaultFileName;
        Path outfilePath = outputPath.isEmpty() ?
                Paths.get(DEFAULT_DIR) :
                Paths.get(outputPath);

        Files.createDirectories(outfilePath.getParent());

        return Files.newBufferedWriter(outfilePath.resolve(fileName), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                appendMode ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void processSingleFile(String inputFile, DataProcessor processor,
                                          BufferedWriter intWriter,
                                          BufferedWriter floatWriter,
                                          BufferedWriter stringWriter) {
        try (Stream<String> lines = Files.lines(Paths.get(inputFile))) {
            lines.filter(line -> !line.isEmpty())
                    .forEach(line -> {
                        try {
                            processor.processLine(line, intWriter, floatWriter, stringWriter);
                        } catch (IOException e) {
                            System.err.println("Line can`t be processed: " + line + e.getMessage());
                        }
                    });
        } catch (NoSuchFileException e) {
            System.err.println("File not found: " + inputFile);
        } catch (IOException e) {
            System.err.println("Error processing file " + inputFile + ": " + e.getMessage());
        }
    }











}
