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

	// 1. Leave Not Found
	@ExceptionHandler(LeaveNotFoundException.class)
	public ResponseEntity<String> handleLeaveNotFound(LeaveNotFoundException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// 2. Duplicate Leave
	@ExceptionHandler(DuplicateLeaveException.class)
	public ResponseEntity<String> handleDuplicateLeave(DuplicateLeaveException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	// 3. Invalid Leave
	@ExceptionHandler(InvalidLeaveException.class)
	public ResponseEntity<String> handleInvalidLeave(InvalidLeaveException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 4. Excessive Leave
	@ExceptionHandler(ExcessiveLeaveException.class)
	public ResponseEntity<String> handleExcessiveLeave(ExcessiveLeaveException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 5. Already Approved
	@ExceptionHandler(LeaveAlreadyApprovedException.class)
	public ResponseEntity<String> handleAlreadyApproved(LeaveAlreadyApprovedException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
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
