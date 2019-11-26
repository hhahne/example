package no.henrik.insurance.example.controller;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.ValidatableResponse;
import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.POLICY_STATUS;
import no.henrik.insurance.example.exception.CustomerCreationException;
import no.henrik.insurance.example.exceptionhandler.PolicyExceptionHandler;
import no.henrik.insurance.example.service.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.with;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InsuranceControllerUnitTest {

    @MockBean
    private InsuranceService service;
    @InjectMocks
    private InsuranceController controller;
    @InjectMocks
    private PolicyExceptionHandler exceptionHandler;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(controller, exceptionHandler);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

   @Test
    public void shouldTestNormalOperationNicely() {
        InsuranceRequest request= createDefaultInsuranceRequest();
        when(service.createPolicy(any(InsuranceRequest.class))).thenReturn(createDefaultInsuranceResponse());
        ValidatableResponse returnObject = with()
                .body(createDefaultInsuranceRequest())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .request("POST", "/new")
                .then()
                .body("customerNumber", is("1"))
                .body("policyNumber", is("A-1"))
                .body("status", is(POLICY_STATUS.DONE.toString()))
                .statusCode(201);
    }

    @Test
    public void shouldTestExceptionWhenCreatingCustomer() {
        InsuranceRequest request= createDefaultInsuranceRequest();
        when(service.createPolicy(any(InsuranceRequest.class))).thenThrow(new CustomerCreationException("Customer could not be created"));
        ValidatableResponse returnObject = with()
                .body(createDefaultInsuranceRequest())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .request("POST", "/new")
                .then()
                .body("customerNumber", nullValue())
                .body("policyNumber", nullValue())
                .body("status", is(POLICY_STATUS.INITIAL_CREATE.toString()))
                .statusCode(500);
    }

    @Test
    public void testSimpleSayHelloController() {
        Object result = given().when().request("GET", "/hello").then().statusCode(200);
        assertNotNull(result);
    }

    private InsuranceRequest createDefaultInsuranceRequest() {
        InsuranceRequest request = new InsuranceRequest("Henrik", "Hahne", "12345678910", true, "Livsforsikring");
        return request;
    }

    private InsuranceResponse createDefaultInsuranceResponse() {
        return new InsuranceResponse("1", "A-1", POLICY_STATUS.DONE.toString());
    }
}
