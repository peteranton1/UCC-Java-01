package org.tabular.accum;

public class TabularException extends RuntimeException {

    public TabularException() {
    }

    public TabularException(String message) {
        super(message);
    }

    public TabularException(String message, Throwable cause) {
        super(message, cause);
    }

    public TabularException(Throwable cause) {
        super(cause);
    }
}
