package com.ronittaleti;

import java.io.IOException;

public class InvalidURLException extends IOException {

	 private String code;

    public InvalidURLException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public InvalidURLException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
