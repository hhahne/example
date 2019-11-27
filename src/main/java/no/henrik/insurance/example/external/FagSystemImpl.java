package no.henrik.insurance.example.external;

import no.henrik.insurance.example.domain.PolicyStatus;

public class FagSystemImpl implements FagSystem {
    @Override
    public String createCustomer(String firstName, String lastName, String fnr, boolean consentGiven) {
        return "1";
    }

    @Override
    public String createPolicy(String customerNumber, String policyName) {
        return "A-1";
    }

    @Override
    public String updatePolicyStatus(String policyNumber, String status) {
        return PolicyStatus.LETTER_SENT.toString();
    }
}
