package cn.bdqn.mapper;

import cn.bdqn.domain.HouseLease;
import cn.bdqn.domain.HouseType;

import java.util.List;

public interface HouseLeaseMapper {

    //    查询全部房屋租赁类型
    public List<HouseLease> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(HouseLease record);

    int insertSelective(HouseLease record);

    HouseLease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseLease record);

    int updateByPrimaryKey(HouseLease record);
}