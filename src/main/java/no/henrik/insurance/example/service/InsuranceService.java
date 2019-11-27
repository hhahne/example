package no.henrik.insurance.example.service;

import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.POLICY_STATUS;
import no.henrik.insurance.example.external.Brevtjeneste;
import no.henrik.insurance.example.external.FagSystem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class InsuranceService {

    @Autowired
    private FagSystem fagSystem;

    @Autowired
    private Brevtjeneste brevTjeneste;


    //TODO: Implement compensating transactions and Sagas to handle errors. Currently, it is possible for the different systems to be out of sync.
    public InsuranceResponse createPolicy(InsuranceRequest request) {
        InsuranceResponse response = new InsuranceResponse();
        updateResponseStatus(response, POLICY_STATUS.INITIAL_CREATE);

        String customerNumber = fagSystem.createCustomer(request.getFirstName(), request.getLastName(), request.getFnr(), true);
        updateResponseWithCustomerInformation(response, customerNumber);

        String policyNumber = fagSystem.createPolicy(customerNumber, "Livsforsikring");
        updateResponseWithPolicyInformation(response, policyNumber);

        //Currently the response from the brevTjeneste is not used.
        brevTjeneste.sendEmailToCustomer("henrik@zuperzoft.com", policyNumber, "Default policy text");
        updateResponseStatus(response, POLICY_STATUS.LETTER_SENT);

        //Currently the response from the fagsystem operation is not used
        fagSystem.updatePolicyStatus(policyNumber, POLICY_STATUS.LETTER_SENT.toString());
        updateResponseStatus(response, POLICY_STATUS.DONE);

        //TODO: The error handling here if the last status is not completely done must be reviewed
        return response;
    }

    private void updateResponseWithCustomerInformation(InsuranceResponse response, String customerNumber) {
        response.setCustomerNumber(customerNumber);
        updateResponseStatus(response, POLICY_STATUS.CUSTOMER_CREATED);
    }

    private void updateResponseWithPolicyInformation(InsuranceResponse response, String policyNumber) {
        response.setPolicyNumber(policyNumber);
        updateResponseStatus(response, POLICY_STATUS.ACCOUNT_CREATED);
    }

    private void updateResponseStatus(InsuranceResponse response, POLICY_STATUS status) {
        response.setStatus(status.toString());
    }
}
