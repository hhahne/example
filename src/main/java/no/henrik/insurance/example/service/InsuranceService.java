package no.henrik.insurance.example.service;

import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.POLICY_STATUS;

/**
 * This class is the service layer class that should handle all calls to the external systems
 */
public class InsuranceService {

    //@Autowired
    //private Fagsystem fagSystem;

    //@Autowired
    //private BrevSystem brevSystem;

    public InsuranceResponse createPolicy(InsuranceRequest request) {
        return new InsuranceResponse("1", "A-1", POLICY_STATUS.DONE.toString());
    }
}
