 package com.cdac.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Bean
		public UserDetailsService userDetailsService() {
			return new CustomMerchantDetailService();
			
		}
		 

		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		@Bean
		public DaoAuthenticationProvider doAuthenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService());
			authProvider.setPasswordEncoder(bCryptPasswordEncoder());
			return authProvider;
		}
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.authenticationProvider(doAuthenticationProvider());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// TODO Auto-generated method stub
		http.csrf().disable()
		.authorizeRequests().antMatchers("/admin/**").authenticated().anyRequest().permitAll()
		.and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/admin/home").permitAll()
		.and().logout().logoutSuccessUrl("/home").permitAll();
			
		}
	}

