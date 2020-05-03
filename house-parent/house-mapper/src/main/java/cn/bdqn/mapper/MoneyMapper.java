package cn.bdqn.mapper;

import cn.bdqn.domain.Money;

public interface MoneyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Money record);

    int insertSelective(Money record);

    Money selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Money record);

    int updateByPrimaryKey(Money record);
}