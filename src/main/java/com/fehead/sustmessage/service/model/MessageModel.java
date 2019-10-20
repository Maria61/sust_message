package com.fehead.sustmessage.service.model;

import java.util.Date;
import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:02
 */
public class MessageModel {
    private Integer id;
    private Date messageDate;
    private String messageContent;
    private String photo;
    private Boolean isAnonymous;
    private UserModel userModel;
    private Integer messageTypeId;
    private Integer like;
    private CommentModel commentModel;

    public CommentModel getCommentModel() {
        return commentModel;
    }

    public void setCommentModel(CommentModel commentModel) {
        this.commentModel = commentModel;
    }

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

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }


    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
