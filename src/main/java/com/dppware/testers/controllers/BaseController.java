package com.dppware.testers.controllers;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.dppware.testers.controllers.session.SessionPojo;

public class BaseController {

	@Resource(name = "sessionScopedBean")
	SessionPojo sessionScopedBean;
	
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public SessionPojo sessionScopedBean() {
	    return new SessionPojo();
	}
}
