package com.dppware.testers.controllers;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controller1")
public class Controller1 extends BaseController{

	/*********************************************
	 * NON SECURED END POINTS 
	 *********************************************/
	/**
	 * With out expecification
	 * @return
	 */
	@GetMapping("nonSecured_endpoint")
	@Secured("ROLE_ANONYMOUS") 	// You can add Role Anonymous or comment this line 
    public String nonSecured() {
        return "Hello from nonsecured endpoint!";
    }
	
	/**
	 * Using jsr250
	 * @return
	 */
	@GetMapping("nonSecuredJSR250_endpoint")
    @PermitAll  //JSR250 annotation
    public String nonSecuredJSR250() {
        return "Hello from nonSecuredJSR250 endpoint!";
    }

	/**
	 * Using PrePost
	 * @return
	 */
	@GetMapping("nonSecuredPrePost_endpoint")
	@PreAuthorize("permitAll()")  //Supports Spring Expression Language (SpEL)
    public String nonSecuredPrePost() {
        return "Hello from nonSecuredPrePost endpoint!";
    }	
	
	
	/*********************************************
	 * SECURED END POINTS 
	 *********************************************/
	/**
	 * With out expecification
	 * @return
	 */
	@GetMapping("secured_endpoint")
	@Secured("ROLE_USER") 	//the user must have USER role
    public String secured() {
        return "Hello from User Secured endpoint!";
    }
	
	/**
	 * Using jsr250
	 * @return
	 */
	@GetMapping("securedJSR250_endpoint")
	@RolesAllowed({"ROLE_ADMINISTRATOR"})  //the user must have ADMINSITRATOR role
    public String securedJSR250() {
        return "Hello from Admininstrator SecuredJSR250 endpoint!";
    }

	/**
	 * Using PrePost
	 * @return
	 */
	@GetMapping("securedPrePost_endpoint")
	@PreAuthorize("hasAnyAuthority('ROLE_BACKEND')")  //the user must have BACKEND role almost
    public String securedPrePost() {
        return "Hello from backend SecuredPrePost endpoint!";
    }
	
	
}
