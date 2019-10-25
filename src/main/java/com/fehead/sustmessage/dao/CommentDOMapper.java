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
    /**
     * 通过评论id查找评论
     * @param commentId
     * @return
     */
    CommentDO selectCommentByCommentId(Integer commentId);

    /**
     * 发布评论
     * @param commentDO
     */
    void insertComment(CommentDO commentDO);

    /**
     * 删除评论
     */
    void deleteComment(Integer commentId);

    /**
     * 按留言id删除相应评论
     */
    void deleteCommentByMessageId(Integer messageId);
}
