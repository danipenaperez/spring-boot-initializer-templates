package com.dppware.testers.controllers;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.dppware.testers.controllers.session.CustomSessionInfo;

import lombok.extern.slf4j.Slf4j;
/**
 * Base Hierarchy Apps Controllers, 
 * contains Session scoped beans and other common Controller utilities
 * @author dpena
 *
 */
@Slf4j
public class BaseController {

	@Resource
	CustomSessionInfo customSessionInfo;
	
	
}


