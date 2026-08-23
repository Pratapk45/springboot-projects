package com.example.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@NotNull(message = "Oder id can not be null")
	private Integer orderId;

	@NotBlank(message = "Customer name required...")
	private String customerName;

	@NotBlank(message = "Food item cannot be empty")
	private String foodItem;

	@Min(value = 1, message = "Quantity must be atleat 1 ")
	private Integer quantity;

	@NotNull(message = "Price is mandatory")
	private Double price;

}
