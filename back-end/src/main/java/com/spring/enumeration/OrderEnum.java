package com.spring.enumeration;

public enum OrderEnum {

    NEW("NEW"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    COMPLETE("COMPLETE");

    private String value;

    private OrderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
