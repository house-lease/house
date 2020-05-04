package cn.bdqn.mapper;

import cn.bdqn.domain.HouseType;

public interface HouseTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseType record);

    int insertSelective(HouseType record);

    HouseType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseType record);

    int updateByPrimaryKey(HouseType record);
}