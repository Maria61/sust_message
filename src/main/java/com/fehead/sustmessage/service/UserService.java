package com.fehead.sustmessage.service;

import com.fehead.sustmessage.dataobject.PasswordDO;
import com.fehead.sustmessage.dataobject.UserDO;
import com.fehead.sustmessage.service.model.MessageModel;
import com.fehead.sustmessage.service.model.UserModel;

import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:06
 */
public interface UserService {
    /**
     * 通过id查找用户
     */
    UserModel selectUserById(String studentId) ;

    /**
     * 添加用户（注册）
     */
    void insertUser(UserModel userModel);

//
    /**
     * 添加密码记录（注册）
//     * @param studentId
//     * @param password
     */
    void insertPassword(PasswordDO passwordDO);

    /**
     *根据用户id和密码查找密码id（登录）
     * @param studentId
     * @param password
     */
    Integer selectPasswordIdByStudentIdAndPassWord(String studentId,String password);

}
