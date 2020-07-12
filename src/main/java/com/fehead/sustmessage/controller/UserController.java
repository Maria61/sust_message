package com.fehead.sustmessage.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fehead.sustmessage.controller.vo.CommentDetailVO;
import com.fehead.sustmessage.controller.vo.CommentListVO;
import com.fehead.sustmessage.controller.vo.MessageVO;
import com.fehead.sustmessage.controller.vo.UserVO;
import com.fehead.sustmessage.error.BusinessException;
import com.fehead.sustmessage.error.EmBusinessError;
import com.fehead.sustmessage.response.CommonReturnType;
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
import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:05
 */
@RequestMapping("/api/v1.0/SUSTMessage")
@RestController
public class UserController extends BaseController{

    public static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 通过id查找用户
     * @param studentId
     * @return
     * @throws BusinessException
     */
    @GetMapping("/user/{studentId}/info")
    public CommonReturnType getUserById(@PathVariable("studentId") String studentId) throws BusinessException {
        logger.info("PARAM: studentId "+studentId);
        UserVO userVO = new UserVO();
        UserModel userModel = new UserModel();
        userModel = userService.selectUserById(studentId);
        if(userModel.getStudentId() == null){
            logger.info("异常码："+EmBusinessError.USER_NOT_EXIST);
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST,"用户不存在");
        }
        BeanUtils.copyProperties(userModel,userVO);
        logger.info("Success:selectUserById");
        return CommonReturnType.create(userVO);
    }

    /**
     * 用户登录
     * @param studentId
     * @param password
     * @return
     * @throws BusinessException
     */
    @PutMapping("/user/login/{studentId}/{password}")
    public CommonReturnType login(@PathVariable("studentId") String studentId,
                                  @PathVariable("password") String password) throws BusinessException {
        logger.info("PARAM:studentId "+studentId);
        logger.info("PARAM:password "+password);
        Integer id = userService.selectPasswordIdByStudentIdAndPassWord(studentId,password);

        if(id == null){
            logger.info("异常码："+EmBusinessError.USER_LOGIN_FAIL);
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        logger.info("Success:selectPasswordIdByStudentIdAndPassWord");
        return CommonReturnType.create("登录成功");


    }

    /**
     * 用户注册
     * @param studentId
     * @param telephone
     * @param displayName
     * @param avatar
     * @param password
     * @return
     * @throws BusinessException
     */
    @PostMapping("/user/register")
    public CommonReturnType register(@RequestParam("studentId") String studentId,
                                     @RequestParam("telephone") String telephone,
                                     @RequestParam("displayName") String displayName,
                                     @RequestParam("avatar") String avatar,
                                     @RequestParam("password") String password) throws BusinessException {
        logger.info("PARAM:studentId "+studentId);
        logger.info("PARAM:telephone "+telephone);
        logger.info("PARAM:displayName "+displayName);
        logger.info("PARAM:avatar "+avatar);
        logger.info("PARAM:password "+password);

        if(userService.selectUserById(studentId).getStudentId() != null ){
            logger.info("异常码："+EmBusinessError.USER_EXIST);
            throw new BusinessException(EmBusinessError.USER_EXIST);
        }

        UserModel userModel = new UserModel();
        userModel.setAvatar(avatar);
        userModel.setDisplayName(displayName);
        userModel.setStudentId(studentId);
        userModel.setTelephone(telephone);
        userModel.setPassword(password);

        userService.insertUser(userModel);
        logger.info("Success:insertUser");
        return CommonReturnType.create("注册成功");
    }
    

}
