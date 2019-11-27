package no.henrik.insurance.example.service;

import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.PolicyStatus;
import no.henrik.insurance.example.exception.PolicyCreatedException;
import no.henrik.insurance.example.exception.SendLetterException;
import no.henrik.insurance.example.external.Brevtjeneste;
import no.henrik.insurance.example.external.FagSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        when(fagSystem.createCustomer("Henrik", "Hahne", "12345678910", true)).thenReturn("1");
        when(fagSystem.createPolicy("1", "Livsforsikring")).thenReturn("A-1");
        when(brevtjeneste.sendEmailToCustomer("henrik@zuperzoft.com", "A-1", "Default policy Text")).thenReturn(PolicyStatus.LETTER_SENT.toString());
        when(fagSystem.updatePolicyStatus("A-1", PolicyStatus.LETTER_SENT.toString())).thenReturn("OK");
        InsuranceResponse response = service.createPolicy(request);

        assertNotNull(response);
        assertEquals("1", response.getCustomerNumber());
        assertEquals("A-1", response.getPolicyNumber());
        assertEquals(PolicyStatus.DONE.toString(), response.getStatus());
    }

    @Test()
    public void testCreateCustomerFailsOnPolicy() {
        InsuranceRequest request = createInsuranceRequest("Henrik", "Hahne", "12345678910", true, "Livsforsikring", "henrik@zuperzoft.com");
        when(fagSystem.createCustomer("Henrik", "Hahne", "12345678910", true)).thenReturn("1");
        when(fagSystem.createPolicy("1", "Livsforsikring")).thenThrow(new PolicyCreatedException("Could not create policy for customer: 1"));

        assertThrows(PolicyCreatedException.class, () -> {
            service.createPolicy(request);
        });
    }

    @Test()
    public void testCreateCustomerFailsOnSendingLetter() {
        InsuranceRequest request = createInsuranceRequest("Henrik", "Hahne", "12345678910", true, "Livsforsikring", "henrik@zuperzoft.com");
        when(fagSystem.createCustomer("Henrik", "Hahne", "12345678910", true)).thenReturn("1");
        when(fagSystem.createPolicy("1", "Livsforsikring")).thenReturn("A-1");
        when(brevtjeneste.sendEmailToCustomer("henrik@zuperzoft.com", "A-1", "Default policy text")).thenThrow(new SendLetterException("Could not send letter to customer 1"));

        assertThrows(SendLetterException.class, () -> {
            service.createPolicy(request);
        });
    }

    private InsuranceRequest createInsuranceRequest(String fname, String lastName, String fnr, boolean consentGiven, String policyName, String email) {
        InsuranceRequest request = new InsuranceRequest(fname, lastName, fnr, consentGiven, policyName, email);
        return request;
    }

    private InsuranceResponse createDefaultInsuranceResponse(String customerNumber, String policyNumber, String status) {
        return new InsuranceResponse(customerNumber, policyNumber, status, new HashMap<String, String>());
    }
}
