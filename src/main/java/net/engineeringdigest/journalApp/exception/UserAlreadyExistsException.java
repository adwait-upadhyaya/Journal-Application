package net.engineeringdigest.journalApp.exception;

public class UserAlreadyExistsException extends Throwable {
    public UserAlreadyExistsException(String usernameAlreadyTaken) {
        super(usernameAlreadyTaken);
    }
}
