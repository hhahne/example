package no.henrik.insurance.example.exceptionhandler;

import no.henrik.insurance.example.controller.InsuranceController;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.POLICY_STATUS;
import no.henrik.insurance.example.exception.CustomerCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = InsuranceController.class)
public class PolicyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerCreationException.class)
    public ResponseEntity<InsuranceResponse> handleCustomerCreatedException(CustomerCreationException cce) {
        InsuranceResponse response = new InsuranceResponse(null, null, POLICY_STATUS.INITIAL_CREATE.toString());
        return new ResponseEntity<InsuranceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
