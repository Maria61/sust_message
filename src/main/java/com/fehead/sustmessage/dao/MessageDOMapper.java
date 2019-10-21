package com.fehead.sustmessage.dao;

import com.fehead.sustmessage.dataobject.MessageDO;

import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:20
 */
public interface MessageDOMapper {

    /**
     * 查找用户发布的所有留言
     * @param studentId
     * @return
     */
    List<MessageDO> selectAllMessages(String studentId);

    /**
     *发布留言
     * @param messageContent
     * @param photo
     * @param isAnonymous
     * @param messageTypeId
     */
    void publish(String messageContent,String photo,Boolean isAnonymous,Integer messageTypeId);
}
