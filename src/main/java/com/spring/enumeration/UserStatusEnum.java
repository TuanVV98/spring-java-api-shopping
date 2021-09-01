package com.spring.enumeration;

public enum UserStatusEnum {

    NOT_ACTIVE("NOT_ACTIVE"),
    IS_ACTIVE("IS_ACTIVE"),
    IS_BLOCKED("IS_BLOCKED");

    private String value;

    private UserStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
