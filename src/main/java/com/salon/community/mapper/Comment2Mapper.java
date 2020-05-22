package com.salon.community.mapper;

import com.salon.community.model.Comment2;
import com.salon.community.model.Comment2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface Comment2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    long countByExample(Comment2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int deleteByExample(Comment2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int insert(Comment2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int insertSelective(Comment2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    List<Comment2> selectByExampleWithRowbounds(Comment2Example example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    List<Comment2> selectByExample(Comment2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    Comment2 selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByExampleSelective(@Param("record") Comment2 record, @Param("example") Comment2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByExample(@Param("record") Comment2 record, @Param("example") Comment2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByPrimaryKeySelective(Comment2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMMENT2
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByPrimaryKey(Comment2 record);
}