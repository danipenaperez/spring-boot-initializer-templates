package com.dppware.testers;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dppware.testers.dao.entity.Role;
import com.dppware.testers.dao.entity.User;
import com.dppware.testers.dao.repository.RoleRepository;
import com.dppware.testers.dao.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TestersApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TestersApplication.class, args);
	}
	
	
	
	
	//Create Default Users
		@Autowired
		private UserRepository userRepository;
		@Autowired
		private RoleRepository roleRepository;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@PostConstruct
		private void initDb() {
			Role roleUser = null;
			Role roleAdmin = null;
			Role roleBackend = null;
			if(roleRepository.count()==0) {
				roleUser = roleRepository.save(Role.builder().name("USER").build());
				roleAdmin = roleRepository.save(Role.builder().name("ADMIN").build());
				roleBackend = roleRepository.save(Role.builder().name("BACKEND").build());
			}
			
			if(userRepository.count()==0) {
				log.info("NOT FOUND USERS ON DATABASE");
				userRepository.save(User.builder().email("user@gmail.com").password(passwordEncoder.encode("1234")).name("Paco").hobbies("futbol pesca RayoVallecano" ).roles(Arrays.asList(roleUser)).build());
				userRepository.save(User.builder().email("admin@gmail.com").password(passwordEncoder.encode("1234")).name("Maribel").hobbies("futbol natacion leer" ).roles(Arrays.asList(roleAdmin)).build());
				userRepository.save(User.builder().email("backend@gmail.com").password(passwordEncoder.encode("1234")).name("Maribel").hobbies("futbol natacion leer" ).roles(Arrays.asList(roleBackend)).build());
				
			}else {
				log.info("FOUND USERS ON DATABASE");
				/**List all persons **/
				//userRepository.findAll().forEach(found->log.info(found.toString()));
				/**List persons that like hobbies **/
				log.info(String.format("la pesca le gusta a %s personas",userRepository.findAllByHobbiesContaining("pesca").size() ));
				
			}
		}
}
