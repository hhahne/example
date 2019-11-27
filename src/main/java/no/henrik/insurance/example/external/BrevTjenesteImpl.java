package no.henrik.insurance.example.external;

import no.henrik.insurance.example.domain.POLICY_STATUS;
import no.henrik.insurance.example.external.Brevtjeneste;

public class BrevTjenesteImpl implements Brevtjeneste {
    @Override
    public String sendEmailToCustomer(String email, String policyNumber, String policyText) {
        return "Letter sent ok";
    }
}
