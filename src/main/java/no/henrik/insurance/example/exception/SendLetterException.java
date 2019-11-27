package no.henrik.insurance.example.exception;

public class SendLetterException extends RuntimeException {
    public SendLetterException(String code) {
        super(code);
    }
}
