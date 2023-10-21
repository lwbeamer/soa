package itmo.corp.authservice.exception;

public class PasswordIsIncorrectException extends RuntimeException {
    public PasswordIsIncorrectException(String message) {
        super(message);
    }
}
