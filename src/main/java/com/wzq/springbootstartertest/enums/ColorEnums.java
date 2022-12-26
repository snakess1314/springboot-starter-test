package com.wzq.springbootstartertest.enums;

public enum  ColorEnums {
    DEFAULT("zhang"),DD("ddd"),CONTEXT_PARAM("REQUEST_PARAM");
    private String value;
    ColorEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        String value = ColorEnums.CONTEXT_PARAM.getValue();
        System.out.println(value);
    }
}
