package com.miaoshaproject.controller.response;

public enum ResponseStatusEnum {
    SUCCESS("Success"),
    FAIL("Fail");

    private String status;

    ResponseStatusEnum(String status) {
        this.status = status;
    }

    // does not allow to set the status, only allow to get it from the enum element
    public String getStatus() {
        return status;
    }
}
