package no.henrik.insurance.example.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InsuranceResponse {
    private String customerNumber;
    private String policyNumber;
    private String status;
    //TODO: Ppopulate this with any errors that occur (typically from the service, handled by the exception handler)
    private Map<String, String> errors;

    public InsuranceResponse(String customerNumber, String policyNumber, String status, Map<String, String> errors) {
        this.customerNumber = customerNumber;
        this.policyNumber = policyNumber;
        this.status = status;
    }

    public InsuranceResponse() {
    }


}
