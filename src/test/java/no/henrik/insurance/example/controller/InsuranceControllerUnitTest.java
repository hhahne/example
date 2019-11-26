package no.henrik.insurance.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.service.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.with;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@AutoConfigureMockMvc
public class InsuranceControllerUnitTest {

    @Mock
    private InsuranceService service;
    @InjectMocks
    private InsuranceController controller;

   // @Autowired
    //private MockMvc mockMvc;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(controller);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

   @Test
    public void shouldTestDummyController() {
        InsuranceRequest request= createDefaultInsuranceRequest();
        Object returnObject = with()
                .body(createDefaultInsuranceRequest())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .request("POST", "/new")
                .then()
                .statusCode(201);
        assertTrue(true);
    }

/*    @Test
    public void testDummyController() throws Exception {
        this.mockMvc.perform(post("/new")
                .content(asJsonString(createDefaultInsuranceRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void sayHello() throws Exception {
        this.mockMvc.perform(get("/hello")).andExpect(status().isOk());
    }*/

    @Test
    public void testRestAssuredStuff() {
        Object result = given().when().request("GET", "/hello").then().statusCode(200);
        assertNotNull(result);
    }

    private InsuranceRequest createDefaultInsuranceRequest() {
        InsuranceRequest request = new InsuranceRequest("Henrik", "Hahne", "12345678910", true, "Livsforsikring");
        return request;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
