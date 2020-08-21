package com.miaoshaproject.controller.response;

public class CommonReturnType {
    private ResponseStatusEnum status;
    private Object data;

    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(ResponseStatusEnum.SUCCESS, result);
    }

    public static CommonReturnType create(ResponseStatusEnum status, Object result) {
        CommonReturnType type = new CommonReturnType();
        type.status = status;
        type.data = result;
        return type;
    }

    // have to have getters and setters, otherwise, the serialization/deserialization will fail
    public ResponseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseStatusEnum status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
