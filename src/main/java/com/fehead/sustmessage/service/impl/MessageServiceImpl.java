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
    public List<MessageModel> selectAllMessages(String studentId) {
        List<MessageModel> messageModelList = new ArrayList<>();
        List<MessageDO> messageDOList = messageDOMapper.selectAllMessages(studentId);
        UserModel userModel = userService.selectUserById(studentId);

        for(MessageDO messageDO:messageDOList){

            MessageModel  messageModel = new MessageModel();
            BeanUtils.copyProperties(messageDO,messageModel);

            messageModel.setUserModel(userModel);

            List<CommentModel> commentModelList = commentService.selectCommentByMessageId(messageDO.getId());
            messageModel.setCommentModelList(commentModelList);

            messageModelList.add(messageModel);

        }

//        UserModel userModel = userService.selectUserById(studentId);
//        for(MessageModel messageModel:messageModelList){
//            messageModel.setUserModel(userModel);
//            List<CommentModel> commentModelList =
//                    commentService.selectCommentByMessageId(messageModel.getId());
//            messageModel.setCommentModelList(commentModelList);
//        }

        return messageModelList;
    }
}
