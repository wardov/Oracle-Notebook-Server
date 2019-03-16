package com.notebook.oracle.version1;

import java.util.Date;

public class ExecutionExceptionResponse {

    private String message;
    private int exitStatus;
    private Date timestamp;
    private Boolean isSyntaxError;
    private Boolean isCancelled;
    private Boolean isGuestException;
    private Boolean isHostException;
    private Boolean isIncompleteSource;
    private Boolean isInternalError;
    private Boolean isExit;

    public ExecutionExceptionResponse(
            String message,
            int exitStatus,
            Date timestamp,
            Boolean isSyntaxError,
            Boolean isCancelled,
            Boolean isGuestException,
            Boolean isHostException,
            Boolean isIncompleteSource,
            Boolean isInternalError,
            Boolean isExit) {
        this.message = message;
        this.exitStatus = exitStatus;
        this.timestamp = timestamp;
        this.isSyntaxError = isSyntaxError;
        this.isCancelled = isCancelled;
        this.isGuestException = isGuestException;
        this.isHostException = isHostException;
        this.isIncompleteSource = isIncompleteSource;
        this.isInternalError = isInternalError;
        this.isExit = isExit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Boolean getExit() {
        return isExit;
    }

    public String getMessage() {
        return message;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    public Boolean getSyntaxError() {
        return isSyntaxError;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public Boolean getGuestException() {
        return isGuestException;
    }

    public Boolean getHostException() {
        return isHostException;
    }

    public Boolean getIncompleteSource() {
        return isIncompleteSource;
    }

    public Boolean getInternalError() {
        return isInternalError;
    }
}
