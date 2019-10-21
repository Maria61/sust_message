package com.fehead.sustmessage.service;

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
}
