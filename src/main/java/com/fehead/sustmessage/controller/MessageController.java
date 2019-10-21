package com.fehead.sustmessage.controller;

import com.fehead.sustmessage.controller.vo.CommentListVO;
import com.fehead.sustmessage.controller.vo.MessageVO;
import com.fehead.sustmessage.controller.vo.UserVO;
import com.fehead.sustmessage.error.BusinessException;
import com.fehead.sustmessage.error.EmBusinessError;
import com.fehead.sustmessage.response.CommonReturnType;
import com.fehead.sustmessage.service.MessageService;
import com.fehead.sustmessage.service.model.CommentModel;
import com.fehead.sustmessage.service.model.MessageModel;
import com.fehead.sustmessage.service.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        try {
            messageModelList = messageService.selectAllMessages(studentId);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        if(messageModelList.size() == 0){
            throw new BusinessException(EmBusinessError.NO_MESSAGES);
        }

        for(MessageModel messageModel:messageModelList){
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(messageModel,messageVO);

            UserVO userVO = new UserVO();
            UserModel userModel = messageModel.getUserModel();
            BeanUtils.copyProperties(userModel,userVO);

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

            messageVOList.add(messageVO);
        }
        return CommonReturnType.create(messageVOList);
    }
}
