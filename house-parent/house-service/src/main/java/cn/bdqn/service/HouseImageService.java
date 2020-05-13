package cn.bdqn.service;

import cn.bdqn.domain.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//图片业务接口
public interface HouseImageService {


    /**
     * 根据房屋id和图片位置id查询图片
     * @param houseId
     * @param imagePlaceId
     * @return
     */
    public List<HouseImage> queryByHouseIdAndImagePlaceId(Integer houseId, Integer imagePlaceId);



    /**
     * 添加图片对象
     * @param houseImage
     */
    public void save(HouseImage houseImage,Integer houseId,Integer imagePlaceId);
}
