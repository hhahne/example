package no.henrik.insurance.example.external;

public interface Brevtjeneste {
    String sendEmailToCustomer(String email, String policyNumber, String policyText);
}
