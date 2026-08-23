package com.example.demo.controller;

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

import com.example.demo.entity.EmployeeLeave;
import com.example.demo.service.LeaveService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leaves")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;

	// GET ALL
	@GetMapping
	public ResponseEntity<List<EmployeeLeave>> getAllLeaves() {

		return ResponseEntity.ok(leaveService.getAllLeaves());
	}

	// GET BY ID
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeLeave> getLeaveById(@PathVariable Integer employeeId) {

		return ResponseEntity.ok(leaveService.getLeaveById(employeeId));
	}

	// POST
	@PostMapping
	public ResponseEntity<EmployeeLeave> addLeave(@Valid @RequestBody EmployeeLeave leave) {

		return new ResponseEntity<>(leaveService.addLeave(leave), HttpStatus.CREATED);
	}

	// PUT
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeLeave> updateLeave(@PathVariable Integer employeeId,
			@Valid @RequestBody EmployeeLeave leave) {

		return ResponseEntity.ok(leaveService.updateLeave(employeeId, leave));
	}

	// PATCH
	@PatchMapping("/{employeeId}")
	public ResponseEntity<EmployeeLeave> patchLeave(@PathVariable Integer employeeId,
			@RequestBody EmployeeLeave leave) {

		return ResponseEntity.ok(leaveService.patchLeave(employeeId, leave));
	}

	// DELETE
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteLeave(@PathVariable Integer employeeId) {

		leaveService.deleteLeave(employeeId);

		return ResponseEntity.ok("Leave record for Employee ID " + employeeId + " deleted successfully");
	}
}