package no.henrik.insurance.example.service;

import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.PolicyStatus;
import no.henrik.insurance.example.external.Brevtjeneste;
import no.henrik.insurance.example.external.FagSystem;
import org.springframework.beans.factory.annotation.Autowired;

public class InsuranceService {

    @Autowired
    private FagSystem fagSystem;

    @Autowired
    private Brevtjeneste brevTjeneste;


    //TODO: Implement compensating transactions and Sagas to handle errors. Currently, it is possible for the different systems to be out of sync.
    public InsuranceResponse createPolicy(InsuranceRequest request) {
        InsuranceResponse response = new InsuranceResponse();
        updateResponseStatus(response, PolicyStatus.INITIAL_CREATE);

        String customerNumber = fagSystem.createCustomer(request.getFirstName(), request.getLastName(), request.getFnr(), true);
        updateResponseWithCustomerInformation(response, customerNumber);

        String policyNumber = fagSystem.createPolicy(customerNumber, "Livsforsikring");
        updateResponseWithPolicyInformation(response, policyNumber);

        //Currently the response from the brevTjeneste is not used.
        brevTjeneste.sendEmailToCustomer("henrik@zuperzoft.com", policyNumber, "Default policy text");
        updateResponseStatus(response, PolicyStatus.LETTER_SENT);

        //Currently the response from the fagsystem operation is not used
        fagSystem.updatePolicyStatus(policyNumber, PolicyStatus.LETTER_SENT.toString());
        updateResponseStatus(response, PolicyStatus.DONE);

        //TODO: The error handling here if the last status is not completely done must be reviewed
        return response;
    }

    private void updateResponseWithCustomerInformation(InsuranceResponse response, String customerNumber) {
        response.setCustomerNumber(customerNumber);
        updateResponseStatus(response, PolicyStatus.CUSTOMER_CREATED);
    }

    private void updateResponseWithPolicyInformation(InsuranceResponse response, String policyNumber) {
        response.setPolicyNumber(policyNumber);
        updateResponseStatus(response, PolicyStatus.ACCOUNT_CREATED);
    }

    private void updateResponseStatus(InsuranceResponse response, PolicyStatus status) {
        response.setStatus(status.toString());
    }
}
