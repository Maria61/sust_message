package com.fehead.sustmessage.service.impl;

import com.fehead.sustmessage.dao.MessageDOMapper;
import com.fehead.sustmessage.dataobject.MessageDO;
import com.fehead.sustmessage.service.CommentService;
import com.fehead.sustmessage.service.MessageService;
import com.fehead.sustmessage.service.UserService;
import com.fehead.sustmessage.service.model.CommentModel;
import com.fehead.sustmessage.service.model.MessageModel;
import com.fehead.sustmessage.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:11
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDOMapper messageDOMapper;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Override
    public List<MessageModel> selectAllMessages(String studentId) throws Exception {
        List<MessageModel> messageModelList = new ArrayList<>();
        List<MessageDO> messageDOList = messageDOMapper.selectAllMessages(studentId);
        if(messageDOList != null){
            BeanUtils.copyProperties(messageDOList,messageModelList);
        }else{
            return null;
        }

        /*
        UserDO userDO = userDOMapper.selectUserById(studentId);
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        */
        UserModel userModel = userService.selectUserById(studentId);
        for(MessageModel messageModel:messageModelList){
            messageModel.setUserModel(userModel);
            List<CommentModel> commentModelList =
                    commentService.selectCommentByMessageId(messageModel.getId());
            messageModel.setCommentModelList(commentModelList);
        }

        return messageModelList;
    }
}
