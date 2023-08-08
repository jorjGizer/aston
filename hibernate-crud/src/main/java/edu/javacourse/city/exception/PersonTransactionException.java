package edu.javacourse.city.exception;

public class PersonTransactionException extends RuntimeException{
    public PersonTransactionException() {
        super();
    }

    public PersonTransactionException(String message) {
        super(message);
    }

    public PersonTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonTransactionException(Throwable cause) {
        super(cause);
    }

    protected PersonTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
