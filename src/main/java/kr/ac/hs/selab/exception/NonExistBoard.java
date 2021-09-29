package kr.ac.hs.selab.exception;

public class NonExistBoard extends RuntimeException {
    public NonExistBoard(ErrorMessage message) {
        super(message.getMessage());
    }
}
