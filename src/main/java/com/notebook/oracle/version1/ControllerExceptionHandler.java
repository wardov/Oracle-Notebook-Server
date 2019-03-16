package com.notebook.oracle.version1;

import org.graalvm.polyglot.PolyglotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotSupportedException.class)
    public final ResponseEntity<GenericExceptionResponse> handleLanguageNotSupported(
            final NotSupportedException ex,
            final WebRequest request) {

        GenericExceptionResponse response = new GenericExceptionResponse(
                ex.getMessage(),
                new Date(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ParseException.class)
    public final ResponseEntity<GenericExceptionResponse> handleErrorParse(
            final ParseException ex) {
        GenericExceptionResponse response = new GenericExceptionResponse(
                ex.getMessage(),
                new Date(),
                ex.getInput()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(PolyglotException.class)
    public final ResponseEntity<ExecutionExceptionResponse> handleSyntaxError(
            final PolyglotException ex) {

        ExecutionExceptionResponse response = new ExecutionExceptionResponse(
                ex.getMessage(),
                ex.getExitStatus(),
                new Date(),
                ex.isSyntaxError(),
                ex.isCancelled(),
                ex.isGuestException(),
                ex.isHostException(),
                ex.isIncompleteSource(),
                ex.isInternalError(),
                ex.isExit()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
