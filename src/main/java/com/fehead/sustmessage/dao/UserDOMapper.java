package com.fehead.sustmessage.dao;

import com.fehead.sustmessage.dataobject.UserDO;

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

}
