package com.fehead.sustmessage.dao;

import com.fehead.sustmessage.dataobject.CommentDO;

import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:21
 */
public interface CommentDOMapper {
    /**
     * 通过留言id查找评论
     * @param messageId
     * @return
     */
    List<CommentDO> selectCommentByMessageId(Integer messageId);
}
