/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.object;

public class UnsupportedStringException extends Exception {

    private String s;

    public UnsupportedStringException(String s) {
        this.s = s;
    }

    public String getString() {
        return s;
    }

    @Override
    public String getMessage() {
        return "The string " + this.s + " is not allowed";
    }
}
