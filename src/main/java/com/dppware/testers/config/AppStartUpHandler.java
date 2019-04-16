package com.dppware.testers.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dppware.testers.dao.entity.Role;
import com.dppware.testers.dao.entity.User;
import com.dppware.testers.dao.repository.RoleRepository;
import com.dppware.testers.dao.repository.UserRepository;
import com.dppware.testers.util.constants.ApplicationRolesEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Handle StartUp process
 * -sucha as, create defautl users if not exists
 * - ...
 * @author dpena
 *
 */
@Component
@Slf4j
public class AppStartUpHandler {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void onAppStartUp() {
		initUsersIfNotExists();
	}
	
	
	/**
	 * Create Default Roles And Users
	 */
	private void initUsersIfNotExists() {
		Role roleUser = null;
		Role roleAdmin = null;
		Role roleBackend = null;
		if(roleRepository.count()==0) {
			roleUser = roleRepository.save(Role.builder().name(ApplicationRolesEnum.USER.getName()).build());
			roleAdmin = roleRepository.save(Role.builder().name(ApplicationRolesEnum.ADMINISTRATOR.getName()).build());
			roleBackend = roleRepository.save(Role.builder().name(ApplicationRolesEnum.BACKEND.getName()).build());
		}
		
		if(userRepository.count()==0) {
			log.info("NOT FOUND USERS ON DATABASE");
			userRepository.save(User.builder().email("user@gmail.com").password(passwordEncoder.encode("1234")).name("Paco").hobbies("futbol pesca RayoVallecano" ).roles(Arrays.asList(roleUser)).build());
			userRepository.save(User.builder().email("admin@gmail.com").password(passwordEncoder.encode("1234")).name("Maribel").hobbies("futbol natacion leer" ).roles(Arrays.asList(roleAdmin)).build());
			userRepository.save(User.builder().email("backend@gmail.com").password(passwordEncoder.encode("1234")).name("Maribel").hobbies("futbol natacion leer" ).roles(Arrays.asList(roleBackend)).build());
			
		}
	}
}
