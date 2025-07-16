package com.util.cli;

public interface Command {
    void execute(String[] args, IntWrapper index) throws IllegalArgumentException;
}