package no.henrik.insurance.example.controller;

import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.service.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class InsuranceController {

    //Handles all calls to the external systems
    private InsuranceService service;

    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @PostMapping(value = "/new", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceResponse> postInsuranceClaim(@RequestBody InsuranceRequest request) {
            InsuranceResponse response = new InsuranceResponse();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}
