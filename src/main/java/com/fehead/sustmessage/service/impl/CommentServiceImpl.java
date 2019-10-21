package com.fehead.sustmessage.service.impl;

import com.fehead.sustmessage.dao.CommentDOMapper;
import com.fehead.sustmessage.dataobject.CommentDO;
import com.fehead.sustmessage.service.CommentService;
import com.fehead.sustmessage.service.UserService;
import com.fehead.sustmessage.service.model.CommentModel;
import com.fehead.sustmessage.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:11
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDOMapper commentDOMapper;

    @Autowired
    private UserService userService;


    @Override
    public List<CommentModel> selectCommentByMessageId(Integer messageId){
        List<CommentDO> commentDOList = commentDOMapper.selectCommentByMessageId(messageId);
        List<CommentModel> commentModelList = new ArrayList<>();

        for(CommentDO commentDO:commentDOList){
            CommentModel commentModel = new CommentModel();
            BeanUtils.copyProperties(commentDO,commentModel);

            UserModel userModel = userService.selectUserById(commentDO.getCommentStudentId());
            commentModel.setUser(userModel);

            commentModelList.add(commentModel);
        }
        return commentModelList;
    }
}
