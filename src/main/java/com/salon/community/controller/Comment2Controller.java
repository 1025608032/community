package com.salon.community.controller;

import com.salon.community.dto.Comment2CreateDTO;
import com.salon.community.dto.Comment2DTO;
import com.salon.community.dto.ResultDTO;
import com.salon.community.enums.CommentTypeEnum;
import com.salon.community.exception.CustomizeErrorCode;
import com.salon.community.model.Comment2;
import com.salon.community.model.User;
import com.salon.community.service.Comment2Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Comment2Controller {
    @Autowired
    private Comment2Service comment2Service;

    @ResponseBody
    @RequestMapping(value = "/comment2", method = RequestMethod.POST)
    public Object post2(@RequestBody Comment2CreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment2 comment = new Comment2();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment2Service.insert(comment, user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment2/{id}", method = RequestMethod.GET)
    public ResultDTO<List<Comment2DTO>> comments2(@PathVariable(name = "id") Long id) {
        List<Comment2DTO> commentDTOS = comment2Service.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
