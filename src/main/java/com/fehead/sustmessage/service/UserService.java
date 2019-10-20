package com.fehead.sustmessage.service;

import com.fehead.sustmessage.service.model.UserModel;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:06
 */
public interface UserService {
    /**
     * 通过id查找用户
     */
    UserModel selectUserById(String studentId);
}
