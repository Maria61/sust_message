package com.fehead.sustmessage.service.impl;

import com.fehead.sustmessage.dao.UserDOMapper;
import com.fehead.sustmessage.dataobject.UserDO;
import com.fehead.sustmessage.service.UserService;
import com.fehead.sustmessage.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public UserModel selectUserById(String studentId) {
        UserDO userDO = userDOMapper.selectUserById(studentId);
        UserModel userModel = new UserModel();
        if(userDO != null){
            BeanUtils.copyProperties(userDO,userModel);
        }
        return userModel;
    }
}
