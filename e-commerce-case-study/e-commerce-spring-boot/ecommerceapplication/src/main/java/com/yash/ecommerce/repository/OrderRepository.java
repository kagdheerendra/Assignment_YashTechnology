package com.yash.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ecommerce.entity.PlaceOrder;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<PlaceOrder, Long> {

	Optional<PlaceOrder> findByOrderId(int orderId);

	List<PlaceOrder> findAll();

}
