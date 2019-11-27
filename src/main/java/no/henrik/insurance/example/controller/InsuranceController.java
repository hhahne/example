package no.henrik.insurance.example.controller;

import no.henrik.insurance.example.domain.InsuranceRequest;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//TODO: Add a logger and some nice logging, especially for exception cases but also general tracing.

@RestController
public class InsuranceController {

    //Handles all calls to the external systems
    private InsuranceService service;

    @Autowired
    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @PostMapping(value = "/new", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceResponse> postInsuranceClaim(@RequestBody @Valid InsuranceRequest request) {
            InsuranceResponse response = service.createPolicy(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}
