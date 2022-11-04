package com.yash.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ecommerce.entity.Bufcart;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Bufcart, Long> {

	List<Bufcart> findByEmail(String email);

	Optional<Bufcart> findByBufcartIdAndEmail(int bufcartId, String email);

	void deleteByBufcartIdAndEmail(int bufcartId, String email);

	List<Bufcart> findAllByEmail(String email);

	List<Bufcart> findAllByOrderId(int orderId);
}
