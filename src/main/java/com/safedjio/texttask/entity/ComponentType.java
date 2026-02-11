package com.safedjio.texttask.entity;

public enum ComponentType {
    TEXT("\n"),
    PARAGRAPH("\t"),
    SENTENCE(" "),
    LEXEME(" "),
    SYMBOL("");

    private final String delimiter;
    ComponentType(String delimiter) {
        this.delimiter = delimiter;
    }
    public String getDelimiter() {
        return delimiter;
    }
}