package com.salon.community.model;

public class Comment2 {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.ID
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.PARENT_ID
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.TYPE
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.COMMENTATOR
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Long commentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.CONTENT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.GMT_CREATE
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.GMT_MODIFIED
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.LIKE_COUNT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Long likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT2.COMMENT_COUNT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    private Integer commentCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.ID
     *
     * @return the value of COMMENT2.ID
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.ID
     *
     * @param id the value for COMMENT2.ID
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.PARENT_ID
     *
     * @return the value of COMMENT2.PARENT_ID
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.PARENT_ID
     *
     * @param parentId the value for COMMENT2.PARENT_ID
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.TYPE
     *
     * @return the value of COMMENT2.TYPE
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.TYPE
     *
     * @param type the value for COMMENT2.TYPE
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.COMMENTATOR
     *
     * @return the value of COMMENT2.COMMENTATOR
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Long getCommentator() {
        return commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.COMMENTATOR
     *
     * @param commentator the value for COMMENT2.COMMENTATOR
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setCommentator(Long commentator) {
        this.commentator = commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.CONTENT
     *
     * @return the value of COMMENT2.CONTENT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.CONTENT
     *
     * @param content the value for COMMENT2.CONTENT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.GMT_CREATE
     *
     * @return the value of COMMENT2.GMT_CREATE
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.GMT_CREATE
     *
     * @param gmtCreate the value for COMMENT2.GMT_CREATE
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.GMT_MODIFIED
     *
     * @return the value of COMMENT2.GMT_MODIFIED
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.GMT_MODIFIED
     *
     * @param gmtModified the value for COMMENT2.GMT_MODIFIED
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.LIKE_COUNT
     *
     * @return the value of COMMENT2.LIKE_COUNT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.LIKE_COUNT
     *
     * @param likeCount the value for COMMENT2.LIKE_COUNT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT2.COMMENT_COUNT
     *
     * @return the value of COMMENT2.COMMENT_COUNT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT2.COMMENT_COUNT
     *
     * @param commentCount the value for COMMENT2.COMMENT_COUNT
     *
     * @mbg.generated Sat Apr 18 20:06:51 CST 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}