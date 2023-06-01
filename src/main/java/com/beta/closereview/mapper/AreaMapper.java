package com.beta.closereview.mapper;

import com.beta.closereview.dao.Area;

public interface AreaMapper {
    int insert(Area record);

    int insertSelective(Area record);
}