package com.example.demo.entity;

import com.example.demo.enums.Operator;
import com.example.demo.enums.PlanType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recharge {

    private Integer rechargeId;


    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^\\+91[6-9][0-9]{9}$",
        message = "Mobile number must be in +91XXXXXXXXXX format"
    )
    private String mobileNumber;


    @NotNull(message = "Operator is required")
    private Operator operator;


    @NotNull(message = "Amount is required")
    @Min(
        value = 10,
        message = "Minimum recharge amount is 10"
    )
    private Integer amount;


    @NotNull(message = "Plan type is required")
    private PlanType planType;
}
