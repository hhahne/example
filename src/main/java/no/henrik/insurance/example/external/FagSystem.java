package no.henrik.insurance.example.external;

public interface FagSystem {

    String createCustomer(String firstName, String lastName, String fnr, boolean consentGiven);
    String createPolicy(String customerNumber, String policyName);
    String updatePolicyStatus(String policyNumber, String status);

}
