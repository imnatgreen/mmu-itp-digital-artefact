package me.nathangreen.digitalartefact;

public class OutOfRangeException extends Exception {
    public OutOfRangeException(String message) {
        super("OutOfRangeException: " + message);
    }
}
