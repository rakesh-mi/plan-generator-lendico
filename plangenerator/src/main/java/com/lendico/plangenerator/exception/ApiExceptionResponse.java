package com.lendico.plangenerator.exception;

public class ApiExceptionResponse {

    private String errorMessage;

    ApiExceptionResponse(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}