package org.example.studentmanagement;

public class InvalidGPAException extends RuntimeException {
    public InvalidGPAException() {
        super("Error: GPA must be between 0 and 100");
    }
}
