package com.dppware.testers.service.bean.userdetails;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dppware.testers.dao.entity.User;
import com.dppware.testers.dao.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * This method have to look the user by name in the database, 
	 * If found, must return the UserDetails, and populate the UserDetailsBeanImpl.password field with the obtained from database
	 * Spring will manage it, to return a 401 if password does not match, or return the 403 if the roles not apply to requested resource
	 * Makes sense because, the pass will never be logged (obtained from database) and if we want to query database by 2 fileds (username and password) will be slower than only one key (username)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsBeanImpl user = null;
		System.out.println("BUSCANDO Y BUSCANDO USUARIO....EN EL USERDETAILS SERVICE....");
		User fetchUser = userRepository.findByEmail(username);	
		
		if(fetchUser!=null) {
			List<GrantedAuthorityImpl> userAuthorities = new ArrayList<GrantedAuthorityImpl>();
			
			if(fetchUser.getRoles()!=null) {
				fetchUser.getRoles().stream().forEach(authority->userAuthorities.add(new GrantedAuthorityImpl(authority.getName())));
			}
			
			user = new UserDetailsBeanImpl(fetchUser.getName(), fetchUser.getEmail(), fetchUser.getPassword(),userAuthorities, new Date());
		}

		return user;
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
