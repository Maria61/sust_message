package com.fehead.sustmessage.controller.vo;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:00
 */
public class CommentListVO {
    private Integer id;
    private Integer messageId;
    private String commenttatorId;
    private String commentContent;
    private String commentPhoto;

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

    public String getCommenttatorId() {
        return commenttatorId;
    }

    public void setCommenttatorId(String commenttatorId) {
        this.commenttatorId = commenttatorId;
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
}
