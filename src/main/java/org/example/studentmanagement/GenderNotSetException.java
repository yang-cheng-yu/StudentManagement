package org.example.studentmanagement;

import java.util.NoSuchElementException;

public class GenderNotSetException extends NoSuchElementException {

    public GenderNotSetException() {
        super("Error: Gender not set");
    }
}
