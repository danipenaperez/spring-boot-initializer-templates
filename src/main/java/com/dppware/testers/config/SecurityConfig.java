package com.dppware.testers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dppware.testers.controllers.filter.CSRFTokenResponseHeaderBindingFilter;
import com.dppware.testers.controllers.session.CustomAuthenticationSuccessHandler;
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

	@Autowired 
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Defauts Basic Auth
    	http.authorizeRequests()
    		//.antMatchers("/login**").permitAll()
    		.antMatchers("/dashboardUser.html").access("hasRole('ROLE_USER')")
    		.antMatchers("/dashboardBackend.html").access("hasRole('ROLE_BACKEND')")
    		.antMatchers("/dashboardAdmin.html").access("hasRole('ROLE_ADMIN')")
    		.antMatchers("/api").access("hasRole('ROLE_ADMIN')") //for spring data rest only accessible by Admin Users
    		.and().formLogin()
    						.loginPage("/customLogin.html")
    						.loginProcessingUrl("/login_perform")
    						
    						//.defaultSuccessUrl("/paco.html") //This behaviour is based on SavedRequestAwareAuthenticationSuccessHandler not works if you define your customAuthenticationHandler
    						.successHandler(customAuthenticationSuccessHandler)
    		//enables endPoint at "/logout" and management
    		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/goodbye.html")
    		.and().addFilterAfter(new CSRFTokenResponseHeaderBindingFilter(),CsrfFilter.class); //add CSRF info to response headers to be accesible at client on easy way
    		;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);
    }


}
