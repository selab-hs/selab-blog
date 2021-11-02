package kr.ac.hs.selab.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(ErrorMessage message) {
        super(message.getMessage());
    }
}