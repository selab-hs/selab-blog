package kr.ac.hs.selab.exception;

public class IncorrectPermissionException extends RuntimeException {
    public IncorrectPermissionException(ErrorMessage message) {
        super(message.getMessage());
    }
}
