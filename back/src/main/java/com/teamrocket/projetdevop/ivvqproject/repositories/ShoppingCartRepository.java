package com.teamrocket.projetdevop.ivvqproject.repositories;

import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
