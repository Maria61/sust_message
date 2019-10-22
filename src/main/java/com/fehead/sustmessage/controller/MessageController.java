package com.fehead.sustmessage.controller;

import com.fehead.sustmessage.controller.vo.CommentListVO;
import com.fehead.sustmessage.controller.vo.MessageVO;
import com.fehead.sustmessage.controller.vo.UserVO;
import com.fehead.sustmessage.error.BusinessException;
import com.fehead.sustmessage.error.EmBusinessError;
import com.fehead.sustmessage.response.CommonReturnType;
import com.fehead.sustmessage.service.MessageService;
import com.fehead.sustmessage.service.UserService;
import com.fehead.sustmessage.service.model.CommentModel;
import com.fehead.sustmessage.service.model.MessageModel;
import com.fehead.sustmessage.service.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class MessageController {

    public static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    /**
     * 通过用户id查找用户发布的所有留言
     * @param studentId
     * @return
     * @throws BusinessException
     */
    @GetMapping("/user/{studentId}/myMessages")
    public CommonReturnType getAllMessages(@PathVariable("studentId") String studentId) throws BusinessException {
        logger.info("PARAM: studentId "+studentId);
        List<MessageVO> messageVOList = new ArrayList<>();
        List<MessageModel> messageModelList = new ArrayList<>();
        messageModelList = messageService.selectAllMessages(studentId);

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
        return CommonReturnType.create(messageVOList);
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
                                    ){
        MessageModel messageModel = new MessageModel();
        messageModel.setMessageContent(messageContent);
        messageModel.setPhoto(photo);
        messageModel.setMessageDate(new Date());
        messageModel.setMessageTypeId(messageTypeId);
        messageModel.setAnonymous(isAnonymous);
        messageModel.setUserModel(userService.selectUserById(studentId));

        messageService.insertMessage(messageModel);

        return CommonReturnType.create("发布成功");
    }

    @DeleteMapping("/user/{studentId}/myMessages/{messageId}")
    public CommonReturnType delect(@PathVariable("studentId") String studentId,
                                   @PathVariable("messageId") Integer messageId){

        messageService.delectMessage(messageId);
        return CommonReturnType.create("删除成功");
    }
}
