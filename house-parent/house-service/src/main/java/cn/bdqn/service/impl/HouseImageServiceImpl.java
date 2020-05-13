package cn.bdqn.service.impl;

import cn.bdqn.domain.HouseImage;
import cn.bdqn.domain.ImagePlace;
import cn.bdqn.mapper.HouseImageMapper;
import cn.bdqn.mapper.ImagePlaceMapper;
import cn.bdqn.service.HouseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseImageServiceImpl implements HouseImageService {

    @Autowired
    private HouseImageMapper houseImageMapper;

    @Autowired
    private ImagePlaceMapper imagePlaceMapper;

    /**
     * 根据房屋id和图片位置id查询图片
     * @param houseId
     * @param imagePlaceId
     * @return
     */
    @Override
    public List<HouseImage> queryByHouseIdAndImagePlaceId(Integer houseId, Integer imagePlaceId) {
        return houseImageMapper.selectByHouseIdAndImagePlaceId(houseId,imagePlaceId);
    }

    /**
     * 添加图片对象
     * @param houseImage
     */
    @Override
    public void save(HouseImage houseImage,Integer houseId,Integer imagePlaceId) {
//        封装对象
        houseImage.setHouseId(houseId);
        ImagePlace imagePlace = imagePlaceMapper.selectByPrimaryKey(imagePlaceId);
        houseImage.setImagePlace(imagePlace);
        //添加对象
        houseImageMapper.insert(houseImage);
    }
}
