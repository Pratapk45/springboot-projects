package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recharge;
import com.example.demo.enums.Operator;
import com.example.demo.enums.PlanType;
import com.example.demo.enums.RechargePlan;
import com.example.demo.exception.DuplicateRechargeException;
import com.example.demo.exception.InvalidRechargePlanException;
import com.example.demo.exception.RechargeNotFoundException;

@Service
public class RechargeService {

	private List<Recharge> recharges = new ArrayList<>();

	// Initial Records
	public RechargeService() {

		recharges.add(new Recharge(101, "+919172391249", Operator.JIO, 349, PlanType.UNLIMITED_5G));

		recharges.add(new Recharge(102, "+919172391249", Operator.JIO, 399, PlanType.UNLIMITED_5G));

		recharges.add(new Recharge(103, "+917654321098", Operator.AIRTEL, 349, PlanType.UNLIMITED_5G));

		recharges.add(new Recharge(104, "+916543210987", Operator.AIRTEL, 449, PlanType.UNLIMITED_5G));

		recharges.add(new Recharge(105, "+919543210876", Operator.VI, 409, PlanType.DATA));

		recharges.add(new Recharge(106, "+918543210765", Operator.BSNL, 299, PlanType.LONG_VALIDITY));
	}
	
	public List<RechargePlan> getRechargePlans() {

	    return List.of(RechargePlan.values());
	}

	// GET ALL
	public List<Recharge> getAllRecharges() {

		return recharges;
	}

	// GET BY ID
	public List<Recharge> getRechargeByMobileNumber(String mobileNumber) {

	    List<Recharge> result = recharges.stream()
	            .filter(r -> r.getMobileNumber().equals(mobileNumber))
	            .toList();

	    if (result.isEmpty()) {
	        throw new RechargeNotFoundException(
	            "Recharge with number " + mobileNumber + " not found"
	        );
	    }

	    return result;
	}

	// POST
	public Recharge addRecharge(Recharge recharge) {

		boolean exists = recharges.stream()
				.anyMatch(r -> r.getRechargeId().equals(recharge.getRechargeId()));

		if (exists) {

			throw new DuplicateRechargeException("Recharge with ID " + recharge.getRechargeId() + " already exists");
		}

		validateRechargePlan(recharge);

		recharges.add(recharge);

		return recharge;
	}

	// PLAN VALIDATION
	private void validateRechargePlan(Recharge recharge) {

		boolean validPlan = false;

		for (RechargePlan plan : RechargePlan.values()) {

			if (plan.getOperator().equals(recharge.getOperator())

					&& plan.getAmount() == recharge.getAmount()

					&& plan.getPlanType().equals(recharge.getPlanType())) {

				validPlan = true;
				break;
			}
		}

		if (!validPlan) {

			throw new InvalidRechargePlanException(
					"Invalid recharge plan for selected " + "operator, amount and plan type");
		}
	}

	
	
	// PUT
//	public Recharge updateRecharge(Integer id, Recharge updatedRecharge) {
//
//		Recharge existingRecharge = getRechargeById(id);
//
//		validateRechargePlan(updatedRecharge);
//
//		existingRecharge.setMobileNumber(updatedRecharge.getMobileNumber());
//
//		existingRecharge.setOperator(updatedRecharge.getOperator());
//
//		existingRecharge.setAmount(updatedRecharge.getAmount());
//
//		existingRecharge.setPlanType(updatedRecharge.getPlanType());
//
//		return existingRecharge;
//	}
//
//	// PATCH
//	public Recharge patchRecharge(Integer id, Recharge updatedRecharge) {
//
//		Recharge existingRecharge = getRechargeById(id);
//
//		if (updatedRecharge.getMobileNumber() != null) {
//
//			existingRecharge.setMobileNumber(updatedRecharge.getMobileNumber());
//		}
//
//		if (updatedRecharge.getOperator() != null) {
//
//			existingRecharge.setOperator(updatedRecharge.getOperator());
//		}
//
//		if (updatedRecharge.getAmount() != null) {
//
//			existingRecharge.setAmount(updatedRecharge.getAmount());
//		}
//
//		if (updatedRecharge.getPlanType() != null) {
//
//			existingRecharge.setPlanType(updatedRecharge.getPlanType());
//		}
//
//		return existingRecharge;
//	}
//
//	// DELETE
//	public void deleteRecharge(Integer id) {
//
//		Recharge recharge = recharges.stream()
//				.filter(r -> r.getRechargeId().equals(id))
//				.findFirst()
//				.orElseThrow(() -> new RechargeNotFoundException("Recharge with ID " + id + " not found"));
//
//		recharges.remove(recharge);
//	}
}