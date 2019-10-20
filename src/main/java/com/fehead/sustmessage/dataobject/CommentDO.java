package com.fehead.sustmessage.dataobject;

import java.util.Date;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 13:53
 */
public class CommentDO {
    private Integer id;
    private Integer messageId;
    private String commentContent;
    private String commentPhoto;
    private Date commentDate;
    private String commentStudentId;

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

    public String getCommentStudentId() {
        return commentStudentId;
    }

    public void setCommentStudentId(String commentStudentId) {
        this.commentStudentId = commentStudentId;
    }
}
