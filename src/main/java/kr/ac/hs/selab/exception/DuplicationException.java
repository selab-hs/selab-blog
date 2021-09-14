package kr.ac.hs.selab.exception;

public class DuplicationException extends RuntimeException {
    public DuplicationException(ErrorMessage message) {
        super(message.getMessage());
    }
}
