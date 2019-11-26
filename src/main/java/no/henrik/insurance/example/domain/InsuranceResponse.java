package no.henrik.insurance.example.domain;

public class InsuranceResponse {
    private String customerNumber;
    private String policyNumber;
    private String status;

    //Add a List of error message
    //private String errorMessage;

    //Add Annotations

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getStatus() {
        return status;
    }
}