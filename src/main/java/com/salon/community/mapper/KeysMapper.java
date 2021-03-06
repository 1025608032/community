package com.salon.community.mapper;

import com.salon.community.model.Keys;
import com.salon.community.model.KeysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface KeysMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    long countByExample(KeysExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int deleteByExample(KeysExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int insert(Keys record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int insertSelective(Keys record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    List<Keys> selectByExampleWithRowbounds(KeysExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    List<Keys> selectByExample(KeysExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    Keys selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByExampleSelective(@Param("record") Keys record, @Param("example") KeysExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByExample(@Param("record") Keys record, @Param("example") KeysExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByPrimaryKeySelective(Keys record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KEYS
     *
     * @mbg.generated Thu May 21 12:57:09 CST 2020
     */
    int updateByPrimaryKey(Keys record);
}