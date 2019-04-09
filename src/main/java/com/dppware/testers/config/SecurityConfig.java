package com.dppware.testers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //In Spring 5 is not permitted save password in plain, you will get the java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null" Error 
    	// Adding Utilities , default use PasswordEncoder = BCrypt
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	
    	auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("1234")).roles("USER")
            .and()
                .withUser("administrator").password(encoder.encode("1234")).roles("ADMINISTRATOR")
             .and()
                .withUser("backend").password(encoder.encode("1234")).roles("BACKEND");
    }

}
