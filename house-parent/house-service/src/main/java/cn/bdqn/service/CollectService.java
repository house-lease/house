package cn.bdqn.service;

import cn.bdqn.domain.Collect;

import java.util.List;

/**
 * 收藏业务层
 */
public interface CollectService {

    /**
     * 根据用户id和房屋id添加收藏
     */
    public Collect insertCollectByUserIdAndHouseId(Integer userId, Integer houseId);
    

    /**
     * 根据收藏id删除收藏
     */
    public void deleteCollectByCollectId(Integer CollectId);

    /**
     * 根据用户id查看收藏房屋信息
     */
    public List<Collect> queryInfoByUser_id(Integer userId);

    /**
     * 根据房屋id 和用户id查询查看收藏信息
     */
    public Collect queryInfoByHouse_idAndUser_id(Integer houseId,Integer userId);

}
