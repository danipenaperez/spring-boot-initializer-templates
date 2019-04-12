package com.dppware.testers.controllers;

import java.time.LocalDateTime;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("session")
public class SessionController extends BaseController{
	
	
	
	/**
	 * Using jsr250
	 * @return
	 */
	@GetMapping("currentUser")
	@RolesAllowed({"ROLE_ADMINISTRATOR", "ROLE_USER"})  //the user must have ADMINSITRATOR role
    public String securedJSR250(HttpSession httpSession) {
		
		if(sessionScopedBean.getValue()==null) {
			sessionScopedBean.setValue("manolo");	
		}
		
		
		
			String sessionKey = "firstAccessTime";
	      Object time = httpSession.getAttribute(sessionKey);
	      if (time == null) {
	          time = LocalDateTime.now();
	          httpSession.setAttribute(sessionKey, time);
	      }
	      return "first access time : " + time+"\nsession id: "+httpSession.getId();
    }
}
