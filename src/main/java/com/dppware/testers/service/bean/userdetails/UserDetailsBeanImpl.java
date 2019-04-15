package com.dppware.testers.service.bean.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Encapsulate User Details
 * @author dpena
 *
 */
public class UserDetailsBeanImpl  implements UserDetails{
	private String userName;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthorityImpl>();
	private Date created;

	
	public String getEmail() {
		return email;
	}


	public Date getCreated() {
		return created;
	}


	/**
	 * All fields constructor
	 * @param userName
	 * @param email
	 * @param password
	 * @param authorities
	 * @param created
	 */
	public UserDetailsBeanImpl(String userName, String email, String password,List<GrantedAuthorityImpl> authorities,  Date created) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.authorities=authorities;
		this.created = created;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	/**
	 * You can implement a Account expired policy for example:
	 * 	 If last login if older than one year, account is expired
	 *   return System.currentTimeMillis() - this.lastLogin.getTime() < TimeUnit.DAYS.toMillis(365l);
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	/**
	 * Based on this POJO data you can implement a policy for Blocking an account
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	/**
	 * If you want to implement a renove credentials policy:
	 * return System.currentTimeMillis() - this.lastLogin.getTime() < TimeUnit.DAYS.toMillis(365l);
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	/**
	 * For blocking users
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}


}
