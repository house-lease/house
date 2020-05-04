package cn.bdqn.service;

/**
 * 收藏业务层
 */
public interface CollectService {

    /**
     * 根据用户id和房屋id添加收藏
     */
    public int insertCollectByUserIdAndHouseId(Integer userId, Integer houseId);
    

    /**
     * 根据收藏id删除收藏
     */
    public int deleteCollectByCollectId(Integer CollectId);

    /**
     * 根据用户id查看收藏房屋信息
     */
    public int queryInfoByUser_id(Integer userId);


}
