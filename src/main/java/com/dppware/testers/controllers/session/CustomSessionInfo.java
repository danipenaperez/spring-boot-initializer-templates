package com.dppware.testers.controllers.session;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Custom example POJO scoped Session.
 * @author dpena
 *
 */

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomSessionInfo {
	/**
	 * User succesfull login Date
	 */
	private Date currentLogin;
	/**
	 * Additional Info exaple
	 */
	private String additionalInfo;
	public Date getCurrentLogin() {
		return currentLogin;
	}
	public void setCurrentLogin(Date currentLogin) {
		this.currentLogin = currentLogin;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	
}
