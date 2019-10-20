package com.fehead.sustmessage.controller.vo;

import java.util.Date;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:00
 */
public class CommentDetailVO {
    private Integer id;
    private Integer messageId;
    private UserVO commentator;
    private String commentContent;
    private String commentPhoto;
    private Date commentDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public UserVO getCommentator() {
        return commentator;
    }

    public void setCommentator(UserVO commentator) {
        this.commentator = commentator;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentPhoto() {
        return commentPhoto;
    }

    public void setCommentPhoto(String commentPhoto) {
        this.commentPhoto = commentPhoto;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
