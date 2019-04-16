package com.dppware.testers.controllers.session;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dppware.testers.dao.repository.UserRepository;
import com.dppware.testers.util.constants.ApplicationRolesEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Authentication success Handler, will initialize CustomSessionInfo.currentLogin property.
 * If need initialize info to this session, this is the correct place to do it. 
 * @author dpena
 *
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
	/**
	 * Session scoped Bean
	 */
	@Resource
	CustomSessionInfo customSessionInfo;
	
	/**
	 * Access to repository if need to fetch inital user database info
	 */
	@Autowired 
	UserRepository userRepository;

    
	
    
    /**
     * Add some info to current Session (SessonPojo object  that is session Scoped Bean)
     * Redirect to the view based on Role of the logged user
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	//1.Initialize Session scoped Beans
    	Date loginSuccess = new Date();
    	log.info("Initialized Session with Date "+loginSuccess);
    	customSessionInfo.setCurrentLogin(loginSuccess);
        
    	//2.Redirect based on Current User Role
    	new DefaultRedirectStrategy().sendRedirect(request, response, getRedirectURL(authentication));

    }
    
    /**
     * Return the correct url to redirect user after login success, based on User role.
     * @param authentication
     * @return
     */
    protected String getRedirectURL(Authentication authentication) {
    	String redirect = "/";
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
    	for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(ApplicationRolesEnum.USER.getName())) {
            	redirect = "dashboardUser.html";
                break;
            } else if (grantedAuthority.getAuthority().equals(ApplicationRolesEnum.BACKEND.getName())) {
            	redirect = "dashboardBackend.html";
                break;
            } else if (grantedAuthority.getAuthority().equals(ApplicationRolesEnum.ADMINISTRATOR.getName())) {
            	redirect = "dashboardAdmin.html";
                break;
            }
        }
    	return redirect;
    }
}

