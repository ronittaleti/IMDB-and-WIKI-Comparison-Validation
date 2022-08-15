package com.ronittaleti;

import java.io.IOException;

// Simple exception class, used in the case that a URL is valid, but the webpage threw an invalid response (such as a 404).
public class InvalidWebpageResponseException extends IOException {

	private String code;

    public InvalidWebpageResponseException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public InvalidWebpageResponseException(String code, String message, Throwable cause) {
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
