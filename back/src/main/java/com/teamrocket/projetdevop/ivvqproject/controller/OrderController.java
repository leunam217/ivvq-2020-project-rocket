package com.teamrocket.projetdevop.ivvqproject.controller;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;

import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.service.OrderService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import com.teamrocket.projetdevop.ivvqproject.util.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;

	@GetMapping("/order")
	public List<Order> orderHistoric(Authentication authentication) {

		List<Order> order;
		if (authentication.getAuthorities()
				.contains(new SimpleGrantedAuthority(UserRoleEnum.ROLE_CUSTOMER.toString()))) {
			order = orderService.findByBuyerEmail(authentication.getName());
		} else {
			order = orderService.findAll();
		}
		return order;
	}

	@PatchMapping("/order/cancel/{id}")
	public ResponseEntity<Order> cancelOrder(@PathVariable("id") Long orderId, Authentication authentication) {
		Order order = orderService.findOne(orderId);
		if (!authentication.getName().equals(order.getBuyerEmail()) && authentication.getAuthorities()
				.contains(new SimpleGrantedAuthority(UserRoleEnum.ROLE_CUSTOMER.toString()))) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(orderService.cancel(orderId));
	}

	@PatchMapping("/order/finish/{id}")
	public ResponseEntity<Order> finishOrder(@PathVariable("id") Long orderId, Authentication authentication) {
		if (authentication.getAuthorities()
				.contains(new SimpleGrantedAuthority(UserRoleEnum.ROLE_CUSTOMER.toString()))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(orderService.finish(orderId));
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<Order> showOneOrder(@PathVariable("id") Long orderId, Authentication authentication) {
		boolean isCustomer = authentication.getAuthorities()
				.contains(new SimpleGrantedAuthority(UserRoleEnum.ROLE_CUSTOMER.toString()));
		Order order = orderService.findOne(orderId);
		if (isCustomer && !authentication.getName().equals(order.getBuyerEmail())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		return ResponseEntity.ok(order);
	}
}
