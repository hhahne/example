package no.henrik.insurance.example.domain;

import lombok.Getter;

/**
 * Domain class for creating a simple (very simplified) insurance claim for a customer.
 *  Given more time, a proper class hierarchy could be set up and also mapped between Java and JSON.
 * It would also be a good idea to use something like a JSON Schema to define the API itself
 */
@Getter
public class InsuranceRequest {
    private String firstName;
    private String lastName;
    private String fnr;
    private String email;
    private boolean consentGiven;
    private String policyName;

    //Would probably need to add other things as well, Dates, payment information and so on.
    public InsuranceRequest(String firstName, String lastName, String fnr, boolean consentGiven, String policyName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fnr = fnr;
        this.consentGiven = consentGiven;
        this.policyName = policyName;
        this.email = email;
    }
}
