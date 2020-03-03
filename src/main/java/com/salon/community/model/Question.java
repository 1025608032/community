package com.salon.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private Long gmtCreate;
    private Long gmtModified;
    private String title;
    private String tag;
    private String description;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
}
