package com.dppware.testers.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dppware.testers.dao.entity.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(@Param("email") String email);
	
    List<User> findByName(@Param("name") String name);
    
    List<User> findAllByHobbiesContaining(@Param("hobbies") String hobbies);
}
