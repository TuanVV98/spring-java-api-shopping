package com.spring.enumeration;

/**
 * Enum đại diện cho hai loại vai trò trong API: ROLE_ADMIN và ROLE_USER.
 * 
 * @since 19/05/2021
 */
public enum RoleEnum {

	
	ROLE_ADMIN("ROLE_ADMIN"), 
	ROLE_USER("ROLE_USER");
	
	private String value;
	
	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
