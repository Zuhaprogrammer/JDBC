package com.zuhriddin.dao.config.exception;

public class PostgresConnectException extends RuntimeException {
    public PostgresConnectException(String message) {
        super(message);
    }
}
