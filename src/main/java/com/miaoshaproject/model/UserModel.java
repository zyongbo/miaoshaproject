package com.miaoshaproject.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO: To be generated using swagger codegen
 */
public class UserModel {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄必须大于0岁")
    @Max(value = 200, message = "年龄必须小于200岁")
    private Integer age;

    @NotBlank(message = "电话不能为空")
    private String telephone;
    private String registerMode;
    private Integer thirdPartyId;

    @NotBlank(message = "电话不能为空")
    private String encryptPassword;

    private UserModel() {}

    // builder pattern is used if BeanUtils.copyProperties(,) not exists
    private UserModel(UserModelBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
        this.telephone = builder.telephone;
        this.registerMode = builder.registerMode;
        this.thirdPartyId = builder.thirdPartyId;
        this.encryptPassword = builder.encryptPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public Integer getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public static class UserModelBuilder {
        private Integer id;
        private String name;
        private Integer gender;
        private Integer age;
        private String telephone;
        private String registerMode;
        private Integer thirdPartyId;
        private String encryptPassword;

        public UserModelBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public UserModelBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserModelBuilder setGender(Integer gender) {
            this.gender = gender;
            return this;
        }

        public UserModelBuilder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public UserModelBuilder setTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public UserModelBuilder setRegisterMode(String registerMode) {
            this.registerMode = registerMode;
            return this;
        }

        public UserModelBuilder setThirdPartyId(Integer thirdPartyId) {
            this.thirdPartyId = thirdPartyId;
            return this;
        }

        public UserModelBuilder setEncryptPassword(String encryptPassword) {
            this.encryptPassword = encryptPassword;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }
}
