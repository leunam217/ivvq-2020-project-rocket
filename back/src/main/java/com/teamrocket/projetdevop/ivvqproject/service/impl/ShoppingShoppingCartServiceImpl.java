package com.teamrocket.projetdevop.ivvqproject.service.impl;

import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.OrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ProductInOrderRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.ShoppingCartService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductService productService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductInOrderRepository productInOrderRepository;
	@Autowired
	ShoppingCartRepository cartRepository;
	@Autowired
	UserService userService;

	@Override
	public ShoppingCart getCart(User user) {
		return user.getCart();
	}

	@Override
	@Transactional
	public void finalCart(Collection<ProductOrdered> productInOrders, User user) {
		ShoppingCart finalCart = user.getCart();
		productInOrders.forEach(productInOrder -> {
			Set<ProductOrdered> set = finalCart.getProducts();
			Optional<ProductOrdered> old = set.stream()
					.filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
			ProductOrdered prod;
			if (old.isPresent()) {
				prod = old.get();
				prod.setCount(productInOrder.getCount() + prod.getCount());
			} else {
				prod = productInOrder;
				prod.setCart(finalCart);
				finalCart.getProducts().add(prod);
			}
			productInOrderRepository.save(prod);
		});
		cartRepository.save(finalCart);

	}

	@Override
	@Transactional
	public void delete(String itemId, User user) {
		Optional<ProductOrdered> op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId()))
				.findFirst();
		op.ifPresent(productInOrder -> {
			productInOrder.setCart(null);
			productInOrderRepository.deleteById(productInOrder.getId());
		});
	}

	@Override
	@Transactional
	public void placeOrder(User user) {
		Order order = new Order(user);
		orderRepository.save(order);

		user.getCart().getProducts().forEach(productInOrder -> {
			productInOrder.setCart(null);
			productInOrder.setOrder(order);
			productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
			productInOrderRepository.save(productInOrder);
			cartRepository.deleteAll();
		});

	}
}
