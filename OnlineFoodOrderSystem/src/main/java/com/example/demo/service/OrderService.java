package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.DuplicateOrderException;
import com.example.demo.exception.InsufficientQuantityException;
import com.example.demo.exception.InvalidOrderException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.model.Order;

@Service
public class OrderService {

	private List<Order> orders = new ArrayList<>();

	public OrderService() {

		orders.add(new Order(101, "Pratap", "Pizza", 2, 450.0));
		orders.add(new Order(102, "Rahul", "Burger", 3, 300.0));
		orders.add(new Order(103, "Sneha", "Biryani", 1, 250.0));
		orders.add(new Order(104, "Amit", "Pasta", 2, 400.0));
		orders.add(new Order(105, "Priya", "Sandwich", 4, 320.0));
		orders.add(new Order(106, "Neha", "Dosa", 2, 180.0));
	}

	// GET ALL ORDERS
	public List<Order> getAllOrders() {

		return orders;
	}

	// GET ORDER BY ID
	public Order getOrderById(Integer id) {

		return orders.stream().filter(order -> order.getOrderId().equals(id)).findFirst()
				.orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
	}

	// ADD ORDER
	public Order addOrder(Order order) {

		boolean exists = orders.stream().anyMatch(or -> or.getOrderId().equals(order.getOrderId()));

		if (exists) {
			throw new DuplicateOrderException("Order with ID " + order.getOrderId() + " already exists");
		}

		if (order.getPrice() <= 0) {
			throw new InvalidOrderException("Price must be greater than 0");
		}

		if (order.getQuantity() > 20) {
			throw new InsufficientQuantityException("Maximum order quantity is 20");
		}

		orders.add(order);

		return order;
	}

	// UPDATE ORDER
	public Order updateOrder(Integer id, Order updatedOrder) {

		Order existingOrder = getOrderById(id);

		existingOrder.setCustomerName(updatedOrder.getCustomerName());

		existingOrder.setFoodItem(updatedOrder.getFoodItem());

		existingOrder.setQuantity(updatedOrder.getQuantity());

		existingOrder.setPrice(updatedOrder.getPrice());

		return existingOrder;
	}

	// DELETE ORDER
	// DELETE ORDER
	public void deleteOrder(Integer id) {

		Order order = orders.stream()
				.filter(o -> o.getOrderId().equals(id)).findFirst()
				.orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));

		orders.remove(order);
	}

	// PATCH ORDER
	public Order patchOrder(Integer id, Order updatedOrder) {

		Order existingOrder = getOrderById(id);

		if (updatedOrder.getCustomerName() != null) {
			existingOrder.setCustomerName(updatedOrder.getCustomerName());
		}

		if (updatedOrder.getFoodItem() != null) {
			existingOrder.setFoodItem(updatedOrder.getFoodItem());
		}

		if (updatedOrder.getQuantity()!= null) {
			existingOrder.setQuantity(updatedOrder.getQuantity());
		}

		if (updatedOrder.getPrice() != null) {
			existingOrder.setPrice(updatedOrder.getPrice());
		}

		return existingOrder;
	}
}