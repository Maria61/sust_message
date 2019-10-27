package com.fehead.sustmessage.service.model;

import javax.validation.constraints.NotBlank;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:02
 */
public class UserModel {
    private Integer id;
    @NotBlank(message = "学号不能为空")
    private String studentId;
    @NotBlank(message = "手机号不能为空")
    private String telephone;
    @NotBlank(message = "昵称不能为空")
    private String displayName;
    private String avatar;
    @NotBlank(message = "密码不能为空")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
