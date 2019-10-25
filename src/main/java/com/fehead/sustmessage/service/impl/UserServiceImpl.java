package com.fehead.sustmessage.service.impl;

import com.fehead.sustmessage.dao.CommentDOMapper;
import com.fehead.sustmessage.dao.PasswordDOMapper;
import com.fehead.sustmessage.dao.UserDOMapper;
import com.fehead.sustmessage.dataobject.CommentDO;
import com.fehead.sustmessage.dataobject.MessageDO;
import com.fehead.sustmessage.dataobject.PasswordDO;
import com.fehead.sustmessage.dataobject.UserDO;
import com.fehead.sustmessage.service.CommentService;
import com.fehead.sustmessage.service.UserService;
import com.fehead.sustmessage.service.model.CommentModel;
import com.fehead.sustmessage.service.model.MessageModel;
import com.fehead.sustmessage.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private PasswordDOMapper passwordDOMapper;

    /**
     * 通过学号查找用户
     * @param studentId
     * @return
     */
    @Override
    public UserModel selectUserById(String studentId) {

        UserDO userDO = userDOMapper.selectUserById(studentId);
        UserModel userModel = new UserModel();
        if(userDO != null){
            BeanUtils.copyProperties(userDO,userModel);
        }
        return userModel;
    }

    /**
     * 插入用户（注册）
     * @param studentId
     * @param telephone
     * @param displayName
     * @param avatar
     */
    @Override
    public void insertUser(UserModel userModel) {
        UserDO userDO = new UserDO();
        PasswordDO passwordDO = new PasswordDO();
        if(userModel != null){
            BeanUtils.copyProperties(userModel,userDO);
            BeanUtils.copyProperties(userModel,passwordDO);
        }
        userDOMapper.insertUser(userDO);
        passwordDOMapper.insertPasswprd(passwordDO);

    }

    /**
     * 插入用户密码（注册）
     * @param studentId
     * @param password
     */
    @Override
    public void insertPasswprd(String studentId, String password) {


    }

    /**
     *
     * 根据用户密码和id查找用户密码记录id
     * @param studentId
     * @param password
     * @return
     */
    @Override
    public Integer selectPasswordIdByStudentIdAndPassWord(String studentId, String password) {
        PasswordDO passwordDO = new PasswordDO();
        passwordDO.setPassword(password);
        passwordDO.setStudentId(studentId);

        Integer id = passwordDOMapper.selectPasswordIdByStudentIdAndPassWord(passwordDO);

        return id;
    }


}
