package com.notebook.oracle.version1;

import java.util.Date;

public class GenericExceptionResponse {
    private String message;
    private Date timestamp;
    private String detail;

    public GenericExceptionResponse(String message, Date timestamp, String detail) {
        this.message = message;
        this.timestamp = timestamp;
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetail() {
        return detail;
    }

    public String getMessage() {
        return message;
    }
}
