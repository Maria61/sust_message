package com.fehead.sustmessage.service.impl;

import com.fehead.sustmessage.dao.CommentDOMapper;
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

    @Autowired
    CommentDOMapper commentDOMapper;

    /**
     * 通过用户Id查看所有留言
     * @param studentId
     * @return
     */
    @Override
    public List<MessageModel> selectMessages(String studentId) {
        List<MessageModel> messageModelList = new ArrayList<>();
        List<MessageDO> messageDOList = messageDOMapper.selectMessages(studentId);
        UserModel userModel = userService.selectUserById(studentId);

        for(MessageDO messageDO:messageDOList){

            MessageModel  messageModel = new MessageModel();
            BeanUtils.copyProperties(messageDO,messageModel);

            messageModel.setUserModel(userModel);

            List<CommentModel> commentModelList = commentService.selectCommentByMessageId(messageDO.getId());
            messageModel.setCommentModelList(commentModelList);

            messageModelList.add(messageModel);

        }

        return messageModelList;
    }

    /**
     * 查找所有留言
     * @return
     */
    @Override
    public List<MessageModel> selectAllMessages() {
        List<MessageModel> messageModelList = new ArrayList<>();
        List<MessageDO> messageDOList = messageDOMapper.selectAllMessages();


        for(MessageDO messageDO:messageDOList){
            MessageModel  messageModel = new MessageModel();
            BeanUtils.copyProperties(messageDO,messageModel);

            UserModel userModel = userService.selectUserById(messageDO.getStudentId());
            messageModel.setUserModel(userModel);

            List<CommentModel> commentModelList = commentService.selectCommentByMessageId(messageDO.getId());
            messageModel.setCommentModelList(commentModelList);

            messageModelList.add(messageModel);

        }

        return messageModelList;
    }

    /**
     * 按分类查找留言
     * @param messageTypeId
     * @return
     */
    @Override
    public List<MessageModel> selectMessageByMessageTypeId(Integer messageTypeId) {
        List<MessageModel> messageModelList = new ArrayList<>();
        List<MessageDO> messageDOList = messageDOMapper.selectMessageByMessageTypeId(messageTypeId);


        for(MessageDO messageDO:messageDOList){
            MessageModel  messageModel = new MessageModel();
            BeanUtils.copyProperties(messageDO,messageModel);

            UserModel userModel = userService.selectUserById(messageDO.getStudentId());
            messageModel.setUserModel(userModel);

            List<CommentModel> commentModelList = commentService.selectCommentByMessageId(messageDO.getId());
            messageModel.setCommentModelList(commentModelList);

            messageModelList.add(messageModel);

        }

        return messageModelList;
    }

    /**
     * 根据留言id查找留言
     * @param id
     * @return
     */
    @Override
    public MessageModel selectMessageById(Integer id) {
        MessageModel messageModel = new MessageModel();
        MessageDO messageDO = messageDOMapper.selectMessageById(id);
        if(messageDO != null){
            BeanUtils.copyProperties(messageDO,messageModel);

            UserModel userModel = userService.selectUserById(messageDO.getStudentId());
            messageModel.setUserModel(userModel);

            List<CommentModel> commentModelList = commentService.selectCommentByMessageId(messageDO.getId());
            messageModel.setCommentModelList(commentModelList);
        }
        return messageModel;

    }

    /**
     * 发布留言
     * @param messageModel
     */
    @Override
    public void insertMessage(MessageModel messageModel) {
        MessageDO messageDO = new MessageDO();
        if(messageModel != null){
            BeanUtils.copyProperties(messageModel,messageDO);
        }
        messageDO.setStudentId(messageModel.getUserModel().getStudentId());
        messageDOMapper.publish(messageDO);
    }

    /**
     * 删除留言
     * @param messageId
     */
    @Override
    public void deleteMessage(Integer messageId) {
        messageDOMapper.delete(messageId);
        commentDOMapper.deleteCommentByMessageId(messageId);
    }

    /**
     *修改留言
     * @param messageModel
     */
    @Override
    public void updateMessage(MessageModel messageModel) {
        MessageDO messageDO = new MessageDO();
        if(messageModel != null){
            BeanUtils.copyProperties(messageModel,messageDO);
        }
        messageDO.setStudentId(messageModel.getUserModel().getStudentId());
        messageDOMapper.update(messageDO);
    }

    /**
     * 给留言点赞
     * @param messageId
     */
    @Override
    public void like(Integer messageId) {
        messageDOMapper.like(messageId);
    }

}
