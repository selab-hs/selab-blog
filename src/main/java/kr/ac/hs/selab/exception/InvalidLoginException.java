package kr.ac.hs.selab.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(ErrorMessage message) {
        super(message.getMessage());
    }
}
