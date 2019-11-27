package no.henrik.insurance.example.exception;

public class PolicyCreatedException extends RuntimeException {
    public PolicyCreatedException(String code) {
        super(code);
    }
}
