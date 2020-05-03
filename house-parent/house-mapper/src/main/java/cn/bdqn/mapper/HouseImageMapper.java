package cn.bdqn.mapper;

import cn.bdqn.domain.HouseImage;

import java.util.List;

/**
 * 房屋图片接口
 */
public interface HouseImageMapper {


    /**
     * 根据房屋id查询
     * @param houseId
     */
    public List<HouseImage> selectByHouseId(Integer houseId);

    int deleteByPrimaryKey(Integer id);

    int insert(HouseImage record);

    int insertSelective(HouseImage record);

    HouseImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseImage record);

    int updateByPrimaryKey(HouseImage record);
}