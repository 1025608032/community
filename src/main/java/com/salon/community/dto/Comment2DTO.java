package com.salon.community.dto;

import com.salon.community.model.User;
import lombok.Data;

@Data
public class Comment2DTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}