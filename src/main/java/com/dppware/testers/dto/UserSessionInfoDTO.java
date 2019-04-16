package com.dppware.testers.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO DTO with current User Info
 * @author dpena
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserSessionInfoDTO {
	/**
	 * User succesfull login Date
	 */
	private Date currentLogin;
	/**
	 * Additional Info exaple
	 */
	private String additionalInfo;
	
}
