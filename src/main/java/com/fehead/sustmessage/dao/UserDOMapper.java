package com.fehead.sustmessage.dao;

import com.fehead.sustmessage.dataobject.MessageDO;
import com.fehead.sustmessage.dataobject.UserDO;

import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:19
 */
public interface UserDOMapper {
    /**
     * 通过id查找用户
     */
    UserDO selectUserById(String studentId);

    /**
     * 注册
     */
    void insertUser(UserDO userDO);

}
