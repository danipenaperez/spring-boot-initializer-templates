package com.dppware.testers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.dppware.testers.service.bean.userdetails.UserDetailsServiceImpl;

/**
 * It not only web security, is the whole application security  configuration
 * @author dpena
 *
 */
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true, //Enables @Security annotation
        jsr250Enabled = true, //Enables @RolesAllowed and @PermitAll annotations
        prePostEnabled = true) //@PreAuthorize and @PostAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);
    }


}
