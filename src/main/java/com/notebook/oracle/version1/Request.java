package com.notebook.oracle.version1;


public class Request {
    private String code;
    private String sessionId;

    public Request(String code, String SessionId) {
        this.code = code;
        this.sessionId = SessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getCode() {
        return code;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
