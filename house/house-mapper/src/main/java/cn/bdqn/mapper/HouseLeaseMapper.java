package cn.bdqn.mapper;

import cn.bdqn.domain.HouseLease;

public interface HouseLeaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseLease record);

    int insertSelective(HouseLease record);

    HouseLease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseLease record);

    int updateByPrimaryKey(HouseLease record);
}