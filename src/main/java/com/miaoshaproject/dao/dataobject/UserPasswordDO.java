package com.miaoshaproject.dao.dataobject;

public class UserPasswordDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.id
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.encrypt_password
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    private String encryptPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_password.user_id
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    private Integer userId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.id
     *
     * @return the value of user_password.id
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.id
     *
     * @param id the value for user_password.id
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.encrypt_password
     *
     * @return the value of user_password.encrypt_password
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    public String getEncryptPassword() {
        return encryptPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.encrypt_password
     *
     * @param encryptPassword the value for user_password.encrypt_password
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword == null ? null : encryptPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_password.user_id
     *
     * @return the value of user_password.user_id
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_password.user_id
     *
     * @param userId the value for user_password.user_id
     *
     * @mbg.generated Fri Aug 07 10:22:43 PDT 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}