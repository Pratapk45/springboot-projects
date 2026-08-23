package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 1. Recharge Not Found
	@ExceptionHandler(RechargeNotFoundException.class)
	public ResponseEntity<String> handleRechargeNotFound(RechargeNotFoundException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// 2. Duplicate Recharge
	@ExceptionHandler(DuplicateRechargeException.class)
	public ResponseEntity<String> handleDuplicateRecharge(DuplicateRechargeException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	// 3. Invalid Mobile Number
	@ExceptionHandler(InvalidMobileNumberException.class)
	public ResponseEntity<String> handleInvalidMobile(InvalidMobileNumberException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 4. Invalid Recharge Plan
	@ExceptionHandler(InvalidRechargePlanException.class)
	public ResponseEntity<String> handleInvalidPlan(InvalidRechargePlanException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 5. Invalid Operator
	@ExceptionHandler(InvalidOperatorException.class)
	public ResponseEntity<String> handleInvalidOperator(InvalidOperatorException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 6. Field Validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	// 7. General Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {

		return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
