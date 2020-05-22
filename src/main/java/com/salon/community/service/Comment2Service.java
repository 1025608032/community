package com.salon.community.service;

import com.salon.community.dto.Comment2DTO;
import com.salon.community.enums.CommentTypeEnum;
import com.salon.community.enums.NotificationStatusEnum;
import com.salon.community.enums.NotificationTypeEnum;
import com.salon.community.exception.CustomizeErrorCode;
import com.salon.community.exception.CustomizeException;
import com.salon.community.mapper.*;
import com.salon.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Comment2Service {

    @Autowired
    private Comment2Mapper comment2Mapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleExtMapper articleExtMapper;

    @Autowired
    private Comment2ExtMapper comment2ExtMapper;

    @Autowired
    private NotificationMapper notificationMapper;


    @Transactional
    public void insert(Comment2 comment2, User commentator) {
        if (comment2.getParentId() == null || comment2.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment2.getType() == null || !CommentTypeEnum.isExist(comment2.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment2.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment2 dbComment2 = comment2Mapper.selectByPrimaryKey(comment2.getParentId());
            if (dbComment2 == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            // 回复问题
            Article article = articleMapper.selectByPrimaryKey(dbComment2.getParentId());
            if (article == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment2Mapper.insert(comment2);
            // 增加评论数
            dbComment2.setCommentCount(1);
            comment2ExtMapper.incCommentCount(dbComment2);
            //Comment2 parentComment2 = new Comment2();
            //parentComment2.setId(comment2.getParentId());
            //parentComment2.setComment2Count(1);
            //comment2ExtMapper.incComment2Count(parentComment2);
            // 创建通知
            createNotify(comment2, dbComment2.getCommentator(), commentator.getName(), article.getTitle(), NotificationTypeEnum.REPLY_COMMENT, article.getId());
        } else {
            // 回复问题
            Article article = articleMapper.selectByPrimaryKey(comment2.getParentId());
            if (article == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment2.setCommentCount(0);
            comment2Mapper.insert(comment2);
            article.setCommentCount(1);
            articleExtMapper.incCommentCount(article);
            // 创建通知
            createNotify(comment2, article.getCreator(), commentator.getName(), article.getTitle(), NotificationTypeEnum.REPLY_QUESTION, article.getId());
        }
    }

    private void createNotify(Comment2 comment2, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
        if(receiver==comment2.getCommentator()){
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment2.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<Comment2DTO> listByTargetId(Long id, CommentTypeEnum type) {
        Comment2Example comment2Example = new Comment2Example();
        comment2Example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        comment2Example.setOrderByClause("gmt_create desc");
        List<Comment2> comment2s = comment2Mapper.selectByExample(comment2Example);

        if (comment2s.size() == 0) {
            return new ArrayList<>();
        }
        // 获取去重的评论人
        Set<Long> commentators = comment2s.stream().map(comment2 -> comment2.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);


        // 获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));


        // 转换 comment2 为 comment2DTO
        List<Comment2DTO> comment2DTOS = comment2s.stream().map(comment2 -> {
            Comment2DTO comment2DTO = new Comment2DTO();
            BeanUtils.copyProperties(comment2, comment2DTO);
            comment2DTO.setUser(userMap.get(comment2.getCommentator()));
            return comment2DTO;
        }).collect(Collectors.toList());

        return comment2DTOS;
    }
}