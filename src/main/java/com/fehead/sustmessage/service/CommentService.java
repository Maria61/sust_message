package com.fehead.sustmessage.service;

import com.fehead.sustmessage.dataobject.CommentDO;
import com.fehead.sustmessage.service.model.CommentModel;

import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:08
 */
public interface CommentService {
    /**
     * 通过留言Id查找评论
     * @param messageId
     * @return
     */
    List<CommentModel>  selectCommentByMessageId(Integer messageId);

    /**
     * 通过评论id查找评论
     * @param commentId
     * @return
     */
    CommentModel selectCommentByCommentId(Integer commentId);

    /**
     * 发布评论
     * @param messageId
     */
    void insertComment(CommentModel commentModel);

    /**
     * 删除评论
     */
    void deleteComment(Integer commentId);


}
