package no.henrik.insurance.example.exception;

public class CustomerCreationException extends RuntimeException  {

    public CustomerCreationException(String code) {
        super(code);
    }
}
