package com.ronittaleti;
import java.io.IOException;

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
