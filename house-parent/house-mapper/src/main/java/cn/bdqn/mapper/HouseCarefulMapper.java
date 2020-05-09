package cn.bdqn.mapper;

import cn.bdqn.domain.HouseCareful;

/**
 * 房屋详细信息接口
 */
public interface HouseCarefulMapper {


    /**
     * 根据房屋id查询
     * @param houseId
     * @return
     */
    public HouseCareful selectByHouseId(Integer houseId);


}