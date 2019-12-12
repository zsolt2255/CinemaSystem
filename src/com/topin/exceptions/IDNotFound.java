package com.topin.exceptions;

public class IDNotFound extends Exception {
    public IDNotFound(int message) {
        super(String.valueOf(message));
    }
}
