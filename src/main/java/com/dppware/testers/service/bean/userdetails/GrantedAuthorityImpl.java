package com.dppware.testers.service.bean.userdetails;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority{

	private String authorityName;
	
	public GrantedAuthorityImpl(String authorityName ) {
		super();
		this.authorityName=authorityName;
	}
	@Override
	public String getAuthority() {
		return this.authorityName;
	}

}
