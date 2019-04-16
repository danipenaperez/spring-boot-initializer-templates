package com.dppware.testers.controllers;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dppware.testers.controllers.session.CustomSessionInfo;
import com.dppware.testers.dto.UserSessionInfoDTO;

@RestController
@RequestMapping("session")
public class SessionController extends BaseController{
	

	/**
	 * Open endpoint to obtain info about the created web session, basically the csrf info (paramname and tokenvalue) to perform login
	 * or sing up or other non GET operations
	 * Return headers  
	 * 
	 * @return
	 */
	@GetMapping
	@PermitAll
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void openWebSessionInfo() {
	      //CSRFTokenResponseHeaderBindingFilter  will add header info to this request
    }
	
	
	
	/**
	 * Return current Session info:
	 * 
	 * such as CSRF Token
	 * @return
	 */
	@GetMapping("me")
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER", "ROLE_BACKEND"})  //the user must have ADMINSITRATOR role
    public UserSessionInfoDTO securedJSR250(HttpSession httpSession) {
		CustomSessionInfo currentInfo= customSessionInfo;
		if(currentInfo.getCurrentLogin() == null) {
			currentInfo.setCurrentLogin(new Date());
		}
	    return UserSessionInfoDTO.builder().currentLogin(currentInfo.getCurrentLogin()).additionalInfo(currentInfo.getAdditionalInfo()).build();
    }
}










/**
 * //		if(sessionScopedBean.getValue()==null) {
//			sessionScopedBean.setValue("manolo");	
//		}
//		
//		
//		
//			String sessionKey = "firstAccessTime";
//	      Object time = httpSession.getAttribute(sessionKey);
//	      if (time == null) {
//	          time = LocalDateTime.now();
//	          httpSession.setAttribute(sessionKey, time);
 */

///**
// * Using jsr250
// * @return
// */
//@PostMapping
////@RolesAllowed({"ROLE_ADMINISTRATOR", "ROLE_USER"})  //JSR250 annotation
//@PermitAll
//public String dologin(HttpSession httpSession) {
//	return "you are in POST";
//}
///**
// * Using jsr250
// * @return
// */
//@GetMapping
//@PermitAll  //JSR250 annotation
//public String getlogin(HttpSession httpSession) {
//	return "you are in GET";
//}