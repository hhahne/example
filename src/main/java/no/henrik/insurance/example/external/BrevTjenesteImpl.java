package no.henrik.insurance.example.external;

public class BrevTjenesteImpl implements Brevtjeneste {
    @Override
    public String sendEmailToCustomer(String email, String policyNumber, String policyText) {
        return "Letter sent ok";
    }
}
