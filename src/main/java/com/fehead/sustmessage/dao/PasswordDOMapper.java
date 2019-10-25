package com.fehead.sustmessage.dao;

import com.fehead.sustmessage.dataobject.PasswordDO;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:20
 */
public interface PasswordDOMapper {

    /**
     * 注册
     * @param studentId
     * @param password
     */
    void insertPasswprd(PasswordDO passwordDO);
    /**
     *根据用户id和密码查找密码id
     * @param studentId
     * @param password
     */
    Integer selectPasswordIdByStudentIdAndPassWord(PasswordDO passwordDO);
}
