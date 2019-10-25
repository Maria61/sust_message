package com.fehead.sustmessage.controller;

import com.fehead.sustmessage.controller.vo.CommentDetailVO;
import com.fehead.sustmessage.controller.vo.CommentListVO;
import com.fehead.sustmessage.controller.vo.MessageVO;
import com.fehead.sustmessage.controller.vo.UserVO;
import com.fehead.sustmessage.dataobject.CommentDO;
import com.fehead.sustmessage.dataobject.MessageDO;
import com.fehead.sustmessage.error.BusinessException;
import com.fehead.sustmessage.error.EmBusinessError;
import com.fehead.sustmessage.response.CommonReturnType;
import com.fehead.sustmessage.service.CommentService;
import com.fehead.sustmessage.service.MessageService;
import com.fehead.sustmessage.service.UserService;
import com.fehead.sustmessage.service.model.CommentModel;
import com.fehead.sustmessage.service.model.MessageModel;
import com.fehead.sustmessage.service.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:05
 */
@RequestMapping("/api/v1.0/SUSTMessage")
@RestController
public class MessageController extends BaseController {

    public static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;


    /**
     * 查看所有留言
     * @return
     */

    @GetMapping("/message/lists")
    public CommonReturnType getAllMessages(){
        List<MessageVO> messageVOList = new ArrayList<>();
        List<MessageModel> messageModelList = new ArrayList<>();
        messageModelList = messageService.selectAllMessages();

        for(MessageModel messageModel:messageModelList){
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(messageModel,messageVO);

            UserVO userVO = new UserVO();
            UserModel userModel = messageModel.getUserModel();
            BeanUtils.copyProperties(userModel,userVO);
            messageVO.setStudent(userVO);

            List<CommentListVO> commentListVOList = new ArrayList<>();
            List<CommentModel> commentModelList = messageModel.getCommentModelList();
            if(commentModelList != null){
                for(CommentModel commentModel:commentModelList){
                    CommentListVO commentListVO = new CommentListVO();
                    BeanUtils.copyProperties(commentModel,commentListVO);
                    commentListVO.setCommentatorId(commentModel.getUser().getStudentId());
                    commentListVOList.add(commentListVO);
                }
            }
            messageVO.setCommentListVO(commentListVOList);

            messageVOList.add(messageVO);
        }
        logger.info("Success:selectAllMessages");
        return CommonReturnType.create(messageVOList);
    }

    /**
     * 通过用户id查找用户发布的所有留言
     * @param studentId
     * @return
     * @throws BusinessException
     */
    @GetMapping("/user/{studentId}/myMessages")
    public CommonReturnType getMessages(@PathVariable("studentId") String studentId) throws BusinessException {
        logger.info("PARAM: studentId "+studentId);

        List<MessageVO> messageVOList = new ArrayList<>();
        List<MessageModel> messageModelList = new ArrayList<>();

        messageModelList = messageService.selectMessages(studentId);

        if(userService.selectUserById(studentId).getStudentId() == null){
            logger.info("异常码："+EmBusinessError.USER_NOT_EXIST);
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        if(messageModelList == null){
            logger.info("异常码："+EmBusinessError.NO_MESSAGES);
            throw new BusinessException(EmBusinessError.NO_MESSAGES);
        }

        for(MessageModel messageModel:messageModelList){
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(messageModel,messageVO);

            UserVO userVO = new UserVO();
            UserModel userModel = messageModel.getUserModel();
            BeanUtils.copyProperties(userModel,userVO);
            messageVO.setStudent(userVO);

            List<CommentListVO> commentListVOList = new ArrayList<>();
            List<CommentModel> commentModelList = messageModel.getCommentModelList();
            if(commentModelList != null){
                for(CommentModel commentModel:commentModelList){
                    CommentListVO commentListVO = new CommentListVO();
                    BeanUtils.copyProperties(commentModel,commentListVO);
                    commentListVO.setCommentatorId(commentModel.getUser().getStudentId());
                    commentListVOList.add(commentListVO);
                }
            }
            messageVO.setCommentListVO(commentListVOList);

            messageVOList.add(messageVO);
        }
        logger.info("Success:selectMessages");
        return CommonReturnType.create(messageVOList);
    }

    /**
     * 根据留言分类查看留言
     * @param messageTypeId
     * @return
     */
    @GetMapping("/message/lists/{messageTypeId}")
    public CommonReturnType getMessageByTypeId(@PathVariable("messageTypeId") Integer messageTypeId){
        logger.info("PARAM:messageTypeId"+messageTypeId);

        List<MessageVO> messageVOList = new ArrayList<>();
        List<MessageModel> messageModelList = messageService.selectMessageByMessageTypeId(messageTypeId);

        for(MessageModel messageModel:messageModelList){
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(messageModel,messageVO);

            UserVO userVO = new UserVO();
            UserModel userModel = messageModel.getUserModel();
            BeanUtils.copyProperties(userModel,userVO);
            messageVO.setStudent(userVO);

            List<CommentListVO> commentListVOList = new ArrayList<>();
            List<CommentModel> commentModelList = messageModel.getCommentModelList();
            if(commentModelList != null){
                for(CommentModel commentModel:commentModelList){
                    CommentListVO commentListVO = new CommentListVO();
                    BeanUtils.copyProperties(commentModel,commentListVO);
                    commentListVO.setCommentatorId(commentModel.getUser().getStudentId());
                    commentListVOList.add(commentListVO);
                }
            }
            messageVO.setCommentListVO(commentListVOList);

            messageVOList.add(messageVO);
        }
        logger.info("Success:selectMessageByMessageTypeId");
        return CommonReturnType.create(messageVOList);
    }

    /**
     * 根据留言id查找留言
     * @param messageId
     * @return
     */
    @GetMapping("/message/lists/{messageId}")
    public CommonReturnType getMessageById(@PathVariable("messageId") Integer messageId) throws BusinessException {
        logger.info("PARAM:messageId"+messageId);

        MessageModel messageModel = messageService.selectMessageById(messageId);
        if(messageModel.getUserModel().getStudentId() == null){
            logger.info("异常码："+EmBusinessError.NO_MESSAGE);
            throw new BusinessException(EmBusinessError.NO_MESSAGE);
        }
        MessageVO messageVO = new MessageVO();
        if(messageModel != null){
            BeanUtils.copyProperties(messageModel,messageVO);
        }

        UserVO userVO = new UserVO();
        UserModel userModel = messageModel.getUserModel();
        BeanUtils.copyProperties(userModel,userVO);
        messageVO.setStudent(userVO);

        List<CommentListVO> commentListVOList = new ArrayList<>();
        List<CommentModel> commentModelList = messageModel.getCommentModelList();
        if(commentModelList != null){
            for(CommentModel commentModel:commentModelList){
                CommentListVO commentListVO = new CommentListVO();
                BeanUtils.copyProperties(commentModel,commentListVO);
                commentListVO.setCommentatorId(commentModel.getUser().getStudentId());
                commentListVOList.add(commentListVO);
            }
        }
        messageVO.setCommentListVO(commentListVOList);

        logger.info("Success:selectMessageById");
        return CommonReturnType.create(messageVO);
    }

    /**
     * 发布留言
     * @param studentId
     * @param messageContent
     * @param photo
     * @param isAnonymous
     * @param messageTypeId
     * @return
     */
    @PostMapping("/user/{studentId}/publish")
    public CommonReturnType publish(@PathVariable("studentId") String studentId,
                                    @RequestParam(value = "messageContent") String messageContent,
                                    @RequestParam(value = "photo" ) String  photo,
                                    @RequestParam(value = "isAnonymous") Boolean isAnonymous,
                                    @RequestParam(value = "messageTypeId") Integer messageTypeId
                                    ) throws BusinessException {
        logger.info("PARAM: studentId "+studentId);
        logger.info("PARAM: messageContent "+messageContent);
        logger.info("PARAM: photo "+photo);
        logger.info("PARAM: isAnonymous "+isAnonymous);
        logger.info("PARAM: messageTypeId "+messageTypeId);

        MessageModel messageModel = new MessageModel();
        messageModel.setMessageContent(messageContent);
        messageModel.setPhoto(photo);
        messageModel.setMessageDate(new Date());
        messageModel.setMessageTypeId(messageTypeId);
        messageModel.setAnonymous(isAnonymous);
        UserModel userModel = userService.selectUserById(studentId);
        if(userModel.getStudentId() == null){
            logger.info("异常码："+EmBusinessError.USER_NOT_EXIST);
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        messageModel.setUserModel(userModel);

        logger.info("Success:insertMessage");
        messageService.insertMessage(messageModel);

        return CommonReturnType.create("发布成功");
    }

    /**
     * 删除留言
     * @param studentId
     * @param messageId
     * @return
     */
    @DeleteMapping("/user/{studentId}/myMessages/{messageId}")
    public CommonReturnType delete(@PathVariable("studentId") String studentId,
                                   @PathVariable("messageId") Integer messageId){
        logger.info("PARAM: studentId "+studentId);
        logger.info("PARAM: messageId "+messageId);

        messageService.deleteMessage(messageId);
        logger.info("Success:deleteMessage");
        return CommonReturnType.create("删除成功");
    }

    /**
     * 修改留言
     * @param studentId
     * @param messageId
     * @param messageContent
     * @param photo
     * @param isAnonymous
     * @param messageTypeId
     * @return
     */
    @PutMapping("/user/{studentId}/myMessages/{messageId}")
    public CommonReturnType update(@PathVariable("studentId") String studentId,
                       @PathVariable("messageId") Integer messageId,
                       @RequestParam("messageContent") String messageContent,
                       @RequestParam("photo") String photo,
                       @RequestParam("isAnonymous") Boolean isAnonymous,
                       @RequestParam("messageTypeId") Integer messageTypeId) throws BusinessException {
        logger.info("PARAM: studentId "+studentId);
        logger.info("PARAM: messageId "+messageId);
        logger.info("PARAM: messageContent "+messageContent);
        logger.info("PARAM: photo "+photo);
        logger.info("PARAM: isAnonymous "+isAnonymous);
        logger.info("PARAM: messageTypeId "+messageTypeId);


        if(messageService.selectMessageById(messageId).getUserModel().getStudentId() == null){
            logger.info("异常码："+EmBusinessError.NO_MESSAGE);
            throw new BusinessException(EmBusinessError.NO_MESSAGE);
        }

        MessageModel messageModel = new MessageModel();
        messageModel.setId(messageId);
        messageModel.setUserModel(userService.selectUserById(studentId));
        messageModel.setAnonymous(isAnonymous);
        messageModel.setMessageContent(messageContent);
        messageModel.setMessageDate(new Date());
        messageModel.setMessageTypeId(messageTypeId);
        messageModel.setPhoto(photo);

        messageService.updateMessage(messageModel);
        logger.info("Success:updateMessage");
        return CommonReturnType.create("修改成功");
    }



    /**
     * 查看留言评论详情
     * @param messageId
     * @param commentId
     * @return
     */
    @GetMapping("/message/{messageId}/comments/{commentId}")
    public CommonReturnType getCommentByCommentId(@PathVariable("messageId") Integer messageId,
                                                  @PathVariable("commentId") Integer commentId){
        logger.info("PARAM:messageId"+messageId);
        logger.info("PARAM:commentId"+commentId);

        CommentDetailVO commentDetailVO = new CommentDetailVO();
        CommentModel commentModel = commentService.selectCommentByCommentId(commentId);
        if(commentModel != null){
            BeanUtils.copyProperties(commentModel,commentDetailVO);
        }
        UserVO userVO = new UserVO();
        UserModel userModel = commentModel.getUser();
        BeanUtils.copyProperties(userModel,userVO);
        commentDetailVO.setCommentator(userVO);
        logger.info("Success:selectCommentByCommentId");
        return CommonReturnType.create(commentDetailVO);
    }

    /**
     * 发布评论
     * @param studentId
     * @param messageId
     * @param commentContent
     * @param commentPhoto
     * @return
     */
    @PutMapping("/user/{studentId}/{messageId}/comment")
    public CommonReturnType comment(@PathVariable("studentId") String studentId,
                                    @PathVariable("messageId") Integer messageId,
                                    @RequestParam("commentContent") String commentContent,
                                    @RequestParam("commentPhoto") String commentPhoto) throws BusinessException {
        logger.info("PARAM:studentId"+studentId);
        logger.info("PARAM:messageId"+messageId);
        logger.info("PARAM:commentContent"+commentContent);
        logger.info("PARAM:commentPhoto"+commentPhoto);

        if(messageService.selectMessageById(messageId).getUserModel().getStudentId() == null){
            logger.info("异常码："+EmBusinessError.NO_MESSAGE);
            throw new BusinessException(EmBusinessError.NO_MESSAGE);
        }

        CommentModel commentModel = new CommentModel();
        commentModel.setMessageId(messageId);
        commentModel.setCommentContent(commentContent);
        commentModel.setCommentPhoto(commentPhoto);
        commentModel.setCommentDate(new Date());
        UserModel userModel = userService.selectUserById(studentId);
        commentModel.setUser(userModel);
        commentService.insertComment(commentModel);
        logger.info("Success:insertComment");
        return CommonReturnType.create("评论成功！");
    }

    /**
     * 删除评论
     * @param studentId
     * @param messageId
     * @param commentId
     * @return
     */
    @DeleteMapping("/user/{studentId}/{messageId}/{commentId}")
    public CommonReturnType deleteComment(@PathVariable("studentId") String studentId,
                                          @PathVariable("messageId") Integer messageId,
                                          @PathVariable("commentId")Integer commentId){

        logger.info("PARAM:studentId"+studentId);
        logger.info("PARAM:messageId"+messageId);
        logger.info("PARAM:commentId"+commentId);

        commentService.deleteComment(commentId);
        logger.info("Success:deleteComment");
        return CommonReturnType.create("删除成功");

    }

    /**
     * 给留言点赞
     * @param studentId
     * @param messageId
     * @return
     */
    @PutMapping("/user/{studentId}/{messageId}/like")
    public CommonReturnType like(@PathVariable("studentId") String studentId,
                                 @PathVariable("messageId") Integer messageId) throws BusinessException {
        logger.info("PARAM:studentId"+studentId);
        logger.info("PARAM:messageId"+messageId);

        if(messageService.selectMessageById(messageId).getUserModel().getStudentId() == null){
            logger.info("异常码："+EmBusinessError.NO_MESSAGE);
            throw new BusinessException(EmBusinessError.NO_MESSAGE);
        }

        messageService.like(messageId);
        logger.info("Success:like");
        return CommonReturnType.create("点赞成功！");
    }

}
