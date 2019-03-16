package com.notebook.oracle.version1;

import com.notebook.oracle.version1.NotSupportedException;
import com.notebook.oracle.version1.ParseException;

import java.util.regex.Pattern;

public class Input {

    private String language;
    private String code;

    public Input(final String input)
            throws NotSupportedException, ParseException {

        if (!parse(input)) {
            throw new ParseException(input);
        }
        if (!isSupported(language)) {
            throw new NotSupportedException(language);
        }
    }

    private Boolean parse(final String input) {
        
        Boolean isValid = Pattern.matches("%[a-zA-Z]+ (.|\n|\r\n)+", input);

        if (!isValid) {
            return false;
        }

        
        this.language = input.split(" ", 2)[0].substring(1);

        
        this.code = input.split(" ", 2)[1];

        return true;
    }

    private Boolean isSupported(final String language) {
        switch (language) {
            case "python":
            case "R":
            case "js":
            case "ruby":
                return true;
            default:
                return false;
        }
    }

    public String getCode() {
        return code;
    }


    public String getLanguage() {
        return language;
    }

}
