package com.salon.community.dto;

import lombok.Data;

@Data
public class Comment2CreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}