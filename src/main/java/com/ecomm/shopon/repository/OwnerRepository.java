package com.ecomm.shopon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecomm.shopon.entity.Owner;


public interface OwnerRepository extends JpaRepository<Owner, Long>{
	
	@Query("select owner from Owner owner where owner.email=?1")
	public Owner findByEmail(@Param("email") String email);
	

}
