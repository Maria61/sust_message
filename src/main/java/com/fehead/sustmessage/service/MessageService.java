package com.fehead.sustmessage.service;

import com.fehead.sustmessage.service.model.MessageModel;

import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:07
 */
public interface MessageService {
    /**
     * 查找用户发布的所有留言
     * @param studentId
     * @return
     */
    List<MessageModel> selectAllMessages(String studentId);

    /**
     * 发布留言
     * @param messageModel
     */
    void insertMessage(MessageModel messageModel);

    /**
     * 删除留言
     */
    void delectMessage(Integer messageId);
}
