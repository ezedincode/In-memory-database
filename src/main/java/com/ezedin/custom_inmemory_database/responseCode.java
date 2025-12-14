package com.ezedin.custom_inmemory_database;

public enum responseCode {
    STRING('+'),
    ERROR('-'),
    INTEGER(':'),
    BULK('$'),
    ARRAY('*');

    private final char value;
    responseCode (char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
