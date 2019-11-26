package no.henrik.insurance.example.external;

public class FagSystemImpl implements FagSystem {
    @Override
    public Integer createCustomer(String firstName, String lastName, String fnr, boolean consentGiven) {
        return 1;
    }
}
