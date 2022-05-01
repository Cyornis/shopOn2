package com.cdac.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cdac.entity.Merchant;
import com.cdac.repository.MerchantRepository;

public class CustomMerchantDetailService implements UserDetailsService {
		
		@Autowired
		private MerchantRepository merchantRepository;
		
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
			Merchant merchant =merchantRepository.findByEmail(email);
			if(merchant==null) {
				throw new UsernameNotFoundException("User email not found");
			}
			return new CustomMerchantDetails(merchant);
		}
	}

