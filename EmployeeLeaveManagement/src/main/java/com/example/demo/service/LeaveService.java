package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.EmployeeLeave;
import com.example.demo.exception.DuplicateLeaveException;
import com.example.demo.exception.ExcessiveLeaveException;
import com.example.demo.exception.InvalidLeaveException;
import com.example.demo.exception.LeaveNotFoundException;

@Service
public class LeaveService {

	private List<EmployeeLeave> leaves = new ArrayList<>();

	// Initial Records
	public LeaveService() {

		leaves.add(new EmployeeLeave(101, "Pratap", "Casual", 2, "Personal work"));

		leaves.add(new EmployeeLeave(102, "Rahul", "Sick", 3, "Not feeling well"));

		leaves.add(new EmployeeLeave(103, "Sneha", "Casual", 1, "Family function"));

		leaves.add(new EmployeeLeave(104, "Amit", "Earned", 5, "Vacation"));

		leaves.add(new EmployeeLeave(105, "Priya", "Sick", 2, "Medical reason"));

		leaves.add(new EmployeeLeave(106, "Neha", "Casual", 3, "Personal work"));
	}

	// GET ALL
	public List<EmployeeLeave> getAllLeaves() {

		return leaves;
	}

	// GET BY ID
	public EmployeeLeave getLeaveById(Integer employeeId) {

		return leaves.stream()
				.filter(leave -> leave.getEmployeeId().equals(employeeId)).findFirst()
				.orElseThrow(() -> new LeaveNotFoundException("Leave record for Employee ID " + employeeId + " not found"));
	}

	// POST
	public EmployeeLeave addLeave(EmployeeLeave leave) {

		boolean exists = leaves.stream()
				.anyMatch(empleave -> empleave.getEmployeeId().equals(leave.getEmployeeId()));

		if (exists) {

			throw new DuplicateLeaveException(
					"Leave record for Employee ID " + leave.getEmployeeId() + " already exists");
		}

		if (leave.getNumberOfDays() < 15) {

			throw new ExcessiveLeaveException("Employee cannot apply for more than 15 days leave");
		}

		if (leave.getLeaveType().equalsIgnoreCase("Casual") && leave.getNumberOfDays() > 5) {

			throw new InvalidLeaveException("Casual leave cannot be more than 5 days");
		}

		leaves.add(leave);

		return leave;
	}

	// PUT
	public EmployeeLeave updateLeave(Integer employeeId, EmployeeLeave updatedLeave) {

		EmployeeLeave existingLeave = getLeaveById(employeeId);

		existingLeave.setEmployeeName(updatedLeave.getEmployeeName());

		existingLeave.setLeaveType(updatedLeave.getLeaveType());

		existingLeave.setNumberOfDays(updatedLeave.getNumberOfDays());

		existingLeave.setReason(updatedLeave.getReason());

		return existingLeave;
	}

	// PATCH
	public EmployeeLeave patchLeave(Integer employeeId, EmployeeLeave updatedLeave) {

		EmployeeLeave existingLeave = getLeaveById(employeeId);

		if (updatedLeave.getEmployeeName() != null) {

			existingLeave.setEmployeeName(updatedLeave.getEmployeeName());
		}

		if (updatedLeave.getLeaveType() != null) {

			existingLeave.setLeaveType(updatedLeave.getLeaveType());
		}

		if (updatedLeave.getNumberOfDays() != null) {

			existingLeave.setNumberOfDays(updatedLeave.getNumberOfDays());
		}

		if (updatedLeave.getReason() != null) {

			existingLeave.setReason(updatedLeave.getReason());
		}

		return existingLeave;
	}

	// DELETE
	public void deleteLeave(Integer employeeId) {

		EmployeeLeave leave = leaves.stream()
				.filter(l -> l.getEmployeeId().equals(employeeId))
				.findFirst()
				.orElseThrow(() -> new LeaveNotFoundException("Leave record for Employee ID " + employeeId + " not found"));

		leaves.remove(leave);
	}
}
