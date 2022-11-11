package com.yash.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(nativeQuery = true, value = "select * from users where email = :email")
	Optional<User> findByUserEmail(@Param("email") String email);
	
//	@Query(nativeQuery = true, value = "select * from users where user_name = :username")
//	Optional<User> findByUserName(@Param("username") String username);
	
	Optional<User> findByUserName(String userName);
	
	Optional<User> findByEmailAndPasswordAndUserType(String email, String password, String userType);
	
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);
}
