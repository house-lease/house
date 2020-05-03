package cn.bdqn.mapper;

import cn.bdqn.domain.HouseImage;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据房屋id和图片位置id查询图片
     * @param houseId
     * @param imagePlaceId
     * @return
     */
    public List<HouseImage> selectByHouseIdAndImagePlaceId(@Param("houseId") Integer houseId,@Param("imagePlaceId") Integer imagePlaceId);

    int deleteByPrimaryKey(Integer id);

    int insert(HouseImage record);

    int insertSelective(HouseImage record);

    HouseImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseImage record);

    int updateByPrimaryKey(HouseImage record);
}