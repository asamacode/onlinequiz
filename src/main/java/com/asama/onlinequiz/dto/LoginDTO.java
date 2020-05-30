package com.asama.onlinequiz.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {

    public static final Integer TYPE_STUDENT = 0;
    public static final Integer TYPE_LECTURER = 1;
    public static final Integer TYPE_MANAGER = 2;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    
    private Integer userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}
