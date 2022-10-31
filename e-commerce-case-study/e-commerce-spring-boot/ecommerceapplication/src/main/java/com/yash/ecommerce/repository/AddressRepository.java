package com.yash.ecommerce.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.ecommerce.entity.Address;
import com.yash.ecommerce.entity.User;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {

	Optional<Address> findByUser(User user);
}
