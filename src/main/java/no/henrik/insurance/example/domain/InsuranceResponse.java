package no.henrik.insurance.example.domain;

import lombok.Getter;

import java.util.Map;

@Getter
public class InsuranceResponse {
    private String customerNumber;
    private String policyNumber;
    private String status;
    private Map<String, String> errors;

    public InsuranceResponse(String customerNumber, String policyNumber, String status, Map<String, String> errors) {
        this.customerNumber = customerNumber;
        this.policyNumber = policyNumber;
        this.status = status;
    }

}
