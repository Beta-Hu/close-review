package com.beta.closereview.mapper;

import com.beta.closereview.dao.Review;
import org.apache.ibatis.annotations.Param;

public interface ReviewMapper {
    int deleteByPrimaryKey(@Param("reviewer") Integer reviewer, @Param("submission") Integer submission);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(@Param("reviewer") Integer reviewer, @Param("submission") Integer submission);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKeyWithBLOBs(Review record);

    int updateByPrimaryKey(Review record);
}