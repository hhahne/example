package no.henrik.insurance.example.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Domain class for creating a simple (very simplified) insurance claim for a customer.
 *  Given more time, a proper class hierarchy could be set up and also mapped between Java and JSON.
 * It would also be a good idea to use something like a JSON Schema to define the API itself
 */
@Getter
@Entity
public class InsuranceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String fnr;
    @NotNull
    private String email;
    @NotNull
    private boolean consentGiven;
    @NotNull
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
