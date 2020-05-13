package cn.bdqn.mapper;

import cn.bdqn.domain.HouseCareful;

import java.util.List;

public interface HouseCarefulMapper {

    //    根据房屋id查询
    public HouseCareful selectByHouseId(Integer houseId);

    //添加
    void insert(HouseCareful record);


}