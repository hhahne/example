package no.henrik.insurance.example.exceptionhandler;

import no.henrik.insurance.example.controller.InsuranceController;
import no.henrik.insurance.example.domain.InsuranceResponse;
import no.henrik.insurance.example.domain.POLICY_STATUS;
import no.henrik.insurance.example.exception.CustomerCreationException;
import no.henrik.insurance.example.exception.PolicyCreatedException;
import no.henrik.insurance.example.exception.SendLetterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = InsuranceController.class)
public class PolicyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerCreationException.class)
    public ResponseEntity<InsuranceResponse> handleCustomerCreatedException(CustomerCreationException cce) {
        InsuranceResponse response = new InsuranceResponse(null, null, POLICY_STATUS.INITIAL_CREATE.toString(), new HashMap<String, String>());
        return new ResponseEntity<InsuranceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PolicyCreatedException.class)
    public ResponseEntity<InsuranceResponse> handlePolicyCreatedException(PolicyCreatedException pce) {
        InsuranceResponse response = new InsuranceResponse("1", null, POLICY_STATUS.CUSTOMER_CREATED.toString(), new HashMap<String, String>());
        return new ResponseEntity<InsuranceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SendLetterException.class)
    public ResponseEntity<InsuranceResponse> handleSendLetterException(SendLetterException sle) {
        InsuranceResponse response = new InsuranceResponse("1", null, POLICY_STATUS.ACCOUNT_CREATED.toString(), new HashMap<String, String>());
        return new ResponseEntity<InsuranceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
