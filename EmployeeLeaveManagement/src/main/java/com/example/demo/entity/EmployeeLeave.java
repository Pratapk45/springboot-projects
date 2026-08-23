package com.example.demo.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeave {

    private Integer employeeId;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotBlank(message = "Leave type is mandatory")
    private String leaveType;

    @NotNull(message = "Number of days is mandatory")
    @Min(value = 1, message = "Leave days should be minimum 1")
    private Integer numberOfDays;

    @NotBlank(message = "Reason is required")
    private String reason;
}
