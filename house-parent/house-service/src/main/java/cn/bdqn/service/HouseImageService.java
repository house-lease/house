package cn.bdqn.service;

import cn.bdqn.domain.HouseImage;

//图片业务接口
public interface HouseImageService {

    /**
     * 添加图片对象
     * @param houseImage
     */
    public void save(HouseImage houseImage,Integer houseId,Integer imagePlaceId);
}
