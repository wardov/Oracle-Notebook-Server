package com.notebook.oracle.version1;

public class ParseException extends Exception {
    private String input;

    public ParseException(String input) {
        super("Unable to parse the input");
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
