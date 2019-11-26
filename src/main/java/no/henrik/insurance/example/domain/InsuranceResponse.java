package no.henrik.insurance.example.domain;

import lombok.Getter;

@Getter
public class InsuranceResponse {
    private String customerNumber;
    private String policyNumber;
    private String status;

    public InsuranceResponse(String customerNumber, String policyNumber, String status) {
        this.customerNumber = customerNumber;
        this.policyNumber = policyNumber;
        this.status = status;
    }

}
