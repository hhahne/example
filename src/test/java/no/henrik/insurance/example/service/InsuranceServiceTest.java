package no.henrik.insurance.example.service;

import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.POLICY_STATUS;
import no.henrik.insurance.example.external.Brevtjeneste;
import no.henrik.insurance.example.external.FagSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsuranceServiceTest {

    @Mock
    private Brevtjeneste brevtjeneste;

    @Mock
    private FagSystem fagSystem;

    @InjectMocks
    private InsuranceService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCustomerOk() {
        InsuranceRequest request = createInsuranceRequest("Henrik", "Hahne", "12345678910", true, "Livsforsikring", "henrik@zuperzoft.com");
        assertNotNull(service);
    }

    private InsuranceRequest createInsuranceRequest(String fname, String lastName, String fnr, boolean consentGiven, String policyName, String email) {
        InsuranceRequest request = new InsuranceRequest(fname, lastName, fnr, consentGiven, policyName, email);
        return request;
    }

    private InsuranceResponse createDefaultInsuranceResponse(String customerNumber, String policyNumber, String status) {
        return new InsuranceResponse(customerNumber, policyNumber, status, new HashMap<String, String>());
    }
}
