package ir.mostafa.semnani.phonebook.exception;

public class PersonAlreadyExistsException extends RuntimeException {

    public PersonAlreadyExistsException(String message) {
        super(message);
    }

}
