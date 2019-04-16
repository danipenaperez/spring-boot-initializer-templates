package com.dppware.testers.controllers;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dppware.testers.dto.UserSessionInfoDTO;

@RestController
@RequestMapping("feature")
public class BasicExampleController extends BaseController{
	
	/**
	 * Permit all feature
	 * @return
	 */
	@GetMapping("openEndpoint")
    @PermitAll 
    public String nonSecuredJSR250() {
        return "Greeting from Open all roles Resource";
    }
	
	/**
	 * With out expecification
	 * @return
	 */
	@GetMapping("secured_user_endpoint")
	@Secured("ROLE_USER") 	//the user must have USER role
    public String secured() {
        return "Hello from -USER- Secured endpoint!";
    }
	
	/**
	 * Using jsr250
	 * @return
	 */
	@GetMapping("securedJSR250_admin_endpoint")
	@RolesAllowed({"ROLE_ADMIN"})  //the user must have ADMINSITRATOR role
    public String securedJSR250() {
        return "Hello from -ADMINSITRATOR- SecuredJSR250 endpoint!";
    }

	/**
	 * Using PrePost
	 * @return
	 */
	@GetMapping("securedPrePost_backend_endpoint")
	@PreAuthorize("hasAnyAuthority('ROLE_BACKEND')")  //the user must have BACKEND role almost
    public String securedPrePost() {
        return "Hello from -BACKEND- SecuredPrePost endpoint!";
    }
	
	
	
	/**
	 * Permit all feature, with post.
	 * Needed CSRF token to access it
	 * @return
	 */
	@PostMapping("refreshLogin")
    @PermitAll 
    public UserSessionInfoDTO refreshLoginInfo() {
		customSessionInfo.setCurrentLogin(new Date());
		return UserSessionInfoDTO.builder().currentLogin(customSessionInfo.getCurrentLogin()).additionalInfo(customSessionInfo.getAdditionalInfo()).build();
    }
	
	
}
