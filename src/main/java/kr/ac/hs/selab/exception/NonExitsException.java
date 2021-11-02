package kr.ac.hs.selab.exception;

public class NonExitsException extends RuntimeException {
    public NonExitsException(ErrorMessage message) {
        super(message.getMessage());
    }
}
