package kr.ac.hs.selab.exception;

public class OverabundanceException extends RuntimeException {
    public OverabundanceException(ErrorMessage message) {
        super(message.getMessage());
    }
}
