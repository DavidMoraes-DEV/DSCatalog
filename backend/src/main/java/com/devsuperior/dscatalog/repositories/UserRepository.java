package com.devsuperior.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email); 
	
	@Query("SELECT DISTINCT obj FROM User obj WHERE "
			+ "(LOWER(obj.firstName) LIKE LOWER(CONCAT('%',:firstName,'%'))) ")
	Page<User> findAllFirstName(String firstName, Pageable pageable);

	public User findByPasswordResetToken(String token);
}
