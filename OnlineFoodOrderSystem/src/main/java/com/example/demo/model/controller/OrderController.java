package com.example.demo.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {

		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {

		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@PostMapping
	public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {

		return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @Valid @RequestBody Order order) {

		return ResponseEntity.ok(orderService.updateOrder(id, order));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Order> patchOrder(@PathVariable Integer id, @RequestBody Order order) {

		return ResponseEntity.ok(orderService.patchOrder(id, order));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {

		orderService.deleteOrder(id);

		return ResponseEntity.ok("Order deleted successfully");
	}
}
