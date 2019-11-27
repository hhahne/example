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

    public InsuranceResponse createPolicy(InsuranceRequest request) {
        return new InsuranceResponse("1", "A-1", POLICY_STATUS.DONE.toString(), new HashMap<String, String>());
    }
}
