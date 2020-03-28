package com.salon.community.mapper;

import com.salon.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}