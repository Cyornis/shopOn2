package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.entity.Merchant;


public interface MerchantRepository extends JpaRepository<Merchant, Long>{
	@Query("select i from Merchant i where i.email=?1")
	public Merchant findByEmail(@Param("email") String email);

}
