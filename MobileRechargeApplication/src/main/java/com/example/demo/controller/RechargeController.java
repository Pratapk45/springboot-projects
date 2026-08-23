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

import com.example.demo.entity.Recharge;
import com.example.demo.enums.RechargePlan;
import com.example.demo.service.RechargeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

	@Autowired
	private RechargeService rechargeService;
	
	@GetMapping("/plans")
	public ResponseEntity<List<RechargePlan>> getRechargePlans() {

	    return ResponseEntity.ok(
	        rechargeService.getRechargePlans()
	    );
	}

	// GET ALL
	@GetMapping
	public ResponseEntity<List<Recharge>> getAllRecharges() {

		return ResponseEntity.ok(rechargeService.getAllRecharges());
	}


	@GetMapping("/{mobileNumber}")
	public ResponseEntity<List<Recharge>> getRechargeByMobileNumber(@PathVariable String mobileNumber) {

		return ResponseEntity.ok(rechargeService.getRechargeByMobileNumber(mobileNumber));
	}

	// POST
	@PostMapping
	public ResponseEntity<Recharge> addRecharge(@Valid @RequestBody Recharge recharge) {

		return new ResponseEntity<>(rechargeService.addRecharge(recharge), HttpStatus.CREATED);
	}

//	// PUT
//	@PutMapping("/{id}")
//	public ResponseEntity<Recharge> updateRecharge(@PathVariable Integer id, @Valid @RequestBody Recharge recharge) {
//
//		return ResponseEntity.ok(rechargeService.updateRecharge(id, recharge));
//	}
//
//	// PATCH
//	@PatchMapping("/{id}")
//	public ResponseEntity<Recharge> patchRecharge(@PathVariable Integer id, @RequestBody Recharge recharge) {
//
//		return ResponseEntity.ok(rechargeService.patchRecharge(id, recharge));
//	}
//
//	// DELETE
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteRecharge(@PathVariable Integer id) {
//
//		rechargeService.deleteRecharge(id);
//
//		return ResponseEntity.ok("Recharge with ID " + id + " deleted successfully");
//	}
}
