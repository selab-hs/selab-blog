package kr.ac.hs.selab.exception;

public class BasicAuthLoginException extends RuntimeException {
    public BasicAuthLoginException(ErrorMessage message) {
        super(message.getMessage());
    }
}
