package com.dppware.testers.util.constants;

/**
 * Constants for Application Roles
 * @author dpena
 *
 */
public enum ApplicationRolesEnum {
    ADMINISTRATOR("ROLE_ADMIN"),
    BACKEND("ROLE_BACKEND"),
    USER("ROLE_USER");

    private final String name;
    

    ApplicationRolesEnum(String name) {
        this.name=name;
    }

    public String getName() {
    	return name;
    }
    
    public static String[] allRoles() {
    	return new String [] {ApplicationRolesEnum.BACKEND.getName(),ApplicationRolesEnum.USER.getName(),ApplicationRolesEnum.USER.getName()};
    }
}
