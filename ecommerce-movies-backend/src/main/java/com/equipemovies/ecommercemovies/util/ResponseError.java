package com.equipemovies.ecommercemovies.util;

// Responde os erros no formato Json, passando para o badRequest
public class ResponseError {

    private String error;

    public ResponseError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "error='" + error + '\'' +
                '}';
    }

    // GETTERS AND SETTERS
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
