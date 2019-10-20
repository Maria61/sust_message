package com.fehead.sustmessage.controller;

import com.fehead.sustmessage.controller.vo.UserVO;
import com.fehead.sustmessage.error.BusinessException;
import com.fehead.sustmessage.error.EmBusinessError;
import com.fehead.sustmessage.response.CommonReturnType;
import com.fehead.sustmessage.service.UserService;
import com.fehead.sustmessage.service.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:05
 */
@RequestMapping("/api/{version}/SUSTMessage")
@RestController
public class UserController extends BaseController{

    public static Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/user/{studentId}/info")
    public CommonReturnType getUserById(@PathVariable("studentId") String studentId) throws BusinessException {
        logger.info("PARAM: studentId "+studentId);
        UserVO userVO = new UserVO();
        UserModel userModel = userService.selectUserById(studentId);
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        BeanUtils.copyProperties(userModel,userVO);
        return CommonReturnType.create(userVO);
    }




}
