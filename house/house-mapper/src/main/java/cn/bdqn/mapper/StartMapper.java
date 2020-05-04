package cn.bdqn.mapper;

import cn.bdqn.domain.Start;

public interface StartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Start record);

    int insertSelective(Start record);

    Start selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Start record);

    int updateByPrimaryKey(Start record);
}