package com.miaoshaproject.error;

public enum BusinessErrorEnum implements CommonError {
    // common errors
    UNKNOWN_ERROR(10001, "Unknown error"),
    PARAMETER_VALIDATION_ERROR(10002, "Parameters invalid"),

    // 20000 are errors from user information
    USER_NOT_EXIST(20001, "User does not exist"),
    USER_LOGIN_FAIL(20002, "User name or password not correct"),
    USER_NOT_LOGIN(20003, "User has not login"),

    // 3000 are errors from transactions
    STOCK_NOT_ENOUGH(30001, "Stock not enough");

    // errorCode cannot be changed, the code can only be obtained from the enum elements
    // errorMsg can be changed for each errorCode
    private int errorCode;
    private String errorMsg;

    BusinessErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
