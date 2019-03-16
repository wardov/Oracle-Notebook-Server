package com.notebook.oracle.version1;

public class NotSupportedException extends Exception {

    public NotSupportedException(String language) {
        super("Language " + language + " is not supported");
    }

}
