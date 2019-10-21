package com.fehead.sustmessage.controller.vo;

import java.util.Date;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 13:59
 */
public class MessageVO {
    private Integer id;
    private Date messageDate;
    private String messageContent;
    private String photo;
    private UserVO student;
    private Integer messageTypeId;
    private Integer likes;
    private CommentListVO commentListVO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserVO getStudent() {
        return student;
    }

    public void setStudent(UserVO student) {
        this.student = student;
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public Integer getlikes() {
        return likes;
    }

    public void setlikes(Integer likes) {
        this.likes = likes;
    }

    public CommentListVO getCommentListVO() {
        return commentListVO;
    }

    public void setCommentListVO(CommentListVO commentListVO) {
        this.commentListVO = commentListVO;
    }
}
