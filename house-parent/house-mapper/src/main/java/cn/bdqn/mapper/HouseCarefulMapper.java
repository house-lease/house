package cn.bdqn.mapper;

import cn.bdqn.domain.HouseCareful;

import java.util.List;

/**
 * 房屋详细信息接口
 */
public interface HouseCarefulMapper {


    /**
     * 根据房屋id查询房屋详细信息
     * @param houseId
     * @return
     */
    public List<HouseCareful> selectByHouseId(Integer houseId);

    int deleteByPrimaryKey(Integer id);

    int insert(HouseCareful record);

    int insertSelective(HouseCareful record);

    HouseCareful selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseCareful record);

    int updateByPrimaryKey(HouseCareful record);
}