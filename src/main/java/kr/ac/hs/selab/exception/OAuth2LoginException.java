package kr.ac.hs.selab.exception;

public class OAuth2LoginException extends RuntimeException {
    public OAuth2LoginException(ErrorMessage message) {
        super(message.getMessage());
    }
}
