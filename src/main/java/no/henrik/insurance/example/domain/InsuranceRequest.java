package no.henrik.insurance.example.domain;

/**
 * Domain class for creating a simple (very simplified) insurance claim for a customer.
 *  Given more time, a proper class hierarchy could be set up and also mapped between Java and JSON.
 * It would also be a good idea to use something like a JSON Schema to define the API itself
 */
public class InsuranceRequest {
    private String firstName;
    private String lastName;
    private String fnr;
    private boolean consentGiven;
    private String policyName;

    //Would probably need to add other things as well, Dates, payment information and so on.
    public InsuranceRequest(String firstName, String lastName, String fnr, boolean consentGiven, String policyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fnr = fnr;
        this.consentGiven = consentGiven;
        this.policyName = policyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFnr() {
        return fnr;
    }

    public boolean isConsentGiven() {
        return consentGiven;
    }

    public String getPolicyName() {
        return policyName;
    }
}
