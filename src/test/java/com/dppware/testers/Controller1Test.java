package com.dppware.testers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class Controller1Test {

	@Autowired
    private MockMvc mockMvc;

	/**
	 * Test Controller1 nonSecuredEndPoints
	 * @throws Exception
	 */
	@Test
    public void accessAnonymous() throws Exception {
        this.mockMvc.perform(
                get("/controller1/nonSecured_endpoint"))
                .andExpect(
                        status().isOk()).andExpect(content().string("Hello from nonsecured endpoint!"));
        this.mockMvc.perform(
                get("/controller1/nonSecuredJSR250_endpoint"))
                .andExpect(
                        status().isOk()).andExpect(content().string("Hello from nonSecuredJSR250 endpoint!"));
        this.mockMvc.perform(
                get("/controller1/nonSecuredPrePost_endpoint"))
                .andExpect(
                        status().isOk()).andExpect(content().string("Hello from nonSecuredPrePost endpoint!"));
    }

	
	/**
	 * Test Controller1 SecuredEndPoints
	 * @throws Exception
	 */
	@Test
    public void accessSecured() throws Exception {
		/***********************
        * SECURED_ENDPOINT
        ***********************/
		//Probamos que NO se puede entrar y da error 401 UnAuthorized si intentamos entrar sin credenciales
        this.mockMvc.perform(
                get("/controller1/secured_endpoint"))
                .andExpect(
                        status().isUnauthorized());
        //Probamos que NO se puede entrar y da error 403 Forbidden si intentamos entrar con credenciales correctas pero no es el rol permitido
        this.mockMvc.perform(
                get("/controller1/secured_endpoint").with(httpBasic("administrator", "1234")))
                .andExpect(
                        status().isForbidden());
        
        //Probamos que NO se puede entrar y da error 403 Forbidden si intentamos entrar con credenciales correctas pero no es el rol permitido
        this.mockMvc.perform(
                get("/controller1/secured_endpoint").with(httpBasic("user", "1234")))
                .andExpect(
                        status().isOk()).andExpect(content().string("Hello from User Secured endpoint!"));
        
        /***********************
        * securedJSR250_endpoint
        ***********************/
         
        //Probamos que NO se puede entrar y da error 401 UnAuthorized si intentamos entrar sin credenciales
        this.mockMvc.perform(
                get("/controller1/securedJSR250_endpoint"))
                .andExpect(
                        status().isUnauthorized());
        //Probamos que NO se puede entrar y da error 403 Forbidden si intentamos entrar con credenciales correctas pero no es el rol permitido
        this.mockMvc.perform(
                get("/controller1/securedJSR250_endpoint").with(httpBasic("backend", "1234")))
                .andExpect(
                        status().isForbidden());
        
        //Probamos que NO se puede entrar y da error 403 Forbidden si intentamos entrar con credenciales correctas pero no es el rol permitido
        this.mockMvc.perform(
                get("/controller1/securedJSR250_endpoint").with(httpBasic("administrator", "1234")))
                .andExpect(
                        status().isOk()).andExpect(content().string("Hello from Admininstrator SecuredJSR250 endpoint!"));
        
        
        /***********************
         * securedPrePost_endpoint
         ***********************/
          
         //Probamos que NO se puede entrar y da error 401 UnAuthorized si intentamos entrar sin credenciales
         this.mockMvc.perform(
                 get("/controller1/securedPrePost_endpoint"))
                 .andExpect(
                         status().isUnauthorized());
         //Probamos que NO se puede entrar y da error 403 Forbidden si intentamos entrar con credenciales correctas pero no es el rol permitido
         this.mockMvc.perform(
                 get("/controller1/securedPrePost_endpoint").with(httpBasic("administrator", "1234")))
                 .andExpect(
                         status().isForbidden());
         
         //Probamos que NO se puede entrar y da error 403 Forbidden si intentamos entrar con credenciales correctas pero no es el rol permitido
         this.mockMvc.perform(
                 get("/controller1/securedPrePost_endpoint").with(httpBasic("backend", "1234")))
                 .andExpect(
                         status().isOk()).andExpect(content().string("Hello from backend SecuredPrePost endpoint!"));
        
    }

    
}
