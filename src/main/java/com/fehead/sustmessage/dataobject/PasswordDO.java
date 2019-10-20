package com.fehead.sustmessage.dataobject;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 13:52
 */
public class PasswordDO {
    private Integer id;
    private String studentId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
