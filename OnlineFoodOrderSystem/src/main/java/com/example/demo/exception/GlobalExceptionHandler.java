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

	// 1. Order Not Found
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// 2. Duplicate Order
	@ExceptionHandler(DuplicateOrderException.class)
	public ResponseEntity<String> handleDuplicateOrder(DuplicateOrderException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	// 3. Invalid Order
	@ExceptionHandler(InvalidOrderException.class)
	public ResponseEntity<String> handleInvalidOrder(InvalidOrderException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 4. Insufficient Quantity
	@ExceptionHandler(InsufficientQuantityException.class)
	public ResponseEntity<String> handleInsufficientQuantity(InsufficientQuantityException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// 5. Order Already Cancelled
	@ExceptionHandler(OrderAlreadyCancelledException.class)
	public ResponseEntity<String> handleCancelledOrder(OrderAlreadyCancelledException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	// 6. Field Validation Exception
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
