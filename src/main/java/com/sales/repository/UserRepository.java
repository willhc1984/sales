package com.sales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sales.domain.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
	
	Optional<UserModel> findByUsername(String username);

}
