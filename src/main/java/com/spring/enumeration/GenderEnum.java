package com.spring.enumeration;

public enum GenderEnum {

    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");

    private String value;

    private GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
